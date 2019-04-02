import javax.swing.*;

public class LifeFrame{

	static JFrame frame = new JFrame("Game of Life");
	
	public static void main(String[] args){	

		Statistics stats = new Statistics();

		LifePanel panel = new LifePanel(300,300,10,10,stats);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}

}