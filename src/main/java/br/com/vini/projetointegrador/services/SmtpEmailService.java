package br.com.vini.projetointegrador.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractMailService {
	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("simulando o envio de e-mail...");
		mailSender.send(msg);
		LOG.info("E-mail Enviado!!");
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("simulando o envio de e-mail...");
		javaMailSender.send(msg);
		LOG.info("E-mail Enviado!!");
		
	}
	
	
}
