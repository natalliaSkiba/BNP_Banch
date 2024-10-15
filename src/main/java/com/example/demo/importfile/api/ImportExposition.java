package com.example.demo.importfile.api;

import com.example.demo.importfile.job.ImportJobFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
