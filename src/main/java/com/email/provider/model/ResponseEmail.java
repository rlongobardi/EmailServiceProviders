package com.email.provider.model;

import java.io.Serializable;

public class ResponseEmail implements Serializable {

    private String emailFrom;
    private String message;

    public ResponseEmail(String email, String message) {
        this.emailFrom = email;
        this.message = message;
    }

    public String getEmail() {
        return emailFrom;
    }

    public void setEmail(String email) {
        this.emailFrom = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}