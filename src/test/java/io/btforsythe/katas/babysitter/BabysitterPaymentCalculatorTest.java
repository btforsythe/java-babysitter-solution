package io.btforsythe.katas.babysitter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BabysitterPaymentCalculatorTest {

	@Test
	public void shouldPayBabysitterForASessionEndingBeforeBedtime() {
		BabysitterPaymentCalculator underTest = new BabysitterPaymentCalculator();
		
		Time startTime = new Time("6:00 PM");
		Time endTime = new Time("8:00 PM");
		
		int payment = underTest.calculatePayment(startTime, endTime);
		
		assertThat(payment, is(2*BabysitterPaymentCalculator.BEFORE_BEDTIME_RATE));
	}
}
