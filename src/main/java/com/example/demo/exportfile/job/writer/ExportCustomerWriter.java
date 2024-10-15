package com.example.demo.exportfile.job.writer;

import com.example.demo.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExportCustomerWriter extends JsonFileItemWriter<Customer> implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        var filePath = stepExecution.getJobExecution().getJobParameters().getString("export.file.path");
        if (filePath != null) {
            setResource(new FileSystemResource(filePath));
            log.info("Writer is set to write to file: {}", filePath);
        } else {
            log.warn("No file path provided, using default configuration");
        }
        log.info("Export file path received: {}", filePath);

    }

    public ExportCustomerWriter() {
        super(new FileSystemResource("defaultPath.json"), new JacksonJsonObjectMarshaller<>());
        log.info("Writer setup completed with default configuration");
    }


}
