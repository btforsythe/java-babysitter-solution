package io.btforsythe.katas.babysitter;

public class Time {

	public static final Time EARLIEST_START_TIME = new Time("5:00 PM");
	
	private String asString;

	public Time(String asString) {
		this.asString = asString;
	}

	public int minutesSinceStart() {
		return (hour() - EARLIEST_START_TIME.hour())*60;
	}

	private int hour() {
		return Integer.parseInt(asString.split(":")[0]);
	}

}
