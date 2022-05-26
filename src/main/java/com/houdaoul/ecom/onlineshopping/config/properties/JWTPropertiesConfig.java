package com.houdaoul.ecom.onlineshopping.config.properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/jwt-config.properties")
public class JWTPropertiesConfig {
}
