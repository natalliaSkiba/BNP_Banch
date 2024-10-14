package com.example.demo.exportfile.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ExportJobDefinition {
    private final JobRepository jobRepository;


    public ExportJobDefinition(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    /*
    @Bean
    public Job exportCustomerJob(Step step1) {
        log.info("tarting Job to export customers to file");
        return new JobBuilder("exportCustomerJob", jobRepository)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JdbcCursorItemReader<Customer> itemReader,
                      JsonFileItemWriter<Customer> itemWriter,
                      PlatformTransactionManager transactionManager) {
        System.out.println("Настройка Step 1");
        return new StepBuilder("step1", jobRepository)
                .<Customer, Customer>chunk(10, transactionManager)
                .reader(itemReader)
                .writer(itemWriter)
                .transactionManager(new ResourcelessTransactionManager())
                .build();
    }

     */
}
