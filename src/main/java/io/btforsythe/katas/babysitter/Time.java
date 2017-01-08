package io.btforsythe.katas.babysitter;

import static java.lang.Integer.parseInt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time {

	private static final int MIN_PER_HOUR = 60;

	private static final Pattern timePattern = Pattern.compile("(\\d+):(\\d+) PM");

	public static final Time EARLIEST_START_TIME = new Time("5:00 PM");

	private Matcher matcher;

	public Time(String asString) {
		matcher = timePattern.matcher(asString);
		matcher.matches();
	}

	public int minutesSinceStart() {
		return totalMinutes() - EARLIEST_START_TIME.totalMinutes();
	}

	private int totalMinutes() {
		return hour()*MIN_PER_HOUR + minutes();
	}

	private int hour() {
		return parseInt(matcher.group(1));
	}

	private int minutes() {
		return parseInt(matcher.group(2));
	}

}
