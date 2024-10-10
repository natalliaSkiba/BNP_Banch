package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class BatchConfig {

    private final JobRepository jobRepository;

    public BatchConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job importCustomerJob(Step step1) {
        System.out.println("Запуск Job для импорта клиентов");
        return new JobBuilder("importCustomerJob", jobRepository)
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        System.out.println("Начало выполнения Job");
                        log.debug("Контекст для сериализации: " + jobExecution.getExecutionContext());
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        System.out.println("Завершение выполнения Job");
                        log.debug("Контекст после завершения: " + jobExecution.getExecutionContext());
                    }
                })
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(PlatformTransactionManager transactionManager,
                      FlatFileItemReader<Customer> itemReader,
                      JdbcBatchItemWriter<Customer> itemWriter,
                      ItemProcessor<Customer, Customer> customerItemProcessor
    ) {
        System.out.println("Настройка Step 1");
        return new StepBuilder("step1", jobRepository)
                .<Customer, Customer>chunk(10, transactionManager)
                .reader(itemReader)
                .writer(itemWriter)
                .processor(customerItemProcessor)
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        System.out.println("Начало выполнения Step");
                    }

                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        System.out.println("Завершение Step с статусом: " + stepExecution.getStatus());
                        return stepExecution.getExitStatus();
                    }
                })
                .build();
    }
}
