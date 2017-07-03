package server.service.mail;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import server.model.Booking;
import server.model.Client;
import server.repository.ClientRepository;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ileossa on 27/06/2017.
 */
@Service
public class MailService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private static final String CHARSET_UTF8 = "UTF-8";

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    ClientRepository clientRepository;

    @Value("spring.mail.path.file.attachement")
    String pathOfFileAttachement;



    public void sendEmail(final Client client, String subject, String template) {
        MimeMessagePreparator preparator = getMimeMessagePreparator(client, subject, template);
        this.javaMailSender.send(preparator);
        LOG.debug("Clientid: {}, mail send to {} with subject: {}. Template used: {}",
                client.getClientId(),
                client.getEmail(),
                subject,
                template);
    }


    public void sendEmail(final Client client, final Booking booking, String subject, String template) {
        MimeMessagePreparator preparator = getMimeMessagePreparator(client, booking, subject, template);
        this.javaMailSender.send(preparator);
        LOG.debug("BookingId: {}, mail send to {} with subject: {}. Template used: {}",
                booking.getIdBook(),
                client.getEmail(),
                subject,
                template);
    }



// -----------------------------

    private MimeMessagePreparator getMimeMessagePreparator(Client client, String subject, String template) {
        return new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mimeMessage) {
                    try {
                        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                        message.setTo(client.getEmail());
                        message.setSubject(subject);
                        Map model = new HashMap<>();
                        model.put("user", client);
                        message.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine
                                , template, CHARSET_UTF8, model), true);
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
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                    message.setTo(client.getEmail());
                    message.setSubject(subject);
                    Map model = new HashMap<>();
                    model.put("user", client);
                    model.put("booking", booking);
                    message.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine
                            , template, CHARSET_UTF8, model), true);
                }catch (Exception e){
                    LOG.error("Failed to send email for booking: {} to {}: {}", booking.toString(), client.toString(), e );
                }
            }
        };
    }


}
