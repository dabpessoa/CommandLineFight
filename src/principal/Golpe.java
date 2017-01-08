package principal;

import java.io.Serializable;

public class Golpe implements Serializable {
	private static final long serialVersionUID = -3706196961164546699L;
	private String nome;
	private int tipo;
	public static final int TIPO_FRACO = 1, TIPO_FORTE = 2;

	public Golpe(String nome, int tipo) {
		this.tipo = tipo;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
