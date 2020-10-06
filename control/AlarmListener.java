package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import model.Alarm;
import model.Clock.States;
import view.AlarmScreen;
import view.StopWatchScreen;
import view.TimerScreen;

public class AlarmListener implements ActionListener {

	AlarmScreen panel;
	Alarm alarm;
	
	JComboBox<String> hoursCombo;
	JComboBox<String> minutesCombo;
	JComboBox<String> secondsCombo;
	JComboBox<String> clockCombo;

	public AlarmListener(AlarmScreen panel) {
		this.panel = panel;
		alarm = panel.getAlarm();
		updateComboBoxes(panel.getHoursCombo(), panel.getMinutesCombo(), panel.getSecondsCombo(), panel.getClockCombo());
	}

	public void updateComboBoxes(JComboBox<String> hoursCombo, JComboBox<String> minutesCombo, 
									JComboBox<String> secondsCombo, JComboBox<String> clockCombo) {
		this.hoursCombo = hoursCombo;
		this.minutesCombo = minutesCombo;
		this.secondsCombo = secondsCombo;
		this.clockCombo = clockCombo;
	}

	public void resetComboBoxes() {
		panel.getHoursCombo().setSelectedIndex(0);
		panel.getMinutesCombo().setSelectedIndex(0);
		panel.getSecondsCombo().setSelectedIndex(0);
	}

	public void comboBoxesEnabled(boolean bool) {
		hoursCombo.setEnabled(bool);
		minutesCombo.setEnabled(bool);
		secondsCombo.setEnabled(bool);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		var button = e.getSource();
		if (button == panel.getOffButton()) {
			alarm.stopTimer();
			Alarm.state = States.STOPPED;
			comboBoxesEnabled(true);
		}
		else if (button == panel.getOnButton()) {
			alarm.startTimer();
			Alarm.state = States.RUNNING;
			comboBoxesEnabled(false);
		}
	
		updateComboBoxes(panel.getHoursCombo(), panel.getMinutesCombo(), panel.getSecondsCombo(), panel.getClockCombo());
		alarm.buildAlarmTime(hoursCombo, minutesCombo, secondsCombo);
		panel.getCanvas().repaint();
		
		
		JFrame window = panel.getWindow();
		switch (clockCombo.getSelectedIndex()) {
			case 0:
				break;
			case 1:
				window.getContentPane().removeAll();
				TimerScreen timer = new TimerScreen(window);
				timer.init();
				window.revalidate();
				break;
			case 2:
				panel.getClockCombo().setSelectedIndex(2);
				window.getContentPane().removeAll();
				StopWatchScreen stopWatch = new StopWatchScreen(window);
				stopWatch.init();
				window.revalidate();
				break;
		}
		
		

	}
	
}
