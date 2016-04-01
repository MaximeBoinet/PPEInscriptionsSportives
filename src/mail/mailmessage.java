package mail;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class mailmessage {
	private final String smtpHost = "smtp-mail.outlook.com";
	private final String from = "m2l.java@hotmail.com";
	private final String username = "m2l.java@hotmail.com";
	private final String password = "IticParis";
	Properties props = null;
	Session session = null;
	MimeMessage message = null;

	public mailmessage(String to, String subject, String text) {
		props = new Properties();
		props.put("mail.smtp.host", smtpHost);
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		session = Session.getDefaultInstance(props);
		session.setDebug(true);

		message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void envoyer() {
		Transport tr;
		try {
			tr = session.getTransport("smtp");
			try {
				tr.connect(smtpHost, username, password);
				message.saveChanges();
				tr.sendMessage(message,message.getAllRecipients());
				tr.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
