package com.argonaute.checkpoint.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.argonaute.checkpoint.api.dto.EmailDto;
import com.argonaute.checkpoint.api.service.EmailSenderService;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

	@Autowired
	EmailSenderService emailSenderService;

	@PostMapping
	public Map<String, String> sendEmail(@Valid @RequestBody EmailDto emailDto) throws MessagingException {
		String response = emailSenderService.sendMail(emailDto);
		HashMap<String, String> message = new HashMap<>();
		if (response.equals("success")) {
			message.put("message", "success");
			return message;
		} else {
			message.put("message", "error");
			return message;
		}
	}
}
