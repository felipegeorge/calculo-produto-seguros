package com.seguros.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

	@Value("${api.openapi.url}")
	private String devUrl;

	@Bean
	public OpenAPI openAPI() {
		Server server = new Server();
		server.setUrl(devUrl);
		server.setDescription("URL do servidor");
		
		Info info = new Info()
				.title("Produto de seguros")
				.version("1.0.0")
				.description("API REST capaz de calcular o preço tarifado de um produto de seguros a partir do preço base informado.");

		return new OpenAPI().info(info).servers(List.of(server));
	}

}
