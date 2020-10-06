package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.swing.JPanel;

import model.StopWatch;

public class StopWatchCanvas extends JPanel {
	
	private StopWatchScreen panel;
	StopWatch stopWatch;

	public StopWatchCanvas(StopWatchScreen panel) {
		this.panel = panel;
		stopWatch = this.panel.getStopWatch();
		setPreferredSize(new Dimension(500, 200));
		setBackground(Color.white);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		stopWatch.render(g2);
	}
}
