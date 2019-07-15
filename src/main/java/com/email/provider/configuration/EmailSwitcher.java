package com.email.provider.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "features")
public class EmailSwitcher {

    private Email email;

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }


    public static class Email {

        private int switcher;

        public int getSwitcher() {
            return switcher;
        }

        public void setSwitcher(int switcher) {
            this.switcher = switcher;
        }
    }
}