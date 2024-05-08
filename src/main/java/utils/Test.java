package utils;

import java.util.Scanner;

import business.model.Exam;
import business.services.StartServer;
import data.ExamAccess;

public class Test {
	public static void main(String[] args) throws Exception {
		testDataSource();
	}
	private static void testDataSource() throws Exception {
		Exam exam = new ExamAccess().get("2024-05-08 09:00:00");
		new ExamAccess().getQuestions(exam);
		System.out.println(exam);
		StartServer server = new StartServer(exam, 2000);
		try(Scanner sc = new Scanner(System.in)) {
			System.out.println("Input: ");
			sc.nextLine();
		}
		server.shutdownServer();
		System.out.println("Server already shutdown");
	}
}
