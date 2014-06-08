package model;

import javax.swing.JOptionPane;

public class Map_piece {
	int current;
	int prev_map;
	int next_map;
	public int map_number;

	public Map_piece() {
	}

	public Map_piece(int current, int post, int next) {
		this.current = current;
		this.prev_map = post;
		this.next_map = next;
	}

	public void setMapNumber(int map_number) {
		this.map_number = map_number;
	}

	public int get_map_number() {
		return map_number;
	}

	public void exec(Piece player) {
		switch (this.map_number) {
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
		case 11:
			break;
		case 12:
			break;
		case 13:
			break;
		// normal
		default:
		}
	}

	public void getTypeText(int map_number) {
		String msg = null;
		switch (map_number) {
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
		case 11:
			msg = "Test11";
			break;
		case 12:
			msg = "Test12";
			break;
		case 13:
			msg = "Test13";
			break;
		// normal
		default:
			msg = "Normal";
		}
		JOptionPane.showMessageDialog(null, msg);
	}
}