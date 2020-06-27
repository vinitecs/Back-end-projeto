package br.com.vini.projetointegrador.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.vini.projetointegrador.dominio.Cliente;
import br.com.vini.projetointegrador.dominio.Pedido;

public abstract class AbstractMailService implements EmailService {
	@Value("${default.sender}")
	private String sender;
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}
	@Autowired
	private  TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido Confirmado! Codigo"+ obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	protected String htmlFromTemplatePedido(Pedido obj) {
		//Context é responsavel por processar ca rquisição dos dados Pedido obj
		Context context = new Context();
		context.setVariable("pedido", obj);
		// ele vai no diretorio html epuxa os objetos para o html
		return templateEngine.process("email/confirmacaPedido", context);
		
	}
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		try {
		MimeMessage mm = prepareMimeMessageFromPedido(obj);
		sendHtmlEmail(mm);
		}catch(MessagingException e) {
			sendOrderConfirmationEmail(obj);
			
		}
	}
	//preparando o objeto para envio em html
	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException  {
		MimeMessage mimeMessage  = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage,true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido Confirmado  Codigo: " + obj.getId() );
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj),true);
		return mimeMessage;
	}
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(cliente, newPass);
	sendEmail(sm);
	}
	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cliente.getEmail());
		sm.setFrom(sender);
		sm.setSubject("solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("nova senha: "+ newPass);
		return sm;
	
	}
}
