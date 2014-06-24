package standard;

import java.util.ArrayList;
import model.Piece;

public class Monopoly {
	public static void main(String args[]) throws Exception {
		Map map = new Map();
		map.generate_map();

		ArrayList<Piece> Players = new ArrayList<Piece>(2);
		Piece Player1 = new Piece(0, 0);
		Player1.setName("Sonokong");
		Player1.map_size = 36;
		Piece Player2 = new Piece(1, 0);
		Player2.setName("MasterYi");
		Player2.map_size = 36;

		Players.add(Player1);
		Players.add(Player2);

		System.out.print("\n");

		GameController gameController = new GameController();
		gameController.setPlayer(Players);

		Board board = new Board(map);
		gameController.setView(board);
		board.getController(gameController);
	}
}
