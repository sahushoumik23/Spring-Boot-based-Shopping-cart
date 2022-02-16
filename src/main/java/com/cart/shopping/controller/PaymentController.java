package com.cart.shopping.controller;

import java.util.Map;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaymentController
{   
    @PostMapping("/create_order")
    @ResponseBody
    public String createOrder(@RequestBody Map<String,Object> data) throws Exception
    {
        System.out.println(data);
        int amt=Integer.parseInt(data.get("amount").toString());

        var client= new RazorpayClient("rzp_live_6LlYKTC2PlJvqR","qRzvsGywsaguA3hfWaoABYtB");

        JSONObject ob=new JSONObject();
        ob.put("amount",amt*100);
        ob.put("currency","INR");
        ob.put("receipt","txn_23545");

        Order order=client.Orders.create(ob);
        System.out.println(order);
        return order.toString();
    }
}
