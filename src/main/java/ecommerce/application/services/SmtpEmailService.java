package ecommerce.application.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class SmtpEmailService extends AbstractEmailService {

	private static final Logger Log = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender JavaMailSender;
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		Log.info("Simulando envio de email...");
		mailSender.send(msg);
		Log.info("Email enviado");
		// TODO Auto-generated method stub
		
	}
	
	public void sendHtmlEmail(MimeMessage msg) {
		Log.info("Simulando envio de email...");
		JavaMailSender.send(msg);
		Log.info("Email enviado");
		
	}

}
