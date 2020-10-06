package model;

import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

import view.StopWatchScreen;

public class StopWatch extends Clock {

	int deciseconds = 0;
	StopWatchScreen panel;

	int lapHours = 0;
	int lapMinutes = 0;
	int lapSeconds = 0;
	int lapDeciseconds = 0;

	public StopWatch() {
		super();
		state = States.STOPPED;
		defineListener();
		setTimer(100, listener);
		reset();
	}

	public void setPanel(StopWatchScreen panel) {
		this.panel = panel;
	}

	public void resetLapTime() {
		lapHours = 0;
		lapMinutes = 0;
		lapSeconds = 0;
		lapDeciseconds = 0;
	}

	@Override
	public String toString() {
		String lapMinutesString = lapMinutes > 9 ? "" + lapMinutes : "0" + lapMinutes;
		String lapSecondsString = lapSeconds > 9 ? "" + lapSeconds : "0" + lapSeconds;
		String lapDecisecondsString = "" + lapDeciseconds;

		return "" + lapMinutesString + ":" + lapSecondsString + ":" + lapDecisecondsString.substring(0, 1) + "0";
	}

	@Override
	protected void defineListener() {
		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				functionTime += 100;
				updateTimeFactors();
				panel.getCanvas().repaint();

				if (state == States.PAUSED) {
					timer.stop();
					state = States.STOPPED;
				}
			}
		};
	}

	public void updateLapTime() {
		lapMinutes = minutes - lapMinutes;
		lapSeconds = seconds - lapSeconds;
		lapDeciseconds = deciseconds - lapDeciseconds;
	}

	public void updateTimeFactors() {
		seconds = (int) (functionTime / 1000);

		deciseconds = (int) (functionTime % 1000);

		if (minutes == 60) {
			hours++;
			minutes = 0;
			functionTime -= 3600000;
		}
		if (seconds == 60) {
			minutes++;
			seconds = 0;
			functionTime -= 60000;
		}
	}

	@Override
	public void reset() {
		functionTime = 0;
		hours = 0;
		minutes = 0;
		seconds = 0;
		deciseconds = 0;
		if (panel != null) {
			panel.getCanvas().repaint();
			panel.getLapInfo().setText("");
		}
	}

	@Override
	public void setFunctionTime(int hours, int minutes, int seconds) {

	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.gray);
		g2.setFont(new Font("Courier", Font.BOLD, 100));

		if (minutes > 9)
			g2.drawString("" + minutes, 40, 115);
		else 
			g2.drawString("0" + minutes, 40, 115);

		g2.drawString(":", 160, 115);

		if (seconds > 9)
			g2.drawString("" + seconds, 200, 115);
		else
			g2.drawString("0" + seconds, 200, 115);

		g2.drawString(":", 320, 115);

		String decisecondsString = "" + deciseconds;

		g2.drawString("" + decisecondsString.substring(0, 1) + "0", 360, 115);
	}


}
