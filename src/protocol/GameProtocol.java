package protocol;

import java.util.ArrayList;

import model.Piece;

import standard.Map;

public class GameProtocol implements Protocol{
	private static final long serialVersionUID = 1L;
	
	public static final short GAME_START = 1000;
	public static final short GAME_DOREST = 3000;
	public static final short GAME_DOFINAL = 4000;
	public static final short GAME_USECARD = 5000;
	public static final short OUT_GAME = 6000;
	public static final short GAME_OVER = 7000;
	
	private String name;
	private short state;
	private int [] mapList;
	private ArrayList<String> roomPlayers;
	private String roomName;
	private int [] dice;
	private int [] card;
	private int cardPositon;
	
	private String currentName;
	private int currentRotationCnt;
	private int currentCatchCnt;
	private int turn;
	
	public GameProtocol(String name, short state) {
		this.name = name;
		this.state = state;
	}
	
	public GameProtocol(String name, short state, int [] mapList, ArrayList<String> roomPlayers) {
		this.name = name;
		this.state = state;
		this.mapList = mapList;
		this.roomPlayers = roomPlayers;
	}

	@Override
	public short getProtocol() {
		return state;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getMessage() {
		return null;
	}
	
	public int[] getMapList(){
		return mapList;
	}
	
	public ArrayList<String> getRoomPlayer(){
		return roomPlayers;
	}
	
	public String getRoomName(){
		return roomName;
	}
	
	public int [] getDice(){
		return dice;
	}
	
	public int[] getCard(){
		return card;
	}
	
	public int getCardPosition(){
		return cardPositon;
	}
	
	public String getCurrentPlayer(){
		return currentName;
	}
	
	public int getCurrentRotion(){
		return currentRotationCnt;
	}
	
	public int getCurrentCatch(){
		return currentCatchCnt;
	}
	
	public int getTurn(){
		return turn;
	}
	
	public void setCurrentPlayer(String name, int rotationCnt, int catchCnt, int turn){
		this.currentName = name;
		this.currentRotationCnt = rotationCnt;
		this.currentCatchCnt = catchCnt;
		this.turn = turn;
	}
	
	public void setCard(int[] card, int position){
		this.card = card;
		this.cardPositon = position;
	}
	
	public void setDice(int Dice1, int Dice2){
		this.dice = new int[2];
		this.dice[0] = Dice1;
		this.dice[1] = Dice2;
	}
	
	public void setRoomName(String roomName){
		this.roomName = roomName;
	}

	@Override
	public void setMessage(String message) {
	}

	@Override
	public void setName(String name) {
	}
}
