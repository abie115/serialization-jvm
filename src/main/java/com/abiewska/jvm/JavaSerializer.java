package com.abiewska.jvm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class JavaSerializer {

	static final String FILE_NAME = "serialized.object";

	public void serialize(List <Login> object) {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
			out.writeObject(object);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//@SuppressWarnings("unchecked")
	public List <Login> deserialize() {
		List<Login> result = new ArrayList<>();
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					FILE_NAME));
			result = (List<Login>) in.readObject();
			in.close();
			return result;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;

	}
}
