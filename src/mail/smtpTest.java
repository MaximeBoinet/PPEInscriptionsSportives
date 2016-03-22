package mail;
	
	 
	import java.util.Properties;
	import javax.mail.*;
	import javax.mail.internet.*;
	 
	public class smtpTest {
	 
	  public static void main (String[] args) throws Exception {
	 
	    String smtpHost = "smtp.xyz.com";
	    String from = "originataire@origine.com";
	    String to = "destinataire@destination.com";
	    String username = "moi";
	    String password = "mon_mot_secret";
	 
	    Properties props = new Properties();
	    props.put("mail.smtp.host", smtpHost);
	    props.put("mail.smtp.auth", "true");
	 
	    Session session = Session.getDefaultInstance(props);
	    session.setDebug(true);
	 
	    MimeMessage message = new MimeMessage(session);   
	    message.setFrom(new InternetAddress(from));
	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	    message.setSubject("Hello");
	    message.setText("Hello World");
	 
	    Transport tr = session.getTransport("smtp");
	    tr.connect(smtpHost, username, password);
	    message.saveChanges();
	 
	    // tr.send(message);
	    /** Genere l'erreur. Avec l authentification, oblige d utiliser sendMessage meme pour une seule adresse... */
	 
	    tr.sendMessage(message,message.getAllRecipients());
	    tr.close();
	 
	  }
}
