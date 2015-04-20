package com.flipmind.localizationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.flipmind.localizationservice.controllers.DocumentController;
import com.flipmind.localizationservice.controllers.ProjectsController;
import com.flipmind.localizationservice.controllers.TranslationController;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackageClasses = {
        ProjectsController.class,
        DocumentController.class,
        TranslationController.class

})
public class TranslatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TranslatorApplication.class, args);
	}

	@Bean
	public Docket translatorApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("full-localizationservice-api").apiInfo(apiInfo())
				.select().build();
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"Localization Service",
				"The Localization Service acts as a centralized repository for all our resource bundles used by our Java Spring and Angular apps",
				"1.0", "", "", "@flipmind", "");
		return apiInfo;

	}

}
