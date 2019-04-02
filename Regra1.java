public class Regra1 extends EstrategiaDeDerivacao{
	Cell cell[][] = new Cell[12][12];

	public Regra1(){
		for(int i=1; i<11; i++){
			for(int j=1;j<11;j++){
				cell[i][j] = new Cell();
			}
		}
	}

	public void set(Cell cell1[][]){
		for(int i=1; i<11; i++){
			for(int j=1;j<11;j++){
				cell[i][j] = cell1[i][j];
			}
		}

	}
//---------------------------------------- verifica se posicao ta no limite //bool
	public boolean validPosition(int a, int b, int w, int h) { //recebe mais 2 ints
		return a >= 0 && a < h && b >= 0 && b < w;
	}

	//---------------------------------------- Celula continua viva? //bool
	public boolean shouldKeepAlive(int i, int j, int w, int h) {
		return (cell[i][j].isAlive())
				&& (numberOfNeighborhoodAliveCells(i, j, w, h) == 2 || numberOfNeighborhoodAliveCells(i, j, w, h) == 3);
	}
//---------------------------------------- Celula deve viver? //bool
	public boolean shouldRevive(int i, int j, int w, int h) { 
		return (!cell[i][j].isAlive())
				&& (numberOfNeighborhoodAliveCells(i, j, w, h) == 3);
	}

	//---------------------------------------- Numero de vizinhos vivos 
	public int numberOfNeighborhoodAliveCells(int i, int j, int w, int h) {
		int alive = 0, c ,d;
		
		for (int a = i - 1; a <= i + 1; a++) {
			for (int b = j - 1; b <= j + 1; b++) {
				c=a;
				d=b;
				if(c==0)
					c=10;
				if(c==11)
					c=1;
				if(d==0)
					d=10;
				if(d==11)
					d=1;
				if (validPosition(c, d, w, h)  && (!(c==i && d == j)) && cell[c][d].isAlive()) {
					alive++;
				}
			}
		}
		return alive;
	}
}