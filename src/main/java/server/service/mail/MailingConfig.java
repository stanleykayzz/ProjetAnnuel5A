package server.service.mail;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import server.repository.ServicesHotelRepository;

import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by ileossa on 27/06/2017.
 */
@Configuration
public class MailingConfig {

    private final org.slf4j.Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Value("${mail.protocol}") // this is to read variable from application.properties
    private String mailProtocol;
    @Value("${mail.smtp.host}")
    private String host;
    @Value("${mail.smtp.port}")
    private int port;
    @Value("${mail.support.username}")
    private String userName;
    @Value("${mail.support.password}")
    private String password;



    @Bean
    public JavaMailSenderImpl javaMailSender(){
        JavaMailSenderImpl jms = new JavaMailSenderImpl();
        jms.setProtocol(mailProtocol);
        jms.setHost(host);
        jms.setPort(port);
        jms.setUsername(userName);
        jms.setPassword(password);
        jms.setJavaMailProperties(getMailProperties());
        return jms;
    }


    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "true");
        return properties;
    }



}
