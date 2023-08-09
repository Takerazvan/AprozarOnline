package com.codecool.backend.products.orders.payments.paypal;

import com.codecool.backend.products.orders.OrderForm;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaypalController {


    PaymentMaker paymentMaker;
@Autowired
    public PaypalController(PaymentMaker paymentMaker) {
        this.paymentMaker = paymentMaker;
    }

    public static final String HOST_URL="http://localhost:8080/";




    public String payment(OrderForm order, String SUCCESS_URL, String CANCEL_URL){
        try {
            Payment payment=paymentMaker.createPayment(order.total(),
                    order.currency(),
                    order.paymentMethod(),
                    order.intent(),
                    order.description(),
                    HOST_URL+CANCEL_URL,
                    HOST_URL+SUCCESS_URL);
        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }



    public String successPay(String paymentId,  String payerId) {
        try {
            Payment payment = paymentMaker.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
}
