package com.example.demo.exportfile.job.reader;

import com.example.demo.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class ExportCustomerReader {
    @Bean
    public JdbcCursorItemReader<Customer> itemReader(DataSource dataSource){
        log.info("Begin read from DB");
        return new JdbcCursorItemReaderBuilder<Customer>()
                .name("CustomerRiderFromDB")
                .dataSource(dataSource)
                .sql("SELECT id, first_name, last_name, address, birth FROM customer")
                .rowMapper(new BeanPropertyRowMapper<>(Customer.class))
                        .build();
    }
}
