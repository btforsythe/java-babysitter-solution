package io.btforsythe.katas.babysitter;

import static io.btforsythe.katas.babysitter.Time.MIDNIGHT;
import static io.btforsythe.katas.babysitter.Time.MIN_PER_HOUR;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TimeTest {

	private static final Time FIVE_PM = new Time("5:00 PM");

	@Test
	public void shouldCalculateMinutesOnTheHour() {
		Time underTest = new Time("6:00 PM");
		
		assertThat(underTest.minutesSincePreviousNoon(), is(6*MIN_PER_HOUR));
	}
	
	@Test
	public void shouldBeTwelveHoursSincePreviousMidnight() {
		assertThat(MIDNIGHT.minutesSincePreviousNoon(), is(12*MIN_PER_HOUR));
	}
	
	@Test
	public void shouldIncludeMinutesPastTheHourInCalculation() {
		Time underTest = new Time("6:30 PM");
		
		assertThat(underTest.minutesSincePreviousNoon(), is(6*MIN_PER_HOUR + 30));
	}
	
	@Test
	public void shouldCalculateMinutesForAmTime() {
		Time underTest = new Time("1:30 AM");
		
		assertThat(underTest.minutesSincePreviousNoon(), is(13*MIN_PER_HOUR + 30));
	}
	
	@Test
	public void shouldCalculatePayableHoursOnTheHour() {
		Time start = FIVE_PM;
		Time end = new Time("8:00 PM");
		
		assertThat(start.payableHoursUntil(end), is(3));
	}
	
	@Test
	public void shouldRoundUpPayableHours() {
		Time start = FIVE_PM;
		Time end = new Time("8:30 PM");
		
		assertThat(start.payableHoursUntil(end), is(4));
	}
	
	@Test
	public void shouldReturnZeroPayableHoursIfEndIsBeforeStart() {
		Time start = new Time("8:30 PM");
		Time end = FIVE_PM;
		
		assertThat(start.payableHoursUntil(end), is(0));
	}
	
	@Test
	public void shouldCalculatePayableHoursFromMidnight() {
		Time start = new Time("12:00 AM");
		Time end = new Time("2:00 AM");
		
		assertThat(start.payableHoursUntil(end), is(2));
	}
	
	@Test
	public void shouldCalculatePayableHoursFromMidnightHour() {
		Time start = new Time("12:30 AM");
		Time end = new Time("2:00 AM");
		
		assertThat(start.payableHoursUntil(end), is(2));	
	}
	
	@Test
	public void shouldBeOnOrBeforeAnotherTimeForSameTime() {
		
		assertThat(MIDNIGHT.isOnOrBefore(MIDNIGHT), is(true));
	}
	
	@Test
	public void shouldNotBeOnOrBeforeForLaterTime() {
		assertThat(MIDNIGHT.isOnOrBefore(FIVE_PM), is(false));
	}
	
	@Test
	public void shouldBeAfterEarlierTime() {
		assertThat(MIDNIGHT.isOnOrAfter(FIVE_PM), is(true));
	}
	
	@Test
	public void shouldNotBeAfterLaterTime() {
		assertThat(FIVE_PM.isOnOrAfter(MIDNIGHT), is(false));
	}
	
	@Test
	public void shouldBeOnOrAfterItself() {
		assertThat(MIDNIGHT.isOnOrAfter(MIDNIGHT), is(true));
	}
}
