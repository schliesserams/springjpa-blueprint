package com.ams.jpa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.time.Instant;

@Configuration
public class OpenApiConfig {
    @Nullable
    private final BuildProperties buildProperties;

    public OpenApiConfig(@Nullable BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        final String version = buildProperties != null ? buildProperties.getVersion() : "undefined";
        final String buildDate = buildProperties != null ? buildProperties.getTime().toString() : Instant.now().toString();

        return new OpenAPI()
                .info(new Info()
                        .title("Spring JPA Blueprint")
                        .description("Spring JPA Blueprint, build at " + buildDate)
                        .version(version));
    }
}