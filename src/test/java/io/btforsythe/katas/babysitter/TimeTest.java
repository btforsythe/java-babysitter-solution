package io.btforsythe.katas.babysitter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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
}
