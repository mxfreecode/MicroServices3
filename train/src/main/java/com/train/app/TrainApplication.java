package com.train.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TrainApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainApplication.class, args);
	}

}
