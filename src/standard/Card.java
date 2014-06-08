package standard;

import java.util.Calendar;
import java.util.Random;

import javax.swing.JOptionPane;

public class Card {
	int card_number;

	public Card() {
	}

	public Card(int num) {
		Calendar now = Calendar.getInstance();
		int seed = now.get(Calendar.MILLISECOND) + num;
		Random r = new Random();
		r.setSeed(seed);
		this.setCardNumber(Math.abs(r.nextInt() % 10) + 1);
	}

	public void setCardNumber(int card_number) {
		this.card_number = card_number;
	}

	public int getCardNumber() {
		return card_number;
	}

	public void exec(Piece player) {
		switch (this.card_number) {
		// corner
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		// special maps
		case 4:
			System.out.println("Player " + player.getName()
					+ " moved one block forward.");
			player.movePosition(1);
			break;
		case 5:
			System.out.println("Player " + player.getName()
					+ " moved one block back.");
			player.movePosition(-1);
			break;
		case 6:
			System.out.println("Player " + player.getName()
					+ " moved to start point.");
			player.setPosition(0);
			break;
		case 7:
			System.out.println("Player " + player.getName()
					+ " moved to start point.");
			player.setPosition(0);
			break;
		case 8:
			System.out.println("Player " + player.getName()
					+ " moved to start point.");
			player.setPosition(0);
			break;
		case 9:
			System.out.println("Player " + player.getName()
					+ " moved to start point.");
			player.setPosition(0);
			break;
		case 10:
			break;
		// normal
		default:
		}
	}

	public String getTypeText(int card_number) {
		String msg = null;
		switch (card_number) {
		// corner
		case 0:
			msg = "Test0";
			break;
		case 1:
			msg = "Test1";
			break;
		case 2:
			msg = "Test2";
			break;
		case 3:
			msg = "Test3";
			break;
		// special maps
		case 4:
			msg = "승갓의 가호를 받아 앞으로 한 칸 전진하게 됩니다.";
			break;
		case 5:
			msg = "방향 감각 상실";
			break;
		case 6:
			msg = "H..hi...";
			break;
		case 7:
			msg = "Test7";
			break;
		case 8:
			msg = "Test8";
			break;
		case 9:
			msg = "Test9";
			break;
		case 10:
			msg = "Test10";
			break;
		// normal
		default:
			msg = "Normal";
		}
		return msg;
	}
}