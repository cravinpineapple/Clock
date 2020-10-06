package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import control.StopWatchListener;
import model.StopWatch;

public class StopWatchScreen {
	final int HEIGHT = 500;
	final int WIDTH = 500;

	private JFrame window;
	StopWatch stopWatch = new StopWatch();

	private StopWatchCanvas canvas = new StopWatchCanvas(this);

	private JComboBox<String> clockCombo = new JComboBox<String>(new String[] {"StopWatch", "Timer", "Alarm"});
	private JTextArea lapInfo = new JTextArea();

	private String existingMessage = "";

	JButton startStopButton = new JButton("Start");
	JButton lapResetButton = new JButton("Lap");

	public StopWatchScreen(JFrame window) {
		this.window = window;
		//window.setSize(new Dimension(WIDTH, HEIGHT));
		stopWatch.setPanel(this);
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
		lapInfo.setEditable(false);
		var scrollPane = new JScrollPane(lapInfo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // display is text area, wrapped up by scrollPane
		scrollPane.setPreferredSize(new Dimension(490, 80));
		southPanel1.add(scrollPane);
		
		JPanel southPanel2 = new JPanel();
		southPanel2.add(startStopButton);
		lapResetButton.setEnabled(false);
		southPanel2.add(lapResetButton);
		southPanel.add(BorderLayout.CENTER, southPanel1);
		southPanel.add(BorderLayout.SOUTH, southPanel2);
		cp.add(BorderLayout.SOUTH, southPanel);

		StopWatchListener listener = new StopWatchListener(this);
		startStopButton.addActionListener(listener);
		lapResetButton.addActionListener(listener);
		clockCombo.addActionListener(listener);
	}

	public StopWatch getStopWatch() {
		return stopWatch;
	}

	public String getExistingMessage() {
		return existingMessage;
	}

	public JTextArea getLapInfo() {
		return lapInfo;
	}

	public StopWatchCanvas getCanvas() {
		return canvas;
	}

	public JButton getLapResetButton() {
		return lapResetButton;
	}

	public JButton getStartStopButton() {
		return startStopButton;
	}

	public JComboBox<String> getClockCombo() {
		return clockCombo;
	}

	public JFrame getWindow() {
		return window;
	}

	public void setExistingMessage(String message) {
		existingMessage = message;
	}
}
