package bavaria.hightech.time;

import java.util.TimerTask;
import java.util.Timer;
import java.util.Calendar;
import java.text.DateFormat;

public class Zeitgeber {

	private static Calendar c;
	private static Zeitgeber z;

	private Zeitgeber() {
		c = Calendar.getInstance();
		new Quarz().startTiming();
		Clock clock = new Clock();
		clock.start();
	}
	
	public static Zeitgeber getZeitgeber()  {
        if (z == null)
            z = new Zeitgeber();
        return z;
    }

	public static Calendar getCalender() {
		return (Calendar) c.clone();
	}

	public class Quarz {
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT);

		private void timewarper(int value) {
			c.add(Calendar.MINUTE, value);
			// System.out.println("Jetzt: " + df.format(c.getTime()) + " " +
			// tf.format(c.getTime()));
		}

		public void startTiming() {
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					Quarz.this.timewarper(1000);
				}
			}, 0, 10);
		}
	}
}