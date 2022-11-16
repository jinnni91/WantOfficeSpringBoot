package com.project.office.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${image.add-resource-locations}")
	private String ADD_RESOURCE_LOCATION;
	
	@Value("${image.add-resource-handler}")
	private String ADD_RESOURCE_HANDLER;
	
	@Value("${file.add-resource-locations}")
	private String ADD_RESOURCE_LOCATIONS;
	
	@Value("${file.add-resource-handler}")
	private String ADD_RESOURCE_HANDLERS;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(ADD_RESOURCE_HANDLER, ADD_RESOURCE_HANDLERS)
        		.addResourceLocations(ADD_RESOURCE_LOCATION, ADD_RESOURCE_LOCATIONS);
	}
	
}
