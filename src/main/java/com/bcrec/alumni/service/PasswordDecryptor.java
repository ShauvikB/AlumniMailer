package com.bcrec.alumni.service;

public interface PasswordDecryptor {
	
	public String encrypt(String unencryptedString);
	
	 public String decrypt(String encryptedString);

}
