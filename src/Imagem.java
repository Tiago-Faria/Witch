import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public abstract class Imagem{
	public float x; //posi��o da imagem no eixo y
	public float y; //posi��o da imagem no eixo x
	public ImageIcon imagem; //imagem default, quando nao tem anima��o
	public ImageIcon imagemAtual; //imagem atual da anima��o
	private int tempo; //Tempo de um ciclo da anima��o
	private int tempoAtual; //Tempo atual do ciclo da anima��o
	private int quantidade; //Quantidade de imagens na anima��o
	private ImageIcon imagens[]; //lista de imagens da anima��o
	private boolean animado = false; //True quando a anima��o esta ativada
	private boolean loop; //true quando a anima��o deve acontecer em loop,false
	//quando a anima��o deve terminar ao final de um ciclo
	
	public Imagem(float x,float y,String file){
		this.x = x;
		this.y = y;
		carregarImagem(file); //carrega imagem sem anima��o
		ListaDeImagensNaTela.adicionar(this);
	}
	public Image getImagem(){
		if(animado){
			updateAnimacao();
			return this.imagemAtual.getImage();
		}
		else return this.imagem.getImage();
	}
	public void setImagem(ImageIcon imagem){
		this.imagem = imagem;
	}
	public void setImagem(int i){
		try{
			this.imagem = imagens[i];
		}
		catch(Exception e){
			System.out.println("imagem nao encontrada.");
		}
	}
	public void setAnimacao(boolean estado){
		this.animado = estado;
	}
	public void setAnimacao(String imagens[],int quantidade,int tempo,boolean loop){
		this.imagens = new ImageIcon[quantidade];
		for(int i = 0;i<quantidade;i++){
			try {
				this.imagens[i] = new ImageIcon(imagens[i]);
			} catch (Exception e) {
				System.out.println("falhou em carregar " + imagens[i]);
				e.printStackTrace();
			}
		}
		this.tempo = tempo;
		this.tempoAtual = 0;
		this.quantidade = quantidade;
		this.animado = true;
		this.loop = loop;
	}
	private void updateAnimacao(){
		this.imagemAtual = imagens[(tempoAtual*quantidade/(tempo+1))];
	
		tempoAtual++;
		if(tempoAtual > tempo){
			if(loop)tempoAtual = 0;
			else setAnimacao(false);
		}
	}
	public void carregarImagem(String file){
		ImageIcon i = null;
		try {
			i = new ImageIcon(file);
		} catch (Exception e) {
			System.out.println("falhou em carregar " + file);
			e.printStackTrace();
		}
		
		setImagem(i); //imagem sem anima��o
	}
}