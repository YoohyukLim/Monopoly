package protocol;

import java.util.ArrayList;
import java.util.HashMap;

public class LobbyProtocol implements Protocol {
	private static final long serialVersionUID = 1L;
	
	public static final short ENTER = 1000;
	public static final short LOGIN_CHECK = 1100;
	public static final short LOGIN_ERROR = 1200;
	public static final short MESSAGE = 2000;
	public static final short MESSAGE_SLIP = 2100;
	public static final short EXIT = 3000;
	public static final short SEND_USER_LIST = 4000;
	public static final short SEND_TOTAL_USER = 4100;
	
	private ArrayList<String> clients;
	private String name;
	private short state;
	
	public LobbyProtocol(String name, short state){
		this.name = name;
		this.state = state;
	}
	
	public LobbyProtocol(ArrayList <String> data){
		this.clients = data;
		this.state = SEND_USER_LIST;
	}
	
	public ArrayList<String> getUserlist(){
		return clients;
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

	@Override
	public void setMessage(String message) {
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
