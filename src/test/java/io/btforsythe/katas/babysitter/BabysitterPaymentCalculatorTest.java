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
	public void shouldPayBabysitterForASessionEndingBeforeBedtime() {
		
		int payment = underTest.calculatePayment("6:00 PM", "8:00 PM");
		
		assertThat(payment, is(2*BEFORE_BEDTIME_RATE));
	}

	@Test
	public void shouldPayBabysitterForASessionBetweenBedtimeAndMidnight() {
		
		int payment = underTest.calculatePayment("10:00 PM", "12:00 AM");
		
		assertThat(payment, is(2*AFTER_BEDTIME_RATE));
	}
	
	@Test
	public void shouldPayBabysitterForASessionAfterMidnight() {
		
		int payment = underTest.calculatePayment("12:00 AM", "2:00 AM");
		
		assertThat(payment, is(2*AFTER_MIDNIGHT_RATE));
	}
	
	@Test
	public void shouldPayBabysitterForASessionStartingBeforeBedtimeAndEndingBetweenBedtimeAndMidnight() {
		
		int payment = underTest.calculatePayment("9:00 PM", "10:00 PM");
		
		assertThat(payment, is(BEFORE_BEDTIME_RATE + AFTER_BEDTIME_RATE));
	}
	
	@Test
	public void shouldPayBabysitterForASessionStartingAfterBedtimeAndEndingAfterMidnight() {
		
		int payment = underTest.calculatePayment("11:30 PM", "12:30 AM");
		
		assertThat(payment, is(AFTER_BEDTIME_RATE + AFTER_MIDNIGHT_RATE));
	}
	
	@Test
	public void shouldPayBabysitterForASessionStartingBeforeBedtimeAndEndingAfterMidnight() {
		
		int payment = underTest.calculatePayment("8:30 PM", "12:30 AM");
		
		assertThat(payment, is(BEFORE_BEDTIME_RATE + 3*AFTER_BEDTIME_RATE + AFTER_MIDNIGHT_RATE));
	}
}
