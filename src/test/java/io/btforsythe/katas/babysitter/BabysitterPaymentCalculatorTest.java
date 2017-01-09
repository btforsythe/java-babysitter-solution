package io.btforsythe.katas.babysitter;

import static io.btforsythe.katas.babysitter.BabysitterPaymentCalculator.AFTER_BEDTIME_RATE;
import static io.btforsythe.katas.babysitter.BabysitterPaymentCalculator.AFTER_MIDNIGHT_RATE;
import static io.btforsythe.katas.babysitter.BabysitterPaymentCalculator.BEFORE_BEDTIME_RATE;
import static io.btforsythe.katas.babysitter.Time.MIDNIGHT;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BabysitterPaymentCalculatorTest {

	public BabysitterPaymentCalculator underTest;

	@Test
	public void shouldPayBabysitterForASessionEndingBeforeBedtime() {

		underTest = new BabysitterPaymentCalculator("6:00 PM", "8:00 PM", "9:30 PM");
		int payment = underTest.calculatePayment();

		assertThat(payment, is(2 * BEFORE_BEDTIME_RATE));
	}

	@Test
	public void shouldPayBabysitterForASessionBetweenBedtimeAndMidnight() {

		underTest = new BabysitterPaymentCalculator("10:00 PM", "12:00 AM", "9:30 PM");
		int payment = underTest.calculatePayment();

		assertThat(payment, is(2 * AFTER_BEDTIME_RATE));
	}

	@Test
	public void shouldPayBabysitterForASessionAfterMidnight() {

		underTest = new BabysitterPaymentCalculator("12:00 AM", "2:00 AM", "9:30 PM");
		int payment = underTest.calculatePayment();

		assertThat(payment, is(2 * AFTER_MIDNIGHT_RATE));
	}

	@Test
	public void shouldPayBabysitterForASessionStartingBeforeBedtimeAndEndingBetweenBedtimeAndMidnight() {

		underTest = new BabysitterPaymentCalculator("9:00 PM", "10:00 PM", "9:30 PM");
		int payment = underTest.calculatePayment();

		assertThat(payment, is(BEFORE_BEDTIME_RATE + AFTER_BEDTIME_RATE));
	}

	@Test
	public void shouldPayBabysitterForASessionStartingAfterBedtimeAndEndingAfterMidnight() {

		underTest = new BabysitterPaymentCalculator("11:30 PM", "12:30 AM", "9:30 PM");
		int payment = underTest.calculatePayment();

		assertThat(payment, is(AFTER_BEDTIME_RATE + AFTER_MIDNIGHT_RATE));
	}

	@Test
	public void shouldPayBabysitterForASessionStartingBeforeBedtimeAndEndingAfterMidnight() {

		underTest = new BabysitterPaymentCalculator("8:30 PM", "12:30 AM", "9:30 PM");
		int payment = underTest.calculatePayment();

		assertThat(payment, is(BEFORE_BEDTIME_RATE + 3 * AFTER_BEDTIME_RATE + AFTER_MIDNIGHT_RATE));
	}
}
