package model;

import javax.swing.JFrame;

public class ClockTest {
	public static void main(String args[]) {
		/*
		Clock timer = new Timer();
		timer.updateCurrentTime();
		System.out.println("Current Time: " + timer.currentTime);
		timer.setFunctionTime(0, 0, 10);
		System.out.println("Timer Time: " + timer.functionTime);

		timer.startTimer();
		*/
		
		Alarm alarm = new Alarm();
		alarm.updateCurrentTime();
		System.out.println("Current Time: " + alarm.currentTime);
		System.out.println("Alarm Time Format: " + alarm.alarmString);
		System.out.println(alarm.toString());
		alarm.startTimer();

		
		/*
		StopWatch stopWatch = new StopWatch();
		stopWatch.startTimer();
		*/
		
		JFrame window = new JFrame();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
}
