package com.statista.code.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.Contact;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String PACKAGE_NAME = "com.statista.code.challenge.controller";

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Booking Service")
                .description("This project is a RESTful web service developed to handle bookings for Statista's Sales team. It allows users to create and retrieve booking information through a simple and intuitive API.")
                .contact(new Contact("Amruta Kulkarni", "https://www.linkedin.com/in/amruta-kulkarni-031bb3160/", "amruta.kulkarni.20121991@gmail.com"))
                .build();
    }

    @Bean
    public Docket swaggerImplementation() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(PACKAGE_NAME))
                .build().apiInfo(apiInfo());
    }
}
