package protocol;

import standard.GameController;

public class GameProtocol implements Protocol{
	private static final long serialVersionUID = 1L;
	
	public static final short GAME_START = 1000;
	
	String name;
	short state;
	GameController gameController;
	
	public GameProtocol(String name, short state) {
		this.name = name;
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
		return null;
	}

	@Override
	public void setMessage(String message) {
	}

	@Override
	public void setName(String name) {
	}
	
	public GameController getGameController() {
		return gameController;
	}
	
	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}
}
