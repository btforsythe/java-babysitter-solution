package io.btforsythe.katas.babysitter;

public class BabysitterPaymentCalculator {

	public static final int BEFORE_BEDTIME_RATE = 12;
	public static final int AFTER_BEDTIME_RATE = 8;
	
	private Time bedTime;

	public BabysitterPaymentCalculator(Time bedTime) {
		this.bedTime = bedTime;
	}

	public int calculatePayment(Time startTime, Time endTime) {
		return startTime.isOnOrBefore(bedTime)? 24: 16;
	}

}
