package com.web_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WebShopClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebShopClientApplication.class, args);
	}
}

