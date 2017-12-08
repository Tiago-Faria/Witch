import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class ListaDeImagensNaTela {
	private static ArrayList<Imagem> Imagens = new ArrayList<Imagem>();
	public static void adicionar(Imagem i){
		Imagens.add(i);
	}
	public static void excluir(Imagem I){
		Imagens.remove(I);                              
	}
	public static void desenhar(Graphics bbg,jogo j){
		for(Imagem I:Imagens){
			bbg.drawImage(I.getImagem(),(int)I.x - I.getImagem().getHeight(j)/2,(int)I.y - I.getImagem().getWidth(j)/2,j);
			
		}
	}
	public static void resetar(){
		Imagens.clear();
	}
}