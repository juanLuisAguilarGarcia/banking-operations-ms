package com.pichincha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BankingOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingOperationsApplication.class, args);
	}

}
