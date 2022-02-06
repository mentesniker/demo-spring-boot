package com.example.demo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * <p>
 * Descripción:
 * </p>
 * Implementacion del servicio de envio de mail.
 *
 * @author Gustavo A. Arellano (GAA)
 * @version 1.0-SNAPSHOT
 */
@Service
public class MailSenderServiceImpl implements MailSenderService {
    @Value("${spring.mail.from}")
    private String from;

    /** java mail sender. */
    @Autowired
    private final JavaMailSender javaMailSender;

    /**
     * CBDI Constructor.
     *
     * @param javaMailSender (avoiding Autowire)
     */
    public MailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /** {@inheritDoc} */
    @Override
    @Async
    public String sendHtmlMail(String to, String subject, String body) {
        String returnedValue = "";
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            helper.setFrom(from);
            javaMailSender.send(mail);
            returnedValue = "succeed";
        } catch (MessagingException me) {
            System.out.println("x_x");
        }
        return returnedValue;
    }

}
