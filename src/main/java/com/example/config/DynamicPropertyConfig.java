package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
public class DynamicPropertyConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer(Environment environment) {
        String activeProfile = System.getProperty("spring.profiles.active", "dev");
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new org.springframework.core.io.ClassPathResource("application-" + activeProfile + ".properties"));
        return configurer;
    }
}
