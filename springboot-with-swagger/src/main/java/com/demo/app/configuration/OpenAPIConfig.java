package com.demo.app.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

	@Value("${swagger.openapi.url}")
	private String url;

	@Bean
	public OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl(url);
		devServer.setDescription("Server URL");

		Contact contact = new Contact();
		contact.setEmail("swagger@gmail.com");
		contact.setName("swagger");
		contact.setUrl("https://www.swagger.com");

		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

		Info info = new Info().title("Management API").version("1.0").contact(contact)
				.description("This API exposes application endpoints.").termsOfService("https://www.swagger.com/terms")
				.license(mitLicense);

		return new OpenAPI().info(info).servers(List.of(devServer));
	}
}
