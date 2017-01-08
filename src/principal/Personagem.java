package principal;

public class Personagem {
	private String nome, sexo, origem, ultimoGolpe;
	private int idade, life;
	private boolean bloqueio;
	private final int LIFE = 100;
	private String golpes[]; // A última posição do vetor é sempre destinada ao golpe forte.

	public Personagem(String nome, String sexo, int idade, String origem, String[] golpes) {
		this.nome = nome;
		this.sexo = sexo;
		this.origem = origem;
		this.idade = idade;
		this.golpes = golpes;
		this.bloqueio = false;
		life = LIFE;
		ultimoGolpe = "";
	}

	public String getUltimoGolpe() {
		return ultimoGolpe;
	}

	public void setUltimoGolpe(String ultimoGolpe) {
		this.ultimoGolpe = ultimoGolpe;
	}

	public int getLife() {
		return life;
	}
	public int getLIFE() {
		return LIFE;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public boolean isBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(boolean bloqueio) {
		this.bloqueio = bloqueio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Golpe golpear(String golpe) {
		if (!ultimoGolpe.equalsIgnoreCase(golpe)) {
			if (golpe.equalsIgnoreCase("bloq")) {
				if (bloqueio) System.out.println("\n- Seu Bloqueio Já Está Ativado!");
				else {
					bloqueio = true;
					System.out.println("\n- Seu Bloqueio Foi Ativado!");
				}
			} else {
				for (int i = 0; i < golpes.length; i++) {
					if (golpes[i].equalsIgnoreCase(golpe)) {
						if (i == golpes.length - 1) {
							ultimoGolpe = golpe;
							return new Golpe(golpe, Golpe.TIPO_FORTE); // Último golpe do vetor será sempre o forte.
						} else {
							ultimoGolpe = golpe;
							return new Golpe(golpe, Golpe.TIPO_FRACO);
						}
					}
				}
			}
		}
		if ( !golpe.equalsIgnoreCase("bloq") && this.life > 0 ) System.out.println("\n- Golpe Repetido ou Inválido. Tente Outro Golpe!");
		return null;
	}

	public void receberDano (Golpe golpe){
		if (golpe.getTipo() == Golpe.TIPO_FORTE){
			if (bloqueio){ 
				bloqueio = false;
				life -= 0.05*LIFE;
				System.out.println("\n- Seu Bloqueio Foi Desativado!");
			} else {
				life -= 0.1*LIFE;
			}
		} else {
			if (bloqueio) {
				bloqueio = false;
				System.out.println("\n- Seu Bloqueio Foi Desativado!");
			} else {
				life -= 0.05*LIFE;
			}
		}
		if (this.life >= 0) System.out.print("\n- Você recebeu o golpe: " + golpe.getNome() + "   ->  Seu life é: " +  this.life+"  ");
	}
}
