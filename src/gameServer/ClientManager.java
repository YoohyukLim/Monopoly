package gameServer;

import java.io.ObjectOutputStream;

public class ClientManager {
	String name;
	ObjectOutputStream out;
	boolean isRoom = false;
	boolean isMaster = false;
	String RoomName;
	
	public ClientManager(String name, ObjectOutputStream out){
		this.name = name;
		this.out = out;
	}
	
	public String getName(){
		return name;
	}
	
	public ObjectOutputStream getOuputStream(){
		return out;
	}
	
	public boolean isRoom(){
		return isRoom;
	}
	
	public boolean isMaster(){
		return isMaster;
	}
	
	public String getRoomName(){
		return RoomName;
	}
	
	public void enterRoom(String RoomName, boolean Master){
		this.RoomName = RoomName;
		this.isMaster = Master;
		this.isRoom = true;
	}
	
	public void outRoom(){
		this.RoomName = null;
		this.isMaster = false;
		this.isRoom = false;
	}
}
