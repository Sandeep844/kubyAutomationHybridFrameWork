package kuby.web.utility;


import java.nio.file.FileSystems;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
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

import kuby.web.extentReport.MyReportListner;
import kuby.web.testBase.TestBase;

public class MailUtils extends TestBase{


	public static String printEnvironment()
	{
		String getEnv=CommonUtilities.getEnvironment();
		if(getEnv.equals("uat")) {
			return "UAT";
		}if(getEnv.equals("demo")) {
			return "DEMO";
		}if(getEnv.equals("dev")) {
			return "DEV";
		}
		return getEnv;
	}
	public static void sendAutomationReportMail() {
	  String fs=FileSystems.getDefault().getSeparator();
	  String toEmail=prop.getProperty("receiverEmailAddress"); // Change this to the recipient's email address
	  String fromEmail=prop.getProperty("senderEmailAddress"); // Change this to your email address
      String password=prop.getProperty("senderEmailPassword");// Change this to your email password 
	  String subject = "Kuby Automation Test Report - "+printEnvironment();
	  String date = new SimpleDateFormat("dd-MMMM-yyyy").format(new Date());
	  String time =new SimpleDateFormat("HH-mm-ss").format(new Date());
	  String body=ReportsUtils.generateHtmlContent(MyReportListner.totalcount,MyReportListner.passcount,MyReportListner.failedcount,MyReportListner.skippedcount,printEnvironment(),date,time,MyReportListner.sb);
	  /* Attached the Automation Report and  This is the Path */
	  String reportFilePath =System.getProperty("user.dir") + fs + "test-output" + fs + "htmlReport"+fs+"TestReport.html"; // Change this to the actual file path
	  /* Attached is the automation log file and the path here */
	  String logsFilePath =System.getProperty("user.dir") + fs + "test-output" + fs + "log4j"+fs+"logs.out"; // Change this to the actual file path
	  /* Send the email here */ 
	  sendEmailWithAttachment(toEmail, fromEmail, password, subject, body, reportFilePath, logsFilePath);
	}
	public static void sendEmailWithAttachment(String toEmail, final String fromEmail, final String password,
			String subject, String body, String attachmentFilePath1, String attachmentFilePath2 ) {
        /* Setup mail server properties */
		Properties props = new Properties();
		//props.put("mail.smtp.host", "smtp.office365.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable","true");
		//props.put("mail.smtp.socketFactory.fallback", "true");
		/* Enabling SMTP Authentication */
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587"); // SMTP Port
//		props.put("mail.debug", "true");
		//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

       /* Create a session with authentication */
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});

		try {
// Create a MimeMessage object
			Message message = new MimeMessage(session);

// Set the sender and recipient addresses
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

// Set the email subject
			message.setSubject(subject);

// Create the message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			//messageBodyPart.setText(body);
			messageBodyPart.setContent(body,"text/html");

// Create a multipart message to handle attachments
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

// Attach the file TestReport and Logs file
			MimeBodyPart attachmentBodyPart1 = new MimeBodyPart();
			attachmentBodyPart1.attachFile(attachmentFilePath1);
			multipart.addBodyPart(attachmentBodyPart1);
			
			MimeBodyPart attachmentBodyPart2 = new MimeBodyPart();
			attachmentBodyPart2.attachFile(attachmentFilePath2);
			multipart.addBodyPart(attachmentBodyPart2);

// Set the complete message parts
			message.setContent(multipart);

// Send the email
			Transport.send(message);
			System.out.println("Email sent successfully!");
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("Error sending email: " + e.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
