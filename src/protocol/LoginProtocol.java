package protocol;

public class LoginProtocol implements Protocol {
	private static final long serialVersionUID = 1L;
	
	public static final short ENTER = 1000;
	public static final short LOGIN_CHECK = 1100;
	public static final short LOGIN_ERROR = 1200;
	public static final short MESSAGE = 2000;
	public static final short MESSAGE_SLIP = 2100;
	public static final short EXIT = 3000;
	public static final short SEND_USER_LIST = 4000;
	public static final short SEND_TOTAL_USER = 4100;
	
	private String name;
	private short state;
	
	public LoginProtocol(String name, short state){
		this.name = name;
		this.state = state;
	}

	public short getProtocol() {
		return 0;
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
