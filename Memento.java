//A classe memento serve como memoria de estados de matrizes de celulas. Cada memento contem apenas um estado.
public class Memento {
   private Cell cell[][] = new Cell[12][12];

   public Memento(Cell[][] cell2){ //criando a matriz de celula vazia para depois salvar o estado do originator
     for(int i=1;i<11;i++){
         for(int j=1; j<11; j++){
            cell[i][j] = new Cell();
             this.cell[i][j].set(cell2[i][j].isAlive());
         }
      }
   }

   public Cell[][] getState(){
      return cell;
   }	
}
