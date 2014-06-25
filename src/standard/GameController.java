package standard;

import gameClient.monoClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JOptionPane;

import protocol.ChatProtocol;
import protocol.GameProtocol;

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
	public String myName;
	public int cardmap[][];
	public int myNumber;

	public int[] card;

	public monoClient monoClient;

	public GameController() {
	}

	public void diceButton() {
		if (getMyName().equals(Players.get(currentPlayer).getName())) {
			System.out.println("/********************************/");
			if (board.lessThanFive == false) {
				JOptionPane.showMessageDialog(null, "지울 카드를 클릭해야 합니다.");
				return;
			} else if (board.cardtime == true) {
				JOptionPane.showMessageDialog(null, "카드를 사용하지 않고 넘어갑니다.");

				try {
					dofinal();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
			}

			board.lessThanFive = getCard();

			if (board.lessThanFive == true) {
				try {
					dorest();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public void dorest() throws Exception {
		board.executableCards();

		GameProtocol data = new GameProtocol(monoClient.name,
				GameProtocol.GAME_DOREST);
		data.setRoomName(monoClient.roomName);
		data.setCurrentPlayer(Players.get(currentPlayer).getName(),
				Players.get(currentPlayer).rotationCnt,
				Players.get(currentPlayer).catchCnt, turn);
		monoClient.sendToServer(data);
	}

	public void dorestServer() {
		try {
			new Champion_Sound(Players.get(currentPlayer).getType()).normal();
			board.disappearPiece(currentPlayer);
			setPlayerbyDice();
			board.refreshInfo(currentPlayer);

			CardExec();
			MapExec();
			CardExec();
			board.refreshInfo(currentPlayer);

			catching();
			board.showPiece(currentPlayer);

			board.Dice_button.setVisible(false);
			board.Next_button.setVisible(true);

			if (getMyName().equals(Players.get(currentPlayer).getName()))
				board.executableCards();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dofinal() {
		GameProtocol data = new GameProtocol(monoClient.name,
				GameProtocol.GAME_DOFINAL);
		data.setRoomName(monoClient.roomName);
		data.setCurrentPlayer(Players.get(currentPlayer).getName(),
				Players.get(currentPlayer).rotationCnt,
				Players.get(currentPlayer).catchCnt, turn);
		monoClient.sendToServer(data);
	}

	public void dofinalServer() throws Exception {
		board.Next_button.setVisible(false);
		board.Dice_button.setVisible(true);
		board.gameController.missionCheck();
		board.refreshInfo(currentPlayer);
		currentPlayer = changePlayer();
		board.refreshInfo(currentPlayer);
		board.cardtime = false;
	}

	public void setPlayerbyDice() throws Exception {
		Dice1 = monoClient.Dice[0];
		Dice2 = monoClient.Dice[1];

		DiceNumber = Dice1 + Dice2;
		String msg = "Dice Number: " + Dice1 + " + " + Dice2 + " = "
				+ DiceNumber;

		if (getMyName().equals(Players.get(currentPlayer).getName()))
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

	public void CardExec() {
		Piece player = Players.get(currentPlayer);
		int position = player.getPosition();
		if (cardmap[position][0] == 0)
			return;

		System.out.println("//Card Type: " + cardmap[position][2] + "//");
		System.out.println("//" + new Card().getTypeText(cardmap[position][2])
				+ "//");
		if (getMyName().equals(Players.get(currentPlayer).getName()))
			JOptionPane.showMessageDialog(null,
					new Card().getTypeText(cardmap[position][2]));
		new Card().exec(cardmap[position][1], player, cardmap[position][2]);
		board.disappearCard(position);

		for (int i = 0; i < playerN; i++)
			System.out.println(Players.get(i).getName() + ": "
					+ Players.get(i).getPosition());
	}

	public boolean getCard() {
		Card card = new Card();
		Calendar now = Calendar.getInstance();
		int seed = now.get(Calendar.MILLISECOND);
		Random r = new Random();
		r.setSeed(seed);

		if (Math.abs(r.nextInt() % 3) < 2)
			return true;

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
			board.showCard(Players.get(currentPlayer).cardList.size());
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

	public boolean useCard(int n) {
		int position = Players.get(currentPlayer).getPosition();
		if (position == 0) {
			JOptionPane.showMessageDialog(null, "시작 위치에는 카드를 놓을 수 없습니다.");
			return false;
		}

		if (cardmap[position][0] == 1) {
			JOptionPane.showMessageDialog(null, "카드가 이미 존재합니다.");
			return false;
		}
		card = new int[3];
		card[0] = 1;
		card[1] = currentPlayer;
		card[2] = Players.get(currentPlayer).cardList.get(n).getCardNumber();
		
		System.out.println("Card" + n + " is used");
		Players.get(currentPlayer).deleteCard(n);
		board.disappearCard(n);
		try {
			board.executableCards();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public void sendCardtoServer() {
		GameProtocol data = new GameProtocol(monoClient.name,
				GameProtocol.GAME_USECARD);
		data.setRoomName(monoClient.roomName);
		data.setCurrentPlayer(Players.get(currentPlayer).getName(),
				Players.get(currentPlayer).rotationCnt,
				Players.get(currentPlayer).catchCnt, turn);
		data.setCard(card, Players.get(currentPlayer).getPosition());
		monoClient.sendToServer(data);
	}

	public void useCardServer(int[] card, int cardPosition) {
		cardmap[cardPosition][0] = card[0];
		cardmap[cardPosition][1] = card[1];
		cardmap[cardPosition][2] = card[2];
		board.showCard(cardPosition);
	}

	public void deleteCard(int n) {
		System.out.println("Card " + n + " is deleted");
		Players.get(currentPlayer).deleteCard(n);
		board.disappearCard(n);
	}

	public void sendMessage(String text) {
		String message = new String("[" + myName + "]" + ": " + text + "\n");
		monoClient.sendToServer(new ChatProtocol(myName, message,
				monoClient.roomName, ChatProtocol.SEND_MESSAGE));
	}

	public void recieveMessage(String text) {
		board.chatRead.append(text);
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

	public void catching() throws Exception {
		int nowposition = Players.get(currentPlayer).getPosition();
		int size = Players.size();
		Sound defeat = new Sound("Resources/sounds/game/defeat_enemy1.wav");
		if (nowposition == 0)
			return;
		for (int i = 0; i < size; i++) {
			if (i != currentPlayer) {
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

	public void missionCheck() throws Exception {
		int size = Players.size();
		for (int i = 0; i < size; i++) {
			if (Players.get(i).missionCheck())
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

	public void loadMissionDialog(int i) throws Exception {
		System.out.println("Game Over!");
		new missionDialog(board.frame, this, monoClient.name, Players.get(i)
				.getName());
	}

	public void getClient(monoClient monoClient) {
		this.monoClient = monoClient;
	}

	public int getTurn() {
		return turn / Players.size() + 1;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public int getMyNumber() {
		return myNumber;
	}

	public void setMyNumber(int myNumber) {
		this.myNumber = myNumber;
	}
}