package io.btforsythe.katas.babysitter;

import static io.btforsythe.katas.babysitter.BabysitterPaymentCalculator.AFTER_BEDTIME_RATE;
import static io.btforsythe.katas.babysitter.BabysitterPaymentCalculator.AFTER_MIDNIGHT_RATE;
import static io.btforsythe.katas.babysitter.BabysitterPaymentCalculator.BEFORE_BEDTIME_RATE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BabysitterPaymentCalculatorTest {

	public BabysitterPaymentCalculator underTest;

	@Test
	public void shouldPayBabysitterForASessionEndingBeforeBedtime() {

		initUnderTest("6:00 PM", "8:00 PM");
		
		int payment = underTest.calculatePayment();

		assertThat(payment, is(2 * BEFORE_BEDTIME_RATE));
	}

	private void initUnderTest(String startTime, String endTime) {
		underTest = new BabysitterPaymentCalculator(startTime, endTime, "9:30 PM");
	}

	@Test
	public void shouldPayBabysitterForASessionBetweenBedtimeAndMidnight() {

		initUnderTest("10:00 PM", "12:00 AM");
		
		int payment = underTest.calculatePayment();

		assertThat(payment, is(2 * AFTER_BEDTIME_RATE));
	}

	@Test
	public void shouldPayBabysitterForASessionAfterMidnight() {

		initUnderTest("12:00 AM", "2:00 AM");
		
		int payment = underTest.calculatePayment();

		assertThat(payment, is(2 * AFTER_MIDNIGHT_RATE));
	}

	@Test
	public void shouldPayBabysitterForASessionStartingBeforeBedtimeAndEndingBetweenBedtimeAndMidnight() {

		initUnderTest("9:00 PM", "10:00 PM");
		
		int payment = underTest.calculatePayment();

		assertThat(payment, is(BEFORE_BEDTIME_RATE + AFTER_BEDTIME_RATE));
	}

	@Test
	public void shouldPayBabysitterForASessionStartingAfterBedtimeAndEndingAfterMidnight() {

		initUnderTest("11:30 PM", "12:30 AM");
		
		int payment = underTest.calculatePayment();

		assertThat(payment, is(AFTER_BEDTIME_RATE + AFTER_MIDNIGHT_RATE));
	}

	@Test
	public void shouldPayBabysitterForASessionStartingBeforeBedtimeAndEndingAfterMidnight() {

		initUnderTest("8:30 PM", "12:30 AM");
		
		int payment = underTest.calculatePayment();

		assertThat(payment, is(BEFORE_BEDTIME_RATE + 3 * AFTER_BEDTIME_RATE + AFTER_MIDNIGHT_RATE));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotAllowAStartTimeBefore5Pm() {
		
		initUnderTest("4:59 PM", "6:00 PM");
	}
	
	@Test
	public void shouldAllowStartTimeAtExactly5Pm() {
		
		initUnderTest("5:00 PM", "6:00 PM");
	}
}
