package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import standard.GameController;

public class Piece {
	int piece_type;
	int position;
	boolean mission;

	public int map_size;
	public int rotationCnt;
	public int catchCnt;
	String name;
	public ArrayList<Card> cardList = new ArrayList<Card>();

	public Piece(int type, int start_position) {
		piece_type = type;
		position = start_position;
		rotationCnt = 0;
		catchCnt = 0;
		mission = false;
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
	
	public void caught() throws Exception{
		if(piece_type==1)
			new OhKong_Sound().dead();
		else if(piece_type==0)
			new MasterYi_Sound().dead();
		
		this.position = 0;
	}
	
	public void catching(){
		this.catchCnt++;
	}
	
	public boolean missionCheck(){
		switch(piece_type){
		case 0:
			if(rotationCnt == 5)
				mission = true;
			break;
		case 1:
			if(catchCnt == 3)
				mission = true;
			break;
		}
		return mission;
	}	
}
