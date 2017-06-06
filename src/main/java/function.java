import com.relevantcodes.extentreports.LogStatus;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Created by innoraft on 5/6/17.
 */
public class function {

    public void sendmail(String username, String password, String from_email, String to_email, String cc, String bcc , String subject, String filepath,
                         String reportname, String mailbody){
        final String email = username;
        final String mail_password = password;

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, mail_password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from_email));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to_email));
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
            message.setSubject(subject);
//            message.setContent("<p>There is HTML report attached containing output of testcase</p>","text/html");

            MimeBodyPart attachementBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            String file = filepath;
            String fileName = reportname;
            DataSource source = new FileDataSource(file);
            attachementBodyPart.setDataHandler(new DataHandler(source));
            attachementBodyPart.setFileName(fileName);

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(mailbody);
            multipart.addBodyPart(textBodyPart);
            multipart.addBodyPart(attachementBodyPart);

            message.setContent(multipart);

            System.out.println("Sending");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
