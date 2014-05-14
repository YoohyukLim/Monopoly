package standard;

public class Map_piece {
	int current;
	int post_map;
	int next_map;
	int map_number;
	
	public Map_piece(){
	}
	
	public Map_piece(int current, int post, int next){
		this.current = current;
		this.post_map = post;
		this.next_map = next;
	}
}
