package com.email.provider.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class MailMessage implements Serializable {


    private String from;
    private List<String> tos;
    private List<String> ccs;
    private List<String> bbcs;
    private String bodyEmail;
    private String subject;

    public MailMessage() {
    }


    public MailMessage(String from, List<String> tos, List<String> ccs, List<String> bbcs, String bodyEmail, String subject) {
        this.from = from;
        this.tos = tos;
        this.ccs = ccs;
        this.bbcs = bbcs;
        this.bodyEmail = bodyEmail;
        this.subject = subject;
    }


    @Override
    public String toString() {
        return "MailMessage{" +
                "from='" + from + '\'' +
                ", tos='" + tos + '\'' +
                ", ccs='" + ccs + '\'' +
                ", bbcs='" + bbcs + '\'' +
                ", bodyEmail='" + bodyEmail + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTos() {
        return tos;
    }

    public void setTos(List<String> tos) {
        this.tos = tos;
    }

    public List<String> getCcs() {
        return ccs;
    }

    public void setCcs(List<String> ccs) {
        this.ccs = ccs;
    }

    public List<String> getBbcs() {
        return bbcs;
    }

    public void setBbcs(List<String> bbcs) {
        this.bbcs = bbcs;
    }

    public String getBodyEmail() {
        return bodyEmail;
    }

    public void setBodyEmail(String bodyEmail) {
        this.bodyEmail = bodyEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MailMessage)) return false;
        MailMessage that = (MailMessage) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(tos, that.tos) &&
                Objects.equals(ccs, that.ccs) &&
                Objects.equals(bbcs, that.bbcs) &&
                Objects.equals(bodyEmail, that.bodyEmail) &&
                Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, tos, ccs, bbcs, bodyEmail, subject);
    }
}

