import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;

import javax.swing.*;

/*public class TestaEventos extends JFrame{
JButton but=new JButton("Botao");

final JLabel texto= new JLabel("Numero de cliques: 0");
JPanel painel;
int cont;

public TestaEventos(JPanel painel){
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	painel.setLayout(new GridBagLayout());
	GridBagConstraints constraints = new GridBagConstraints();
	constraints.fill = GridBagConstraints.HORIZONTAL;
	constraints.gridx =2;
	constraints.gridy = 0;
	painel.add(ar, constraints);
	//but.addActionListener(this);
	addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e){
		System.exit(0); // fecha e sai do programa
		}}); 
	getContentPane().add(painel);
}}

/*public static void main(String[] args) {
			TestaEventos a = new TestaEventos();
			a.setSize(300,100);
			a.setVisiblse(true);
		}
}*/
