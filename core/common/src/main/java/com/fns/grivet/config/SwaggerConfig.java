package com.fns.grivet.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(
            @Value("${info.title}") String projectName,
            @Value("${info.description}") String projectDescription) { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.fns.grivet.controller"))             
          .paths(PathSelectors.any())
          .build()                                          
              .apiInfo(apiInfo(projectName, projectDescription));
    }
    
    private ApiInfo apiInfo(
            String projectName,
            String projectDescription) {
        ApiInfo apiInfo = new ApiInfo(
          String.format("%s REST API", projectName),
          projectDescription,
          "v1",
          "https://www.apache.org/licenses/LICENSE-2.0",
          new Contact("Chris Phillipson", "http://techblitz.io/grivet/", "fastnsilver@gmail.com"),
          "Apache License 2.0",
          "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}