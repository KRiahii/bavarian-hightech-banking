package bavaria.hightech.time;

import java.util.TimerTask;
import java.util.Timer;
import java.util.Calendar;

import bavaria.hightech.exceptions.MoneyException;
import bavaria.hightech.testit.Test;

/**
 * 
 * time emitter
 * 
 */
public final class TimeEmitter {

	private Calendar calendar;
	private Calendar revcalendar;
	private static TimeEmitter timeEmitter;

	private TimeEmitter() {
		calendar = Calendar.getInstance();
		revcalendar = Calendar.getInstance();
	}

	/**
	 * elapstime()
	 * 
	 * @param time
	 */
	public void elapstime(int time) {
		Quarz quarz = new Quarz();
		revcalendar.add(Calendar.DATE, time);
		revcalendar.add(Calendar.SECOND, 1);
		quarz.startTimeing();
		while (calendar.before(revcalendar))
			;
		quarz.stopTimeing();
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

		private void timewarper(int value) throws MoneyException {
			calendar.add(Calendar.SECOND, value);
			counter++;

			if (counter == 151) {
				Test.bank.chargeInterest();
				counter = 1;
			}
		}

		public void startTimeing() {
			timer = new Timer();
			clock = new Clock();
			clock.start();

			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					try {
						Quarz.this.timewarper(576);
					} catch (MoneyException e) {

						e.printStackTrace();
					}
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