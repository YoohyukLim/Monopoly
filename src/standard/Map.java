package standard;

import java.util.Calendar;
import java.util.Random;

import model.Map_piece;

public class Map {
	static int Map_number_length = 20;
	static int Map_size = 36;
	static int Map_side = 4;
	static Map_piece[] Map_pieces;
	public static int mapList[];

	public Map() {
	}

	public void generate_map() {
		Map_pieces = new Map_piece[Map_size];
		int special_map = 0;

		Calendar now = Calendar.getInstance();
		int seed = now.get(Calendar.MILLISECOND);
		Random r = new Random();
		r.setSeed(seed);

		Map_pieces[0] = new Map_piece(0, Map_size - 1, 1);
		Map_pieces[0].setMapNumber(special_map++);
		for (int i = 1; i < Map_size - 1; i++) {
			Map_pieces[i] = new Map_piece(i, i - 1, i + 1);
			if ((i + 1) % (Map_size / Map_side) == 1)
				Map_pieces[i].setMapNumber(special_map++);
			else
				Map_pieces[i].setMapNumber(Math.abs(r.nextInt()
						% (Map_number_length - 4)) + 4);
		}
		Map_pieces[Map_size - 1] = new Map_piece(Map_size - 1, Map_size - 2, 0);
		Map_pieces[Map_size - 1].setMapNumber(Math.abs(r.nextInt()
				% (Map_number_length - 4)) + 4);
	}

	public void generate_map(int size) {
		Map_size = size;
		Map_pieces = new Map_piece[Map_size];
		int special_map = 0;

		Calendar now = Calendar.getInstance();
		int seed = now.get(Calendar.MILLISECOND);
		Random r = new Random();
		r.setSeed(seed);

		Map_pieces[0] = new Map_piece(0, Map_size - 1, 1);
		Map_pieces[0].setMapNumber(special_map++);
		for (int i = 1; i < Map_size - 1; i++) {
			Map_pieces[i] = new Map_piece(i, i - 1, i + 1);
			if ((i + 1) % (Map_size / Map_side) == 1)
				Map_pieces[i].setMapNumber(special_map++);
			else
				Map_pieces[i].setMapNumber(Math.abs(r.nextInt()
						% (Map_number_length - 4)) + 4);
		}
		Map_pieces[Map_size - 1] = new Map_piece(Map_size - 1, Map_size - 2, 0);
		Map_pieces[Map_size - 1].setMapNumber(Math.abs(r.nextInt()
				% (Map_number_length - 4)) + 4);
	}
	
	public void generate_map(int mapList[]) {
		Map_pieces = new Map_piece[Map_size];

		Map_pieces[0] = new Map_piece(0, Map_side - 1, 1);
		Map_pieces[0].setMapNumber(mapList[0]);
		for (int i = 1; i < Map_size - 1; i++) {
			Map_pieces[i] = new Map_piece(i, i - 1, i + 1);
			Map_pieces[i].setMapNumber(mapList[i]);
		}
		Map_pieces[Map_size - 1] = new Map_piece(Map_size -1, Map_size - 2, 0);
		Map_pieces[Map_size - 1].setMapNumber(mapList[Map_size - 1]);
	}
	
	public void generateMapList(){
		int special_map = 0;
		
		Calendar now = Calendar.getInstance();
		int seed = now.get(Calendar.MILLISECOND);
		Random r = new Random();
		r.setSeed(seed);
		
		mapList = new int[Map_size];
		for(int i=0; i<Map_size; i++){
			if(i == 0 || (i + 1) % (Map_size / Map_side) == 1 || i == 36)
				mapList[i] = special_map++;
			else 
				mapList[i] = Math.abs(r.nextInt() % (Map_number_length - 4)) + 4;
		}
	}
}
