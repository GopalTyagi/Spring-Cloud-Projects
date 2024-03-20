package com.rays.service03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.rays.service03.ctl") 
public class Service03Application {

	public static void main(String[] args) {

		SpringApplication.run(Service03Application.class, args);

	}
}
