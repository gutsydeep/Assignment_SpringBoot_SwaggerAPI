package com.Subhadeep.Assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/Pet/*"))
				.apis(RequestHandlerSelectors.basePackage("com.Subhadeep.Assignment"))
				.build()
				.apiInfo(apiCustomData());
	}

	private ApiInfo apiCustomData(){
		return new ApiInfo(
				"Pet API Application",
				"Pet Documentation",
				"1.0",
				"Pet Service Terms",
				new Contact("Subhadeep Dutta", "https://www.linkedin.com/in/subhadeepdutta001",
						"subhadeep.dutta.edu@gmail.com"),
				"Subhadeep Dutta License",
				"https://www.linkedin.com/in/subhadeepdutta001",
				Collections.emptyList()
		);
	}
}


