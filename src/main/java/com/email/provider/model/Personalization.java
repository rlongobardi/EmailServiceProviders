package com.email.provider.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Personalization {

    @JsonProperty("to")
    private List<Email> tos = new ArrayList<>();
    @JsonProperty("cc")
    private List<Email> ccs = new ArrayList<>();
    @JsonProperty("bcc")
    private List<Email> bccs = new ArrayList<>();
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("headers")
    private Map<String, String> headers = new ConcurrentHashMap<>();
    @JsonProperty("substitutions")
    private Map<String, String> substitutions = new ConcurrentHashMap<>();
    @JsonProperty("custom_args")
    private Map<String, String> customArgs = new ConcurrentHashMap<>();
    @JsonProperty("dynamic_template_data")
    private Map<String, Object> dynamicTemplateData = new ConcurrentHashMap<>();
    @JsonProperty("send_at")
    private long sendAt;

    public Personalization() {
    }

    @JsonProperty("to")
    public List<Email> getTos() {
        return this.tos == null ? Collections.emptyList() : this.tos;
    }

    public void addTo(Email email) {
        Email newEmail = new Email();
        newEmail.setName(email.getName());
        newEmail.setEmail(email.getEmail());
        if (this.tos == null) {
            this.tos = new ArrayList();
            this.tos.add(newEmail);
        } else {
            this.tos.add(newEmail);
        }

    }

    @JsonProperty("cc")
    public List<Email> getCcs() {
        return this.ccs == null ? Collections.emptyList() : this.ccs;
    }

    public void addCc(Email email) {
        Email newEmail = new Email();
        newEmail.setName(email.getName());
        newEmail.setEmail(email.getEmail());
        if (this.ccs == null) {
            this.ccs = new ArrayList();
            this.ccs.add(newEmail);
        } else {
            this.ccs.add(newEmail);
        }

    }

    @JsonProperty("bcc")
    public List<Email> getBccs() {
        return this.bccs == null ? Collections.emptyList() : this.bccs;
    }

    public void addBcc(Email email) {
        Email newEmail = new Email();
        newEmail.setName(email.getName());
        newEmail.setEmail(email.getEmail());
        if (this.bccs == null) {
            this.bccs = new ArrayList();
            this.bccs.add(newEmail);
        } else {
            this.bccs.add(newEmail);
        }

    }

    @JsonProperty("subject")
    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("headers")
    public Map<String, String> getHeaders() {
        return this.headers == null ? Collections.emptyMap() : this.headers;
    }

    public void addHeader(String key, String value) {
        if (this.headers == null) {
            this.headers = new HashMap();
            this.headers.put(key, value);
        } else {
            this.headers.put(key, value);
        }

    }

    @JsonProperty("substitutions")
    public Map<String, String> getSubstitutions() {
        return this.substitutions == null ? Collections.emptyMap() : this.substitutions;
    }

    public void addSubstitution(String key, String value) {
        if (this.substitutions == null) {
            this.substitutions = new HashMap();
            this.substitutions.put(key, value);
        } else {
            this.substitutions.put(key, value);
        }

    }

    @JsonProperty("custom_args")
    public Map<String, String> getCustomArgs() {
        return this.customArgs == null ? Collections.emptyMap() : this.customArgs;
    }

    public void addCustomArg(String key, String value) {
        if (this.customArgs == null) {
            this.customArgs = new HashMap();
            this.customArgs.put(key, value);
        } else {
            this.customArgs.put(key, value);
        }

    }

    @JsonProperty("send_at")
    public long sendAt() {
        return this.sendAt;
    }

    public void setSendAt(long sendAt) {
        this.sendAt = sendAt;
    }

    @JsonProperty("dynamic_template_data")
    public Map<String, Object> getDynamicTemplateData() {
        return this.dynamicTemplateData == null ? Collections.emptyMap() : this.dynamicTemplateData;
    }

    public void addDynamicTemplateData(String key, Object value) {
        if (this.dynamicTemplateData == null) {
            this.dynamicTemplateData = new HashMap();
        }

        this.dynamicTemplateData.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personalization)) return false;
        Personalization that = (Personalization) o;
        return sendAt == that.sendAt &&
                Objects.equals(tos, that.tos) &&
                Objects.equals(ccs, that.ccs) &&
                Objects.equals(bccs, that.bccs) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(headers, that.headers) &&
                Objects.equals(substitutions, that.substitutions) &&
                Objects.equals(customArgs, that.customArgs) &&
                Objects.equals(dynamicTemplateData, that.dynamicTemplateData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tos, ccs, bccs, subject, headers, substitutions, customArgs, dynamicTemplateData, sendAt);
    }
}

