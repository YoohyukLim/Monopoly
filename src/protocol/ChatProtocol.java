package protocol;

public class ChatProtocol implements Protocol{
	private static final long serialVersionUID = 1L;
	
	private static final short ENTER = 1000;
	private static final short LOGIN_CHECK = 1100;
	public static final short LOGIN_ERROR = 1200;
	public static final short MESSAGE = 2000;
	public static final short MESSAGE_SLIP = 2100;
	public static final short EXIT = 3000;
	public static final short SEND_USER_LIST = 4000;
	public static final short SEND_TOTAL_USER = 4100;
	
	private String name, message;
	private short state;
	
	public ChatProtocol(String name, String message, short state){
		this.name = name;
		this.message = message;
		this.state = state;
	}

	@Override
	public short getProtocol() {
		return 0;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getMessage() {
		return message;
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
