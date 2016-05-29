package com.abiewska.jvm;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;


public class App {
	

	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException, JAXBException {
		Test test = new Test();
		test.run();

	}

}
