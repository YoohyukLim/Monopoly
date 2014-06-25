package model;

import java.util.Calendar;
import java.util.Random;

import javax.swing.JOptionPane;

public class Card {
	int card_number;

	public Card() {
		Calendar now = Calendar.getInstance();
		int seed = now.get(Calendar.MILLISECOND);
		Random r = new Random();
		r.setSeed(seed);
		this.setCardNumber(Math.abs(r.nextInt() % 10) + 1);
	}
	
	public Card(int num){
		this.card_number = num;
	}

	public void setCardNumber(int card_number) {
		this.card_number = card_number;
	}

	public int getCardNumber() {
		return card_number;
	}

	public void exec(int master, Piece player, int card_number) {
		switch (card_number) {
		case 1:
			System.out.println("니달리의 덫\n니달리의 방해를 받아 한 칸 후퇴합니다.");
			player.movePosition(1);
			break;
		case 2:
			System.out.println("잔나의 계절풍\n잔나가 플레이어를 밀쳐내 세 칸 후퇴합니다.");
			player.movePosition(-3);
			break;
		case 3:
			System.out.println("기민함의 노래\n소나의 오오라로 두 칸 전진합니다.");
			player.movePosition(2);
			break;
		case 4:
			System.out.println("승갓의 가호\n승갓의 가호를 받아 한 칸 전진합니다.");
			player.movePosition(1);
			break;
		case 5:
			System.out.println("샤코의 깜짝 상자\n방향 감각을 상실하여 한 칸 후퇴합니다.");
			player.movePosition(-1);
			break;
		case 6:
			System.out.println("람머스의 도발\n람머스를 따라가 두 칸 후퇴합니다.");
			player.movePosition(-2);
			break;
		case 7:
			System.out.println("흑점 폭발\n레오나가 방해하여 시작 위치로 돌아갑니다.");
			player.setPosition(0);
			break;
		case 8:
			System.out.println("민병대\n이동속도가 빨라져 한 바퀴를 더 돕니다.");
			player.rotationCnt++;
			break;
		case 9:
			System.out.println("로켓 손\n블리츠크랭크가 끌어당겨 네 칸 후퇴합니다.");
			player.movePosition(-4);
			break;
		case 10:
			System.out.println("달빛 낙하\n다이애나가 주변을 모아 두 칸 후퇴합니다.");
			player.movePosition(-2);
			break;
		default:
		}
	}

	public String getTypeText(int card_number) {
		String msg = null;
		switch (card_number) {
		// corner
		case 1:
			msg = "니달리의 덫\n니달리의 방해를 받아 한 칸 후퇴합니다.";
			break;
		case 2:
			msg = "잔나의 계절풍\n잔나가 플레이어를 밀쳐내 세 칸 후퇴합니다.";
			break;
		case 3:
			msg = "기민함의 노래\n소나의 오오라로 두 칸 전진합니다.";
			break;
		// special maps
		case 4:
			msg = "승갓의 가호\n승갓의 가호를 받아 한 칸 전진합니다.";
			break;
		case 5:
			msg = "샤코의 깜짝 상자\n방향 감각을 상실하여 한 칸 후퇴합니다.";
			break;
		case 6:
			msg = "람머스의 도발\n람머스를 따라가 두 칸 후퇴합니다.";
			break;
		case 7:
			msg = "흑점 폭발\n레오나가 방해하여 시작 위치로 돌아갑니다.";
			break;
		case 8:
			msg = "민병대\n이동속도가 빨라져 한 바퀴를 더 돕니다.";
			break;
		case 9:
			msg = "로켓 손\n블리츠크랭크가 끌어당겨 네 칸 후퇴합니다.";
			break;
		case 10:
			msg = "달빛 낙하\n다이애나가 주변을 모아 두 칸 후퇴합니다.";
			break;
		// normal
		default:
			msg = "Normal";
		}
		return msg;
	}
}