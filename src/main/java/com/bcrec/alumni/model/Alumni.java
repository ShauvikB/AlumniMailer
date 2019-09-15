package com.bcrec.alumni.model;

/**
 * Created by anirudh on 20/10/14.
 */
public class Alumni {

    private String name;
    private Integer yearOfGraduation;
    private String stream;
    private Integer amount;
    private String email;
    private String mailSent;

    public Alumni(){}

    public Alumni(String name, Integer yearOfGraduation, String stream, Integer amount, String email, String mailSent) {
        this.name = name;
        this.yearOfGraduation = yearOfGraduation;
        this.stream = stream;
        this.amount = amount;
        this.email = email;
        this.mailSent = mailSent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearOfGraduation() {
		return yearOfGraduation;
	}

	public void setYearOfGraduation(Integer yearOfGraduation) {
		this.yearOfGraduation = yearOfGraduation;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMailSent() {
		return mailSent;
	}

	public void setMailSent(String mailSent) {
		this.mailSent = mailSent;
	}

	@Override
    public String toString() {
        return name+ ": yearOfGraduation "+yearOfGraduation+ " stream "+stream+" amount "+amount+ "email "+email+ "mailSent "+mailSent;
    }
}
