import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
public class jogo extends JFrame implements KeyListener,MouseListener{
 
	 BufferedImage backBuffer; 
	 private int FPS = 30;    
	 private final static int janelaW = 550;
	 private final static int janelaH = 440;
///	 final static JLabel ar = new JLabel(
	 ImageIcon GameOverImage=		 new ImageIcon("src/Imagens/gameover.png");
	 ImageIcon TitleImage=		 new ImageIcon("src/Imagens/title.png");
	 ImageIcon pausar = new ImageIcon("src/Imagens/Pausar.png");
	 ImageIcon despausar = new ImageIcon("src/Imagens/Despausar.png");
	 public static boolean pause = false;
	 public static boolean pauseaux = false;
	 ImageIcon boneco = new ImageIcon("src/Imagens/boneco.png");
	 boolean teclas[] = new boolean[1024];
	 private boolean menu = true;
	 KeyEvent teclaPressionada;
	 Controle controle;
	 Personagem p;
	  JPanel painel = new JPanel();
	
	 public void atualizar() {
			painel.setSize(janelaH,janelaW);
			painel.setVisible(true);
		     if(!menu){
			 ListaDeUpdaters.update(teclas);
			 ListaDeColisores.update();
			 }

	 }
	 public void desenharMenuPausa(){
		 Graphics q = getGraphics();
		 Graphics bbq = backBuffer.getGraphics();
		 bbq.drawImage(despausar.getImage(),107,74,this);
		 q.drawImage(backBuffer, 0, 0, this);
	 }
	 
	 public void desenharGraficos() {
		 Graphics g = getGraphics();//COM g IREMOS DESENHAR O QUE ESTÃ� NO BUFFER NA TELA
		 Graphics bbg = backBuffer.getGraphics();//COM bbg IREMOS DESENHAR NO NOSSO BUFFER

		 if(!menu){
			  desenharFundo(bbg);
			  ListaDeImagensNaTela.desenhar(bbg,this);
			  bbg.setFont(new Font("AddStandardBitmap",Font.BOLD,17));
			  bbg.drawString("Score : "+Controle.numero,10, 423);
			  bbg.drawImage(pausar.getImage(),500,30,this);
			  if(!Controle.viva) {
				  bbg.drawImage(GameOverImage.getImage(),135,150,this);
				  bbg.setFont(new Font("AddStandardBitmap",Font.BOLD,17));
				  Controle.changeHighScore();
				  if(Controle.numero!=Controle.highScore){
				  bbg.drawString("Score = \n"+Controle.numero,190, 223);
				  bbg.drawString("\nHighScore = "+Controle.highScore,190, 240);
				  }else{
					//  bbg.drawString("New = \n"+Controle.numero,190, 223);
				  bbg.drawString("\n NewHighScore = "+Controle.highScore,160, 230);
				  }
			 }
				 
		 }else {
				  bbg.drawImage(TitleImage.getImage(),0,13,this);
				  bbg.setFont(new Font("AddStandardBitmap",Font.BOLD,40));
				  bbg.drawString(""+Controle.highScore, 150, 420);
		    
			 }
		 
				 painel.setSize(janelaH,janelaW);
			      painel.setVisible(true);
				 g.drawImage(backBuffer, 0, 0, this);
	 
	  
	  //AQUI ESTAMOS DESENHANDO O BUFFER NA TELA,
	
	  
	  
	 }
	 public void desenharFundo(Graphics bbg){
		 Image backGround = new ImageIcon("src/Imagens/backTile.png").getImage(); 
		 for(int i=0;i<10;i++){
			 for(int j=0;j<10;j++){
				 bbg.drawImage(backGround,i*janelaW/10,j*janelaH/10,this);
			 }
		 }
	 }
	 public void inicializar() {
		 controle.readHighScore();
		 addKeyListener(this);
		 addMouseListener(this);
		 setTitle("The Fire Witch");
		 setSize(janelaW, janelaH);
		 setResizable(false);
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 setLayout(null);
		 setVisible(true);
		 setLocationRelativeTo(null);
		 backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);
		 p = new Personagem(janelaW/2,janelaH/2);
		 controle = new Controle(p);

		 
		 
	 }
	 
	/* public static void main1(String[] args) {
	 jogo jogo = new jogo();
	     jogo.run();

		}*/
	 public void run() {	 
	  inicializar();
	  while (true) {
		  if(pause){
			  try{
			  desenharMenuPausa();
			  Thread.sleep(0);
			  }
			  catch(InterruptedException ie){
				  System.out.println(ie);
			  }
		  }
		  else{
	   atualizar();
	   desenharGraficos();
	    try {
	     Thread.sleep(1000/FPS);
	    } catch (Exception e) {
	     System.out.println("Thread interrompida!");
	    }
	  }
	 }
}
	 public static void main(String[] args)  {
			jogo jogo = new jogo();
			jogo.run();

		
	 }
	 
		public void keyPressed(KeyEvent e) {
			teclas[e.getKeyCode()] = true;
		}
		public void keyReleased(KeyEvent e) {
			teclas[e.getKeyCode()] = false;
		}
		public void keyTyped(KeyEvent e) {}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getX() > 192 && arg0.getX() < 355
			&& arg0.getY() > 226 && arg0.getY() < 302 && menu)menu=false;
			if(!menu){
				if(!Controle.viva){
				if(arg0.getX() > 232 && arg0.getX() < 320
				&& arg0.getY() > 263 && arg0.getY() < 291)
						controle.reiniciar(this);
				}
				else if(Controle.viva){
					if(arg0.getX() > 500 && arg0.getX() < 530 && arg0.getY() > 20 && arg0.getY() < 50){
						pause = true;
					}
					if(pause){
					if(arg0.getX() > 205 && arg0.getX() < 335 && arg0.getY() > 214 && arg0.getY() < 278){
						pause=false;
					}
				}
				}
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		
}