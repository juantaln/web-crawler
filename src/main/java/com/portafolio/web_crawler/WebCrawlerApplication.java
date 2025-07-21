package com.portafolio.web_crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = "com.portafolio.web_crawler")
@SpringBootApplication

public class WebCrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCrawlerApplication.class, args);
	}

}
