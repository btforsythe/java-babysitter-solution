package io.btforsythe.katas.babysitter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TimeTest {

	@Test
	public void shouldBeZeroMinutesForEarliestStartTime() {
		Time underTest = Time.EARLIEST_START_TIME;
		
		assertThat(underTest.minutes(), is(0));
	}
}
