package com.miw.gildedrose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableSwagger2
@ActiveProfiles("dev")
public class GildedroseApplication {

	public static void main(String[] args) {
		SpringApplication.run(GildedroseApplication.class, args);
	}

}
