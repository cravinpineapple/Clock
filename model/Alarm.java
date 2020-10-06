package model;

import java.awt.Graphics2D;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import view.AlarmScreen;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Alarm extends Clock {

	AlarmScreen panel;

	String alarmString = "";

	public DateFormat format = new SimpleDateFormat("HH:mm:ss");
	Date date;

	public Alarm() {
		super();
		defineListener();
		setTimer(1000, listener);
		//buildAlarmString(); for testing
	}

	public void buildAlarmString() {
		int hoursIndex = panel.getHoursCombo().getSelectedIndex();
		int minutesIndex = panel.getMinutesCombo().getSelectedIndex();
		int secondsIndex = panel.getSecondsCombo().getSelectedIndex();

		String hoursString = hoursIndex < 10 ? "0" + hoursIndex : Integer.toString(hoursIndex);
		String minutesString = minutesIndex < 10 ? "0" + minutesIndex : Integer.toString(minutesIndex);
		String secondsString = secondsIndex < 10 ? "0" + secondsIndex : Integer.toString(secondsIndex);

		alarmString = hoursString + ":" + minutesString + ":" + secondsString;
	}

	public void setAlarmScreen(AlarmScreen panel) {
		this.panel = panel;
	}

	@Override
	protected void defineListener() {
		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentTime += 1000;

				if (externalToString().equals(alarmString)) {
					timer.stop();
				}
			}
		};
	}

	@Override
	public String toString() {
		date = new Date(currentTime);
		return format.format(date);
	}

	public String externalToString() {
		return toString();
	}

	@Override
	public void reset() {
		hours = 0;
		minutes = 0;
		seconds = 0;
	}

	@Override
	public void setFunctionTime(int hours, int minutes, int seconds) {
		if (hours == 12)
			hours = 0;

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
	
}
