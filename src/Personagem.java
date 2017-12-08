///Classe Personagem

import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.*;
public class Personagem extends colisor implements updater{
	
	private  static final int DIREITA = 0;
	private  static final int CIMA = 1;
	private  static final int ESQUERDA = 2;
	private  static final int BAIXO = 3;
	
	private int direçao = DIREITA;
	private float velocidade = 3;
	private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
	private final int tempoDeRecarga = 30;
	private int tempoDeRecargaAtual = 0;
	private boolean vivo = true;
	JPanel painel;
	
	Personagem(float x,float y){
		super(x,y,"src/Imagens/baixo.png",16);	
		String animacao[] = new String[4];
		animacao[0] = "src/Imagens/esquerda.png";
		animacao[1] = "src/Imagens/cima.png";
		animacao[2] = "src/Imagens/direita.png";
		animacao[3] = "src/Imagens/baixo.png";
		setAnimacao(animacao, 4, 1, false);
		ListaDeUpdaters.adicionar(this);
	}
	
	public String getNomeClasse() {
		return this.getClass().getName();
	}
	
	public void update(boolean teclas[]){
		movimentacao(teclas);
		if(tempoDeRecargaAtual > 0)tempoDeRecargaAtual--;
		if(tempoDeRecargaAtual == 0)ataque(teclas);
	}
	public void movimentacao(boolean teclas[]){
		if(teclas[KeyEvent.VK_D]){
			if(this.x + this.velocidade < 550)
			this.x += this.velocidade;
			else this.x = 550;
			if(this.direçao != DIREITA){
				direçao = DIREITA;
				this.setImagem(DIREITA);
			}
		}
		if(teclas[KeyEvent.VK_W]){
			if(this.y - this.velocidade > 0)
			this.y -= this.velocidade;
			else this.y = 0;
			if(this.direçao != CIMA){
				direçao = CIMA;
				this.setImagem(CIMA);
			}
		}
		if(teclas[KeyEvent.VK_A]){
			if(this.x - this.velocidade > 0)
			  this.x -= this.velocidade;
			else this.x = 0;
			if(this.direçao != ESQUERDA){
				direçao = ESQUERDA;
				this.setImagem(ESQUERDA);
			}
		}
		if(teclas[KeyEvent.VK_S]){
			if(this.y + this.velocidade < 430)
			this.y += this.velocidade;
			else this.y = 430;
			if(this.direçao != BAIXO){
				direçao = BAIXO;
				this.setImagem(BAIXO);
			}
		}
	}
	public void ataque(boolean teclas[]){
		if(teclas[KeyEvent.VK_RIGHT]){
			new fogo(this.x,this.y,1);
			tempoDeRecargaAtual = tempoDeRecarga;
		}
		if(teclas[KeyEvent.VK_UP]){
			new fogo(this.x,this.y,2);
			tempoDeRecargaAtual = tempoDeRecarga;
		}
		if(teclas[KeyEvent.VK_LEFT]){
			new fogo(this.x,this.y,3);
			tempoDeRecargaAtual = tempoDeRecarga;
		}
		if(teclas[KeyEvent.VK_DOWN]){
			new fogo(this.x,this.y,4);
			tempoDeRecargaAtual = tempoDeRecarga;
		}
	}
	public void colidir(colisor c){
		if(c.getNomeClasse().equals("fantasma") && c.getAtivo()){
			this.vivo = false;
			c.getAtivo();
			ListaDeColisores.excluir(this);
			ListaDeUpdaters.excluir(this);
			ListaDeImagensNaTela.excluir(this);
			Controle.morrer();
		
		}
	}

	public boolean isVivo() {
		return vivo;
	}
}
