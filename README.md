# GameOfLifeGraphical
GameOfLife with graphical UI and some functions added

##Readme
Pedro Henrique Santos Gonzaga - 140030131
Rômulo de Vasconcelos Feijão Filho - 140031260
Yan Victor dos Santos - 140033599

##O jogo Game of Life pode ser compilado e executado da seguinte forma:
#Pelo terminal, digite:
javac Main.java
#Em seguida digite:
java Main

##Como jogar: ao executar o jogo pelo terminal, abrirá um menu contendo 3 opções de escolhas. Tais escolhas são pacotes de regras criadas e implementadas. O jogador deverá selecionar um dos pacotes de regras e em seguidar apertar em "Start Game" para jogar o Game Of Life seguindo as regras do pacote selecionado.

##Flexibilidade de regras: É possível criar novas regras e alterar as existentes apenas por meio do framework utilizado para a implementação. Para novas regras, basta implementar regras em novas subclasses de 'EstrategiaDeDerivacao'. Para alterar as existentes, basta alteras nos arquivos enviados. Em seguida, na classe Main, deve ser inserido as regras criadas, seguindo o padrão usado para as regras já existentes. A inserção foi feita usando injeção de dependências. Assim a classe 'GameOfLifeGraphic' usa a regra selecionada através da injeção.
