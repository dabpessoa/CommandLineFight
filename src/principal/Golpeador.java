package principal;

public class Golpeador extends Personagem{
	private static final long serialVersionUID = 5378878521625366316L;
	private static String golpes[] = {"soco","chute","rasteira"};

	public Golpeador(String nome, String sexo, int idade, String origem) {
		super(nome, sexo, idade, origem,golpes);
	}
}
