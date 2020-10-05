package model;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.TimerScreen;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;

public class Timer extends Clock {

	// canvas needed to repaint each cycle
	TimerScreen panel;

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
				panel.getCanvas().repaint();

				if (currentTime >= desiredTime) {
					stopTimer();
					panel.getHoursCombo().setEnabled(true);
					panel.getMinutesCombo().setEnabled(true);
					panel.getSecondsCombo().setEnabled(true);
					panel.getHoursCombo().setSelectedIndex(0);
					panel.getMinutesCombo().setSelectedIndex(0);
					panel.getSecondsCombo().setSelectedIndex(0);
					panel.getStartPauseButton().setText(("Start"));
					panel.getCancelButton().setEnabled(false);
					JOptionPane.showMessageDialog(panel.getWindow(), "Timer Complete!");
					Timer.state = Timer.States.STOPPED;
				}
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
		hours = 0;
		minutes = 0;
		seconds = 0;
	}

	@Override
	public void setFunctionTime(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;

		functionTime = (hours * 3600000) + (minutes * 60000) + (seconds * 1000);
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.gray);
		g2.setFont(new Font("Courier", Font.BOLD, 100));
		if (hours > 9)
			g2.drawString("" + hours, 40, 115);
		else 
			g2.drawString("0" + hours, 40, 115);

		g2.drawString(":", 160, 115);

		if (minutes > 9)
			g2.drawString("" + minutes, 200, 115);
		else
			g2.drawString("0" + minutes, 200, 115);

		g2.drawString(":", 320, 115);

		if (seconds > 9)
			g2.drawString("" + seconds, 360, 115);
		else
			g2.drawString("0" + seconds, 360, 115);
	}

	public void setTimerScreen(TimerScreen panel) {
		this.panel = panel;
	}
	
}
