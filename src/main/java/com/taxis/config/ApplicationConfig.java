package com.taxis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.taxis.Application;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = { "com.taxis" })
class ApplicationConfig {
    //@ComponentScan(basePackageClasses = Application.class)
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}