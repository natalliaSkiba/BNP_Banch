package com.example.demo.importfile.job;

import com.example.demo.importfile.job.listener.ImportStepListener;
import com.example.demo.importfile.job.policy.CustomerSkipPolicy;
import com.example.demo.importfile.job.processor.CustomerProcessor;
import com.example.demo.importfile.job.processor.CustomerValidationProcessor;
import com.example.demo.importfile.job.reader.CustomerReader;
import com.example.demo.importfile.job.writer.CustomerWriter;
import com.example.demo.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import java.util.Arrays;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ImportJobDefinition {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final CustomerReader customerReader;
    private final CustomerWriter customerWriter;
    private final ImportStepListener importStepListener;

    @Bean
    public CompositeItemProcessor<Customer, Customer> compositeItemProcessor(CustomerValidationProcessor validationProcessor,
                                                                             CustomerProcessor customerProcessor) {
        CompositeItemProcessor<Customer, Customer> processor = new CompositeItemProcessor<>();
        processor.setDelegates(Arrays.asList(validationProcessor, customerProcessor));
        return processor;
    }

    @Bean
    public Job importCustomerJob(Step importStep) {
        log.info("Lancement du job pour l'importation des clients");
        return new JobBuilder("importCustomerJob", jobRepository)
                .start(importStep)
                .build();
    }

    @Bean
    public Step importStep() {
        log.info("Configuration importStep");
        return new StepBuilder("importStep", jobRepository)
                .<Customer, Customer>chunk(10, transactionManager)
                .listener(importStepListener)
                .reader(customerReader)
                .processor(compositeItemProcessor(null,null))
                .writer(customerWriter)
                .faultTolerant()
                .skipPolicy(new CustomerSkipPolicy())
                .build();
    }
}
