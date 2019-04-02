// A classe caretaker é responsavel por acessar a lista que contem mementos. Assim so ele pode remover e adicionar novos mementos.
import java.util.ArrayList;
import java.util.List;

public class Caretaker {
	private ArrayList<Memento> mementoList; //esta classe cria apenas uma unica lista de mementos

	public Caretaker(){
		mementoList = new ArrayList<Memento>();
	}
   
	public void add(Memento state){ //adiciona memento na lista
		mementoList.add(state);
	}

	public Memento get(){ //retorna o ultimo memento da lista para colocar a funçao undo em pratica
		if(mementoList.size()<2){ //E necessario garantir que, se a lista estiver vazia, retorne estado inicial(TodasCelulasMortas)
			Cell cell[][] = new Cell[12][12];
			for(int i=1;i<11;i++){ //criando matriz vazia
				for(int j=1; j<11; j++){
					cell[i][j] = new Cell();
				}
			}
			if(mementoList.size()==1)
				mementoList.remove(0);
			return new Memento(cell);
		}
	//Caso a lista possua mais de um elemento(se tiver 1 ele deve tambem deve zerar a matriz no proximo undo)
	//index é a posicao da lista comecando do 0. Ele pega o ultimo elemento, remove e retorna o anterior. 
		int index = mementoList.size() - 1;
		mementoList.remove(index);
		return mementoList.get(index-1);
	}
}
