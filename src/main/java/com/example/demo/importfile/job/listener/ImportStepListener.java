package com.example.demo.importfile.job.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ImportStepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        StepExecutionListener.super.beforeStep(stepExecution);
        log.info("Import step Started");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Import step End");
        return StepExecutionListener.super.afterStep(stepExecution);
    }
}
