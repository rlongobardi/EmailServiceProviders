package com.email.provider.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Email {
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;

    public Email() {
    }

    public Email(String email) {
        this.setEmail(email);
    }

    public Email(String email, String name) {
        this.setEmail(email);
        this.setName(name);
    }

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        Email email1 = (Email) o;
        return Objects.equals(name, email1.name) &&
                Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
