package com.guilhermeneto.crud_spring.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() { //http://localhost:8080/swagger-ui/index.html#/
        return new OpenAPI()
                .info(new Info()
                        .title("Web API Spring")
                        .version("1.0")
                        .description("API de teste")
                        .contact(new Contact()
                                .name("Guilherme R Neto")
                                .email("guilhermeneto@github.com")
                                .url("https://github.com/guilhermeNetogit")));
    }
}
