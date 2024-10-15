package com.example.demo.exportfile.job;

import com.example.demo.exportfile.job.reader.ExportCustomerReader;
import com.example.demo.exportfile.job.writer.ExportCustomerWriter;
import com.example.demo.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ExportJobDefinition {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ExportCustomerReader exportCustomerReader;
    private final ExportCustomerWriter exportCustomerWriter;

    @Bean
    public Job exportCustomerJob(Step exportStep) {
        log.info("tarting Job to export customers to file");
        return new JobBuilder("exportCustomerJob", jobRepository)
                .start(exportStep)
                .build();
    }

    @Bean
    public Step exportStep() {
       log.info("Configuration of exportStep Export to BD");
        return new StepBuilder("exportStep", jobRepository)
                .<Customer, Customer>chunk(10, transactionManager)
                .reader(exportCustomerReader)
                .writer(exportCustomerWriter)
                .transactionManager(transactionManager)
                .build();
    }
}
