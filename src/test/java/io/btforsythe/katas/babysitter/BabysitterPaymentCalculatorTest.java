package io.btforsythe.katas.babysitter;

import static io.btforsythe.katas.babysitter.BabysitterPaymentCalculator.AFTER_BEDTIME_RATE;
import static io.btforsythe.katas.babysitter.BabysitterPaymentCalculator.AFTER_MIDNIGHT_RATE;
import static io.btforsythe.katas.babysitter.BabysitterPaymentCalculator.BEFORE_BEDTIME_RATE;
import static io.btforsythe.katas.babysitter.Time.MIDNIGHT;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BabysitterPaymentCalculatorTest {

	private Time bedtime = new Time("9:30 PM");
	
	public BabysitterPaymentCalculator underTest = new BabysitterPaymentCalculator(bedtime);
	
	@Test
	public void shouldPayBabysitterForASessionEndingBeforeBedTime() {
		
		int payment = underTest.calculatePayment("6:00 PM", "8:00 PM");
		
		assertThat(payment, is(2*BEFORE_BEDTIME_RATE));
	}

	@Test
	public void shouldPayBabysitterForASessionBetweenBedTimeAndMidnight() {
		
		int payment = underTest.calculatePayment("10:00 PM", "12:00 AM");
		
		assertThat(payment, is(2*AFTER_BEDTIME_RATE));
	}
	
	@Test
	public void shouldPayBabysitterForASessionAfterMidnight() {
		
		int payment = underTest.calculatePayment("12:00 AM", "2:00 AM");
		
		assertThat(payment, is(2*AFTER_MIDNIGHT_RATE));
	}
	
	@Test
	public void shouldPayBabysitterForASessionStartingBeforeBedTimeAndEndingBetweenBedTimeAndMidnight() {
		
		int payment = underTest.calculatePayment("9:00 PM", "10:00 PM");
		
		assertThat(payment, is(BEFORE_BEDTIME_RATE + AFTER_BEDTIME_RATE));
	}
}
