package com.email.provider.service;

import com.email.provider.model.MailMessage;

public interface EmailClient {

    boolean isAvailable();

    String sendEmailRequest(MailMessage msg);
}
