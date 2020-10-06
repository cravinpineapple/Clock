package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import control.AlarmListener;
import model.Alarm;

public class AlarmScreen {

	final int HEIGHT = 300;
	final int WIDTH = 500;

	private JFrame window;
	Alarm alarm = new Alarm();

	private AlarmCanvas canvas = new AlarmCanvas(this);

	String[] hoursComboStrings = initTimeOptions(24);
	String[] minutesComboStrings = initTimeOptions(60);
	String[] secondsComboStrings = initTimeOptions(60);
	private JComboBox<String> hoursCombo = new JComboBox<String>(hoursComboStrings);
	private JComboBox<String> minutesCombo = new JComboBox<String>(minutesComboStrings);
	private JComboBox<String> secondsCombo = new JComboBox<String>(secondsComboStrings);
	private JComboBox<String> clockCombo = new JComboBox<String>(new String[] {"Alarm", "Timer", "Stopwatch"});

	private JRadioButton onButton = new JRadioButton("On");
	private JRadioButton offButton = new JRadioButton("Off");


	public AlarmScreen(JFrame window) {
		this.window = window;
		window.setSize(new Dimension(WIDTH, HEIGHT));
		alarm.setAlarmScreenAndWindow(this);
	}

	public void init() {
		Container cp = window.getContentPane();


		JPanel northPanel = new JPanel();
		northPanel.add(clockCombo);
		cp.add(BorderLayout.NORTH, northPanel);


		JPanel centerPanel = new JPanel();
		centerPanel.add(canvas);
		cp.add(BorderLayout.CENTER, centerPanel);


		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(2, 1));

		JPanel southPanel1 = new JPanel();
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(onButton);
		buttonGroup.add(offButton);
		southPanel1.add(onButton);
		southPanel1.add(offButton);
		offButton.setSelected(true);

		JPanel southPanel2 = new JPanel();
		southPanel2.add(hoursCombo);
		southPanel2.add(minutesCombo);
		southPanel2.add(secondsCombo);

		southPanel.add(southPanel1);
		southPanel.add(southPanel2);
		cp.add(BorderLayout.SOUTH, southPanel);

		AlarmListener listener = new AlarmListener(this);
		clockCombo.addActionListener(listener);
		hoursCombo.addActionListener(listener);
		minutesCombo.addActionListener(listener);
		secondsCombo.addActionListener(listener);
		onButton.addActionListener(listener);
		offButton.addActionListener(listener);
		
	}

	private String[] initTimeOptions(int size) {
		String[] options = new String[size];

		for (int i = 0; i < size; i++) {
			options[i] = "" + i;
		}

		return options;
	}

	public Alarm getAlarm() {
		return alarm;
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

	public JComboBox<String> getClockCombo() {
		return clockCombo;
	}

	public JRadioButton getOffButton() {
		return offButton;
	}
	
	public JRadioButton getOnButton() {
		return onButton;
	}

	public AlarmCanvas getCanvas() {
		return canvas;
	}

	public JFrame getWindow() {
		return window;
	}
	
}
