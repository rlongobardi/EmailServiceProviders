package com.email.provider.controller;

import com.email.provider.DefaultMessages;
import com.email.provider.MailApplication;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.email.provider.configuration.EmailSwitcher;
import com.email.provider.model.MailMessage;
import com.email.provider.model.MailMessageBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static com.jayway.restassured.RestAssured.given;
import static com.email.provider.controller.ApiCommonTest.API_EMAIL_SEND;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * @author rosario longobardi
 */
@Ignore//todo remove when you add the right keys in the properties
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MailApplication.class)
@TestPropertySource(value = {"classpath:application.properties"})
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class MailRestControllerIT {

    @Value("${server.port}")
    int port;
    @Value("${server.address}")
    String host;

    @Autowired
    private EmailSwitcher emailSwitcher;

    @Before
    public void setBaseUri() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://" + host;
    }

    @Test
    public void getEmailSent_viaMailGun_Successfully() {
        emailSwitcher.getEmail().setSwitcher(1);
        final String tos = "rosario.longobardi@gmail.com";
        final String ccs = "rosario.longobardi@gmail.com";
        final String bcs = "rosario.longobardi@gmail.com";
        MailMessage emailToSend = new MailMessageBuilder().setSubject("Test MailGun").setBodyEmail("test")
                .setTos(Collections.singletonList(tos))
                .setFrom("test@example.com")
                .setBbcs(Collections.singletonList(bcs))
                .setCcs(Collections.singletonList(ccs))
                .createMailMessage();

        final Response response = given().body(emailToSend)
                .headers("ContentType", ContentType.JSON).contentType(ContentType.JSON)
                .when()
                .post(API_EMAIL_SEND);
        //verify
        assertThat(response.getStatusCode(), is(200));
        JsonPath jsonPathEvaluator = response.jsonPath();
        assertThat(jsonPathEvaluator.getString("email"), is("test@example.com"));
        assertThat(jsonPathEvaluator.getString("message"), CoreMatchers.is(DefaultMessages.SENT_WITH_SUCCESS));
    }

    @Test
    public void giveIncorrectEmailFormat_viaMailGun_then400IsReceived() {
        emailSwitcher.getEmail().setSwitcher(1);
        final String tos = "rosario.longobardi:@:gmail.com";
        final String ccs = "rosario.longobardi@gmail.com";
        final String bcs = "rosario.longobardi@gmail.com";
        MailMessage emailToSend = new MailMessageBuilder().setSubject("Test MailGun").setBodyEmail("test")
                .setTos(Collections.singletonList(tos))
                .setFrom("test@example.com")
                .setBbcs(Collections.singletonList(bcs))
                .setCcs(Collections.singletonList(ccs))
                .createMailMessage();

        final Response response = given().body(emailToSend).contentType(ContentType.JSON)
                .when()
                .post(API_EMAIL_SEND);
        //verify
        assertThat(response.getStatusCode(), is(400));
        JsonPath jsonPathEvaluator = response.jsonPath();
        assertThat(jsonPathEvaluator.getString("email"), is("test@example.com"));
        assertThat(jsonPathEvaluator.getString("message"), is(DefaultMessages.INVALID_EMAIL_PLEASE_CHECK));
    }

    @Test
    public void getEmailSent_viaSendGrid_Successfully() {
        emailSwitcher.getEmail().setSwitcher(2);
        final String tos = "rosario.longobardi@gmail.com";
        final String ccs = "rosario.longobardi@gmail.com";
        final String bcs = "rosario.longobardi@gmail.com";
        MailMessage emailToSend = new MailMessageBuilder().setSubject("Test with SendGrid").setBodyEmail("test")
                .setTos(Collections.singletonList(tos))
                .setFrom("test@example.com")
                .setBbcs(Collections.singletonList(bcs))
                .setCcs(Collections.singletonList(ccs))
                .createMailMessage();

        final Response response = given().body(emailToSend)
                .headers("ContentType", ContentType.JSON).contentType(ContentType.JSON)
                .when()
                .post(API_EMAIL_SEND);
        //verify
        assertThat(response.getStatusCode(), is(200));
        JsonPath jsonPathEvaluator = response.jsonPath();
        assertThat(jsonPathEvaluator.getString("email"), is("test@example.com"));
        assertThat(jsonPathEvaluator.getString("message"), is(DefaultMessages.SENT_WITH_SUCCESS));
    }

    @Test
    public void giveIncorrectEmailFormat_viaSendGrid_then400IsReceived() {
        emailSwitcher.getEmail().setSwitcher(2);
        final String tos = "rosario.longobardi:@:gmail.com";
        final String ccs = "rosario.longobardi@gmail.com";
        final String bcs = "rosario.longobardi@gmail.com";
        MailMessage emailToSend = new MailMessageBuilder().setSubject("Test with SendGrid").setBodyEmail("test")
                .setTos(Collections.singletonList(tos))
                .setFrom("test@example.com")
                .setBbcs(Collections.singletonList(bcs))
                .setCcs(Collections.singletonList(ccs))
                .createMailMessage();

        final Response response = given().body(emailToSend).contentType(ContentType.JSON)
                .when()
                .post(API_EMAIL_SEND);
        //verify
        assertThat(response.getStatusCode(), is(400));
        JsonPath jsonPathEvaluator = response.jsonPath();
        assertThat(jsonPathEvaluator.getString("email"), is("test@example.com"));
        assertThat(jsonPathEvaluator.getString("message"), is(DefaultMessages.INVALID_EMAIL_PLEASE_CHECK));
    }

}
