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
	
	private String asString;
	private Matcher matcher;

	public Time(String asString) {
		this.asString = asString;
		matcher = timePattern.matcher(asString);
		matcher.matches();
	}

	public int minutesSinceEarliest() {
		return minutesSincePreviousMidnight() - EARLIEST_START_TIME.minutesSincePreviousMidnight();
	}

	protected int minutesSincePreviousMidnight() {
		return ((isOneAmOrAfter()? hour() + 12: hour()) - EARLIEST_START_TIME.hour())*MIN_PER_HOUR + minutes();
	}

	private boolean isOneAmOrAfter() {
		return isAm() && !isMidnightHour();
	}

	private boolean isMidnightHour() {
		return hour() == 12 && isAm();
	}

	private int hour() {
		return parseInt(matcher.group(1));
	}

	private int minutes() {
		return parseInt(matcher.group(2));
	}

	private boolean isAm() {
		return matcher.group(3).equals("AM");
	}

	int payableHoursUntil(Time endTime) {
		return max(0, (endTime.minutesSinceEarliest() - minutesSinceEarliest() + (Time.MIN_PER_HOUR - 1))/Time.MIN_PER_HOUR);
	}
	
	public boolean isOnOrBefore(Time other) {
		return minutesSinceEarliest() <= other.minutesSinceEarliest();
	}

	public boolean isOnOrAfter(Time other) {
		return minutesSinceEarliest() >= other.minutesSinceEarliest();
	}

	@Override
	public String toString() {
		return asString;
	}
}
