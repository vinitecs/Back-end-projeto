package br.com.vini.projetointegrador.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractMailService{
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	@Override
	public void sendEmail(SimpleMailMessage msg) {
	
		LOG.info("simulando o envio de e-mail...");
		LOG.info(msg.toString());
		LOG.info("E-mail Enviado!!");
	}
	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("simulando o envio de e-mail html...");
		LOG.info(msg.toString());
		LOG.info("E-mail Enviado!!");
		
	}

}
