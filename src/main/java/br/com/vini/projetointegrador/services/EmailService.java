package br.com.vini.projetointegrador.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.vini.projetointegrador.dominio.Cliente;
import br.com.vini.projetointegrador.dominio.Pedido;

public interface EmailService {
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	// metodo para envio em html
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	void sendHtmlEmail(MimeMessage msg);
	void sendNewPasswordEmail(Cliente cliente, String newPAss);

}
