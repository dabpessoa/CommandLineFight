package principal;

public class Atirador extends Personagem {
	private static final long serialVersionUID = -6580039582454687182L;
	private static String golpes[] = { "bala", "flecha", "geladeira" };

	public Atirador(String nome, String sexo, int idade, String origem) {
		super(nome, sexo, idade, origem, golpes);
	}
}
