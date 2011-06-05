package bavaria.hightech.time;

import java.awt.Graphics;
import java.util.TimerTask;
import java.util.Timer;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * 
 * creating clock display
 * 
 */
public class Clock extends JFrame {
	private static final int TICK_PERIOD = 1;
	private Timer ticker;
	private JPanel panel;

	private class TickerTask extends TimerTask {
		public void run() {
			panel.repaint();
		}
	}

	private static class ClockDisplay extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Calendar cal = TimeEmitter.getTimeEmitter().getCalender();
			Date date = cal.getTime();
			DateFormat dateFormatter = DateFormat.getTimeInstance();
			g.setFont(new Font(null, Font.PLAIN, 20));
			g.drawString(dateFormatter.format(date), 20, 50);
			dateFormatter = DateFormat.getDateInstance();
			g.drawString(dateFormatter.format(date), 10, 30);
		}
	}

	public Clock() {
		panel = new ClockDisplay();
		add("Center", panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(100, 100);
		setVisible(true);

	}

	/**
	 * start
	 */
	public void start() {
		ticker = new Timer(true);
		ticker.scheduleAtFixedRate(new TickerTask(), 0, TICK_PERIOD);
	}

	/**
	 * stop
	 */
	public void stop() {
		ticker.cancel();
		setVisible(false);
	}

	public void showMe() {
		setVisible(true);
	}
}
