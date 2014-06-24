package standard;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dialog.cardDialog;
import dialog.missionDialog;

import model.Card;
import model.Dice;
import model.Champion_Sound;
import model.Piece;
import model.Sound;

public class GameController {
	public Board board;
	public ArrayList<Piece> Players;
	public int currentPlayer;
	public int playerN;
	public int turn;
	public int DiceNumber, Dice1, Dice2;
	Dice dice = new Dice();
	public Card tempcard;
	public int cardmap[][];

	public GameController() {
	}

	public void setPlayerbyDice() throws Exception {
		Dice1 = dice.exec(1);
		Dice2 = dice.exec(2);
		DiceNumber = Dice1 + Dice2;
		String msg = "Dice Number: " + Dice1 + " + " + Dice2 + " = "
				+ DiceNumber;
		new Champion_Sound(Players.get(currentPlayer).getType()).normal();
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
	}
	
	public void CardExec(){
		Piece player = Players.get(currentPlayer);
		int position = player.getPosition();
		if(cardmap[position][0]==0)
			return;
		
		System.out.println("//Card Type: "+cardmap[position][2]+"//");
		System.out.println("//"+new Card().getTypeText(cardmap[position][2])+"//");
		JOptionPane.showMessageDialog(null, new Card().getTypeText(cardmap[position][2]));
		new Card().exec(cardmap[position][1], player, cardmap[position][2]);
		board.disappearCard(position);
		
		for (int i = 0; i < playerN; i++)
			System.out.println(Players.get(i).getName() + ": "
					+ Players.get(i).getPosition());
	}

	public boolean getCard() {
		Card card = new Card(1);
		if (Players.get(currentPlayer).cardList.size() < 5) {
			Players.get(currentPlayer).addCard(card);
			System.out.println("Card Type: "
					+ Players.get(currentPlayer).cardList.get(
							Players.get(currentPlayer).cardList.size() - 1)
							.getCardNumber());
		} else {
			this.tempcard = card;
			return loadCardDialog();
		}

		return true;
	}

	public boolean addCard(Card card) {
		if (Players.get(currentPlayer).cardList.size() < 5) {
			Players.get(currentPlayer).addCard(card);
			System.out.println("Card Type: "
					+ Players.get(currentPlayer).cardList.get(
							Players.get(currentPlayer).cardList.size() - 1)
							.getCardNumber());
		} else {
			this.tempcard = card;
			return loadCardDialog();
		}

		return true;
	}
	
	public boolean useCard(int n){
		Piece player = Players.get(currentPlayer);
		int position = player.getPosition();
		if(position == 0){
			JOptionPane.showMessageDialog(null, "시작 위치에는 카드를 놓을 수 없습니다.");
			return false;
		}
		
		if(cardmap[position][0]==1){
			JOptionPane.showMessageDialog(null, "카드가 이미 존재합니다.");
			return false;
		}
		System.out.println("Card"+n+" is used");
		player.deleteCard(n);
		cardmap[position][0]=1;
		cardmap[position][1]=currentPlayer;
		cardmap[position][2]=n;
		board.showCard(position);
		
		return true;
	}

	public void deleteCard(int n) {
		System.out.println("Card " + n + " is deleted");
		Players.get(currentPlayer).deleteCard(n);
	}

	public void setPlayer(ArrayList<Piece> Players) {
		currentPlayer = 0;
		this.turn = 0;
		this.playerN = Players.size();
		this.Players = Players;
		this.cardmap = new int[36][3];
	}
	
	public int changePlayer() {
		turn++;
		return currentPlayer = (currentPlayer + 1) % playerN;
	}
	
	public void catching() throws Exception{
		int nowposition = Players.get(currentPlayer).getPosition();
		int size = Players.size();
		Sound defeat = new Sound("Resources/sounds/game/defeat_enemy1.wav");
		if(nowposition == 0)
			return;
		for(int i=0; i<size; i++){
			if(i != currentPlayer){
				if (nowposition == Players.get(i).getPosition()) {
					System.out.println(Players.get(currentPlayer).getName()
							+ "이 " + Players.get(i).getName() + "을 잡았습니다!");
					defeat.play();
					board.disappearPiece(i);
					Players.get(currentPlayer).catching();
					Players.get(i).caught();
					board.showPiece(i);
				} else if (Players.get(currentPlayer).piece_type == 1
						&& Players.get(i).getPosition() - nowposition <= 3
						&& Players.get(i).getPosition() - nowposition >= -1) {
					System.out.println(Players.get(currentPlayer).getName()
							+ "의 일격필살!!");
					new Sound("Resources/sounds/MasterYi/alpha.wav").play();
					defeat.play();
					board.disappearPiece(i);
					Players.get(currentPlayer).catching();
					Players.get(i).caught();
					board.showPiece(i);
				}
			}
		}
	}
	
	public void missionCheck() throws Exception{
		int size = Players.size();
		for(int i=0; i<size; i++){
			if(Players.get(i).missionCheck())
				loadMissionDialog(i);
		}
	}

	public void setView(Board board) {
		this.board = board;
		System.out.println("GameController got a Board");
	}

	public boolean loadCardDialog() {
		System.out.println("Loaded CardDialog");
		cardDialog dialog = new cardDialog(board.frame, this);
		return dialog.init();
	}
	
	public void loadMissionDialog(int i) throws Exception{
		System.out.println("Game Over!");
		new missionDialog(board.frame, this, i);
	}

	public int getTurn() {
		return turn / Players.size() + 1;
	}
}