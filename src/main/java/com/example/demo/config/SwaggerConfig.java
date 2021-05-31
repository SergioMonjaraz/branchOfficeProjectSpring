package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@OpenAPIDefinition(
        info = @Info(
                title = "Dacode Challenge API",
                version = "1.0",
                description ="API for branch office",
                contact = @Contact(
                        name = "API Support",
                        url = "",
                        email = "mta.sergio.monjaraz@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080/example_war",
                        description = "Local server for development"
                )
        }
)

@Profile("!production")
@Configuration
public class SwaggerConfig {

}
