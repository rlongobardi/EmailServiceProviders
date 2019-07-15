package com.email.provider.service;

import com.email.provider.DefaultMessages;
import com.email.provider.model.MailMessage;
import com.email.provider.model.ResponseEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailProviderService {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    @Qualifier("EmailGunClient")
    private EmailClient gunClient;

    @Qualifier("EmailGridClient")
    @Autowired
    private EmailClient sendGrid;

    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public ResponseEntity<ResponseEmail> sendEmailToProvider(MailMessage email) {


        AtomicBoolean invalidEmail = emailsValidation(email);
        if (invalidEmail.get()) {
            final ResponseEmail responseEmail = new ResponseEmail(email.getFrom(), DefaultMessages.INVALID_EMAIL_PLEASE_CHECK);
            return ResponseEntity.status(400).body(responseEmail);
        }

        if (gunClient.isAvailable()) {
            final String messageReturnedFromServer = gunClient.sendEmailRequest(email);
            final ResponseEmail responseEmail = new ResponseEmail(email.getFrom(), messageReturnedFromServer);
            return ResponseEntity.ok(responseEmail);

        } else if (sendGrid.isAvailable()) {
            final String messageReturnedFromServer = sendGrid.sendEmailRequest(email);
            final ResponseEmail responseEmail = new ResponseEmail(email.getFrom(), DefaultMessages.SENT_WITH_SUCCESS);
            return ResponseEntity.ok(responseEmail);

        } else {
            final ResponseEmail responseEmail = new ResponseEmail(email.getFrom(), DefaultMessages.EMAIL_SERVICES_ARE_NOT_AVAILABLE);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseEmail);

        }
    }

    private AtomicBoolean emailsValidation(MailMessage email) {
        AtomicBoolean invalidEmail = new AtomicBoolean(false);

        if (!validateEmail(email.getFrom())) {
            invalidEmail.set(true);
            return invalidEmail;
        }

        email.getTos().forEach(to -> {
            if (!validateEmail(to)) {
                invalidEmail.set(true);
            }
        });
        email.getBbcs().forEach(to -> {
            if (!validateEmail(to)) {
                invalidEmail.set(true);
            }
        });
        email.getCcs().forEach(to -> {
            if (!validateEmail(to)) {
                invalidEmail.set(true);
            }
        });
        return invalidEmail;
    }

}