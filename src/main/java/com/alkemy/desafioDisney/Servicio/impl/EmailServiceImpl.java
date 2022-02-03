package com.alkemy.desafioDisney.Servicio.impl;

import com.alkemy.desafioDisney.Servicio.EmailService;
import com.alkemy.desafioDisney.Utils.UtilsLog;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.alkemy.desafioDisney.Enum.TipoLog.INFO;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment env;

    @Value("${alkemy.desafioDisney.email.sender}")
    private String emailSender;
    @Value("${alkemy.desafioDisney.email.enabled}")
    private boolean enabled;


    public void sendWelcomeEmailTo(String to) {
        if (!enabled){
            return;
        }
        String apiKey = env.getProperty("EMAIL_API_KEY");
        SendGrid sg = new SendGrid(apiKey);

        try {
            Request request = this.buildRequest(to);
            Response response = sg.api(request);

            UtilsLog.registrarInfo(EmailService.class, INFO, String.valueOf(response.getStatusCode()));
            UtilsLog.registrarInfo(EmailService.class, INFO, response.getBody());
            UtilsLog.registrarInfo(EmailService.class, INFO, String.valueOf(response.getHeaders()));
        } catch (IOException e) {
            System.out.println("Errors trying to send the email");
        }
    }

    public Content generateEmailContent (){
        return new Content(
                "text/plain",
                "Bienvenido/a al Desafio Alkemy de Disney"
        );
    }

    public Mail buildMail(String to){

        Content content = this.generateEmailContent();
        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        String subject = "Alkemy Disney";

        return new Mail(fromEmail, subject, toEmail, content);
    }

    public Request buildRequest(String to) throws IOException {

        Mail mail = this.buildMail(to);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        return request;
    }
}
