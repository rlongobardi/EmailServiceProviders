package com.email.provider.service;

import com.email.provider.DefaultMessages;
import com.email.provider.model.SendGridMailBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Base64;

@Component
public class RestClientUtil {

    private RestTemplate restTemplate;

    @Autowired
    public RestClientUtil(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public ResponseEntity<String> postEmailRequestViaMailGun(String apiUrl, MultiValueMap<String, String> request, String domain, String key) {
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(request, createHeaders(key));
        URI url = URI.create(apiUrl + domain + "/messages");
        final ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        if (responseEntity.getStatusCodeValue() == 200) {
            return ResponseEntity.ok(DefaultMessages.SENT_WITH_SUCCESS);
        }
        return responseEntity;
    }

    public ResponseEntity<String> postEmailViaSendGrid(SendGridMailBody sendGridMailBody, String url, String key) {
        final MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Authorization", "Bearer " + key);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        final URI apiCall = URI.create(url);
        final HttpEntity<SendGridMailBody> request = new HttpEntity<SendGridMailBody>(sendGridMailBody, headers);
        return restTemplate.postForEntity(apiCall, request, String.class);

    }


    private HttpHeaders createHeaders(String key) {
        return new HttpHeaders() {{
            String auth = "api:" + key;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + encodedAuth;
            set("Accept", MediaType.APPLICATION_JSON_VALUE);
            set("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
            set("Authorization", authHeader);
        }};
    }

}