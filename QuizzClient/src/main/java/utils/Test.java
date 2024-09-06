package utils;

import java.util.ArrayList;
import java.util.List;

import business.model.Exam;
import business.model.SelectedQuestion;
import business.services.StartClient;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		StartClient client = new StartClient("S002", "Nguyen Van HEHE", "192.168.31.163", 2000);
		Exam exam = null;
		while ((exam = client.getExam())==null) {
			System.out.println("Waiting to receive exam...");
			Thread.sleep(5000);
		}
		System.out.println(exam);
		List<SelectedQuestion> list = new ArrayList<SelectedQuestion>();
		list.add(new SelectedQuestion(exam.getQuestions().get(0), new ArrayList<Integer>()));
		list.add(new SelectedQuestion(exam.getQuestions().get(1), new ArrayList<Integer>()));
		client.submit(exam.getExamID().toString(), "S002", list);
	}
}
