package com.bridgelabz.alamarocaine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "com.bridgelabz.alamarocaine" }) // scans for annotated Spring components

/**
 * While developing an application, we need to tell the Spring framework to look
 * for Spring-managed components.
 * 
 * @ComponentScan enables Spring to scan for things like configurations,
 *                controllers, services, and other components we define.
 **/

@SpringBootApplication
public class ALaMarocaineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ALaMarocaineApplication.class, args);
	}
}
