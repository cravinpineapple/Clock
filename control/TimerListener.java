package control;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import model.Timer;
import view.AlarmScreen;
import view.StopWatchScreen;
import view.TimerScreen;

import java.awt.event.ActionEvent;

public class TimerListener implements ActionListener {

	private TimerScreen panel;
	Timer timer;

	JComboBox<String> hoursCombo;
	JComboBox<String> minutesCombo;
	JComboBox<String> secondsCombo;
	JComboBox<String> clockCombo;


	public TimerListener(TimerScreen panel) {
		this.panel = panel;
		timer = panel.getTimer();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		var button = e.getSource();

		if (button == panel.getStartPauseButton()) {
			panel.timeComboBoxSetEnabled(false);

			if (panel.getStartPauseButton().getText() == "Start" || panel.getStartPauseButton().getText() == "Resume") {
				Timer.state = Timer.States.RUNNING;
				if (panel.getStartPauseButton().getText() == "Start")
					timer.startTimer();
				else
					timer.resumeTimer();
				panel.getStartPauseButton().setText("Pause");
				panel.getCancelButton().setEnabled(true);
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
			panel.timeComboBoxSetEnabled(true);
			resetComboBoxes();
			panel.getStartPauseButton().setText("Start");
			panel.getCancelButton().setEnabled(false);
			panel.getCanvas().repaint();
			Timer.state = Timer.States.STOPPED;
		}
		else if (Timer.state == Timer.States.STOPPED) {
			updateComboBoxes(panel.getHoursCombo(), panel.getMinutesCombo(), panel.getSecondsCombo(), panel.getClockCombo());
			timer.setFunctionTime(hoursCombo.getSelectedIndex(), 
				minutesCombo.getSelectedIndex(), secondsCombo.getSelectedIndex());
			panel.getCanvas().repaint();

			JFrame window = panel.getWindow();
			switch (clockCombo.getSelectedIndex()) {
				case 0:
					break;
				case 1:
					window.getContentPane().removeAll();
					AlarmScreen alarm = new AlarmScreen(window);
					alarm.init();
					window.revalidate();
					break;
				case 2:
					window.getContentPane().removeAll();
					StopWatchScreen stopWatch = new StopWatchScreen(window);
					stopWatch.init();
					window.revalidate();
					break;
			}
		}
	}
	
}
