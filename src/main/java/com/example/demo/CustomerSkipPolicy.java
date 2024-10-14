package com.example.demo;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.validator.ValidationException;

public class CustomerSkipPolicy implements SkipPolicy {
    @Override
    public boolean shouldSkip(Throwable t, long skipCount) throws SkipLimitExceededException {

        return t instanceof ParseException || t instanceof ValidationException;
    }
}
