package utils;

import java.io.Serializable;

public class DateTime implements Serializable {

	private static final long serialVersionUID = 1L;
	private String year;
	private String month;
	private String day;
	private String hour;
	private String minute;
	private String second;

	public DateTime(String year, String month, String day, String hour, String minute, String second) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public DateTime(String year, String month, String day, String hour, String minute) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = "00";
	}

	public DateTime(String datetime) {
		// yyyy-mm-dd hh:mm:ss
		this.year = datetime.substring(0, 4);
		this.month = datetime.substring(5, 7);
		this.day = datetime.substring(8, 10);
		this.hour = datetime.substring(11, 13);
		this.minute = datetime.substring(14, 16);
		try {
			this.second = datetime.substring(17, 19);
		} catch (IndexOutOfBoundsException e) {
			this.second = "00";
		}
	}

	public DateTime() {
		this.year = null;
		this.month = null;
		this.day = null;
		this.hour = null;
		this.minute = null;
		this.second = null;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
	}
}
