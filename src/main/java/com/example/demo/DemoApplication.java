package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

@Autowired
private Job importCustomerJob;
	@Autowired
	private JobLauncher jobLauncher;

	@Value("${file.path}")
	private String input;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Запуск Job с параметрами файла");

		JobParametersBuilder jobParameters = new JobParametersBuilder();
		jobParameters.addString("file.input", input);

		jobParameters.addLong("time", System.currentTimeMillis());  // Добавляем текущую метку времени для уникальности

		try {
			jobLauncher.run(importCustomerJob, jobParameters.toJobParameters());
			System.out.println("Job успешно выполнен");
		} catch (Exception e) {
			System.out.println("Ошибка при запуске Job: " + e.getMessage());
		}
	}
	}

