package com.surendra.spring.razorpay.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.surendra.spring.razorpay.dao.PaymentStatusDao;
import com.surendra.spring.razorpay.dao.RazorOrderDao;
import com.surendra.spring.razorpay.model.PaymentStatus;
import com.surendra.spring.razorpay.model.RazorOrder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/razorpay")
public class TicketController {

    @Autowired
    private PaymentStatusDao paymentStatusDao;

    @Autowired
    private RazorOrderDao razorOrderDao;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/order")
    public String createOrder() throws RazorpayException {

        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_tlH7dlVx0y11uY", "wGGhAZOUxWeyLQnSAgO3AhO2");

        JSONObject options = new JSONObject();

        options.put("amount", 500);
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");

        Order order = razorpayClient.Orders.create(options);

        RazorOrder razorOrder = new RazorOrder(order.get("id"), order.get("currency"), order.get("amount"));

        razorOrderDao.save( razorOrder );

        return order.toString();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/payment")
    public String createPaymentEntry(@RequestBody PaymentStatus paymentStatus){
        paymentStatusDao.save( paymentStatus );

        return "payment record inserted";
    }

}
