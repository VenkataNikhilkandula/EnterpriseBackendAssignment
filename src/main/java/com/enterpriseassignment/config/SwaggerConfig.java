package com.enterpriseassignment.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Local Development Server");

        Contact contact = new Contact();
        contact.setName("Project Management Team");
        contact.setEmail("support@projectmanagement.com");
        contact.setUrl("https://projectmanagement.com");

        License license = new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0");

        Info info = new Info()
                .title("Project Management System API")
                .version("1.0")
                .description("""
                        REST APIs for Project Management System
                        
                        Features:
                        - User Management
                        - Project Management
                        - Task Management
                        - Task Comments
                        - Dashboard Metrics
                        - Audit Tracking
                        """)
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("https://projectmanagement.com/docs"));
    }
}