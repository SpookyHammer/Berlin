
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	LocalDateTime time;

	// Create Constructors ("hh:mm", "hh:mm:ss", "local");
	public Clock(String time) {

		// set the chosen time to your machines local time if local was given
		if (time.toLowerCase().equals("local")) {
			this.time = LocalDateTime.now();
			return;
		}

		String[] times = time.split(":");

		// set the chosen time to your specified time
		switch (times.length) {
		case (2):
			this.time = LocalDateTime.now();
			this.time = this.time.withHour(Integer.parseInt(times[0]));
			this.time = this.time.withMinute(Integer.parseInt(times[1]));
			this.time = this.time.withSecond(0);
			break;
		case (3):
			this.time = LocalDateTime.now();
			this.time = this.time.withHour(Integer.parseInt(times[0]));
			this.time = this.time.withMinute(Integer.parseInt(times[1]));
			this.time = this.time.withSecond(Integer.parseInt(times[2]));
			break;
		default:
			throw new IllegalArgumentException("start parameter not correct");
		}
	}

	// Return the specified time in a digital format
	public String writeTimeDigital() {
		return formatter.format(time);
	}

	// Return O if the seconds are even or - if odds
	public String getSeconds() {
		if (time.getSecond() % 2 == 0) {
			return "O";
		} else {
			return "-";
		}
	}

	// Return the five hours lights as H's
	public String getHoursTop() {
		String s = "";

		int amount = time.getHour() / 5;

		for (int i = 0; i < amount; i++) {
			s += "H";
		}
		for (int i = 4 - amount; i > 0; i--) {
			s += "-";
		}

		return s;
	}

	// Return the single hours lights as h's
	public String getHoursLower() {
		String s = "";

		int amount = time.getHour() % 5;

		for (int i = 0; i < amount; i++) {
			s += "h";
		}
		for (int i = 4 - amount; i > 0; i--) {
			s += "-";
		}

		return s;
	}

	// Return the five minutes lights as M's
	// If it is the third in a row, write down R instead
	public String getMinutesTop() {
		String s = "";

		int amount = time.getMinute() / 5;

		for (int i = 1; i < amount + 1; i++) {
			if (i % 3 == 0) {
				s += "R";
			} else {
				s += "M";
			}
		}
		for (int i = 11 - amount; i > 0; i--) {
			s += "-";
		}

		return s;
	}

	// Return the single minutes lights as m's
	public String getMinutesLower() {
		String s = "";

		int amount = time.getMinute() % 5;

		for (int i = 0; i < amount; i++) {
			s += "m";
		}
		for (int i = 4 - amount; i > 0; i--) {
			s += "-";
		}

		return s;
	}

	// Return the full lines for the berlin clock
	public String writeFullBerlin() {
		String full = "";
		full += getSeconds() + "\n";
		full += getHoursTop() + "\n";
		full += getHoursLower() + "\n";
		full += getMinutesTop() + "\n";
		full += getMinutesLower();
		return full;
	}
}
