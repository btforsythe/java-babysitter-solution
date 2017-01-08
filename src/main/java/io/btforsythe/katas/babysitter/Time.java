package io.btforsythe.katas.babysitter;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time {

	private static final int MIN_PER_HOUR = 60;

	private static final Pattern timePattern = Pattern.compile("(\\d+):(\\d+) ([AP]M)");

	public static final Time EARLIEST_START_TIME = new Time("5:00 PM");
	public static final Time MIDNIGHT = new Time("12:00 AM") {
		/**
		 * Hacking midnight since it lies on the boundary.
		 */
		private final int totalMinutes = new Time("11:59 PM").totalMinutes() + 1;
		
		@Override
		protected int totalMinutes() {
			return totalMinutes;
		}
		
	};
	
	private String asString;
	private Matcher matcher;

	public Time(String asString) {
		this.asString = asString;
		matcher = timePattern.matcher(asString);
		matcher.matches();
	}

	public int minutesSinceEarliest() {
		return totalMinutes() + amOffset() - EARLIEST_START_TIME.totalMinutes();
	}

	protected int totalMinutes() {
		return hour()*MIN_PER_HOUR + minutes();
	}

	private int hour() {
		return parseInt(matcher.group(1));
	}

	private int minutes() {
		return parseInt(matcher.group(2));
	}

	private int amOffset() {
		return isAfterMidnight() ? MIDNIGHT.totalMinutes(): 0;
	}

	private boolean isAfterMidnight() {
		return isAm() && !isMidnight();
	}
	
	private boolean isAm() {
		return matcher.group(3).equals("AM");
	}

	private boolean isMidnight() {
		return asString.equals("12:00 AM");
	}

	int payableHoursUntil(Time endTime) {
		return max(0, (endTime.minutesSinceEarliest() - minutesSinceEarliest() + (Time.MIN_PER_HOUR - 1))/Time.MIN_PER_HOUR);
	}
	
	public boolean isOnOrBefore(Time other) {
		return totalMinutes() <= other.totalMinutes();
	}

	public boolean isOnOrAfter(Time other) {
		return totalMinutes() >= other.totalMinutes();
	}
}
