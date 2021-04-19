package com.improveme.administrationservice.services;

import com.improveme.administrationservice.entities.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public Boolean sendMail(Mail mail)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getSendTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        try {
            javaMailSender.send(mailMessage);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
