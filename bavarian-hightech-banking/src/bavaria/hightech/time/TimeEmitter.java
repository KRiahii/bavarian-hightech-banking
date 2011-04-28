package bavaria.hightech.time;

import java.util.TimerTask;
import java.util.Timer;
import java.util.Calendar;
import java.text.DateFormat;

public final class TimeEmitter {

	private static Calendar calender;
	private static TimeEmitter timeEmitter;

	private TimeEmitter() {
		calender = Calendar.getInstance();
		new Quarz().startTiming();
		Clock clock = new Clock();
		clock.start();
	}

	public static TimeEmitter getTimeEmitter() {
		if (timeEmitter == null) {
			timeEmitter = new TimeEmitter();
		}
		return timeEmitter;
	}

	public static Calendar getCalender() {
		return (Calendar) calender.clone();
	}

	public class Quarz {
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT);

		private final void timewarper(final int value) {
			calender.add(Calendar.MINUTE, value);
			// System.out.println("Jetzt: " + df.format(c.getTime()) + " " +
			// tf.format(c.getTime()));
		}

		public final void startTiming() {
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					Quarz.this.timewarper(1000);
				}
			}, 0, 10);
		}
	}
}
