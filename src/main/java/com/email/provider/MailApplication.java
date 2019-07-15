/**
 *
 */
package com.email.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author rosario longobardi
 *
 */
@EnableWebMvc
@EnableConfigurationProperties
@SpringBootApplication
public class MailApplication {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }

}
