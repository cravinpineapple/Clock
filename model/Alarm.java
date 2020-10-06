package model;

import java.awt.Graphics2D;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.AlarmScreen;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Alarm extends Clock {

	AlarmScreen panel;

	String alarmString = "";

	public DateFormat format = new SimpleDateFormat("HH:mm:ss");
	Date date;
	JFrame window;

	public Alarm() {
		super();
		defineListener();
		setTimer(1000, listener);
		//buildAlarmString(); for testing
	}

	public void buildAlarmTime(JComboBox<String> hoursCombo, JComboBox<String> minutesCombo, JComboBox<String> secondsCombo) {
		hours = hoursCombo.getSelectedIndex();
		minutes = minutesCombo.getSelectedIndex();
		seconds = secondsCombo.getSelectedIndex();

		String hoursString = hours < 10 ? "0" + hours : Integer.toString(hours);
		String minutesString = minutes < 10 ? "0" + minutes : Integer.toString(minutes);
		String secondsString = seconds < 10 ? "0" + seconds : Integer.toString(seconds);

		alarmString = hoursString + ":" + minutesString + ":" + secondsString;
	}

	public void setAlarmScreenAndWindow(AlarmScreen panel) {
		this.panel = panel;
		this.window = panel.getWindow();
	}

	@Override
	protected void defineListener() {
		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentTime += 1000;
				if (externalToString().equals(alarmString)) {
					stopTimer();
					reset();
					JOptionPane.showMessageDialog(window, "Alarm Time Reached!");
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
		JComboBox<String> hoursCombo = panel.getHoursCombo();
		JComboBox<String> minutesCombo = panel.getMinutesCombo();
		JComboBox<String> secondsCombo = panel.getSecondsCombo();
		buildAlarmTime(hoursCombo, minutesCombo, secondsCombo);
		panel.getOffButton().setSelected(true);
		hoursCombo.setSelectedIndex(0);
		minutesCombo.setSelectedIndex(0);
		secondsCombo.setSelectedIndex(0);
		hoursCombo.setEnabled(true);
		minutesCombo.setEnabled(true);
		secondsCombo.setEnabled(true);
	}

	@Override
	public void setFunctionTime(int hours, int minutes, int seconds) {

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
