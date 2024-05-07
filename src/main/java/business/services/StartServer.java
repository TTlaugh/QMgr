package business.services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.model.Exam;
import business.model.Submission;
import data.SubmissionAccess;
import utils.SQLUtils;

public class StartServer {
	
	private HashMap<Socket, String> clients;
	private ServerSocket serverSocket;
	private final int port;

	public StartServer(Exam exam, int port) throws IOException {
		this.port = port;
		this.clients = new HashMap<Socket, String>();
		try {
			this.serverSocket = new ServerSocket(port);
			new Thread() {
				@Override public void run() {
					try {
						while (true) {
							new ThreadServer(serverSocket.accept(), clients, exam).start();
						}
					} catch (SocketException e) {
						if(serverSocket.isClosed())
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
		return new ArrayList<String>(clients.values());
	}

}

class ThreadServer extends Thread {

	private Socket socket;
	private HashMap<Socket, String> clients;
	private Exam exam;

	public ThreadServer(Socket socket, HashMap<Socket, String> clients, Exam exam) {
		this.socket = socket;
		this.clients = clients;
		this.exam = exam;
	}

	@Override
	public void run() {
		String studentInfo = null;
		Submission studentSubmission = null;

		try (
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())
		) {
			while (true) {
				Object dataReceived = inputStream.readObject();
				System.out.println(dataReceived);
				if (dataReceived instanceof String) {
					studentInfo = (String) dataReceived;
					clients.put(socket, studentInfo);
					outputStream.writeObject(exam);
				} else {
					studentSubmission = (Submission) dataReceived;
					throw new SocketException();
				}
			}
		} catch (SocketException e) {
			try { new SubmissionAccess().insert(studentSubmission);
			} catch (SQLException sql_ex) { SQLUtils.printSQLException(sql_ex); }

			clients.put(socket, studentInfo + " [Score: "+studentSubmission.getScore()+"]");

			try { socket.close();
			} catch (IOException e1) { e1.printStackTrace(); }
		} catch (Exception e) { e.printStackTrace(); }
	}
}