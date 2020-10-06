package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.Alarm;

public class AlarmCanvas extends JPanel {
	
	private AlarmScreen panel;
	Alarm alarm;

	public AlarmCanvas(AlarmScreen panel) {
		this.panel = panel;
		alarm = this.panel.getAlarm();
		setPreferredSize(new Dimension(500, 200));
		setBackground(Color.white);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		alarm.render(g2);
	}

}
