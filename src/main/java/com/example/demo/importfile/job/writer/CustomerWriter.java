package com.example.demo.importfile.job.writer;

import com.example.demo.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
@Slf4j
@Component
public class CustomerWriter extends JdbcBatchItemWriter<Customer> {

    public CustomerWriter(DataSource dataSource) {
        this.setSql("INSERT INTO customer (first_name, last_name, address, birth, total_amount) VALUES (:firstName, :lastName, :address, :birth, :totalAmount)");
        this.itemSqlParameterSourceProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
        this.setDataSource(dataSource);
    }

    /*
    @Bean
    public JdbcBatchItemWriter<Customer> itemWriter(DataSource dataSource) {
        log.info("Writer setup completed");
        return new JdbcBatchItemWriterBuilder<Customer>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO customer (first_name, last_name, address, birth, total_amount) VALUES (:firstName, :lastName, :address, :birth, :totalAmount)")
                .dataSource(dataSource)
                .build();
    }
     */

}
