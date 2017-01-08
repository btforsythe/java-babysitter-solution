package io.btforsythe.katas.babysitter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.regex.Pattern;

import org.junit.Test;

public class TimeTest {

	@Test
	public void shouldBeZeroMinutesForEarliestStartTime() {
		Time underTest = Time.EARLIEST_START_TIME;
		
		assertThat(underTest.minutesSinceStart(), is(0));
	}
	
	@Test
	public void shouldCalculateMinutesOnTheHour() {
		Time underTest = new Time("6:00 PM");
		
		assertThat(underTest.minutesSinceStart(), is(60));
	}
	
	@Test
	public void shouldIncludeMinutesPastTheHourInCalculation() {
		Time underTest = new Time("6:30 PM");
		
		assertThat(underTest.minutesSinceStart(), is(90));
	}
	
	@Test
	public void shouldCalculateMinutesForAmTime() {
		Time underTest = new Time("1:30 AM");
		
		assertThat(underTest.minutesSinceStart(), is(510));
	}
}
