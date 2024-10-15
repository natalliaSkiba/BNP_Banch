package com.example.demo.importfile.job.reader;

import com.example.demo.model.Customer;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;

@Component
public class CustomerReader extends FlatFileItemReader<Customer> implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        StepExecutionListener.super.beforeStep(stepExecution);
        var filePath = stepExecution.getJobExecution().getJobParameters().getString("import.file.path");
        this.setResource(new PathResource(filePath));
    }
    public CustomerReader() {
        this.setName("customerReader");
        this.setLineMapper(lineMapper());
    }
    private LineMapper<Customer> lineMapper() {
        var tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames("firstName", "lastName", "address", "birth", "totalAmount");
        tokenizer.setStrict(true);

        var defaultlineMapper = new DefaultLineMapper<Customer>();
        defaultlineMapper.setLineTokenizer(tokenizer);

        BeanWrapperFieldSetMapper<Customer> customerBeanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        customerBeanWrapperFieldSetMapper.setTargetType(Customer.class);

        defaultlineMapper.setFieldSetMapper(customerBeanWrapperFieldSetMapper);
        return defaultlineMapper;
    }
}
