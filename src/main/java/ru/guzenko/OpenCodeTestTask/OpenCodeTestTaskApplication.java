package ru.guzenko.OpenCodeTestTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class OpenCodeTestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenCodeTestTaskApplication.class, args);
	}


	//http://localhost:8080/swagger-ui/#/
	//http://localhost:8080/v2/api-docs
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

}
