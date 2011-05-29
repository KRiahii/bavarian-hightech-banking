package bavaria.hightech.time;

import java.util.TimerTask;
import java.util.Timer;
import java.util.Calendar;

/**
 * 
 * time emitter
 * 
 */
public final class TimeEmitter {

	private Calendar calendar;
	private static TimeEmitter timeEmitter;

	private TimeEmitter() {
		calendar = Calendar.getInstance();
	}

	/**
	 * getTimeEmitter()
	 * 
	 * @return
	 */

	public static TimeEmitter getTimeEmitter() {
		if (timeEmitter == null) {
			timeEmitter = new TimeEmitter();
		}
		return timeEmitter;
	}

	/**
	 * getCalendar()
	 * 
	 * @return
	 */
	public Calendar getCalender() {
		return (Calendar) calendar.clone();
	}

	/**
	 * 
	 * son quarz halt
	 * 
	 */
	public class Quarz {
		private Timer timer;
		private Clock clock;
		private int counter = 1;
		
		public Clock getClock () {
			return clock;
		}

		private void timewarper(int value) {
			calendar.add(Calendar.SECOND, value);
			counter++;

			if (counter == 152) {
				stopTimeing();
				counter = 1;
			}
		}

		public void startTimeing() {
			timer = new Timer();
			clock = new Clock();
			clock.start();

			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					Quarz.this.timewarper(576);
				}
			}, 0, 1);
		}

		public void stopTimeing() {
			calendar.add(Calendar.SECOND, -576);
			timer.cancel();
			clock.stop();
		}
	}
}