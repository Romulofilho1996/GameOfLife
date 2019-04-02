import javax.swing.*;
import java.awt.*;
import java.security.*;
import java.util.*;

public class LifePanel extends Canvas{
	private int width, height, rows, columns;
	private int altura;
	private int comprimento;
	private Statistics statistics;
	private Cell[][] cells;

	public LifePanel(int w, int h, int r, int c, Statistics statistics){
		setSize(width = w, height = h);
		rows = r;
		columns = c;
		altura = height / (rows);
		comprimento = width / (columns);

		cells = new Cell[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				cells[i][j] = new Cell();
			}
		}

		this.statistics = statistics;
		//paintComponent()
	}

	public void paint(Graphics g){
		int i;
      	width = getSize().width;
      	height = getSize().height;

      	for (i = 0; i < rows; i++)
      	g.drawLine(0, i * altura , width, i * altura );

      	for (i = 0; i < columns; i++)
      	g.drawLine(i * comprimento , 0, i * comprimento , height);

      	makeCellAlive(4,4,g);
      	makeCellAlive(4,5,g);
      	makeCellAlive(4,3,g);
      	makeCellAlive(5,4,g);
      	makeCellAlive(3,4,g);
      	makeCellAlive(7,1,g);

      	nextGeneration();

	}

	public void nextGeneration() {
		ArrayList<Cell> mustRevive = new ArrayList<Cell>();
		ArrayList<Cell> mustKill = new ArrayList<Cell>();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (shouldRevive(i, j)) {
					mustRevive.add(cells[i][j]);
				} 
				else if ((!shouldKeepAlive(i, j)) && cells[i][j].isAlive()) {
					mustKill.add(cells[i][j]);
				}
			}
		}
		
		for (Cell cell : mustRevive) {
			cell.revive();
			statistics.recordRevive();
		}
		
		for (Cell cell : mustKill) {
			cell.kill();
			statistics.recordKill();
		}
	}

	public void makeCellAlive(int i, int j, Graphics g) throws InvalidParameterException{
		if(i<columns && j<rows){
			g.drawOval((i*(width/10))+(altura/4), (j*(height/10))+(comprimento/4), 15, 15);
			cells[i][j].revive();
			statistics.recordRevive();
		}
		else{		//criar novo frame de alerta
			JOptionPane.showMessageDialog(null, "Posiçao invalida");
		}
	}

	public boolean isCellAlive(int i, int j) throws InvalidParameterException {
		if(i<columns && j<rows) {
			return cells[i][j].isAlive();
		}
		else {
			throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}

	public int numberOfAliveCells() {
		int aliveCells = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(isCellAlive(i,j)) {
					aliveCells++;
				}
			}
		}
		return aliveCells;
	}

	/* verifica se uma celula deve ser mantida viva */
	private boolean shouldKeepAlive(int i, int j) {
		return (cells[i][j].isAlive())
				&& (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3);
	}

	/* verifica se uma celula deve (re)nascer */
	private boolean shouldRevive(int i, int j) {
		return (!cells[i][j].isAlive())
				&& (numberOfNeighborhoodAliveCells(i, j) == 3);
	}

	/*
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 */
	private int numberOfNeighborhoodAliveCells(int i, int j) {
		int alive = 0;
		for (int a = i - 1; a <= i + 1; a++) {
			for (int b = j - 1; b <= j + 1; b++) {
				if (validPosition(a, b)  && (!(a==i && b == j)) && cells[a][b].isAlive()) {
					alive++;
				}
			}
		}
		return alive;
	}

	/*
	 * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
	 */
	private boolean validPosition(int a, int b) {
		return a >= 0 && a < height && b >= 0 && b < width;
	}


}