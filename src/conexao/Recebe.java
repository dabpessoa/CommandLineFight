package conexao;

import java.io.IOException;

public class Recebe extends Thread{

	private Remota conn;
	private boolean running = true;
	
	public Recebe(Remota conn){
		this.conn= conn;
	}

	public void run(){
		while(running){
			try {
				conn.receberGolpe();
			} catch (IOException e) {
				running = false;
			}
		}
	}
	
	public boolean isRunning()
	{
		return running;
	}
}
