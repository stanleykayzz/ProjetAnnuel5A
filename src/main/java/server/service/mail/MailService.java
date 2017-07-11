package server.service.mail;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import server.model.Booking;
import server.model.Client;
import server.repository.ClientRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ileossa on 27/06/2017.
 */
@Service
public class MailService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private static final String CHARSET_UTF8 = "UTF-8";

    private JavaMailSender javaMailSender;
    private VelocityEngine velocityEngine;
    private ClientRepository clientRepository;
    private MailingConfig mailingConfig;

    @Value("spring.mail.path.file.attachement")
    String pathOfFileAttachement;

    @Autowired
    public MailService(JavaMailSender javaMailSender, VelocityEngine velocityEngine, ClientRepository clientRepository, MailingConfig mailingConfig) {
        this.javaMailSender = javaMailSender;
        this.velocityEngine = velocityEngine;
        this.clientRepository = clientRepository;
        this.mailingConfig = mailingConfig;
    }

    public void sendEmail(String email, String subject, String template) {
        MimeMessagePreparator preparator = getSimpleMimeMessagePreparator(email, subject, template);
        this.javaMailSender.send(preparator);
        LOG.debug(" mail send to {} with subject: {}. Template used: {}",
                email,
                subject,
                template);
    }

    public void sendEmail(final Client client, String subject, String template) {
        MimeMessagePreparator preparator = getMimeMessagePreparator(client, subject, template);
        this.javaMailSender.send(preparator);
        LOG.debug("Clientid: {}, mail send to {} with subject: {}. Template used: {}",
                client.getId(),
                client.getEmail(),
                subject,
                template);
    }


    public void sendEmail(final Client client, final Booking booking, String subject, String template) {
        MimeMessagePreparator preparator = getMimeMessagePreparator(client, booking, subject, template);
        this.javaMailSender.send(preparator);
        LOG.debug("BookingId: {}, mail send to {} with subject: {}. Template used: {}",
                booking.getId(),
                client.getEmail(),
                subject,
                template);
    }


    public void sendEmailWithAttachement(final Client client, final Booking booking, String subject, String template, String pathFile){
        MimeMessagePreparator preparator = getMimeMessagePreparator(client, booking, subject, template, pathFile);
        this.javaMailSender.send(preparator);
        LOG.debug("BookingId: {}, mail send to {} with subject: {}. Template used: {}",
                booking.getId(),
                client.getEmail(),
                subject,
                template);
    }

    public void sendEmailWithAttachement(final Client client, String subject, String template, String pathFile){
        MimeMessagePreparator preparator = getMimeMessagePreparator(client, subject, template, pathFile);
        this.javaMailSender.send(preparator);
        LOG.debug("Clientid: {}, mail send to {} with subject: {}. Template used: {}",
                client.getId(),
                client.getEmail(),
                subject,
                template);
    }



// -----------------------------

    private MimeMessagePreparator getSimpleMimeMessagePreparator(String email, String subject, String template) {
        return new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) {
                try {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                    message.setTo(email);
                    message.setSubject(subject);
                    Map model = new HashMap<>();
                    message.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine
                            , template, CHARSET_UTF8, model), true);
                }catch (Exception e){
                    LOG.error("cannnot send simplyMail");
                }
            }
        };
    }


    private MimeMessagePreparator getMimeMessagePreparator(Client client, String subject, String template) {
        return new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mimeMessage) {
                    try {
                        FileSystemResource logo = new FileSystemResource(new File(mailingConfig.getPathLogo()));
                        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                        message.setTo(client.getEmail());
                        message.setSubject(subject);
                        Map model = new HashMap<>();
                        model.put("user", client);
                        message.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine
                                , template, CHARSET_UTF8, model), true);
                        message.addAttachment(logo.getFilename(), logo);
                    }catch (Exception e){
                        LOG.error("Failed to send email to {}: {}", client.toString(), e );
                    }
                }
            };
    }


    private MimeMessagePreparator getMimeMessagePreparator(Client client, String subject, String template, String pathFile) {
        return new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mimeMessage) {
                    try {
                        FileSystemResource logo = new FileSystemResource(new File(mailingConfig.getPathLogo()));
                        FileSystemResource attachement = new FileSystemResource(new File(pathFile));
                        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                        message.setTo(client.getEmail());
                        message.setSubject(subject);
                        Map model = new HashMap<>();
                        model.put("user", client);
                        message.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine
                                , template, CHARSET_UTF8, model), true);
                        message.addAttachment(logo.getFilename(), logo);
                        message.addAttachment(attachement.getFilename(), attachement);
                    }catch (Exception e){
                        LOG.error("Failed to send email to {}: {}", client.toString(), e );
                    }
                }
            };
    }

    private MimeMessagePreparator getMimeMessagePreparator(Client client, Booking booking, String subject, String template) {
        return new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) {
                try {
                    FileSystemResource logo = new FileSystemResource(new File(mailingConfig.getPathLogo()));
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                    message.setTo(client.getEmail());
                    message.setSubject(subject);
                    Map model = new HashMap<>();
                    model.put("user", client);
                    model.put("booking", booking);
                    message.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine
                            , template, CHARSET_UTF8, model), true);
                    message.addAttachment(logo.getFilename(), logo);
                }catch (Exception e){
                    LOG.error("Failed to send email for booking: {} to {}: {}", booking.toString(), client.toString(), e );
                }
            }
        };
    }

    private MimeMessagePreparator getMimeMessagePreparator(Client client, Booking booking, String subject, String template, String pathFile) {
        return new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) {
                try {
                    FileSystemResource logo = new FileSystemResource(new File(mailingConfig.getPathLogo()));
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                    message.setTo(client.getEmail());
                    message.setSubject(subject);
                    Map model = new HashMap<>();
                    model.put("user", client);
                    model.put("booking", booking);
                    message.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine
                            , template, CHARSET_UTF8, model), true);
                    addFileInMimeMessage(message, pathFile);
                    message.addAttachment(logo.getFilename(), logo);
                }catch (Exception e){
                    LOG.error("Failed to send email for booking: {} to {}: {}", booking.toString(), client.toString(), e );
                }
            }
        };
    }



    private MimeMessageHelper addFileInMimeMessage(MimeMessageHelper message, String pathFile){
        FileSystemResource attachement = new FileSystemResource(new File(pathFile));
        try {
            message.addAttachment("Résidence des Hauts de Menaye", attachement);
        } catch (MessagingException e) {
            LOG.error("Cannot attachment file {}", pathFile);
        }
        return message;
    }


}
