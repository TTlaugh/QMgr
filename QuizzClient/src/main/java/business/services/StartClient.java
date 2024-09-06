package business.services;

import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.model.Exam;
import business.model.SelectedQuestion;
import business.model.Submission;
import utils.JsonUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class StartClient {

	private Data submission = new Data();
	private Exam exam=null;

	public StartClient(String id, String fullName, String host, int port) {

		new Thread() {
			@Override public void run() {
				try (
					Socket socket = new Socket(host, port);
					ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())
				) {
					String clientInfo = id + " - " + fullName;
					outputStream.writeObject(clientInfo);

					exam = (Exam) inputStream.readObject();

					Submission receivedSubmission = submission.receive();
					outputStream.writeObject(receivedSubmission);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	public Exam getExam() {
		if (exam != null && exam.isShuffled())
			Collections.shuffle(exam.getQuestions());
		return exam;
	}
	
	public double submit(String examID, String studentID, List<SelectedQuestion> questionSelecteds) {
		long timeTaken = 0;
		SimpleDateFormat smpDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = null;
		try {
			start = smpDF.parse(exam.getExamID().toString());
		} catch (ParseException e) { e.printStackTrace(); }
		Date end = new Date(System.currentTimeMillis());
		timeTaken = end.getTime() - start.getTime();
		timeTaken /= 1000; // convert from mili sec to sec
		timeTaken /= 60; // convert from sec to min
		double score = 0;
		double scorePerQuestion = exam.getMaxScore() / exam.getQuestions().size();
		if (timeTaken <= exam.getTimeLimit())
			for (SelectedQuestion selectedQuestion : questionSelecteds)
				if (selectedQuestion.getCorrectAnswers().equals(selectedQuestion.getSelectedAnswers()))
					score += scorePerQuestion;
		Submission submission = new Submission(examID, studentID, (int)timeTaken, score,
				convertListToJsonMap(questionSelecteds));
		this.submission.send(submission);
		return score;
	}
	
	private String convertListToJsonMap(List<SelectedQuestion> list) {
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>(list.size());
		for (SelectedQuestion i : list) map.put(i.getQuestionID(), i.getSelectedAnswers());
		try {
			return JsonUtils.mapToJson(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

class Data {
	private Submission packet;
	private boolean transfer = true;

	public synchronized Submission receive() {
		while (transfer) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); 
				System.err.println("Thread Interrupted");
			}
		}
		transfer = true;
		Submission returnPacket = packet;
		notifyAll();
		return returnPacket;
	}

	public synchronized void send(Submission packet) {
		while (!transfer) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); 
				System.err.println("Thread Interrupted");
			}
		}
		transfer = false;
		this.packet = packet;
		notifyAll();
	}
}