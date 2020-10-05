package control;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import model.Timer;
import view.TimerScreen;

import java.awt.event.ActionEvent;

public class TimerListener implements ActionListener {

	private TimerScreen panel;
	Timer timer;

	JComboBox<String> hoursCombo;
	JComboBox<String> minutesCombo;
	JComboBox<String> secondsCombo;


	public TimerListener(TimerScreen panel) {
		this.panel = panel;
		timer = panel.getTimer();
	}

	public void updateComboBoxes(JComboBox<String> hoursCombo, JComboBox<String> minutesCombo, JComboBox<String> secondsCombo) {
		this.hoursCombo = hoursCombo;
		this.minutesCombo = minutesCombo;
		this.secondsCombo = secondsCombo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		var button = e.getSource();

		if (button == panel.getStartPauseButton()) {
			timer.startTimer();
			
		}

		updateComboBoxes(panel.getHoursCombo(), panel.getMinutesCombo(), panel.getSecondsCombo());

		timer.setFunctionTime(hoursCombo.getSelectedIndex(), 
			minutesCombo.getSelectedIndex(), secondsCombo.getSelectedIndex());

		panel.getCanvas().repaint();
	}
	
}
