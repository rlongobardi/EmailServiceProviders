/**
 *
 */
package com.email.provider.controller;

import com.email.provider.model.MailMessage;
import com.email.provider.model.ResponseEmail;
import com.email.provider.service.EmailProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rosario longobardi
 *
 */

@RestController
@RequestMapping(path = "/api/email", produces = MediaType.APPLICATION_JSON_VALUE)
public class MailRestController {

    private final EmailProviderService emailProviderService;

    @Autowired
    public MailRestController(EmailProviderService emailProviderService) {
        this.emailProviderService = emailProviderService;
    }

    @PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEmail> sendEmail(@RequestBody MailMessage mailMessage) {

        return emailProviderService.sendEmailToProvider(mailMessage);
    }


}
