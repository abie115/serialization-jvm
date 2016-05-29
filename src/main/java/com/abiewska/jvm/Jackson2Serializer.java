package com.abiewska.jvm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson2Serializer{
	static final String FILE_NAME = "serialized.jackson.json";

	public void serialize(List <Login> object) throws JsonGenerationException, JsonMappingException, IOException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File(FILE_NAME), object);
	}

	@SuppressWarnings("unchecked")
	public List <Login> deserialize() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		  List<Login> result = new ArrayList<>();
		 result = mapper.readValue(new File(FILE_NAME), List.class);
		return result;
	}
}
