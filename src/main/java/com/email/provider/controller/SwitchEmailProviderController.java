package com.email.provider.controller;

import com.email.provider.configuration.EmailSwitcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class SwitchEmailProviderController {

    @Autowired
    private EmailSwitcher properties;

    @GetMapping(value = "/switcher")
    public EmailSwitcher getProperties(@RequestParam(name = "providerid", defaultValue = "1") int providerId) {
        //TODO validate the input
        if (properties.getEmail().getSwitcher() != providerId) {
            properties.getEmail().setSwitcher(providerId);
        }
        return properties;
    }
}