package com.pg.tho.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfiguration extends WebMvcConfigurationSupport {
	
//	@Bean
//	public Docket version1() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.pg.tho.controller")).paths(regex(".*" + ".*"))
//				.build().enable(true).groupName("Grocery_Delivery")
//				.apiInfo(new ApiInfoBuilder().description("Grocery_Delivery_Sysytem").title("The_Originals").build());
//	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	/*@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true).setAmbiguityIgnored(true);
		return modelMapper;
	}*/
}
