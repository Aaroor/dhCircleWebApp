package com.dhcircle.dhCircleWebApp.webservice.user;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.dhcircle.dhCircleWebApp.model.user.Mail;

@Service("emailSenderService")
public class EmailSenderService {
	private JavaMailSender javaMailSender;
	@Autowired
    private SpringTemplateEngine templateEngine;

	@Autowired
	public EmailSenderService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Async
	public void sendEmail(SimpleMailMessage email) {
		javaMailSender.send(email);
	}
	
//	@Async
//	public void sendSimpleMessage(Mail mail) throws MessagingException, IOException {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message,
//                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//                StandardCharsets.UTF_8.name());
//
//        //helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));
//
//        Context context = new Context();
//        context.setVariables(mail.getModel());
//        String html = templateEngine.process("email-template", context);
//
//        helper.setTo(mail.getTo());
//        helper.setText(html, true);
//        helper.setSubject(mail.getSubject());
//        helper.setFrom(mail.getFrom());
//
//        javaMailSender.send(message);
//    }
	
	@Async
	public void send(Mail mail) {
    	//get and fill the template
        final Context context = new Context();
        context.setVariable("message", mail.getMessage());
        String body = templateEngine.process("email/email-template", context);
        //send the html template
        sendPreparedMail(mail.getEmail(), mail.getObject(), body, true);
    }

    private void sendPreparedMail(String to, String subject, String text, Boolean isHtml) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setFrom("aaroor121819@gmail.com");
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            javaMailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
