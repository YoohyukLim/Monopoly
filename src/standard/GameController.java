package standard;

import java.util.ArrayList;

public class GameController {
	Board board;
	ArrayList<Piece> Players;
	
	public GameController(){
	}
	
	public void setPlayer(ArrayList<Piece> Players) {
		this.Players = Players;
	}
	
	public void setView(Board board) {
		this.board = board;
	}
}