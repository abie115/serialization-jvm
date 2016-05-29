package com.abiewska.jvm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class Test {
	int noOfTest = 1;
	boolean warmup = true;
	JavaSerializer javaSerializer = new JavaSerializer();
	GsonSerializer gsonSerializer = new GsonSerializer();
	Jackson2Serializer jacksonSerializer = new Jackson2Serializer();
	JavaExternalizable javaExternalizable = new JavaExternalizable();

	private static List<Login> listLogins;
	private List<LoginExternalizable> elistLogins;

	private static List<Login> generateLogins(int count) {
		List<Login> logins = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			String username = RandomStringUtils.randomAlphanumeric(17)
					.toUpperCase();
			String password = RandomStringUtils.randomAlphanumeric(17)
					.toUpperCase();
			Login login = new Login(username, password);
			logins.add(login);
		}
		return logins;
	}

	private List<LoginExternalizable> generateLoginsExternalizable(
			List<Login> listLogins) {
		List<LoginExternalizable> elist = new ArrayList<>();
		for (Login login : listLogins) {
			LoginExternalizable elogin = new LoginExternalizable(
					login.getUsername(), login.getPassword());
			elist.add(elogin);
		}
		return elist;
	}

	public void run() {
		System.out.println("Rozgrzewka...");
		warmup();
		warmup = false;

		System.out.println("**TESTY**");
		listLogins = generateLogins(1);
		elistLogins = generateLoginsExternalizable(listLogins);
		System.out.println("****1 OBIEKT****");
		runSerializer();

		listLogins = generateLogins(10);
		elistLogins = generateLoginsExternalizable(listLogins);
		System.out.println("****LISTA Z 10 OBIEKTAMI****");
		runSerializer();

		listLogins = generateLogins(10000);
		elistLogins = generateLoginsExternalizable(listLogins);
		System.out.println("****LISTA Z 10000 OBIEKTOW****");
		runSerializer();

	}

	private void warmup() {
		listLogins = generateLogins(30);
		elistLogins = generateLoginsExternalizable(listLogins);
		runSerializer();
	}

	private void runSerializer() {
		try {
			testJavaSerializer();
			testJacksonSerializer();
			testGsonSerializer();
			testJavaExternalizable();
		} catch (IOException | JsonSyntaxException | JsonIOException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void testJavaSerializer() {
		double start = System.nanoTime();
		for (int i = 0; i < noOfTest; i++) {
			javaSerializer.serialize(listLogins);
			javaSerializer.deserialize();
		}
		double end = System.nanoTime();
		double time = end - start;
		double avgTime = time / noOfTest;
		if (!warmup)
			System.out.println("Average time Java Serializer: " + avgTime
					+ " ns");
	}

	private void testJacksonSerializer() throws JsonGenerationException,
			JsonMappingException, IOException {
		double start = System.nanoTime();
		for (int i = 0; i < noOfTest; i++) {
			jacksonSerializer.serialize(listLogins);
			jacksonSerializer.deserialize();
		}
		double end = System.nanoTime();
		double time = end - start;
		double avgTime = time / noOfTest;
		if (!warmup)
			System.out.println("Average time Jackson Serializer: " + avgTime
					+ " ns");
	}

	private void testGsonSerializer() throws JsonIOException, IOException {
		double start = System.nanoTime();
		for (int i = 0; i < noOfTest; i++) {
			gsonSerializer.serialize(listLogins);
			gsonSerializer.deserialize();
		}
		double end = System.nanoTime();
		double time = end - start;
		double avgTime = time / noOfTest;
		if (!warmup)
			System.out.println("Average time Gson Serializer: " + avgTime
					+ " ns");
	}

	private void testJavaExternalizable() throws FileNotFoundException,
			IOException, ClassNotFoundException {
		double start = System.nanoTime();
		for (int i = 0; i < noOfTest; i++) {
			javaExternalizable.serialize(elistLogins);
			javaExternalizable.deserialize();
		}
		double end = System.nanoTime();
		double time = end - start;
		double avgTime = time / noOfTest;
		if (!warmup)
			System.out.println("Average time Java Externalizable: " + avgTime
					+ " ns\n\n");
	}

}
