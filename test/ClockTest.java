import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import org.junit.*;

public class ClockTest {

	@Test
	public void testLocalClock() {
		Clock clock = new Clock("local");
		LocalDateTime now = LocalDateTime.now();

		assertEquals(clock.time.getHour(), now.getHour());
		assertEquals(clock.time.getMinute(), now.getMinute());
		assertEquals(clock.time.getSecond(), now.getSecond());
	}

	@Test
	public void testMinValidClock() {
		Clock clock = new Clock("00:00:00");

		assertEquals(clock.time.getHour(), 0);
		assertEquals(clock.time.getMinute(), 0);
		assertEquals(clock.time.getSecond(), 0);
	}

	@Test
	public void testMaxValidClock() {
		Clock clock = new Clock("23:59:59");

		assertEquals(clock.time.getHour(), 23);
		assertEquals(clock.time.getMinute(), 59);
		assertEquals(clock.time.getSecond(), 59);
	}

	@Test
	public void testMinHourWriten() {
		Clock clock = new Clock("00:00:00");

		assertEquals(clock.getSeconds(), "O");
		assertEquals(clock.getHoursTop(), "----");
		assertEquals(clock.getHoursLower(), "----");
		assertEquals(clock.getMinutesTop(), "-----------");
		assertEquals(clock.getMinutesLower(), "----");
	}

	@Test
	public void testMaxHourWriten() {
		Clock clock = new Clock("23:59:59");

		assertEquals(clock.getSeconds(), "-");
		assertEquals(clock.getHoursTop(), "HHHH");
		assertEquals(clock.getHoursLower(), "hhh-");
		assertEquals(clock.getMinutesTop(), "MMRMMRMMRMM");
		assertEquals(clock.getMinutesLower(), "mmmm");
	}

	@Test(expected = DateTimeException.class)
	public void testOverMaxClock() {
		Clock clock = new Clock("25:25:25");

	}
	
	@Test(expected = DateTimeException.class)
	public void testUnderMinClock() {
		Clock clock = new Clock("-5:00:00");

	}
	
	@Test
	public void testFullBerlinClock() {
		Clock clock = new Clock("19:25:35");

		String expected = "";
		expected += "-" + "\n";
		expected += "HHH-" + "\n";
		expected += "hhhh" + "\n";
		expected += "MMRMM------" + "\n";
		expected += "----";
		
		assertEquals(clock.writeFullBerlin(), expected);

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void illigalArgumentThrownTest() {
		Clock clock = new Clock("wrong text");
	}
}
