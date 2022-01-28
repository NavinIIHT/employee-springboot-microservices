package com.iiht.training.certificates.testutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// boiler-plate code
public class TestUtils {

	public static final String TEXT_RESET = "\033[0m";
	public static final String RED_BOLD_BRIGHT = "\033[1;91m"; // RED
	public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
	public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
	public static final String BLUE_BOLD_BRIGHT = "\033[1;94m"; // BLUE

	public static String testResult;

	public static int total;
	public static int passed;
	public static int failed;

	public static File businessTestFile;
	public static File boundaryTestFile;
	public static File exceptionTestFile;
	public static File xmlFile;
	static {
		total = 0;
		passed = 0;
		failed = 0;

		testResult = "";

		xmlFile = new File("../employee-microservices.xml");
		xmlFile.delete();
		removeLineFromFile("../output_revised.txt", "certificates");
		removeLineFromFile("../output_boundary_revised.txt", "certificates");
		removeLineFromFile("../output_exception_revised.txt", "certificates");

		businessTestFile = new File("../output_revised.txt");
		// businessTestFile.delete();

		boundaryTestFile = new File("../output_boundary_revised.txt");
		/// boundaryTestFile.delete();

		exceptionTestFile = new File("../output_exception_revised.txt");
		// exceptionTestFile.delete();
	}

	public static void yakshaAssert(String testName, Object result, File file) throws IOException {
		total++;
		String[] r = testName.split("(?=\\p{Upper})");
		System.out.print("\n" + BLUE_BOLD_BRIGHT + "TEST CASE => ");

		System.out.print(YELLOW_BOLD_BRIGHT + "Test For " + " ");

		if (file.getName().contains("output_revised")) {
			System.out.print(YELLOW_BOLD_BRIGHT + "[FUNCTIONALITY]" + " ");
		}
		if (file.getName().contains("exception")) {
			System.out.print(YELLOW_BOLD_BRIGHT + "[EXCEPTION]" + " ");
		}
		if (file.getName().contains("boundary")) {
			System.out.print(YELLOW_BOLD_BRIGHT + "[BOUNDARY]" + " ");
		}

		for (int i = 1; i < r.length; i++) {
			System.out.print(YELLOW_BOLD_BRIGHT + r[i] + " ");
		}
		System.out.print(" : ");
		if (result.toString().equals("true")) {
			System.out.println(GREEN_BOLD_BRIGHT + "PASSED" + TEXT_RESET);
			passed++;
		} else {
			System.out.println(RED_BOLD_BRIGHT + "FAILED" + TEXT_RESET);
			failed++;
		}
		FileWriter writer = new FileWriter(file, true);
		writer.append("\n" + testName + "=" + result);
		writer.flush();
		writer.close();

//		 createXML(testName, file);
	}

	public static void testReport() {
		System.out.println(testResult);
		System.out.print("\n" + BLUE_BOLD_BRIGHT + "TEST CASES EVALUATED : " + total + TEXT_RESET);
		System.out.print("\n" + GREEN_BOLD_BRIGHT + "PASSED : " + passed + TEXT_RESET);
		System.out.println("\n" + RED_BOLD_BRIGHT + "FAILED : " + failed + TEXT_RESET);

	}

	public static void createXML(String testName, File file) throws IOException {
		FileWriter writer = new FileWriter(xmlFile, true);
		if (file.getName().contains("output_revised")) {

			writer.append(
					"\r\n<cases xmlns:java=\"http://java.sun.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"java:com.assessment.data.TestCase\">\r\n"
							+ "		<test-case-type>Functional</test-case-type>\r\n"
							+ "		<expected-ouput>true</expected-ouput>\r\n" + "		<name>" + testName
							+ "</name>\r\n" + "		<weight>8</weight>\r\n" + "		<mandatory>true</mandatory>\r\n"
							+ "		<desc>" + testName + "</desc>\r\n" + "	</cases>");
			writer.flush();
			writer.close();
		}
		if (file.getName().contains("boundary")) {

			writer.append(
					"\r\n<cases xmlns:java=\"http://java.sun.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"java:com.assessment.data.TestCase\">\r\n"
							+ "		<test-case-type>Boundary</test-case-type>\r\n"
							+ "		<expected-ouput>true</expected-ouput>\r\n" + "		<name>" + testName
							+ "</name>\r\n" + "		<weight>3</weight>\r\n" + "		<mandatory>true</mandatory>\r\n"
							+ "		<desc>" + testName + "</desc>\r\n" + "	</cases>");
			writer.flush();
			writer.close();
		}
		if (file.getName().contains("exception")) {

			writer.append(
					"\r\n<cases xmlns:java=\"http://java.sun.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"java:com.assessment.data.TestCase\">\r\n"
							+ "		<test-case-type>Exception</test-case-type>\r\n"
							+ "		<expected-ouput>true</expected-ouput>\r\n" + "		<name>" + testName
							+ "</name>\r\n" + "		<weight>5</weight>\r\n" + "		<mandatory>true</mandatory>\r\n"
							+ "		<desc>" + testName + "</desc>\r\n" + "	</cases>");
			writer.flush();
			writer.close();
		}
	}

	public static String currentTest() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	public static void removeLineFromFile(String file, String lineToRemove) {

		try {

			File inFile = new File(file);

			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
				return;
			}

			// Construct the new file that will later be renamed to the original filename.
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			String line = null;

			// Read from the original file and write to the new
			// unless content matches data to be removed.
			while ((line = br.readLine()) != null) {

				if (!line.trim().startsWith(lineToRemove)) {

					pw.println(line);
					pw.flush();
				}

			}
			pw.close();
			br.close();

			// Delete the original file
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}

			// Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(inFile))
				System.out.println("Could not rename file");

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
