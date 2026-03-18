package com.fpt.godii.application.utils;

import com.fpt.godii.application.base.model.HttpEmailData;
import com.fpt.godii.application.exception.FOBadRequestException;
import com.fpt.godii.application.configurations.properties.MailConfiguration;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

@Component
@ConditionalOnProperty(
        prefix = "app.feature",
        name = "mail-enabled",
        havingValue = "true",
        matchIfMissing = false
)
public class MailUtil {
    Logger logger = LoggerFactory.getLogger(MailUtil.class);
    
    private final MailConfiguration mailConfiguration;
    
    private final SpringTemplateEngine thymeleafTemplateEngine;
    private Session session;

    public MailUtil(MailConfiguration mailConfiguration, SpringTemplateEngine thymeleafTemplateEngine) {
        this.mailConfiguration = mailConfiguration;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
        updateSession();
    }

    private void updateSession(){
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", mailConfiguration.getAuth());
        prop.put("mail.smtp.starttls.enable", mailConfiguration.getStarttlsEnable());
        prop.put("mail.smtp.host", mailConfiguration.getHost());
        prop.put("mail.smtp.port", mailConfiguration.getPort());
        prop.put("mail.smtp.ssl.trust", mailConfiguration.getHost());
        prop.put("mail.smtp.starttls.required", mailConfiguration.getStarttlsRequired());

        session = Session.getDefaultInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailConfiguration.getUserName(), mailConfiguration.getPassword());
                    }
                });
    }

    public void sendMail(HttpEmailData htmlEmailData){
        try {
            htmlEmailData.setCc(List.of("sample@gmail.com"));
            Context thymeleafContext = new Context();
            htmlEmailData.getVariables().putAll(defaultVariables());
            thymeleafContext.setVariables(htmlEmailData.getVariables());
            String body = htmlEmailData.isHTML() ?  thymeleafTemplateEngine.process(htmlEmailData.getTemplatePath(), thymeleafContext)
                    : htmlEmailData.getBody();

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailConfiguration.getNoReplyEmail()));
            if(ObjectUtils.isEmpty(htmlEmailData.getTo()))
                throw new FOBadRequestException("To field must not empty");

            for (String t : htmlEmailData.getTo()) {
                message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(t));
            }

            if(!ObjectUtils.isEmpty(htmlEmailData.getCc()))
            {
                for (String c : htmlEmailData.getCc()) {
                    message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(c));
                }
            }

            if(!ObjectUtils.isEmpty(htmlEmailData.getBcc())){
                for (String c : htmlEmailData.getBcc()) {
                    message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(c));
                }
            }

            message.setSubject(htmlEmailData.getSubject());

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            if(htmlEmailData.isHTML())
                mimeBodyPart.setContent(body, "text/html; charset=utf-8");
            else
                mimeBodyPart.setContent(body, "text/plain; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            if(!ObjectUtils.isEmpty(htmlEmailData.getAttachments())){
                for (MimeBodyPart a : htmlEmailData.getAttachments()) {
                    multipart.addBodyPart(a);
                }
            }
            message.setContent(multipart);

            Transport.send(message);
            logger.info("Send email successfully!");

        } catch (MessagingException ex){
            logger.warn("Can't send email");
        }
    }

    private HashMap<String, Object> defaultVariables(){
        return new HashMap<>(){{
            put("sample_mail",mailConfiguration.getEsignUrl());
        }};
    }
}
