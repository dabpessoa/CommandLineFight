package tela;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import conexao.Remota;
import principal.Atirador;
import principal.Golpe;
import principal.Golpeador;
import principal.Mago;
import principal.Personagem;
import utilidades.Reminder;

public class Play {

	private static Personagem player;
	private static String ip;
	private static int port;
	private static Properties p = new Properties();
	private static Golpe golpe;
	private static Remota rem;
	private static int duracao;
	private static boolean continuar = true;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		p.load(new FileInputStream("bin\\utilidades\\config.txt"));
		lerConfigTXT();
		listarPersonagens();
		System.out.println("(*) Esperando o outro jogador...");
		rem = new Remota(ip,port,player);
		new Reminder(duracao);
		System.out.println("(*) Essa Luta Terá " + duracao + " Minuto(s)!");
		System.out.println();
		System.out.println("(*) Jogador Conectado! FIGHT...");
		System.out.println();
		jogar();
	}
	public static void lerConfigTXT() {
		ip = p.getProperty("ip");
		port= Integer.parseInt(p.getProperty("port"));
		duracao= Integer.parseInt(p.getProperty("duracao"));
	}

	public static void terminar(){
		if (continuar) System.out.println("\n\n(*) Tempo Limite!");
		continuar=false;
		
	}

	public static void listarPersonagens(){
		System.out.println("Lista de Personagens");
		
		for (int i =1; i<10;i++){
			System.out.println();
			System.out.println("-=-=-=- " + p.getProperty(i + "especialidade") + " -=-=-=-");
			System.out.println();
			System.out.println("Nome: " + p.getProperty(i + "nome") + " (" + p.getProperty(i + "idade") + ")");
			System.out.println("Sexo: " + p.getProperty(i + "sexo"));
			System.out.println("Origem: " + p.getProperty(i + "origem"));
		}
		criarPersonagem();
		System.out.println();
	}

	public static void criarPersonagem(){
		Scanner input = new Scanner(System.in);
		String nome;
		
		System.out.println();
		System.out.print("(*) Digite o nome do personagem de sua preferência: ");
		nome = input.nextLine();

		for (int i=1; i<10;i++){
			if(nome.equalsIgnoreCase(p.getProperty(i + "nome"))){
				if(p.getProperty(i + "especialidade").equalsIgnoreCase("mago")){
					player= new Mago(p.getProperty(i + "nome"),p.getProperty(i + "sexo"),Integer.parseInt(p.getProperty(i + "idade")),p.getProperty(i + "origem"));
				}
				if(p.getProperty(i + "especialidade").equalsIgnoreCase("golpeador")){
					player= new Golpeador(p.getProperty(i + "nome"),p.getProperty(i + "sexo"),Integer.parseInt(p.getProperty(i + "idade")),p.getProperty(i + "origem"));
				}
				if(p.getProperty(i + "especialidade").equalsIgnoreCase("atirador")){
					player= new Atirador(p.getProperty(i + "nome"),p.getProperty(i + "sexo"),Integer.parseInt(p.getProperty(i + "idade")),p.getProperty(i + "origem"));
				}
			}
		}
		if (player == null) {
			System.out.println("(*) Personagem Inválido! Tente Novamente...");
			criarPersonagem();
		} 
	}

	public static void jogar(){
		Scanner input = new Scanner(System.in);
		String especialidade = null;
		
		if ( player instanceof Mago) {
			especialidade = "Mago";
		}
		
		if ( player instanceof Golpeador) {
			especialidade = "Golpeador";
		}
		
		if ( player instanceof Atirador) {
			especialidade = "Atirador";
		}
		
		while((player.getLife() > 0) && (continuar) && rem.getLifeOponente() > 0){ //Variável "continuar" refere-se ao tempo e problemas de conexao!
			System.out.print("<> "+player.getNome()+" ("+especialidade+")"+" => "+" Digite seu golpe: ");
			golpe = player.golpear(input.nextLine());
			System.out.println();
			
			if(golpe != null){
				try {
					if (continuar) rem.enviarGolpe(golpe);
					else System.out.println("\n(*) A Luta Acabou!!");
				} catch (IOException e) {
					continuar=false;
				}
				golpe=null;
			}
			
			if (!rem.isConnected()) {
				System.out.println("Conexão com o host remoto perdida!");
				System.out.println();
				continuar=false;
			}
			
			if ((player.getLife() <=0) || (rem.getLifeOponente() <=0)) continuar=false;
		}
		
		System.out.println("\n(*) "+player.getNome() + ", Seu life é: " + player.getLife());
		System.out.println("(*) O life do seu oponente é: " + rem.getLifeOponente());
		
		if (player.getLife() > rem.getLifeOponente()) System.out.println("(*) Você Ganhou!");		
		else if (player.getLife() < rem.getLifeOponente()) System.out.println("(*) Você Perdeu!");
		else System.out.println("(*) Jogo Empatado!");
	}
}
