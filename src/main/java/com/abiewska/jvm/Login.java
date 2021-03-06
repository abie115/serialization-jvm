package com.abiewska.jvm;

import java.io.Serializable;

public class Login implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	public Login(){
		
	}
	public Login(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean equals(Object object) {
		Login login = (Login) object;
		if (password.equals(login.getPassword())) {
			if (username.equals(login.getUsername())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "***Login: " + username + " | " + password + "***";
	}
}
