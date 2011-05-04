package bavaria.hightech.time;

import java.util.TimerTask;
import java.util.Timer;
import java.util.Calendar;

public final class TimeEmitter {

	private static Calendar calendar;
	private static TimeEmitter timeEmitter;
	private Calendar revcalendar;
	
	private TimeEmitter() {
		calendar = Calendar.getInstance();
		revcalendar = Calendar.getInstance();
	}

	public void elapsTime(int time) {
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

		private void timewarper(int value) {
			calendar.add(Calendar.SECOND, value);		
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
			timer.cancel();
			clock.stop();
		}
	}
}