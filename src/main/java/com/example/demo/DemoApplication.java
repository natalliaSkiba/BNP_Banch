package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    /*
    @Override
    public void run(String... args) throws Exception {
        log.info("Launching Job with file parameters");
        JobParametersBuilder jobParameters = new JobParametersBuilder();
        jobParameters.addString("file.input", input);
        jobParameters.addLong("time", System.currentTimeMillis());  // Добавляем текущую метку времени для уникальности
        try {
            jobLauncher.run(importCustomerJob, jobParameters.toJobParameters());
            log.info("Job successfully completed");
        } catch (Exception e) {
            log.error("Error Launching Job: " + e.getMessage());
        }
    }
     */
}

