
public class fogo extends colisor implements updater {
	int direcao;
	private final int velocidade = 4;
	public fogo(float x, float y,int direcao) {
		super(x,y+8,"src/Imagens/magia_direita.png",5);
		if(direcao == 1)super.carregarImagem("src/Imagens/magia_direita.png");
		else if(direcao == 2)super.carregarImagem("src/Imagens/magia_cima.png");
		else if(direcao == 3)super.carregarImagem("src/Imagens/magia_esquerda.png");
		else super.carregarImagem("src/Imagens/magia_baixo.png");
		ListaDeUpdaters.adicionar(this);
		ListaDeImagensNaTela.adicionar(this);
		this.direcao = direcao;
	}
	
	public String getNomeClasse() {
		return this.getClass().getName();
	}

	public void update(boolean[] teclas) {
		movimentacao();
	}
	public void movimentacao(){
		if(this.direcao == 1)this.x += this.velocidade;
		if(this.direcao == 2)this.y -= this.velocidade;
		if(this.direcao == 3)this.x -= this.velocidade;
		if(this.direcao == 4)this.y += this.velocidade;
	}
	public void checarExclusao(){
		if(x > 550 + 20 || x < 0 - 20)ListaDeUpdaters.excluir(this);
		if(y > 430 + 20 || y < 0 - 20)ListaDeUpdaters.excluir(this);
	}

}
