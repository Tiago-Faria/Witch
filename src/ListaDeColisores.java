import java.util.ArrayList;

public class ListaDeColisores {
	private static ArrayList<colisor> colisores = new ArrayList<colisor>();
	public static void adicionar(colisor i){
		colisores.add(i);
	}
	public static void excluir(colisor I){
		colisores.remove(I);                              
	}
	public static void update(){
		ArrayList<colisor> copia = new ArrayList<colisor>(colisores);
		for(colisor c1:copia){	
			for(colisor c2:copia){
				if(distancia(c1.x,c1.y,c2.x,c2.y) < c1.getRaioColisao() + c2.getRaioColisao()){
					c1.colidir(c2);
				}
			}
		}
	}
	public static double distancia(float x1,float y1,float x2,float y2){
		return Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
	}
	public static void resetar(){
		colisores.clear();
	}
}
