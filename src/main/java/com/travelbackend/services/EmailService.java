package com.travelbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String email;

    public void sendMail(List<String> to, String object, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to.toArray(new String[0]));
        message.setSubject(object);
        message.setText(text);
        message.setFrom(email);
        mailSender.send(message);
    }
}
