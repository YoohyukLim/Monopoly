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
	public static final short BREAK_ROOM = 2200;
	public static final short EXIT = 3000;
	public static final short SEND_USER_LIST = 4000;
	public static final short SEND_TOTAL_USER = 4100;
	public static final short SEND_ROOM_LIST = 4200;
	
	private ArrayList<String> clients;
	private ArrayList<String> rooms;
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
		}
	}
	
	
	public ArrayList<String> getUserlist(){
		return clients;
	}
	
	public ArrayList<String> getRoomlist(){
		return rooms;
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
