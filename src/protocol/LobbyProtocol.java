package protocol;

import java.util.ArrayList;
import java.util.HashMap;

public class LobbyProtocol implements Protocol {
	private static final long serialVersionUID = 1L;
	
	public static final short ENTER = 1000;
	public static final short LOGIN_CHECK = 1100;
	public static final short LOGIN_ERROR = 1200;
	public static final short CREATE_ROOM = 2000;
	public static final short ENTER_ROOM = 2100;
	public static final short ENTER_FAIL = 2150;
	public static final short OUT_ROOM = 2200;
	public static final short EXIT_ROOM = 2300;
	public static final short EXIT = 3000;
	public static final short SEND_USER_LIST = 4000;
	public static final short SEND_TOTAL_USER = 4100;
	public static final short SEND_ROOM_LIST = 4200;
	public static final short SEND_PLAYER_LIST = 4300;
	public static final short GAME_START_USER = 5000;
	public static final short GAME_START_MASTER = 5100;
	public static final short GAME_READY_CANCEL = 5200;
	public static final short GAME_START_FAIL = 5300;
	
	private ArrayList<String> clients;
	private ArrayList<String> rooms;
	private ArrayList<String> playsers;
	private String name;
	private String roomName;
	private short state;
	
	public LobbyProtocol(String name, short state){
		this.name = name;
		this.state = state;
	}
	
	public LobbyProtocol(ArrayList <String> data, short state){
		this.state = state;
		if(state == SEND_USER_LIST){
			this.clients = data;
		} else if(state == SEND_ROOM_LIST){
			this.rooms = data;
		} else if(state == SEND_PLAYER_LIST){
			this.playsers = data;
		}
	}
	
	
	public ArrayList<String> getUserlist(){
		return clients;
	}
	
	public ArrayList<String> getRoomlist(){
		return rooms;
	}
	
	public ArrayList<String> getPlayerList(){
		return playsers;
	}

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
	
	public String getRoomName(){
		return roomName;
	}
	
	public void setRoomName(String name){
		this.roomName = name;
	}

	@Override
	public void setMessage(String message) {
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
