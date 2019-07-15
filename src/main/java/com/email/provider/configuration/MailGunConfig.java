package com.email.provider.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class MailGunConfig {


    @Value("${mg.api.key}")
    private String mailgunApiKey;
    @Value("${mg.api.url}")
    private String mailgunApiUrl;
    @Value("${mg.api.domain}")
    private String mailgunApiDomain;

    @Bean
    public String mailgunApiKey() {
        return this.mailgunApiKey;
    }

    @Bean
    public String mailgunApiUrl() {
        return this.mailgunApiUrl;
    }

    @Bean
    public String mailgunApiDomain() {
        return this.mailgunApiDomain;
    }


}