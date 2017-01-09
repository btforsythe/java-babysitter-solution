package io.btforsythe.katas.babysitter;

import static io.btforsythe.katas.babysitter.Time.MIDNIGHT;
import static io.btforsythe.katas.babysitter.Time.earlierOf;
import static io.btforsythe.katas.babysitter.Time.laterOf;

public class BabysitterPaymentCalculator {

	public static final int BEFORE_BEDTIME_RATE = 12;
	public static final int AFTER_BEDTIME_RATE = 8;
	public static final int AFTER_MIDNIGHT_RATE = 16;

	public static final Time EARLIEST_START_TIME = new Time("5:00 PM");
	
	private Time startTime;
	private Time endTime;
	private Time bedTime;

	public BabysitterPaymentCalculator(String startTime, String endTime, String bedTime) {
		this(new Time(startTime), new Time(endTime), new Time(bedTime));
	}
	
	public BabysitterPaymentCalculator(Time startTime, Time endTime, Time bedTime) {
		
		this.startTime = startTime;
		validateStartTime();
		
		this.endTime = endTime;
		
		this.bedTime = bedTime;
	}

	private void validateStartTime() {
		if(!startTime.isOnOrAfter(EARLIEST_START_TIME)) {
			throw new IllegalArgumentException();
		}
	}

	public int calculatePayment() {
		return beforeBedTimePay() + betweenBedTimeAndMidnightPay() + afterMidnightPay();
	}

	private int beforeBedTimePay() {
		return earlierOf(startTime, bedTime).payableHoursUntil(earlierOf(bedTime, endTime)) * BEFORE_BEDTIME_RATE;
	}

	private int betweenBedTimeAndMidnightPay() {
		return laterOf(startTime, bedTime).payableHoursUntil(earlierOf(endTime, MIDNIGHT)) * AFTER_BEDTIME_RATE;
	}

	private int afterMidnightPay() {
		return laterOf(startTime, MIDNIGHT).payableHoursUntil(endTime) * AFTER_MIDNIGHT_RATE;
	}
}
