package bavaria.hightech.time;

import java.util.TimerTask;
import java.util.Timer;
import java.util.Calendar;

import bavaria.hightech.exceptions.MoneyException;
import bavaria.hightech.testit.Test;

public final class TimeEmitter {

	private static Calendar calendar;
	private static TimeEmitter timeEmitter;
	private Calendar revcalendar;

	private TimeEmitter() {
		calendar = Calendar.getInstance();
		revcalendar = Calendar.getInstance();
	}

	public void elapstime(int time) {
		Quarz quarz = new Quarz();
		revcalendar.add(Calendar.DATE, time);
		quarz.startTimeing();

		while (calendar.before(revcalendar));
	
			quarz.stopTimeing();
	}

	public static TimeEmitter getTimeEmitter() {
		if (timeEmitter == null) {
			timeEmitter = new TimeEmitter();
		}
		return timeEmitter;
	}

	public Calendar getCalender() {
		return (Calendar) calendar.clone();
	}

	public class Quarz {
		private Timer timer;
		private Clock clock;
		private int counter = 1;

		private void timewarper(int value) throws MoneyException {
			calendar.add(Calendar.SECOND, value);
			counter++;

			if (counter * value == 86400) {
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
			timer.cancel();
			clock.stop();
		}
	}
}