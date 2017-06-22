package server.mail;


import org.springframework.mail.SimpleMailMessage;

/**
 * Created by molla on 22/06/2017.
 */
public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);
}