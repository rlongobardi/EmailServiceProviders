package com.email.provider.service;

import com.email.provider.configuration.EmailSwitcher;
import com.email.provider.model.MailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Qualifier(value = "EmailGunClient")
@Component
public class EmailGunClient implements EmailClient {


    private RestClientUtil restClientUtil;

    private String apiKey;
    private String apiUrl;
    private String apiDomain;
    private EmailSwitcher emailSwitcher;


    @Autowired
    public EmailGunClient(RestClientUtil restClientUtil, String mailgunApiDomain, String mailgunApiKey, String mailgunApiUrl, EmailSwitcher emailSwitcher) {
        this.restClientUtil = restClientUtil;
        this.apiKey = mailgunApiKey;
        this.apiUrl = mailgunApiUrl;
        this.apiDomain = mailgunApiDomain;
        this.emailSwitcher = emailSwitcher;
    }

    @Override
    public boolean isAvailable() {
        return emailSwitcher.getEmail().getSwitcher() == 1;
    }

    @Override
    public String sendEmailRequest(MailMessage msg) {

        final String tos = String.join(";", msg.getTos());
        final String ccs = String.join(";", msg.getCcs());
        final String bbcs = String.join(";", msg.getBbcs());

        final MultiValueMap<String, String> emailDataDetails = getPostRequestObject(msg.getFrom(), tos,
                ccs, bbcs, msg.getSubject(), msg.getBodyEmail());

        ResponseEntity<String> response = restClientUtil.postEmailRequestViaMailGun(this.apiUrl,
                emailDataDetails, this.apiDomain, this.apiKey);
        return response.getBody();
    }


    private MultiValueMap<String, String> getPostRequestObject(String from, String tos,
                                                               String ccs, String bccs,
                                                               String subject, String textBodyEmail) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("from", from);
        map.add("to", tos);
        map.add("subject", subject);
        map.add("text", textBodyEmail);
        return map;
    }

}
