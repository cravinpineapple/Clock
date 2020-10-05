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

	public void resetComboBoxes() {
		panel.getHoursCombo().setSelectedIndex(0);
		panel.getMinutesCombo().setSelectedIndex(0);
		panel.getSecondsCombo().setSelectedIndex(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		var button = e.getSource();

		if (button == panel.getStartPauseButton()) {
			hoursCombo.setEnabled(false);
			minutesCombo.setEnabled(false);
			secondsCombo.setEnabled(false);

			if (panel.getStartPauseButton().getText() == "Start" || panel.getStartPauseButton().getText() == "Resume") {
				Timer.state = Timer.States.RUNNING;
				if (panel.getStartPauseButton().getText() == "Start")
					timer.startTimer();
				else
					timer.resumeTimer();
				panel.getStartPauseButton().setText("Pause");
				panel.getCancelButton().setEnabled(true);
				//System.out.println("Timer After Pause: " + timer.hours + ":" + timer.minutes + ":" + timer.seconds);
			}
			else {
				Timer.state = Timer.States.PAUSED;
				timer.stopTimer();
				panel.getStartPauseButton().setText("Resume");
			}
		}
		else if (button == panel.getCancelButton()) {
			timer.stopTimer();
			timer.reset();
			hoursCombo.setEnabled(true);
			minutesCombo.setEnabled(true);
			secondsCombo.setEnabled(true);
			resetComboBoxes();
			panel.getStartPauseButton().setText("Start");
			panel.getCancelButton().setEnabled(false);
			panel.getCanvas().repaint();
			Timer.state = Timer.States.STOPPED;
		}
		else if (Timer.state == Timer.States.STOPPED) {
			updateComboBoxes(panel.getHoursCombo(), panel.getMinutesCombo(), panel.getSecondsCombo());
			timer.setFunctionTime(hoursCombo.getSelectedIndex(), 
				minutesCombo.getSelectedIndex(), secondsCombo.getSelectedIndex());
			panel.getCanvas().repaint();
		}

		
	}
	
}
