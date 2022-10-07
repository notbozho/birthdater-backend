package dev.bozho.birthdater.service.impl;

import dev.bozho.birthdater.service.IEmailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements IEmailService {

//    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String subject, String emailContent) {
//        try {
////            MimeMessage mimeMessage = mailSender.createMimeMessage();
////            MimeMessageHelper helper =
////                    new MimeMessageHelper(mimeMessage, "utf-8");
////            helper.setText(emailContent, true);
////            helper.setTo(to);
////            helper.setSubject(subject);
////            helper.setFrom("hello@birthdater.com");
////            mailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            throw new IllegalStateException("failed to send email");
//        }
    }
}
