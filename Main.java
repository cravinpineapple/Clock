import javax.swing.JFrame;

import view.TimerScreen;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(200, 100);
		window.setTitle("Clock");

		TimerScreen timerScreen = new TimerScreen(window);
		timerScreen.init();

		//window.pack();
		window.setVisible(true);
	}
}