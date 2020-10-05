package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.TimerListener;
import model.Timer;

public class TimerScreen {

	final int HEIGHT = 300;
	final int WIDTH = 500;
	
	private JFrame window;

	Timer timer = new Timer();

	//String[] clockOptionsList = {"Timer", "Alarm", "Stopwatch"};
	private JComboBox<String> clockOptions = new JComboBox<String>(new String[] {"Timer", "Alarm", "Stopwatch"});

	//private JLabel hmsText = new JLabel("00:00:00");

	String[] hoursComboStrings = initTimeOptions(24);
	String[] minutesComboStrings = initTimeOptions(60);
	String[] secondsComboStrings = initTimeOptions(60);
	private JComboBox<String> hoursCombo = new JComboBox<String>(hoursComboStrings);
	private JComboBox<String> minutesCombo = new JComboBox<String>(minutesComboStrings);
	private JComboBox<String> secondsCombo = new JComboBox<String>(secondsComboStrings);

	private JButton startPauseButton = new JButton("Start");
	private JButton cancelButton = new JButton("Cancel");

	private TimerCanvas canvas = new TimerCanvas(this);

	public TimerScreen(JFrame window) {
		this.window = window;
		timer.setTimerScreen(this);
		window.setSize(new Dimension(WIDTH, HEIGHT));
	}

	public void init() {
		Container cp = window.getContentPane();
		cancelButton.setEnabled(false);

		JPanel northPanel = new JPanel();
		northPanel.add(clockOptions);
		cp.add(BorderLayout.NORTH, northPanel);

		JPanel centerPanel = new JPanel();
		//hmsText.setFont(new Font("Courier", Font.BOLD, 100));
		centerPanel.add(canvas);
		cp.add(BorderLayout.CENTER, centerPanel);

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(2, 1));
		JPanel southPanel1 = new JPanel();
		southPanel1.add(hoursCombo);
		southPanel1.add(minutesCombo);
		southPanel1.add(secondsCombo);
		JPanel southPanel2 = new JPanel();
		southPanel2.add(startPauseButton);
		southPanel2.add(cancelButton);
		southPanel.add(southPanel1);
		southPanel.add(southPanel2);
		cp.add(BorderLayout.SOUTH, southPanel);

		TimerListener listener = new TimerListener(this);
		hoursCombo.addActionListener(listener);
		minutesCombo.addActionListener(listener);
		secondsCombo.addActionListener(listener);
		startPauseButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
		
	}

	private String[] initTimeOptions(int size) {
		String[] options = new String[size];

		for (int i = 0; i < size; i++) {
			options[i] = "" + i;
		}

		return options;
	}

	public Timer getTimer() {
		return timer;
	}

	public JComboBox<String> getHoursCombo() {
		return hoursCombo;
	}

	public JComboBox<String> getMinutesCombo() {
		return minutesCombo;
	}

	public JComboBox<String> getSecondsCombo() {
		return secondsCombo;
	}

	public TimerCanvas getCanvas() {
		return canvas;
	}

	public JButton getStartPauseButton() {
		return startPauseButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public TimerScreen getTimerScreen() {
		return this;
	}

	public JFrame getWindow() {
		return window;
	}
}
