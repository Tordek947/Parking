package ua.hpopov.parking.services;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
public class EmailSender {

	private static EmailSender instance=null;
	private static final Logger log = LoggerFactory.getLogger(EmailSender.class);
	
	private String username;
    private String password;
    private Properties props;
	
	private EmailSender() {
		this.username = "myparking.service.noreply@gmail.com";
        this.password = "default_password";

        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
	}
	
	public static EmailSender getInstance() {
		if (instance == null) {
			instance = new EmailSender();
		}
		return instance;
	}

    public boolean send(String subject, String text, String toEmail){
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {
        	log.error("Error during the message sending",e);
        	return false;
        }
        return true;
    }
}
