package conexao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


import principal.Golpe;
import principal.Personagem;

public class Remota implements Comunicacao {
	private String ip;
	private int porta,lifeOponente;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Recebe recebe;
	private Personagem player;
	private Socket socket;

	public Remota(String ip, int porta, Personagem player) throws IOException {
		this.ip = ip;
		this.porta = porta;
		this.player = player;
		this.lifeOponente = player.getLIFE();

		socket = iniciarCliente();
		if (socket == null) {
			socket = iniciarServidor();
		}
		
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());

		recebe = new Recebe(this);
		recebe.start();
	}

	private Socket iniciarCliente() {
		socket = null;
		try {
			socket = new Socket(ip, porta);
		} catch (UnknownHostException ex) {
		} catch (IOException ex) {
		}

		return socket;
	}

	private Socket iniciarServidor() {
		ServerSocket socket = null;
		
		try {
			
			socket = new ServerSocket(porta);
			return socket.accept();
			
		} catch (UnknownHostException ex) {
		} catch (IOException ex) {
		}

		return null;
	}

	public int getLifeOponente(){
		return this.lifeOponente;
	}

	public boolean isConnected()
	{
		return recebe.isRunning();
	}

	public void enviarGolpe(Golpe golpe) throws IOException {
		out.writeObject(golpe);
		out.flush();
	}

	public void receberGolpe() throws IOException {
		Object obj;
		try {
			obj = in.readObject();
			if (obj instanceof Golpe){
				player.receberDano((Golpe)obj);
				out.writeObject(player.getLife());
			}else {
				if	(obj instanceof Integer) {
					lifeOponente = (Integer)obj;
					System.out.println("\n- O Seu Oponente ficou com " + lifeOponente + "!");
				}
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
