package principal;

public class Mago extends Personagem {
	private static final long serialVersionUID = -5104681163510990011L;
	private static String golpes[] = {"fogo","trovao","tempestade"};
	
	public Mago(String nome, String sexo, int idade, String origem) {
		super(nome, sexo, idade, origem, golpes);
	}
}
