package com.email.provider.service;

import com.email.provider.configuration.EmailSwitcher;
import com.email.provider.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

@Qualifier(value = "EmailGridClient")
@Component
public class EmailGridClient implements EmailClient {

    private RestClientUtil restClientUtil;

    private String sendgridApiUrl;
    private String sendgridApiKey;
    private EmailSwitcher emailSwitcher;

    @Autowired
    public EmailGridClient(RestClientUtil restClientUtil, String sendgridApiUrl, String sendgridApiKey, EmailSwitcher emailSwitcher) {
        this.restClientUtil = restClientUtil;
        this.sendgridApiUrl = sendgridApiUrl;
        this.sendgridApiKey = sendgridApiKey;
        this.emailSwitcher = emailSwitcher;
    }


    @Override
    public boolean isAvailable() {
        return emailSwitcher.getEmail().getSwitcher() == 2;
    }

    @Override
    public String sendEmailRequest(MailMessage msg) {
        //todo problem with body of request in case of multiple recipients, this can be worked around ...
        MultiValueMap<String, String> emailDataDetails = getPostRequestObject(msg.getFrom(), msg.getTos().get(0),
                msg.getCcs().get(0), msg.getBbcs().get(0), msg.getSubject(), msg.getBodyEmail());
        SendGridMailBody mailSendGrid = getSendGridEmail(msg);

        ResponseEntity<String> response =
                restClientUtil.postEmailViaSendGrid(mailSendGrid, sendgridApiUrl, sendgridApiKey);
        return response.getBody();
    }

    private SendGridMailBody getSendGridEmail(MailMessage msg) {
        SendGridMailBody mailSendGrid = new SendGridMailBody();
        From from = new From();
        from.setEmail(msg.getFrom());
        mailSendGrid.setFromObject(from);
        Content content = new Content("text/plain", msg.getBodyEmail());
        mailSendGrid.setContent(Arrays.asList(content));
        Personalization pers = new Personalization();
        pers.setSubject(msg.getSubject());
        pers.getTos().add(new Email(msg.getTos().get(0)));
        mailSendGrid.setPersonalizations(Arrays.asList(pers));
        return mailSendGrid;
    }


    private MultiValueMap<String, String> getPostRequestObject(String from, String tos,
                                                               String ccs, String bccs,
                                                               String subject, String textBodyEmail) {
        MultiValueMap<String, String> emailMap = new LinkedMultiValueMap<String, String>();
        emailMap.add("from", from);
        emailMap.add("to", tos);
        emailMap.add("cc", ccs);
        emailMap.add("bcc", bccs);
        emailMap.add("subject", subject);
        emailMap.add("text", textBodyEmail);
        return emailMap;
    }

}
