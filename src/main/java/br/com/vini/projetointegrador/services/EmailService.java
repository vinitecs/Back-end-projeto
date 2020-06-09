package br.com.vini.projetointegrador.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.vini.projetointegrador.dominio.Pedido;

public interface EmailService {
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
