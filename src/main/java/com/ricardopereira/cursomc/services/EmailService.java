package com.ricardopereira.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.ricardopereira.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
