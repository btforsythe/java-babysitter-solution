package io.btforsythe.katas.babysitter;

import static io.btforsythe.katas.babysitter.BabysitterPaymentCalculator.AFTER_BEDTIME_RATE;
import static io.btforsythe.katas.babysitter.BabysitterPaymentCalculator.BEFORE_BEDTIME_RATE;
import static io.btforsythe.katas.babysitter.Time.MIDNIGHT;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BabysitterPaymentCalculatorTest {

	private Time bedtime = new Time("9:30 PM");
	
	@Test
	public void shouldPayBabysitterForASessionEndingBeforeBedTime() {
		BabysitterPaymentCalculator underTest = new BabysitterPaymentCalculator(bedtime);
		
		Time startTime = new Time("6:00 PM");
		Time endTime = new Time("8:00 PM");
		
		int payment = underTest.calculatePayment(startTime, endTime);
		
		assertThat(payment, is(2*BEFORE_BEDTIME_RATE));
	}
	
	@Test
	public void shouldPayBabysitterForASessionBetweenBedTimeAndMidnight() {
		BabysitterPaymentCalculator underTest = new BabysitterPaymentCalculator(bedtime);
		
		Time startTime = new Time("10:00 PM");
		Time endTime = MIDNIGHT;
		
		int payment = underTest.calculatePayment(startTime, endTime);
		
		assertThat(payment, is(2*AFTER_BEDTIME_RATE));
	}
}
