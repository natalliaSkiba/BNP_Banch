package com.example.demo.importfile.api;

import com.example.demo.importfile.job.ImportJobFactory;
import com.example.demo.shared.DemoJobRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class ImportExposition {

    @Autowired
    private ImportJobFactory importCustomerJob;

    @GetMapping("/importFile")
    public void importFile(@RequestParam("filePosition") String filePosition) {
        importCustomerJob.execute(filePosition);
    }
}
