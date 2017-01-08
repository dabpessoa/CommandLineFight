package conexao;

import java.io.IOException;

import principal.Golpe;

public interface Comunicacao {
	public void enviarGolpe(Golpe golpe) throws IOException;
	public void receberGolpe() throws IOException;
}