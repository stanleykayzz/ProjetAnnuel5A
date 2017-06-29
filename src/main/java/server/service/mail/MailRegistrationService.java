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
import org.springframework.ui.velocity.VelocityEngineUtils;
import server.model.Client;
import server.repository.ClientRepository;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ileossa on 27/06/2017.
 */
public class MailRegistrationService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private static final String SUBJECT = "Confirmation registration";
    private static final String CHARSET_UTF8 = "UTF-8";

    private JavaMailSender javaMailSender;
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    ClientRepository clientRepository;

    @Value("spring.mail.path.file.attachement")
    String pathOfFileAttachement;


    public void sendConfirmationByEmail(final Client client) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                message.setTo(client.getEmail());
                message.setSubject(SUBJECT);
                Map map = new HashMap<>();
                map.put("user", client);
                message.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "registration-confirmation.vm", CHARSET_UTF8, map), true);
                FileSystemResource fileAttachement = new FileSystemResource(new File(pathOfFileAttachement));
                message.addAttachment("confirmation_reservation.pdf", fileAttachement);
            }
        };
        this.javaMailSender.send(preparator);
    }




















}
