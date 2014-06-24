package protocol;

import java.util.ArrayList;

import model.Piece;

import standard.Map;

public class GameProtocol implements Protocol{
	private static final long serialVersionUID = 1L;
	
	public static final short GAME_START = 1000;
	
	private String name;
	private short state;
	private int [] mapList;
	private ArrayList<String> roomPlayers;
	
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

	@Override
	public void setMessage(String message) {
	}

	@Override
	public void setName(String name) {
	}
}
