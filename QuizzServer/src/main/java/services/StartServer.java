package services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.HostExamDAO;
import data.StudentDAO;
import data.SubmissionDAO;
import model.HostExam;
import model.Student;
import model.Submission;
import utils.Notification;

public class StartServer {

    private HashMap<Socket, String> clients;
    private ServerSocket serverSocket;
    private final int port;

    public StartServer(HostExam hostExam, int port) throws IOException {
        this.port = port;
        this.clients = new HashMap<Socket, String>();
        try {
            this.serverSocket = new ServerSocket(port);
            new Thread() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            new ThreadServer(serverSocket.accept(), clients, hostExam).start();
                        }
                    } catch (SocketException e) {
                        if (serverSocket.isClosed())
                            System.out.println("Connection Closed.");
                    } catch (IOException e) {
                        System.err.println("Accept failed.");
                    }
                }
            }.start();
        } catch (IOException e) {
            throw new IOException("Could not listen on port: " + port);
        }
    }

    public void shutdownServer() throws IOException {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new IOException("Could not close port: " + port);
        }
    }

    public static String getIPAddress() {
        try (final DatagramSocket datagramSocket = new DatagramSocket()) {
            datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
            return datagramSocket.getLocalAddress().getHostAddress();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getConnectedClients() {
        // System.out.println(clients.values());
        return new ArrayList<String>(clients.values());
    }

}

class ThreadServer extends Thread {

    private Socket socket;
    private HashMap<Socket, String> clients;
    private HostExam HostExam;

    public ThreadServer(Socket socket, HashMap<Socket, String> clients, HostExam hostExam) {
        this.socket = socket;
        this.clients = clients;
        this.HostExam = hostExam;
    }

    @Override
    public void run() {
        String studentInfo = null;

        Submission studentSubmission = null;

        try (
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
            while (true) {
                Object dataReceived = inputStream.readObject();

                System.out.println(dataReceived);

                if (dataReceived instanceof String) {

                    studentInfo = (String) dataReceived;

                    Student student = new StudentDAO().getByStudentIdfromGroup(studentInfo, HostExam.getGroupId());

                    String studentId = "";

                    String studentName = "";

                    if (student != null) {

                        studentId = student.getStudentId();
                        studentName = student.getFirstName() + " " + student.getLastName();
                    }
                    System.out.println("Student not found");

                    int timeTaken = 0;

                    float score = 0;

                    if (studentSubmission != null) {

                        timeTaken = studentSubmission.getTimeTaken();

                        score = studentSubmission.getScore();
                    }

                    clients.put(socket,
                            studentId + "-" + studentName + "-" + String.valueOf(timeTaken) + "-"
                                    + String.valueOf(score));

                    outputStream.writeObject(HostExam);

                } else {

                    studentSubmission = (Submission) dataReceived;

                    throw new SocketException();
                }
            }
        } catch (SocketException e) {

            int studentId_int = studentSubmission.getStudentId();

            String studentID_String = "";

            if (studentId_int > 99) {
                studentID_String = "ST" + String.valueOf(studentId_int);
            } else {
                studentID_String = "ST"
                        + (studentId_int >= 10 ? "0" + String.valueOf(studentId_int)
                                : "00" + String.valueOf(studentId_int));
            }

            int group_ID = HostExam.getGroupId();

            Student student_Student = new StudentDAO().getByStudentIdfromGroup(studentID_String, group_ID);

            studentSubmission.setStudentId(student_Student.getUid());

            int idSubmiss = new SubmissionDAO().getAll().size() + 1;

            studentSubmission.setSubmissionId(idSubmiss);

            boolean isSubmit = new SubmissionDAO().create(studentSubmission);
            if (isSubmit) {
                System.out.println("Submission success");
            }

            String studentId = "";

            String studentName = "";

            if (student_Student != null) {

                studentId = student_Student.getStudentId();

                studentName = student_Student.getFirstName() + " " + student_Student.getLastName();
            }

            int timeTaken = studentSubmission.getTimeTaken();

            float score = studentSubmission.getScore();

            clients.put(socket, studentId + "-" + studentName + "-" + timeTaken + "-" + score);

            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}