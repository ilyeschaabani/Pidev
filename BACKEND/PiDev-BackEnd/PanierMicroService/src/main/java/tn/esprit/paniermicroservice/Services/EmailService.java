package tn.esprit.paniermicroservice.Services;

import jakarta.activation.DataHandler;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

@Service
public class EmailService {

    public void sendInvoiceEmail(String recipient, ByteArrayOutputStream pdf) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com"); // or another SMTP server
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("zarrouk.medaziz@gmail.com", "lxrn gjty rbpx lwwr");
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("zarrouk.medaziz@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Votre facture ");

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Merci pour votre achat ! Veuillez trouver votre facture ci-jointe.");

            MimeBodyPart attachment = new MimeBodyPart();
            attachment.setDataHandler(new DataHandler(new ByteArrayDataSource(pdf.toByteArray(), "application/pdf")));
            attachment.setFileName("facture.pdf");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachment);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("📧 Invoice email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Failed to send invoice email.");
        }
    }
}
