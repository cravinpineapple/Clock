package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.Timer;

public class TimerCanvas extends JPanel {

	private TimerScreen panel;
	Timer timer;

	public TimerCanvas(TimerScreen panel) {
		this.panel = panel;
		timer = panel.getTimer();
		setPreferredSize(new Dimension(500, 200));
		setBackground(Color.white);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		timer.render(g2);
	}
	
}
