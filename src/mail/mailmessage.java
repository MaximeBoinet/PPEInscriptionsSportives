package mail;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.event.*;
import javax.mail.internet.*;
import javax.activation.*;

public class mailmessage {

	public mailmessage() {
		// TODO Auto-generated constructor stub
	}
	 public static void main (String[] args) throws Exception {
	
		String psw="IticParis";
		String smtp = "smtp-mail.outlook.com";
		String login = "m2l.java@hotmail.com";
		String imap = "imap-mail.outlook.com";
		String text1 ="Mail Test";
    	String subject1 ="zeze";
    	String destinataire1 ="aurelienlazaro@gmail.com";
    	String copyDest1="";
    	
		// 1 -> Création de la session 
		
		
		
	    Properties properties = new Properties(); 
	    properties.setProperty("mail.transport.protocol", "smtp"); 
	    properties.setProperty("mail.smtp.host", smtp); 
	    properties.setProperty("mail.smtp.user", login); 
	    properties.setProperty("mail.from", imap ); 
	    properties.setProperty("mail.smtp.starttls.enable", "true");
	    Session session = Session.getInstance(properties);
	    
	    
	 // 2 -> Création du message 
	    MimeMessage message = new MimeMessage(session); 
	    try { 
	    	
	    	//Lecture du form
	    	
	    	
	        message.setText(text1); 
	        message.setSubject(subject1); 
	        message.addRecipients(Message.RecipientType.TO, destinataire1); 
	        message.addRecipients(Message.RecipientType.CC, copyDest1); 
	    } catch (MessagingException e) { 
	        e.printStackTrace(); 
	    } 
	    
	    // 3 -> Envoi du message 
	    Transport tr = session.getTransport("smtp");
	    tr.connect(smtp, login, psw);
	    message.saveChanges();
	 
	    // tr.send(message);
	    /** Genere l'erreur. Avec l authentification, oblige d utiliser sendMessage meme pour une seule adresse... */
	 
	    tr.sendMessage(message,message.getAllRecipients());
	    tr.close();
	   try { 
	    	Transport transport = session.getTransport("smtp");
	        transport.connect(login, psw); 
	        transport.sendMessage(message, new Address[] 
	        { new InternetAddress(destinataire1), 
	                                                       new InternetAddress(copyDest1) });
	        
	    } catch (MessagingException e) { 
	        e.printStackTrace(); 
	    } finally { 
	        try { 
	        	Transport transport = session.getTransport("smtp");
	            if (transport != null) { 
	                transport.close();; 
	            } 
	        } catch (MessagingException e) { 
	            e.printStackTrace(); 

}
}
	}
}

