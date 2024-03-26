package fxml;


public class Test {

	private String id;
	private String subject;
	private String date;
	private int time;
	private String name;
	
	public Test(String id, String subject, String date, int time, String name) {
		this.id = id;
		this.subject = subject;
		this.date = date;
		this.time = time;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}