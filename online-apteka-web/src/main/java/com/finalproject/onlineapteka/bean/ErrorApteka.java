package com.finalproject.onlineapteka.bean;

public class ErrorApteka {
	private String errorMessage;
	
	public ErrorApteka() {
		
	}

	public ErrorApteka(String errorMessage) {
		
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
