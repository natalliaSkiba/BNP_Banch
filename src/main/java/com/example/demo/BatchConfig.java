package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;

@Slf4j
@Configuration
public class BatchConfig {
    private final JobRepository jobRepository;

    public BatchConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public CompositeItemProcessor<Customer, Customer> compositeItemProcessor(CustomerValidationProcessor validationProcessor,
                                                                             CustomerProcessor customerProcessor) {
        CompositeItemProcessor<Customer, Customer> processor = new CompositeItemProcessor<>();
        processor.setDelegates(Arrays.asList(validationProcessor, customerProcessor));
        return processor;
    }

    @Bean
    public Job importCustomerJob(Step step1) {
        log.info("Lancement du job pour l'importation des clients");
        return new JobBuilder("importCustomerJob", jobRepository)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(PlatformTransactionManager transactionManager,
                      FlatFileItemReader<Customer> itemReader,
                      JdbcBatchItemWriter<Customer> itemWriter,
                      CompositeItemProcessor<Customer, Customer> compositeItemProcessor,
                      CustomerSkipListener skipListener
    ) {
        log.info("Configuration Step 1");
        return new StepBuilder("step1", jobRepository)
                .<Customer, Customer>chunk(10, transactionManager)
                .reader(itemReader)
                .processor(compositeItemProcessor)
                .writer(itemWriter)
                .faultTolerant()
                .skipPolicy(new CustomerSkipPolicy())
                .listener(skipListener)
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        log.info("Début de l'exécution Step");
                    }

                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        log.info("Fin de Step avec le statut: " + stepExecution.getStatus());
                        return stepExecution.getExitStatus();
                    }
                })
                .build();
    }
}
