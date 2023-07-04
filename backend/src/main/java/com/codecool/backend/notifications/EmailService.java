package com.codecool.backend.notifications;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService implements MessageSender {

    private final static Logger LOGGER = LogManager.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String verificationLink ) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom("aprozar4us@gmail.com");
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            String message = "<img src=\"https://raw.githubusercontent.com/Takerazvan/AprozarOnline/development/frontend/src/11zon_cropped.png\" alt=\"Logo\">" +"Welcome to Aprozar Online! Please click the following link to verify your email: " + verificationLink ;
            helper.setText(message, true);


            mailSender.send(mimeMessage);
            System.out.println("Verification email sent!");
        } catch (MessagingException e) {
            LOGGER.error("Failed to send email", e);
            throw new IllegalStateException("Failed to send email");
        }
    }
}