import java.util.Random;
import java.io.*;
import java.util.Scanner;


public class Controle implements updater{
	private static int dificuldade = 0;
	public static int numero=0;
	public static int highScore;
	private int tempoDeRecarga = 60;
	private int tempoDeRecargaAtual = 0;
	Random gerador = new Random();
	public static boolean viva = true;
	Personagem pers;
	public Controle(Personagem p){
		this.pers = p;
		ListaDeUpdaters.adicionar(this);
	}
	public static void morrer(){
		viva = false;
	}
	public void update(boolean[] teclas) {
		if(tempoDeRecargaAtual > 0)tempoDeRecargaAtual--;
		if(tempoDeRecargaAtual == 0)invocarFantasma();
		if(dificuldade > 5 && tempoDeRecarga > 50)tempoDeRecarga = 50;
		if(dificuldade > 10 && tempoDeRecarga > 40)tempoDeRecarga = 40;
		if(dificuldade > 20 && tempoDeRecarga > 30)tempoDeRecarga = 30;
		if(dificuldade > 40 && tempoDeRecarga > 20)tempoDeRecarga = 20;
		if(dificuldade > 70 && tempoDeRecarga > 10)tempoDeRecarga = 10;
	}
	public static void IncrDificuldade() {
		numero++;
		dificuldade++;
	}
	public static void readHighScore(){
		try
		{
			InputStream in = new FileInputStream("highscore.txt");
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inr);
			
			highScore = Integer.parseInt(br.readLine());
			
			inr.close();
		} catch(FileNotFoundException e){
			System.out.println("Arquivo não encontrado");
		} catch(IOException e){
			System.out.println("Erro de leitura!");
		}
}

	public static void changeHighScore(){
		if(numero > highScore){
		highScore = numero;
		try{
			FileWriter f = new FileWriter("highscore.txt");
			PrintWriter o = new PrintWriter(f);
			o.println(highScore);
			o.close();
		} catch(FileNotFoundException e){
			System.out.println("Arquivo não encontrado");
		} catch(IOException e){
			System.out.println("Erro de escrita!");
		}
		}
	}
	public void invocarFantasma(){
		int x = gerador.nextInt(550) + 1;
		int y = gerador.nextInt(430) + 1;
		new fantasma(x,y,pers);
		tempoDeRecargaAtual = tempoDeRecarga;
	}
	public void reiniciar(jogo j){
		numero=0;
		dificuldade = 0;
		viva = true;
		ListaDeColisores.resetar();
		ListaDeImagensNaTela.resetar();
		ListaDeUpdaters.resetar();
		j.p = new Personagem(275,215);
		j.controle = new Controle(j.p);
	}
}
