package com.github.notification_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.github.notification_service.dto.PaymentCreatedEvent;
import com.github.notification_service.dto.RequestNotification;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class MailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remitente;

    public void sendBuyNotification(RequestNotification evento) {
        StringBuilder texto = new StringBuilder();
        texto.append("¡Gracias por tu compra!\n");
        texto.append("Comprobante #").append(evento.getPaymentId()).append("\n\n");

        if (evento.getItems() != null) {
            for (PaymentCreatedEvent item : evento.getItems()) {
                texto.append(item.getQuantity()).append("x ")
                     .append(item.getCoffeeName()).append(" - $")
                     .append(item.getPriceCLP() * item.getQuantity()).append("\n");
            }
        }
        texto.append("\nTotal: $").append(evento.getTotalPrice()).append(" CLP");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(remitente);
        mail.setTo(evento.getUserEmail());
        mail.setSubject("Comprobante de compra #" + evento.getPaymentId());
        mail.setText(texto.toString());
        mailSender.send(mail);
    }

}
