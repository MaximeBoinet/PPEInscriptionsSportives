package mail;
	
	 
	import java.util.Properties;
	import javax.mail.*;
	import javax.mail.internet.*;
	 
	public class smtpTest {
	 
	  public static void main (String[] args) throws Exception {
	 
	    String smtpHost = "smtp-mail.outlook.com";
	    String from = "m2l.java@hotmail.com";
/*	    String to = "aurelienlazaro@gmail.com";
*/	    String to = "lea_cohen@hotmail.fr";
	    String username = "m2l.java@hotmail.com";
	    String password = "IticParis";
	 
	    Properties props = new Properties();
	    props.put("mail.smtp.host", smtpHost);
	    props.setProperty("mail.smtp.starttls.enable", "true");
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
