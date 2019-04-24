

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

	public static Timer timer = new Timer();
	static Scanner scan = new Scanner(System.in);
	static Clock clock;

	public static void main(String[] args) {
		// print out instructions
		printInstructions();
		// Set time
		String chosenTime = scan.next();
		clock = new Clock(chosenTime);
		// Create the timer (to refresh everything every second
		TimerTask task = new TimerTask() {
			public void run() {
				clearConsole();
				writeTime();
				clock.time = clock.time.plusSeconds(1);
			}
		};

		timer.schedule(task, 0L, 1000L);
	}

	private static void printInstructions() {
		System.out.println("please pick a time");
		System.out.println("U can do this by typing it in the following formats HH:mm or HH:mm:ss");
		System.out.println("U can also type 'local' to pick your local time on your machine");
	}

	public static void writeTime() {

		System.out.println("====== Digital Clock ======");
		System.out.println(clock.writeTimeDigital());
		System.out.println("==========================");
		System.out.println();
		System.out.println("====== Berlin Clock ======");
		System.out.println(clock.writeFullBerlin());
		System.out.println("==========================");

	}

	public static void clearConsole() {
		for (int i = 0; i < 50; ++i) System.out.println();
	}
}
