package standard;

import java.util.ArrayList;

public class Monopoly {
	public static void main(String args[]) throws Exception {
		Map map = new Map();
		ArrayList<Piece> Players = new ArrayList<Piece>(2);
		Piece Player1 = new Piece(0, 0);
		Player1.setName("Master Yi");
		Piece Player2 = new Piece(0, 0);
		Player2.setName("Sonokong");
		
		Players.add(Player1);
		Players.add(Player2);
		
		map.generate_map();
		for (int i = 0; i < Map.Map_size; i++) {
			System.out.print(Map.Map_pieces[i].map_number + " ");
			if ((i + 1) % (Map.Map_size / Map.Map_side) == 0)
				System.out.print("\n");
		}
		System.out.print("\n");
		
		GameController gameController = new GameController();
		gameController.setPlayer(Players);
		
		Board board = new Board(map);
		board.getController(gameController);
	}
}
