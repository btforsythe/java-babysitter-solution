package io.btforsythe.katas.babysitter;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time {

	private static final int MIN_PER_HOUR = 60;

	private static final Pattern timePattern = Pattern.compile("(\\d+):(\\d+) ([AP]M)");

	public static final Time EARLIEST_START_TIME = new Time("5:00 PM");
	public static final Time MIDNIGHT = new Time("12:00 AM");
	
	private Matcher matcher;

	public Time(String asString) {
		matcher = timePattern.matcher(asString);
		matcher.matches();
	}

	public int minutesSinceStart() {
		return totalMinutes() - EARLIEST_START_TIME.totalMinutes() + amOffset();
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

	private int amOffset() {
		return isAm()? MIDNIGHT.totalMinutes(): 0;
	}

	private boolean isAm() {
		return matcher.group(3).equals("AM");
	}

	public int payableHoursSince(Time start) {
		return max(0, (totalMinutes() - start.totalMinutes() + (MIN_PER_HOUR - 1))/MIN_PER_HOUR);
	}

	public boolean isOnOrBefore(Time other) {
		return totalMinutes() <= other.totalMinutes();
	}

	public boolean isOnOrAfter(Time other) {
		return totalMinutes() >= other.totalMinutes();
	}
}
