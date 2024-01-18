package com.andresyfr.biblioteca.configuraciones;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfiguraciones extends WebMvcConfigurerAdapter {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*", "/v2/api-docs")
			.allowedMethods("GET","POST", "DELETE","PUT","PATCH", "OPTIONS")
			.allowedHeaders("Content-Type","Authorization","application/json","api_key","Cache-Control","no-store")
			.exposedHeaders("Access-Control-Expose-Headers")
			.allowCredentials(false)
			.maxAge(3600);
	}
}
