package aula8.exercicio1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class Exercicio1 extends JFrame{
	
	static JogoDoGalo jogo;
	static JToggleButton[] botoes = new JToggleButton[9];
	
	public static void main(String []args) {
			
		jogo = new JogoDoGalo("X");
		
		JFrame frame = new JFrame("Jogo Do Galo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		
		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(3,3));
		painel.setBackground(Color.white);
		
		
		for (int i = 0; i < 9; i++) {
			botoes[i] = new JToggleButton();
			painel.add(botoes[i]);
			botoes[i].setFont(new Font("Arial",Font.BOLD,50));
			botoes[i].setBackground(Color.white);
			
			final int id = i;
			
			botoes[i].addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent itemEvent) {
					
					String str = JogoDoGalo.getString(jogo.getNext());
					
					if (jogo.play(id)) {
						JToggleButton b = (JToggleButton) itemEvent.getItem();
						b.setText(str);
						b.setEnabled(false);
						
						int resultado = jogo.checkVictory();
						
						if (resultado == 1 || resultado == 2) {
							JOptionPane.showMessageDialog(frame, "O Jogador que ganhou foi: " + JogoDoGalo.getString(resultado) + JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
						else if (resultado == 3) {
							JOptionPane.showMessageDialog(frame, "Empate!","Empate!",JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}	
					}
				}
			});
		}
		
		frame.setContentPane(painel);
		frame.setVisible(true);
	}
}
