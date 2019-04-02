import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Main {
	static Graphics g;
	private JFrame frame;
	private JButton r1;
	private JButton r2;
	private JButton r3;
	private JButton start;
	private GameOfLifeGraphic game;
	private Regra1 Regra1;
	private Regra2 Regra2;
	private Regra3 Regra3;


	public Main(){
		Regra1 = new Regra1();
		Regra2 = new Regra2();
		Regra3 = new Regra3();
		game = new GameOfLifeGraphic(Regra1);
		r1 = new JButton("1. Tradicional");
		r2 = new JButton("2. HighLife");
		r3 = new JButton("3. ModoHard");
		start = new JButton("Start Game");
		frame = new JFrame("Game of Life");
	}

	public void interfaces(){

		frame.setLayout(new GridLayout(4,3));
		r1.setBackground(new Color(255,255,240));
		r2.setBackground(new Color(255,255,240));
		r3.setBackground(new Color(255,255,240));
		start.setBackground(new Color(238,216,174));
		frame.add(r1);
		frame.add(r2);
		frame.add(r3);
		frame.add(start);
		frame.setSize(300,300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JOptionPane.showMessageDialog(null, "Bem-Vindo ao Game Of Life\n1. Selecione um dos 3 pacotes de Regras\n2. Aperte 'Start Game'\n3. Divirta-se!");
		r1.addActionListener(new ActionListener(){ //adiciona celula
			public void actionPerformed(ActionEvent e){
				try{
					game = new GameOfLifeGraphic(Regra1);
					JOptionPane.showMessageDialog(null, "Regra selecionada: Tradicional\n1.Qualquer célula viva com menos de dois vizinhos vivos morre de solidão.\n2.Qualquer célula viva com mais de três vizinhos vivos morre de superpopulação.\n3.Qualquer célula morta com exatamente três vizinhos vivos se torna uma célula viva.\n4.Qualquer célula viva com dois ou três vizinhos vivos continua no mesmo estado para a próxima geração.");
				}catch(ArrayIndexOutOfBoundsException ex){
					JOptionPane.showMessageDialog(null, "Regra: ModoTradicional.");
				}
			}
		});

		r2.addActionListener(new ActionListener(){ //adiciona celula
			public void actionPerformed(ActionEvent e){
				try{
					game = new GameOfLifeGraphic(Regra2);
					JOptionPane.showMessageDialog(null, "Regra selecionada: HighLife\n1.Qualquer célula viva com menos de quatro vizinhos vivos morre de solidão.\n2.Qualquer célula viva com mais de cinco vizinhos vivos morre de superpopulação.\n3.Qualquer célula morta com exatamente cinco vizinhos vivos se torna uma célula viva.\n4.Qualquer célula viva com quatro ou cinco vizinhos vivos continua no mesmo estado para a próxima geração.");
				}catch(ArrayIndexOutOfBoundsException ex){
					JOptionPane.showMessageDialog(null, "Regra: HighLife.");
				}
			}
		});

		r3.addActionListener(new ActionListener(){ //adiciona celula
			public void actionPerformed(ActionEvent e){
				try{
					game = new GameOfLifeGraphic(Regra3);
					JOptionPane.showMessageDialog(null, "Regra selecionada: ModoHard\n1.Qualquer célula viva com menos de cinco vizinhos vivos morre de solidão.\n2.Qualquer célula viva com mais de 6 vizinhos vivos morre de superpopulação.\n3.Qualquer célula morta com exatamente 8 vizinhos vivos se torna uma célula viva.\n4.Qualquer célula viva com cinco ou seis vizinhos vivos continua no mesmo estado para a próxima geração.");
				}catch(ArrayIndexOutOfBoundsException ex){
					JOptionPane.showMessageDialog(null, "Regra: ModoHard.");
				}
			}
		});

		start.addActionListener(new ActionListener(){ //adiciona celula
			public void actionPerformed(ActionEvent e){
				try{
					game.display(700,700,11,11,g);
				}catch(ArrayIndexOutOfBoundsException ex){
					JOptionPane.showMessageDialog(null, "Valor invalido, selecione outra posiçao");
				}
			}
		});
	}


	public static void main(String args[]) {
		Main main = new Main();
	    main.interfaces();	
	}
}
