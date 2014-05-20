package standard;

import java.util.Calendar;
import java.util.Random;

public class Monopoly {
	static int Dice_num = 6;

	static int piece_num = 4;
	static Piece [] player = new Piece[piece_num];
	
	public static void main(String args[]){
		Map map = new Map();
		map.generate_map();
		
		for(int i=0; i<piece_num; i++){
			player[i] = new Piece(i, 0);
		}
		
		for(int i=0; i<Map.Map_size; i++){
			System.out.print(Map.Map_pieces[i].map_number+" ");
			if((i+1) % (Map.Map_size/Map.Map_side) == 0)
				System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	static class Dice{
		public Dice(){
		}
		
		public int exec(){
			Calendar now = Calendar.getInstance();
			int seed = now.get(Calendar.MILLISECOND);
			Random r = new Random();
			r.setSeed(seed);
			
			return Math.abs(r.nextInt() % Dice_num);
		}
	}
}
