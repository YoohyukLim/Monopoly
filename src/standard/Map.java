package standard;

import java.util.Calendar;
import java.util.Random;

public class Map {
	static int Map_number_length = 20;
	static int Map_size = 36;
	static int Map_side = 4;
	static Map_piece[] Map_pieces;

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
		Map_pieces[0].map_number = special_map++;
		for (int i = 1; i < Map_size - 1; i++) {
			Map_pieces[i] = new Map_piece(i, i - 1, i + 1);
			if ((i + 1) % (Map_size / Map_side) == 1)
				Map_pieces[i].map_number = special_map++;
			else
				Map_pieces[i].map_number = Math.abs(r.nextInt()
						% (Map_number_length - 3)) + 3;
		}
		Map_pieces[Map_size - 1] = new Map_piece(Map_size - 1, Map_size - 2, 0);
		Map_pieces[Map_size - 1].map_number = Math.abs(r.nextInt()
				% (Map_number_length - 3)) + 3;
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
		Map_pieces[0].map_number = special_map++;
		for (int i = 1; i < Map_size - 1; i++) {
			Map_pieces[i] = new Map_piece(i, i - 1, i + 1);
			if ((i + 1) % (Map_size / Map_side) == 1)
				Map_pieces[i].map_number = special_map++;
			else
				Map_pieces[i].map_number = Math.abs(r.nextInt()
						% (Map_number_length - 3)) + 3;
		}
		Map_pieces[Map_size - 1] = new Map_piece(Map_size - 1, Map_size - 2, 0);
		Map_pieces[Map_size - 1].map_number = Math.abs(r.nextInt()
				% (Map_number_length - 3)) + 3;
	}
}
