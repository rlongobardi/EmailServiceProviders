package com.email.provider.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Content {
    @JsonProperty("type")
    private String type;
    @JsonProperty("value")
    private String value;

    public Content() {
    }

    public Content(String type, String value) {
        this.setType(type);
        this.setValue(value);
    }

    @JsonProperty("type")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("value")
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Content)) return false;
        Content content = (Content) o;
        return Objects.equals(type, content.type) &&
                Objects.equals(value, content.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
