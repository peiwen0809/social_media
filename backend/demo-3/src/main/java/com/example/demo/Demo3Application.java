package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class Demo3Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}

}
