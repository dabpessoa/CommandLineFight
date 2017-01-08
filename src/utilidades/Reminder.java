package utilidades;

import java.util.Timer;
import java.util.TimerTask;

import tela.Play;


public class Reminder {
	private Timer timer;

	public Reminder(int minutes) {
		timer = new Timer();
		timer.schedule(new RemindTask(), minutes *60 *1000);
	}

	class RemindTask extends TimerTask {
		public void run() {
			Play.terminar();
			timer.cancel(); 
		}
	}
}