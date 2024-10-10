package com.example.demo;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class CustomerWriter {

    @Bean

    public JdbcBatchItemWriter<Customer> itemWriter(DataSource dataSource) {
        System.out.println("Настройка писателя завершена");
        return new JdbcBatchItemWriterBuilder<Customer>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO customer (first_name, last_name, address, birth, total_amount) VALUES (:firstName, :lastName, :address, :birth, :totalAmount)")
                .dataSource(dataSource)
                .build();
    }
}
