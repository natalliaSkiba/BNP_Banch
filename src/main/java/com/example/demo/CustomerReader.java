package com.example.demo;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineCallbackHandler;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;


@Configuration
public class CustomerReader {
    @Value("${file.path}")
    private String filePath;

    @Bean
   // @JobScope
    public FlatFileItemReader<Customer> itemReader() {
        System.out.println("Начинаю чтение файла: " + filePath);
        return
         new FlatFileItemReaderBuilder<Customer>()
                .name("customerReader")
                .resource(new FileSystemResource(filePath))
                .delimited()
                .names("firstName", "lastName", "address", "birth", "totalAmount")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
                    setTargetType(Customer.class);
                }})
                .build();

    }
}
