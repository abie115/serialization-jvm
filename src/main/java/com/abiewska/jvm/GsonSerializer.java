package com.abiewska.jvm;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class GsonSerializer {
	static final String FILE_NAME = "serialized.gson.json";

	public void serialize(List<Login> object) throws JsonIOException,
			IOException {
		Gson gson = new Gson();
		String json = gson.toJson(object);
		FileWriter writer = new FileWriter(FILE_NAME);
		writer.write(json);
		writer.close();
	}

	@SuppressWarnings("unchecked")
	public List<Login> deserialize() throws JsonSyntaxException,
			JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		List<Login> result = new ArrayList<>();
		result = gson.fromJson(new FileReader(FILE_NAME), List.class);
		return result;
	}
}
