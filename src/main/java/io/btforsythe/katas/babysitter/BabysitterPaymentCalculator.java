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
		int rate = startTime.isOnOrAfter(MIDNIGHT) ? AFTER_MIDNIGHT_RATE
				: startTime.isOnOrAfter(bedTime) ? AFTER_BEDTIME_RATE : BEFORE_BEDTIME_RATE;
		return 2 * rate;
	}
}
