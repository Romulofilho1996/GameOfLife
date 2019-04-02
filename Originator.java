//A classe Originator é responsavel por intermediar o cliente e o caretaker. O estado que se quer manipular é guardado primeiramento aqui como se fosse um pedido. Entao ele envia este mesmo pedido para o caretaker que ira guardar ou recuperar da lista de mementos um determinado estado. 
public class Originator {
   Cell[][] cell = new Cell[12][12];

   public Originator(){ // A matriz inicialmente é apenas uma matriz de referencias. É necessario instanciar elemento por elemento
	for(int i=1;i<11;i++){
         for(int j=1; j<11; j++){
         	cell[i][j] = new Cell(); //Instanciando elemento por elemento
         }
      }
   }

   public void setState(Cell[][] cell1){ //Guardando temporariamente o estado
      for(int i=1;i<11;i++){
         for(int j=1; j<11; j++){
             this.cell[i][j].set(cell1[i][j].isAlive());
         }
      }
   }

   public Cell[][] getState(){ //Retorna o estado temporario
      return cell;
   }

   public Memento saveStateToMemento(){ //retorna para o caretaker um memento contendo o estado temporario daqui
      return new Memento(cell);
   }

   public void getStateFromMemento(Memento memento){ //pegando o estado do memento recuperado pelo caretaker e salvando temporariamente aqui
      for(int i=1;i<11;i++){
         for(int j=1; j<11; j++){
             this.cell[i][j].set(memento.getState()[i][j].isAlive());
         }
      }
      
   }
}
