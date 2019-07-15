package com.email.provider.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class SendGridMailBody {

    @JsonProperty("personalizations")
    private List<Personalization> personalizations;
    @JsonProperty("from")
    private From FromObject;
    @JsonProperty("content")
    private List<Content> content;


    public List<Personalization> getPersonalizations() {
        return personalizations;
    }

    public void setPersonalizations(List<Personalization> personalizations) {
        this.personalizations = personalizations;
    }

    public From getFromObject() {
        return FromObject;
    }

    public void setFromObject(From fromObject) {
        FromObject = fromObject;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SendGridMailBody)) return false;
        SendGridMailBody that = (SendGridMailBody) o;
        return Objects.equals(personalizations, that.personalizations) &&
                Objects.equals(FromObject, that.FromObject) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalizations, FromObject, content);
    }
}
