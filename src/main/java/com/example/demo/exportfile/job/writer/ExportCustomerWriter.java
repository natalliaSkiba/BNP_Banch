package com.example.demo.exportfile.job.writer;

import com.example.demo.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Slf4j
@Configuration
public class ExportCustomerWriter {
    /*

    @Value("${file.path}")
    private String filePath;
    @Bean
    public JsonFileItemWriter<Customer> itemWriter(){
        log.info("Begin write in the file: " + filePath);
        return new JsonFileItemWriterBuilder<Customer>()
                .name("customerWriter")
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
                .resource(new FileSystemResource(filePath))
                .build();

    }

     */
}
