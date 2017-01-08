package io.btforsythe.katas.babysitter;

public class Time {

	public static final Time EARLIEST_START_TIME = new Time("5:00 PM");
	
	private String asString;

	public Time(String asString) {
		this.asString = asString;
	}

	public int minutes() {
		int referenceHour = Integer.parseInt(EARLIEST_START_TIME.asString.split(":")[0]);
		return (Integer.parseInt(asString.split(":")[0]) - referenceHour)*60;
	}

}
