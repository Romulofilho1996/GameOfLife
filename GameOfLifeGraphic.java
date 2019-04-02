import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class GameOfLifeGraphic extends JFrame{
	
	public int w, h, r, c, altura, comprimento;
	int k, z;
	static Graphics g;
	Statistics statistics;
	JFrame frame;
	JTextField getrow;
	JTextField getcolumn;
	JTextField generationskip;
	JButton add;
	JButton next;
	JButton halt;
	JButton go;
	JButton undo;
	EstrategiaDeDerivacao engine;
	Cell cell[][] = new Cell[12][12];
	NewCell[][] nc = new NewCell[11][11]; 
	int[][] position = new int[100][2];
	Originator originator = new Originator();
    Caretaker caretaker = new Caretaker();
//-------------- Adiciona botoes
	public GameOfLifeGraphic(EstrategiaDeDerivacao engine){
		getrow = new JTextField("0");
		getcolumn = new JTextField("0");
		generationskip = new JTextField("0");
		add = new JButton("ADD");
		next = new JButton("NEXT");
		halt = new JButton("HALT");
		go = new JButton("GO");
		undo = new JButton("UNDO");
		k = 0;
		z = 0;
		for(int i = 0; i < 11; i++){
			for(int j = 0; j < 11; j++){
				nc[i][j] = new NewCell();
			}
		}
		statistics = new Statistics();
		this.engine = engine;
	}
//---------------------------------------- Revive celula
	public void makeCellAlive(int i, int j){
		boolean alive = true;
		if((i != 0 && j!= 0) && (i != 0 || j!= 0)){
			cell[i][j].add(nc[i][j]);
			cell[i][j].revive();
			statistics.recordRevive();
		}
	}
//---------------------------------------- Mata celula
	public void makeCellDie(int i, int j){
		boolean alive = false;
		if((i != 0 && j!= 0) && (i != 0 || j!= 0)){
			cell[i][j].remove(nc[i][j]);
			cell[i][j].kill();
			statistics.recordKill();
		}
	}
//---------------------------------------- Pega coordenadas
	public int[] getCoordinates(){
		String row, column;
		int[] values = new int[2];
		row = getrow.getText();
		column = getcolumn.getText();
		values[0] = Integer.parseInt(row);
		values[1] = Integer.parseInt(column);
		return values;
	}
//---------------------------------------- Pula n geracoes
	public int generationSkips(){
		String g;
		int gen;
		g = generationskip.getText();
		gen = Integer.parseInt(g);
		return gen;
	}
//----------------------------------------baseado na atual matriz[][], cria a nova geração
	public void nextGeneration() {
		List<Cell> mustRevive = new ArrayList<Cell>();
		List<Cell> mustKill = new ArrayList<Cell>();
		List<Integer> rowsRevive = new ArrayList<Integer>();
		List<Integer> columnsRevive = new ArrayList<Integer>();
		List<Integer> rowsKill = new ArrayList<Integer>();
		List<Integer> columnsKill = new ArrayList<Integer>();
		engine.set(cell);
		for (int i = 1; i < r; i++) {
			for (int j = 1; j < c; j++) {
				if (engine.shouldRevive(i, j, w, h)) {
					mustRevive.add(cell[i][j]);
					rowsRevive.add(i);
					columnsRevive.add(j);

				} 
				else if ((!engine.shouldKeepAlive(i, j, w, h)) && cell[i][j].isAlive()) {
					mustKill.add(cell[i][j]);
					rowsKill.add(i);
					columnsKill.add(j);
				}
			}
		}

		for(Integer i: rowsRevive){
			for(Integer j: columnsRevive){
				if(mustRevive.contains(cell[i][j])){
					makeCellAlive(i,j);
				}
			}
		}

		for(Integer i: rowsKill){
			for(Integer j: columnsKill){
				if(mustKill.contains(cell[i][j])){
					makeCellDie(i,j);
				}
			}
		}
		//originator.setState salva o estado atual nele mesmo para depois salvar em um memento
		//originator.saveStateToMemento salva o estado do originator para um novo memento que sera adicionado em uma lista
		//carataker.add salva o memento acima em uma lista. Cria a lista automaticamente se nao houver nenhum elemento
		originator.setState(cell); 
		caretaker.add(originator.saveStateToMemento());	
	}
//---------------------------------------- Volta n geraçoes // notação: estado = Cell[][] ("matriz" de celulas)

	void undo(){ 
		//'Caretaker.get()' pega o ultimo memento da lista. 
		//'originator.getStateFromMemento(memento)' pega o estado do memento.
		//originator.getState() retorna cell[][] que é o novo estado que deve ser mostrado apos o undo
		originator.getStateFromMemento(caretaker.get()); 
		   
		for(int i=1;i<11;i++){
			for(int j=1; j<11; j++){
				cell[i][j].remove(nc[i][j]); //zerando a matriz cell para retirar o lixo do estado atual
				cell[i][j].kill();
			 	if(originator.getState()[i][j].isAlive()){  //recriando a matriz com o novo cell[][] apos undo
					cell[i][j].add(nc[i][j]);
					cell[i][j].revive();
				} 
			}
		}
//OBS: os metodos makeCellDie e makeCellAlive nao podem ser usados para nao interferir no statistics.
	}
//----------------------------------------

	void display(int width, int height, int rows, int columns, Graphics g){
		w = width;
		h = height;
		r = rows;
		c = columns;
		frame = new JFrame("Game of Life");
		JLabel row = new JLabel("    Linha");
		JLabel empty1 = new JLabel();
		JLabel column = new JLabel("  Coluna");
		JLabel geracao = new JLabel("Geraçoes");
//--------------
		add.addActionListener(new ActionListener(){ //adiciona celula
			public void actionPerformed(ActionEvent e){
				try{
					position[k] = getCoordinates();
					makeCellAlive(position[k][z], position[k][z+1]);
					frame.invalidate();
					frame.repaint();
					k++;
					frame.revalidate();
					pack();
				}catch(ArrayIndexOutOfBoundsException ex){
					JOptionPane.showMessageDialog(null, "Valor invalido, selecione outra posiçao");
				}
			}
		});
//--------------
		next.addActionListener(new ActionListener(){ //proxima geracao
			public void actionPerformed(ActionEvent e){
				try{
					nextGeneration();
					frame.invalidate();
					frame.repaint();
					frame.revalidate();
					pack();
				}catch(ArrayIndexOutOfBoundsException ex){

				}
			}
		});
//--------------
		halt.addActionListener(new ActionListener(){ //estatisticas
			public void actionPerformed(ActionEvent e){
				statistics.displayGraphic();
			}
		});
//--------------
		go.addActionListener(new ActionListener(){ //geracoes a frente
			public void actionPerformed(ActionEvent e){
				Runnable runnable = new Runnable(){
					int i = 0;
					public void run(){
						while(i < generationSkips()){
							nextGeneration();
							frame.invalidate();
							frame.repaint();
							frame.revalidate();
							pack();
							i++;
							try{
								Thread.sleep(120);
							}catch(InterruptedException ex){

							}
						}
					}
				};

				Thread thread = new Thread(runnable);
				if(generationSkips() > 0)
					thread.start();
				
				else{
					JOptionPane.showMessageDialog(null, "Opçao invalida, tente novamente");
				}
			}
		});
//--------------
		undo.addActionListener(new ActionListener(){ //geracoes pra tras. Usando a mesma logica de animacao do 'go'
			public void actionPerformed(ActionEvent e){
				Runnable runnable = new Runnable(){
					int i = 0;
					public void run(){
						while(i < generationSkips()){
							undo(); //chamando o metodo undo aqui
							frame.invalidate();
							frame.repaint();
							frame.revalidate();
							pack();
							i++;
							try{
								Thread.sleep(120);
							}catch(InterruptedException ex){

							}
						}
					}
				};

				Thread thread = new Thread(runnable);
				if(generationSkips() > 0)
					thread.start();
				
				else{
					JOptionPane.showMessageDialog(null, "Opçao invalida, tente novamente");
				}
			}
		});
//--------------
		frame.setSize(w = width,h = height);
		altura = height/rows;
		comprimento = width/columns;
		frame.setLayout(new GridLayout(13,13));
		position[0] = getCoordinates();
		position[1] = getCoordinates();


		for(int j = 0; j < 12; j++){
				cell[0][j] = new Cell();
				if(j!=0){
					if(j!=11)
						cell[0][j].add(new JLabel(Integer.toString(j)));
				}
				frame.add(cell[0][j]);
		}

		for(int i = 1; i < 12; i++){
			for(int j = 0; j < 12; j++){
				cell[i][j] = new Cell();
					if(j == 0)
						if(j!=11 && i!=11)
							cell[i][j].add(new JLabel(Integer.toString(i)));
					frame.add(cell[i][j]);
				
			}
		}

		frame.add(row);
		frame.add(getrow);
		frame.add(column);
		frame.add(getcolumn);
		frame.add(add);
		frame.add(next);
		frame.add(halt);
		frame.add(empty1);
		frame.add(geracao);
		frame.add(generationskip);
		frame.add(go);
		frame.add(undo);

		frame.setSize(700,700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}
