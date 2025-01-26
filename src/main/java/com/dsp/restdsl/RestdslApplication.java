package com.dsp.restdsl;

import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = { CamelAutoConfiguration.class })
public class RestdslApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestdslApplication.class, args);
	}
}
