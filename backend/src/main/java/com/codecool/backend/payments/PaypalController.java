package com.codecool.backend.payments;

import com.codecool.backend.products.orders.Order;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaypalController {

    @Autowired
    PaypalService service;
    public static final String HOST_URL="http://localhost:8080/";
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";


    @PostMapping("/pay")
    public String payment(@ModelAttribute("order")Order order){
        try {
            Payment payment=service.createPayment(order.getTotal(),
                    order.getCurrency(),
                    order.getPaymentMethod().name(),
                    order.getIntent(),
                    order.getDescription(),
                    HOST_URL+CANCEL_URL,
                    HOST_URL+SUCCESS_URL);
        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }
    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
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
