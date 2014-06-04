package standard;

import java.util.ArrayList;

public class GameController {
	Board board;
	ArrayList<Piece> Players;
	int currentPlayer;
	int playerN;
	int turn;
	
	public GameController(){
	}
	
	public void setPlayerbyDice(int DiceNumber){
		Players.get(currentPlayer).movePosition(DiceNumber);
		System.out.println("CurrentPlayer: "+Players.get(currentPlayer).getName()+" Dice: "+DiceNumber);
		for(int i=0; i<playerN; i++)
			System.out.println(Players.get(i).getName()+": "+Players.get(i).getPosition());
	}
	
	public void changePlayer(){
		turn++;
		currentPlayer = (currentPlayer+1)%playerN;
	}
	
	public void setPlayer(ArrayList<Piece> Players) {
		this.currentPlayer = 0;
		this.turn = 0;
		this.playerN = Players.size();
		this.Players = Players;
	}
	
	public void setView(Board board) {
		this.board = board;
	}
}