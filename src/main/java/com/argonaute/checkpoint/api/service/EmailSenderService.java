package com.argonaute.checkpoint.api.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.argonaute.checkpoint.api.dto.EmailDto;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender javaMailSender;

	private String argoMail = "meziani-i@outlook.fr";

	public String sendMail(EmailDto emailDto) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject(emailDto.getObject());
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setFrom(emailDto.getEmail());
		helper.setTo(argoMail);
		helper.setText(emailDto.getMessage() + "<br><p>From: " + emailDto.getName() + "</p>Answer to: "
				+ emailDto.getEmail() + "</p>", true);
		javaMailSender.send(message);
		return "success";
	}

	public String sendReservationMail(EmailDto emailDto) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("Réservation par " + emailDto.getName());
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setFrom(emailDto.getEmail());
		helper.setTo(argoMail);

		StringBuilder stringBuilder = new StringBuilder();
		helper.setText(stringBuilder.append("Nom Prénom : ").append(emailDto.getName()).append("<br>Mail : ")
				.append(emailDto.getEmail()).append("<br>Vous êtes : ").append(emailDto.getName())
				.append("<br><br>Commentaires : ").append(emailDto.getMessage()).toString(), true);
		javaMailSender.send(message);
		return "success";
	}

}