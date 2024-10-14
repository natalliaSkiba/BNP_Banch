package com.example.demo.importfile.job.processor;

import com.example.demo.model.Customer;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import java.util.Set;

@Slf4j
@Component
public class CustomerValidationProcessor implements ItemProcessor<Customer, Customer> {
    private final Validator validator;
    public CustomerValidationProcessor(Validator validator) {
        this.validator = validator;
    }

    @Override
    public Customer process(Customer customer) throws Exception {
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        if (!violations.isEmpty()) {
            log.info("Error  validation for customer: " + violations);
            return null;
        }
        return customer;
    }
}
