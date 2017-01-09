package io.btforsythe.katas.babysitter;

import static io.btforsythe.katas.babysitter.Time.MIDNIGHT;

public class BabysitterPaymentCalculator {

	public static final int BEFORE_BEDTIME_RATE = 12;
	public static final int AFTER_BEDTIME_RATE = 8;
	public static final int AFTER_MIDNIGHT_RATE = 16;

	private Time bedTime;

	public BabysitterPaymentCalculator(Time bedTime) {
		this.bedTime = bedTime;
	}

	public int calculatePayment(String start, String end) {
		return calculatePayment(new Time(start), new Time(end));
	}

	public int calculatePayment(Time startTime, Time endTime) {
		return beforeBedTimePay(startTime, endTime) + betweenBedTimeAndMidnightPay(startTime, endTime) + afterMidnightPay(startTime, endTime);
	}

	private int beforeBedTimePay(Time startTime, Time endTime) {
		return earlierOf(startTime, bedTime).payableHoursUntil(earlierOf(bedTime, endTime)) * BEFORE_BEDTIME_RATE;
	}

	private int betweenBedTimeAndMidnightPay(Time startTime, Time endTime) {
		return laterOf(startTime, bedTime).payableHoursUntil(earlierOf(endTime, MIDNIGHT)) * AFTER_BEDTIME_RATE;
	}

	private int afterMidnightPay(Time startTime, Time endTime) {
		return laterOf(startTime, MIDNIGHT).payableHoursUntil(endTime) * AFTER_MIDNIGHT_RATE;
	}

	private Time earlierOf(Time time1, Time time2) {
		return time1.isOnOrBefore(time2)? time1: time2;
	}

	private Time laterOf(Time time1, Time time2) {
		return time1.isOnOrAfter(time2)? time1: time2;
	}
}
