package standard;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GameController {
	Board board;
	ArrayList<Piece> Players;
	static int currentPlayer;
	int playerN;
	int turn;
	int DiceNumber, Dice1, Dice2;
	Dice dice = new Dice();

	public GameController() {
	}

	public void setPlayerbyDice() {
		Dice1 = dice.exec(1);
		Dice2 = dice.exec(2);
		DiceNumber = Dice1 + Dice2;
		String msg = "Dice Number: " + Dice1 + " + " + Dice2 + " = "
				+ DiceNumber;
		JOptionPane.showMessageDialog(null, msg);

		Players.get(currentPlayer).movePosition(DiceNumber);
		System.out
				.println("CurrentPlayer: "
						+ Players.get(currentPlayer).getName() + " Dice: "
						+ DiceNumber);
	}

	public void MapExec() {
		System.out.println("Map Type: "
				+ Map.Map_pieces[Players.get(currentPlayer).getPosition()]
						.get_map_number());
		Map.Map_pieces[Players.get(currentPlayer).getPosition()].exec(Players
				.get(currentPlayer));

		for (int i = 0; i < playerN; i++)
			System.out.println(Players.get(i).getName() + ": "
					+ Players.get(i).getPosition());
	}

	public void changePlayer() {
		turn++;
		currentPlayer = (currentPlayer + 1) % playerN;
	}

	public void setPlayer(ArrayList<Piece> Players) {
		GameController.currentPlayer = 0;
		this.turn = 0;
		this.playerN = Players.size();
		this.Players = Players;
	}

	public void setView(Board board) {
		this.board = board;
		System.out.println("GameController got a Board");
	}
}