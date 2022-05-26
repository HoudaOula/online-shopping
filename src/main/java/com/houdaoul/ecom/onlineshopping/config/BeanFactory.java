package com.houdaoul.ecom.onlineshopping.config;

import com.houdaoul.ecom.onlineshopping.util.RandomIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactory {

    @Bean
    public RandomIdGenerator randomIdGenerator() {
        return new RandomIdGenerator();
    }
}
