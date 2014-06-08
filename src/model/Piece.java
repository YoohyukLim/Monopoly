package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import standard.GameController;

public class Piece {
	int piece_type;
	int position;

	public int map_size;
	public int rotationCnt;
	String name;
	public ArrayList<Card> cardList = new ArrayList<Card>();

	public Piece(int type, int start_position) {
		piece_type = type;
		position = start_position;
		rotationCnt = 0;
	}

	public void addCard(Card card) {
		cardList.add(card);
	}

	public int getType() {
		return piece_type;
	}

	public int getPosition() {
		return position;
	}

	public int setPosition(int pos) {
		position = pos;
		return position;
	}

	public int movePosition(int dice) {
		int pos = position + dice;
		if (pos < 0) {
			if (rotationCnt != 0)
				rotationCnt--;
		} else if (pos >= map_size - 1)
			rotationCnt++;
		pos = pos % (map_size - 1);

		return setPosition(pos);
	}

	public String setName(String name) {
		this.name = name;
		return this.name;
	}

	public String getName() {
		return this.name;
	}

	public void deleteCard(int n) {
		cardList.remove(n);
	}
}
