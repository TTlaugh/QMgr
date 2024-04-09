package utils;

public class DateTime {

	private String day;
	private String month;
	private String year;
	private String hour;
	private String minute;

	public DateTime(String day, String month, String year, String hour, String minute) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minute = minute;
	}

	public DateTime(String datetime) {
		// dd-mm-yyyy hh:mm
		this.day = datetime.substring(0, 2);
		this.month = datetime.substring(3, 5);
		this.year = datetime.substring(6, 10);
		this.hour = datetime.substring(11, 13);
		this.minute = datetime.substring(14, 16);
	}

	public DateTime() {
		this.day = null;
		this.month = null;
		this.year = null;
		this.hour = null;
		this.minute = null;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	@Override
	public String toString() {
		return day + "/" + month + "/" + year + " " + hour + ":" + minute;
	}

}
