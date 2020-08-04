package com.goibibo.testcases;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class TestRunner {

	public static void main(String[] args) {
		
		TestNG testNg = new TestNG();
		String userDirectPath = System.getProperty("user.dir");
		
		List<String> suites = new ArrayList<String>();
		suites.add(userDirectPath+"\\testing.xml");
		testNg.setTestSuites(suites);
		testNg.run();
	}

}
