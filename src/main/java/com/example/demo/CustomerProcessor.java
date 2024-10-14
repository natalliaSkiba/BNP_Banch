package com.example.demo;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomerProcessor implements ItemProcessor <Customer, Customer>{

    @Override
    public Customer process( Customer customer) throws Exception {

        if (customer.getTotalAmount() != null) {
            double roundedAmount = Math.round(customer.getTotalAmount());
            customer.setTotalAmount(roundedAmount);
        } else {
            customer.setTotalAmount(0.0);
        }
        return customer;
    }
}
