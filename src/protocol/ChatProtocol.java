package protocol;

public class ChatProtocol implements Protocol{
	private static final long serialVersionUID = 1L;
	
	public static final short ENTER = 1000;
	public static final short SEND_MESSAGE = 1100;
	public static final short EXIT = 3000;
	public static final short SEND_USER_LIST = 4000;
	public static final short SEND_TOTAL_USER = 4100;
	
	private String name, message, roomName;
	private short state;
	
	public ChatProtocol(String name, String message, String roomName, short state){
		this.name = name;
		this.message = message;
		this.roomName = roomName;
		this.state = state;
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
		return message;
	}
	
	public String getRoomName(){
		return roomName;
	}
	
	public short state(){
		return state;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
