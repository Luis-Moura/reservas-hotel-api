package com.reservashoteis.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev") // Só habilita Swagger no profile 'dev'
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Reservas de Hotéis API")
                .description("API REST para gerenciamento de reservas de hotéis.")
                .version("v1")
                .termsOfService("https://meusite.com/termos")
                .contact(new Contact()
                    .name("Suporte Reservas")
                    .email("suporte@meusite.com")
                    .url("https://meusite.com"))
                .license(new License()
                    .name("MIT")
                    .url("https://opensource.org/licenses/MIT"))
            )
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
            .components(new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes("bearerAuth",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER)
                        .name("Authorization")
                )
            );
    }
    // Para desabilitar em produção, basta não usar o profile 'dev' ou remover a dependência.
}

