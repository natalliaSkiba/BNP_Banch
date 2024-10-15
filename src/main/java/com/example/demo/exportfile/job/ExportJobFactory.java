package com.example.demo.exportfile.job;

import com.example.demo.shared.DemoJobRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Slf4j
@Component
public class ExportJobFactory {
    @Autowired
    private DemoJobRunner demoJobRunner;
    @Autowired
    Job exportCustomerJob;

    public void execute(String filePosition) {
        String uuid = UUID.randomUUID().toString();

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("uuid", uuid)
                .addString("export.file.path", filePosition)
                .toJobParameters();
        log.info("*".repeat(15) + "Export Started" + "*".repeat(15));
        demoJobRunner.run(exportCustomerJob, jobParameters);
        log.info("Job parameters: {}", jobParameters);
        log.info("*".repeat(15) + "Export Finished" + "*".repeat(15));
    }
}
