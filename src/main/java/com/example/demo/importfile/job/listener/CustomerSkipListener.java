package com.example.demo.importfile.job.listener;

import com.example.demo.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerSkipListener {

    @OnSkipInRead
    public void handleSkipInRead(Throwable t) {
        log.info("Skipping during reading: " + t.getMessage());
    }

    @OnSkipInWrite
    public void handleSkipInWrite(Customer item, Throwable t) {
        log.info("Skipping during write: " + item + ", error: " + t.getMessage());
    }

    @OnSkipInProcess
    public void handleSkipInProcess(Customer item, Throwable t) {
        log.info("Skipping during process: " + item + ", error: " + t.getMessage());
    }
}