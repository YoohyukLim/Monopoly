package standard;

public class Piece {
	int piece_type;
	int position;
	int map_size;
	int turn_cnt;
	String name;

	public Piece(int type, int start_position) {
		piece_type = type;
		position = start_position;
		turn_cnt = 0;
	}

	public int getType() {
		return piece_type;
	}

	public int getPosition() {
		return position;
	}

	public int setPosition(int pos) {
		position = pos;
		return position;
	}

	public int movePosition(int dice) {
		int pos = position + dice;

		if (pos > map_size - 1)
			turn_cnt++;
		pos = pos % (map_size - 1);

		return setPosition(pos);
	}
	
	public String setName(String name){
		this.name = name;
		return this.name;
	}
	
	public String getName(){
		return this.name;
	}
}
