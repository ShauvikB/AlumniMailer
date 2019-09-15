package com.bcrec.alumni.service;

public interface SendConfirmationMail {
	
	public void sendEmail(String email, String name) throws Exception;

}
