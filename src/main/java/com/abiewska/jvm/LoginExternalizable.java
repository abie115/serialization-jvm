package com.abiewska.jvm;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class LoginExternalizable implements Externalizable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	public LoginExternalizable() {

	}

	public LoginExternalizable(String username, String password) {
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

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(getPassword());
		out.writeObject(getUsername());

	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		this.setPassword((String) (in.readObject()));
		this.setUsername((String) (in.readObject()));

	}
}
