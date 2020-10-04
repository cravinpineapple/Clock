package model;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Timer extends Clock {

	public Timer() {
		super();
		defineListener();
		setTimer(1000, listener);
		System.out.println("Timer constructor called");
	}

	@Override
	protected void defineListener() {
		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentTime += 1000;
				updateHoursMinutesSeconds();
				System.out.println(hours + ":" + minutes + ":" + seconds);

				if (currentTime >= desiredTime)
					stopTimer();
			}
		};
	}

	public void updateHoursMinutesSeconds() {
		hours = (int) ((desiredTime - currentTime) / 3600000);
		minutes = (int) ((desiredTime - currentTime) / 60000);
		seconds = (int) ((desiredTime - currentTime) / 1000);
	}

	@Override
	public void reset() {
		functionTime = 0;
	}

	@Override
	public void setFunctionTime(int hours, int minutes, int seconds) {
		functionTime = (hours * 3600000) + (minutes * 60000) + (seconds * 1000);
	}
	
}
