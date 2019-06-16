package ecommerce.application.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import ecommerce.application.domain.Pedido;

public interface EmailService {
	void sendOrderEmailConfirmation(Pedido object);
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderHtmlEmailConfirmation(Pedido object);
	void sendHtmlEmail(MimeMessage msg);

}
