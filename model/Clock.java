package model;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics2D;

import javax.swing.Timer;


public abstract class Clock {

	public enum States { 
		RUNNING, PAUSED, STOPPED
	};

	static public States state;

	public int hours;
	public int minutes;
	public int seconds;

	public long functionTime;
	public long desiredTime;

	Timer timer;
	// updated when each clock started
	protected long currentTime;
	protected ActionListener listener;
	
	public Clock() {
		state = States.STOPPED;
		setTimer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {}
		});

		System.out.println("Clock constructor called");
	}

	public Clock(int delay, ActionListener action) {
		setTimer(delay, action);
	}

	protected void setTimer(int delay, ActionListener action) {
		timer = new Timer(delay, action);
	}

	public void startTimer() {
		updateCurrentTime();
		desiredTime = currentTime + functionTime;
		timer.start();
	}

	public void resumeTimer() {
		timer.start();
	}

	public void stopTimer() {
		timer.stop();
	}

	public void updateCurrentTime() {
		currentTime = System.currentTimeMillis();
	}

	protected abstract void defineListener();
	public abstract void reset();
	public abstract void setFunctionTime(int hours, int minutes, int seconds);
	public abstract void render(Graphics2D g2);
	

}
