
public abstract class colisor extends Imagem {
	private float raioColisao;
	public colisor(float x, float y, String file,float raioColisao) {
		super(x, y, file);
		this.setRaioColisao(raioColisao);
		ListaDeColisores.adicionar(this);
	}
	public float getRaioColisao() {
		return raioColisao;
	}
	public abstract String getNomeClasse();
	public void setRaioColisao(float raioColisao) {
		this.raioColisao = raioColisao;
	}
	public void colidir(colisor c){}
	public boolean getAtivo(){return true;}

}
