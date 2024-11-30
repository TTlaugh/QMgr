package services;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Answer;
import model.HostExam;
import model.Question;
import model.Submission;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StartClient {

    private Data submission = new Data();
    // private Exam exam = null;

    private HostExam hostExam = null;

    private String studentID = null;

    public StartClient(String id, String host, int port) {

        new Thread() {
            @Override
            public void run() {
                try (
                        Socket socket = new Socket(host, port);

                        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {

                    String clientInfo = id;

                    studentID = id;

                    outputStream.writeObject(clientInfo);

                    hostExam = (HostExam) inputStream.readObject();

                    Submission receivedSubmission = submission.receive();

                    outputStream.writeObject(receivedSubmission);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public HostExam getHostExam() {
        if (hostExam != null && hostExam.isShuffle())
            Collections.shuffle(hostExam.getExamQuestions());
        return hostExam;
    }

    public double submit(ArrayList<Question> questionsIsCorrect, ArrayList<Question> questionSelecteds, Date start) {

        long timeTaken = 0;

        float score = 0;

        Date end = new Date(System.currentTimeMillis());
        timeTaken = end.getTime() - start.getTime();
        timeTaken /= 1000; // convert from mili sec to sec
        timeTaken /= 60; // convert from sec to min

        double scorePerQuestion = hostExam.getMaxScore() / hostExam.getExamQuestions().size();

        if (timeTaken <= hostExam.getTimeLimit()) {
            for (int i = 0; i < questionSelecteds.size(); i++) {
                Question questionSelected = questionSelecteds.get(i);
                Question questionIsCorrect = questionsIsCorrect.get(i);

                ArrayList<Answer> selectedAnswers = questionSelected.getAnswers();
                ArrayList<Answer> correctAnswers = questionIsCorrect.getAnswers();

                for (int j = 0; j < selectedAnswers.size(); j++) {
                    Answer selectedAnswer = selectedAnswers.get(j);
                    Answer correctAnswer = correctAnswers.get(j);
                    if (selectedAnswer.isCorrect() && correctAnswer.isCorrect()) {
                        if (selectedAnswer.getAnswerId() == correctAnswer.getAnswerId()) {
                            score += scorePerQuestion;
                        }
                    }
                }
            }
        }

        int studentID_Sub = Integer.parseInt(this.studentID.substring(2));

        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>(questionSelecteds.size());

        Submission submission = new Submission(0, hostExam.getHostExamId(), studentID_Sub, (int) timeTaken, score,
                map);

        this.submission.send(submission);

        return score;
    }

    // private String convertListToJsonMap(List<Question> list) {
    // Map<Integer, List<Integer>> map = new HashMap<Integer,
    // List<Integer>>(list.size());
    // for (Question i : list)
    // map.put(i.getQuestionId(),
    // i.getAnswers().stream().map(Answer::getAnswerId).collect(Collectors.toList()));
    // try {
    // return JsonUtils.mapToJson(map);
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // return null;
    // }
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