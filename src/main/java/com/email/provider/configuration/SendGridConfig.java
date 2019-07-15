package com.email.provider.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class SendGridConfig {


    @Value("${sg.api.url}")
    private String sendgridApiUrl;

    @Value("${sg.api.key}")
    private String sendgridApiKey;

    @Bean
    public String sendgridApiUrl() {
        return this.sendgridApiUrl;
    }

    @Bean
    public String sendgridApiKey() {
        return this.sendgridApiKey;
    }

}