package ecommerce.application.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import ecommerce.application.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender JavaMailSender;
	
	//@Value("${default.sender}")
	//private String sender;
	
	@Override
	public void sendOrderEmailConfirmation(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		System.out.println("teste1232132131241241");
		sendEmail(sm);
	}
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		//sm.setFrom(sender); //Para o SMTP vai ser sobrescrito aqui , creio eu !
		sm.setSubject("Pedido confirmado! Código: " + obj.getCod_pedido());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido",obj);
		return templateEngine.process("email/confirmacaoPedido",context);
	}
	
	public void sendOrderHtmlEmailConfirmation(Pedido obj) {
		try {
			MimeMessage mime = prepareMimeMessageFromPedido(obj);
			sendHtmlEmail(mime);
		}
		catch(MessagingException e) {
			sendOrderEmailConfirmation(obj);
		}
	}
	
	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
		MimeMessage mimeMessage = JavaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage,true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setSubject("Pedido Confirmado! Código: "+obj.getCod_pedido());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj),true);
		return mimeMessage;
	}
	
}
