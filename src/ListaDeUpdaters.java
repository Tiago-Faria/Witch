import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ListIterator;


public class ListaDeUpdaters {
	private static ArrayList<updater> updaters = new ArrayList<updater>();
	public static void adicionar(updater i){
		
		
		updaters.add(i);
	}
	public static void excluir(updater I){
		updaters.remove(I);                              
	}
	public static void update(boolean teclas[]){
		ArrayList<updater> copia = new ArrayList<updater>(updaters);
		for(updater u:copia){	
			u.update(teclas);
		}
	}
	public static void resetar(){
		updaters.clear();
	}
}
