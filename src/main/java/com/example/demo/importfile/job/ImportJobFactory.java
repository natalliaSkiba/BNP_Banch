package com.example.demo.importfile.job;

import com.example.demo.shared.DemoJobRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ImportJobFactory {
    @Autowired
    private DemoJobRunner demoJobRunner;
    @Autowired
    Job importCustomerJob;

    public void execute(String filePosition) {
        String uuid = UUID.randomUUID().toString();

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("uuid", uuid)
                .addString("import.file.path", filePosition)
                .toJobParameters();
        log.info("*".repeat(15) + "Import Started" + "*".repeat(15));
        demoJobRunner.run(importCustomerJob, jobParameters);
        log.info("*".repeat(15) + "Import Finished" + "*".repeat(15));
    }
}
