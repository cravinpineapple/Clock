package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import model.StopWatch;
import model.Clock.States;
import view.AlarmScreen;
import view.StopWatchScreen;
import view.TimerScreen;

public class StopWatchListener implements ActionListener {

	JComboBox<String> clockCombo;

	private StopWatchScreen panel;
	StopWatch stopWatch;

	int lapCount = 0;

	public StopWatchListener(StopWatchScreen panel) {
		this.panel = panel;
		stopWatch = panel.getStopWatch();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		var button = e.getSource();

		if (button == panel.getStartStopButton()) {
			if (StopWatch.state == States.STOPPED || StopWatch.state == States.PAUSED) {
				stopWatch.resumeTimer();
				panel.getStartStopButton().setText("Stop");
				panel.getLapResetButton().setText("Lap");
				panel.getLapResetButton().setEnabled(true);
				StopWatch.state = States.RUNNING;
			}
			else if (StopWatch.state == States.RUNNING) {
				stopWatch.stopTimer();
				panel.getLapResetButton().setText("Reset");
				panel.getStartStopButton().setText("Start");
				StopWatch.state = States.PAUSED;
			}
		}
		else if (button == panel.getLapResetButton()) {
			if (StopWatch.state == States.RUNNING) {
				String existingMessage = panel.getLapInfo().getText();
				lapCount++;
				panel.getLapInfo().setText(existingMessage + "\n\n" + "Lap " + lapCount + " - " + stopWatch.toString());
				stopWatch.resetLapTime();
			}
			else if (StopWatch.state == States.PAUSED) {
				stopWatch.reset();
				lapCount = 0;
				panel.getLapResetButton().setText("Lap");
				panel.getLapResetButton().setEnabled(false);
				StopWatch.state = States.STOPPED;
			}
		}

		JFrame window = panel.getWindow();
		switch (panel.getClockCombo().getSelectedIndex()) {
			case 0:
			break;
		case 1:
			window.getContentPane().removeAll();
			TimerScreen timer = new TimerScreen(window);
			timer.init();
			window.revalidate();
			break;
		case 2:
			window.getContentPane().removeAll();
			AlarmScreen alarm = new AlarmScreen(window);
			alarm.init();
			window.revalidate();
			break;
			}
	}
	
}
