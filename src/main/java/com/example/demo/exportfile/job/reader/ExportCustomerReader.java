package com.example.demo.exportfile.job.reader;

import com.example.demo.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;

@Slf4j
@Component
public class ExportCustomerReader  extends JdbcCursorItemReader<Customer> implements StepExecutionListener {

    public ExportCustomerReader(DataSource dataSource) {
        log.info("Initializing ExportCustomerReader and setting up data source");
        this.setDataSource(dataSource);
        this.setSql("SELECT id, first_name, last_name, address, birth, total_amount FROM customer");
        this.setRowMapper(new BeanPropertyRowMapper<>(Customer.class));
        this.setName("exportCustomerReader");
    }
}
