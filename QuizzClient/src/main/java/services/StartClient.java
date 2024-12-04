package services;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Answer;
import model.Answer_Select;
import model.HostExam;
import model.Question;
import model.Submission;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StartClient {

    private Data submission = new Data();

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
        HostExam hostExam_Test = this.hostExam;

        return hostExam_Test;
    }

    public HostExam HostExamCorrect() {
        return hostExam;
    }

    public double submit(ArrayList<Question> questionSelecteds, List<Answer_Select> answer_selects, Date start) {

        for (int i = 0; i < questionSelecteds.size(); i++) {
            System.out.println(questionSelecteds.get(i).getContent());
            for (int j = 0; j < questionSelecteds.get(i).getAnswers().size(); j++) {
                System.out.println(questionSelecteds.get(i).getAnswers().get(j).isCorrect());
            }
        }

        long timeTaken = 0;

        float score = 0;

        Date end = new Date(System.currentTimeMillis());
        timeTaken = end.getTime() - start.getTime();
        timeTaken /= 1000; // convert from mili sec to sec
        timeTaken /= 60; // convert from sec to min

        double scorePerQuestion = hostExam.getMaxScore() / (hostExam.getExamQuestions().size() * 1.0);

        if (timeTaken <= hostExam.getTimeLimit()) {
            score = (float) scoreCalculator(questionSelecteds, answer_selects,
                    scorePerQuestion);
        }

        int studentID_Sub = Integer.parseInt(this.studentID.substring(2));

        Map<Integer, List<Integer>> map = convertListMap(questionSelecteds);

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        Submission submission = new Submission(0, hostExam.getHostExamId(), studentID_Sub, (int) timeTaken, score,
                map);

        this.submission.send(submission);

        return (float) (Math.round(score * 100.0) / 100.0);

    }

    private Map<Integer, List<Integer>> convertListMap(List<Question> list) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>(list.size());
        for (Question i : list) {
            int questionId = i.getQuestionId();
            ArrayList<Answer> selectedAnswer = i.getAnswers();
            List<Integer> answerChosen = new ArrayList<Integer>(selectedAnswer.size());
            for (Answer j : selectedAnswer) {
                answerChosen.add(j.isCorrect() ? 1 : 0);
            }
            map.put(questionId, answerChosen);

        }
        return map;
    }

    private double scoreCalculator(ArrayList<Question> questionSelecteds, List<Answer_Select> answer_selects,
            double scorePerQuestion) {

        int count_trueAnswers = 0;

        for (int i = 0; i < questionSelecteds.size(); i++) {

            ArrayList<Answer> answers = questionSelecteds.get(i).getAnswers();

            for (int j = 0; j < answers.size(); j++) {

                if (answers.get(j).isCorrect() != answer_selects.get(j).isChoice()) {
                    break;
                } else {
                    if (j == answers.size() - 1) {
                        count_trueAnswers++;
                        break;
                    }
                    continue;
                }

            }

        }

        return count_trueAnswers * scorePerQuestion * 1.0;
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