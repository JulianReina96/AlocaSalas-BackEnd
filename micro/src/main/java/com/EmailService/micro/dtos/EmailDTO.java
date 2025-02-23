package com.EmailService.micro.dtos;

import com.EmailService.micro.models.Email;

public record EmailDTO(String mailFrom, String mailTo, String mailSubject, String mailText) {
	public EmailDTO(Email email) {
		this( email.getMailFrom(),email.getMailTo(), email.getMailSubject(),email.getMailText());
	}
}

