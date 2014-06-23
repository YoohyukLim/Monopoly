package gameServer;

import java.util.ArrayList;

public class RoomManager {
	String roomName;
	ArrayList<String> clientName;
	
	String roomMaster;
	boolean isFull = false;
	
	public RoomManager(){
		clientName = new ArrayList<>();
	}
	
	public RoomManager(String roomName, String name){
		roomName = new String(roomName);
		clientName = new ArrayList<>();
		clientName.add(name);
		roomMaster = new String(name);
	}
	
	public boolean enterRoom(String name){
		if(clientName.size() < 2){
			clientName.add(name);
			return true;
		} else
			return false;
			
	}
	
	public void outClient(String name){
		if(name.equals(roomMaster))
			roomMaster = null;
		clientName.remove(name);
	}
	
	public void outClientAll(){
		roomMaster = null;
		clientName = null;
	}
	
	public ArrayList getClients(){
		return clientName;
	}
}
