package com.email.provider.model;

import java.util.List;

public class MailMessageBuilder {

    private String from;

    private List<String> tos;
    private List<String> ccs;
    private List<String> bbcs;
    private String bodyEmail;
    private String subject;

    public String getFrom() {
        return from;
    }

    public MailMessageBuilder setFrom(String from) {
        this.from = from;
        return this;
    }

    public List<String> getTos() {
        return tos;
    }

    public MailMessageBuilder setTos(List<String> tos) {
        this.tos = tos;
        return this;
    }

    public List<String> getCcs() {
        return ccs;
    }

    public MailMessageBuilder setCcs(List<String> ccs) {
        this.ccs = ccs;
        return this;
    }

    public List<String> getBbcs() {
        return bbcs;
    }

    public MailMessageBuilder setBbcs(List<String> bbcs) {
        this.bbcs = bbcs;
        return this;
    }

    public String getBodyEmail() {
        return bodyEmail;
    }

    public MailMessageBuilder setBodyEmail(String bodyEmail) {
        this.bodyEmail = bodyEmail;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public MailMessageBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public MailMessage createMailMessage() {
        return new MailMessage(from, tos, ccs, bbcs, bodyEmail, subject);
    }
}