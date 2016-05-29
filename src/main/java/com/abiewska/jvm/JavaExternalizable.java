package com.abiewska.jvm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class JavaExternalizable {

	static final String FILE_NAME = "externalizable.object";

	public void serialize(List<LoginExternalizable> logins)
			throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				FILE_NAME));
		out.writeObject(logins);
		out.close();
	}

	@SuppressWarnings("unchecked")
	public List<LoginExternalizable> deserialize()
			throws FileNotFoundException, IOException, ClassNotFoundException {
		List<LoginExternalizable> result = new ArrayList<>();
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				FILE_NAME));
		result = (List<LoginExternalizable>) in.readObject();
		in.close();
		return result;

	}
}
