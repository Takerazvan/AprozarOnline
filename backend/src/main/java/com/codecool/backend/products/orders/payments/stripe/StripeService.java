package com.codecool.backend.products.orders.payments.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j

public class StripeService {
    @Value("${stripe.key}")
    private String stripeApiKey;


    @PostConstruct
    public void init(){

        Stripe.apiKey = stripeApiKey;
    }


    public StripeTokenDTO createCardToken(StripeTokenDTO model) {

        try {
            Map<String, Object> card = new HashMap<>();
            card.put("number", model.getCardNumber());
            card.put("exp_month", Integer.parseInt(model.getExpMonth()));
            card.put("exp_year", Integer.parseInt(model.getExpYear()));
            card.put("cvc", model.getCvc());
            Map<String, Object> params = new HashMap<>();
            params.put("card", card);
            Token token = Token.create(params);
            if (token != null && token.getId() != null) {
                model.setSuccess(true);
                model.setToken(token.getId());
            }
            return model;
        } catch (StripeException e) {
            log.error("StripeService (createCardToken)", e);
            throw new RuntimeException(e.getMessage());
        }

    }

public StripeChargeDTO charge(StripeChargeDTO chargeRequest) {


        try {
            chargeRequest.setSuccess(false);
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (chargeRequest.getAmount() * 100));
            chargeParams.put("currency", "USD");
            chargeParams.put("description", "Payment for id " + chargeRequest.getAdditionalInfo().getOrDefault("ID_TAG", ""));
            chargeParams.put("source", chargeRequest.getStripeToken());
            Map<String, Object> metaData = new HashMap<>();
            metaData.put("id", chargeRequest.getChargeId());
            metaData.putAll(chargeRequest.getAdditionalInfo());
            chargeParams.put("metadata", metaData);
            Charge charge = Charge.create(chargeParams);
            chargeRequest.setMessage(charge.getOutcome().getSellerMessage());

            if (charge.getPaid()) {
                chargeRequest.setChargeId(charge.getId());
                chargeRequest.setSuccess(true);

            }
            return chargeRequest;
        } catch (StripeException e) {
            log.error("StripeService (charge)", e);
            throw new RuntimeException(e.getMessage());
        }

    }

    public StripeSubscriptionResponse createSubscription(StripeSubscriptionDTO subscriptionDto){


        PaymentMethod paymentMethod = createPaymentMethod(subscriptionDto);
        Customer customer = createCustomer(paymentMethod, subscriptionDto);
        paymentMethod = attachCustomerToPaymentMethod(customer, paymentMethod);
        Subscription subscription = createSubscription(subscriptionDto, paymentMethod, customer);

        return createResponse(subscriptionDto,paymentMethod,customer,subscription);
    }

    private StripeSubscriptionResponse createResponse(StripeSubscriptionDTO subscriptionDto, PaymentMethod paymentMethod, Customer customer, Subscription subscription) {



        return new StripeSubscriptionResponse(customer.getId(),
                paymentMethod.getId(),
                paymentMethod.getId(),subscriptionDto.username());

    }

    private PaymentMethod createPaymentMethod(StripeSubscriptionDTO subscriptionDto){

        try {

            Map<String, Object> card = new HashMap<>();

            card.put("number", subscriptionDto.cardNumber());
            card.put("exp_month", Integer.parseInt(subscriptionDto.expMonth()));
            card.put("exp_year", Integer.parseInt(subscriptionDto.expYear()));
            card.put("cvc", subscriptionDto.cvc());

            Map<String, Object> params = new HashMap<>();
            params.put("type", "card");
            params.put("card", card);

            return PaymentMethod.create(params);

        } catch (StripeException e) {
            log.error("StripeService (createPaymentMethod)", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    private Customer createCustomer(PaymentMethod paymentMethod,StripeSubscriptionDTO subscriptionDto){

        try {

            Map<String, Object> customerMap = new HashMap<>();
            customerMap.put("name", subscriptionDto.username());
            customerMap.put("email", subscriptionDto.email());
            customerMap.put("payment_method", paymentMethod.getId());

            return Customer.create(customerMap);
        } catch (StripeException e) {
            log.error("StripeService (createCustomer)", e);
            throw new RuntimeException(e.getMessage());
        }

    }

    private PaymentMethod attachCustomerToPaymentMethod(Customer customer,PaymentMethod paymentMethod){

        try {

            paymentMethod = com.stripe.model.PaymentMethod.retrieve(paymentMethod.getId());

            Map<String, Object> params = new HashMap<>();
            params.put("customer", customer.getId());
            paymentMethod = paymentMethod.attach(params);
            return paymentMethod;


        } catch (StripeException e) {
            log.error("StripeService (attachCustomerToPaymentMethod)", e);
            throw new RuntimeException(e.getMessage());
        }

    }

    private Subscription createSubscription(StripeSubscriptionDTO subscriptionDto, PaymentMethod paymentMethod, Customer customer){

        try {

            List<Object> items = new ArrayList<>();
            Map<String, Object> item1 = new HashMap<>();
            item1.put(
                    "price",
                    subscriptionDto.priceId()
            );
            item1.put("quantity",subscriptionDto.numberOfLicense());
            items.add(item1);

            Map<String, Object> params = new HashMap<>();
            params.put("customer", customer.getId());
            params.put("default_payment_method", paymentMethod);
            params.put("items", items);
            return Subscription.create(params);
        } catch (StripeException e) {
            log.error("StripeService (createSubscription)", e);
            throw new RuntimeException(e.getMessage());
        }

    }

    public  Subscription cancelSubscription(String subscriptionId){

        try {
            Subscription retrieve = Subscription.retrieve(subscriptionId);
            return retrieve.cancel();
        } catch (StripeException e) {

            log.error("StripeService (cancelSubscription)",e);
        }

        return null;
    }

}


