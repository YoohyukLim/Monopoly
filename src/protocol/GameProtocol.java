package protocol;

public class GameProtocol implements Protocol{
	private static final long serialVersionUID = 1L;

	@Override
	public short getProtocol() {
		return 0;
	}

	@Override
	public String getName() {
		return null;
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
	}
}
