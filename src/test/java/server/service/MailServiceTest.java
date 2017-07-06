package server.service;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import server.model.Client;
import server.service.mail.MailService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ileossa on 05/07/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MailServiceTest {

    @InjectMocks
    MailService mailService;



    @Test
    public void testSend() throws MessagingException, IOException {
        GreenMail greenMail = new GreenMail(ServerSetupTest.SMTP_IMAP);
        Client riri = new Client("riri", "duck", new Date(300879), "riri@localhost", "0616657098", "Disney", "duck city", "12 av disney", "190183", "secretpassword", null, null);
        try {
            greenMail.start();

            //Use random content to avoid potential residual lingering problems
            final String subject = GreenMailUtil.random();
            final String body = GreenMailUtil.random();

            mailService.sendEmail(riri, subject, body);

            //wait for max 5s for 1 email to arrive
            //waitForIncomingEmail() is useful if you're sending stuff asynchronously in a separate thread
            assertTrue(greenMail.waitForIncomingEmail(5000, 2));

            //Retrieve using GreenMail API
            Message[] messages = greenMail.getReceivedMessages();
            assertEquals(2, messages.length);

            // Simple message
            assertEquals(subject, messages[0].getSubject());
            assertEquals(body, GreenMailUtil.getBody(messages[0]).trim());

            //if you send content as a 2 part multipart...
            assertTrue(messages[1].getContent() instanceof MimeMultipart);
            MimeMultipart mp = (MimeMultipart) messages[1].getContent();
            assertEquals(2, mp.getCount());
            assertEquals("body1", GreenMailUtil.getBody(mp.getBodyPart(0)).trim());
            assertEquals("body2", GreenMailUtil.getBody(mp.getBodyPart(1)).trim());
        } finally {
            greenMail.stop();
        }
    }




}
