package io.btforsythe.katas.babysitter;

public class Time {

	public static final Time EARLIEST_START_TIME = new Time("5:00 PM");

	private String asString;

	public Time(String asString) {
		this.asString = asString;
	}

	public int minutesSinceStart() {
		return hour()*60 + minutes() - (EARLIEST_START_TIME.hour()*60 + EARLIEST_START_TIME.minutes());
	}

	private int hour() {
		return Integer.parseInt(asString.split(":")[0]);
	}

	private int minutes() {
		return Integer.parseInt(asString.split(":")[1].split(" ")[0]);
	}

}
