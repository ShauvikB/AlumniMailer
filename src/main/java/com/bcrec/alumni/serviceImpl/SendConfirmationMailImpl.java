package com.bcrec.alumni.serviceImpl;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.bcrec.alumni.service.PasswordDecryptor;
import com.bcrec.alumni.service.SendConfirmationMail;

public class SendConfirmationMailImpl implements SendConfirmationMail{
	
	public Properties prop;
	
	public SendConfirmationMailImpl(Properties prop) {
		this.prop = prop;
	}
	
	public void sendEmail(String email, String name) throws Exception {
    	final String username = "shauvik.bakshi@gmail.com";
    	PasswordDecryptor td= new PasswordDecryptorImpl();
        String target= prop.getProperty("password");
        String decrypted=td.decrypt(target);

        System.out.println("String : "+ target);
        System.out.println("Decrypted String:" + decrypted);
    	
        final String password = decrypted;

		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
        	
        	System.out.println(email);
        
            String to = email;
            
         // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress("shauvik.bakshi@gmail.com"));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
               InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Testing Gmail SSL");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("Testing Gmail SSL"
                    + "\n\n taka collect kore onek chirecho eibar maal khao!");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "E:/BCREC Alumni/" + "qr-code-vcard-" + name + ".png";
            
            System.out.println(filename);
            
            FileDataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

           Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
