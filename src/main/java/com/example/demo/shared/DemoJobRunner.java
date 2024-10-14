package com.example.demo.shared;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class DemoJobRunner {
    @Autowired
    private JobLauncher jobLauncher;

    public void run(Job job, JobParameters jobParameters){
        try {
            jobLauncher.run(job, jobParameters);
            log.info("Job successfully completed");
        } catch (JobExecutionAlreadyRunningException e) {
            log.error("JobExecutionAlreadyRunningException",e);
        } catch (JobRestartException e) {
            log.error("JobRestartException",e);
        } catch (JobInstanceAlreadyCompleteException e) {
            log.error("JobInstanceAlreadyCompleteException",e);
        } catch (JobParametersInvalidException e) {
            log.error("RuntimeException",e);
        }
    }
}
