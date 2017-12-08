
public class fantasma extends colisor implements updater{
	public String getNomeClasse() {
		return this.getClass().getName();
	}

	private Personagem pers;
	private int numero=0;
	private float velocidade = 2;
	private int tempoSurgir = 30;
	public fantasma(float x, float y,Personagem pers) {
		super(x, y,"src/Imagens/fantasma_direita.png",14);
		ListaDeUpdaters.adicionar(this);
		this.pers = pers;
		String animacao[] = new String[3];
		animacao[0] = "src/Imagens/fantasma_esquerda_transp_1.png";
		animacao[1] = "src/Imagens/fantasma_esquerda_transp_2.png";
		animacao[2] = "src/Imagens/fantasma_esquerda_transp_3.png";
		setAnimacao(animacao, 3, tempoSurgir,false);
	}
	public boolean getAtivo(){
		if (this.tempoSurgir < 0)return true;
		else return false;
	}
	public void colidir(colisor c) {
		if(c.getNomeClasse().equals("fogo")){
			Controle.IncrDificuldade();
			ListaDeColisores.excluir(this);
			ListaDeUpdaters.excluir(this);
			ListaDeImagensNaTela.excluir(this);
		}
	}
	
	public void update(boolean[] teclas) {
		if (pers.x < x)
			this.carregarImagem("src/Imagens/fantasma_direita.png");
		else
			this.carregarImagem("src/Imagens/fantasma_esquerda.png");
		if(tempoSurgir < 0)movimentacao();
		else if(tempoSurgir > 0)tempoSurgir--;
		else{
			tempoSurgir--;
		}
	}
	public void movimentacao(){
		if(pers.isVivo()){
			x += (float) (velocidade * (pers.x - x)/Math.sqrt((pers.x - x)*(pers.x - x)+ (pers.y - y)*(pers.y - y)));
			y += (float) (velocidade * (pers.y - y)/Math.sqrt((pers.x - x)*(pers.x - x)+ (pers.y - y)*(pers.y - y)));
	
		}
	}
}
