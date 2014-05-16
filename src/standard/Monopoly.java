package standard;

import java.util.Calendar;
import java.util.Random;

public class Monopoly {
	static int dice_num = 6;
	static int map_number_length = 0;
	static int map_side = 4;
	static Map_piece [] map_piece;
	static int Map_size=36;
	
	public static void main(){
	}
	
	static class Map{
		public void generate_map(){
			Calendar now = Calendar.getInstance();
			int seed = now.get(Calendar.MILLISECOND);
			Random r = new Random();
			r.setSeed(seed);
			
			map_piece[0] = new Map_piece(0, Map_size-1, 1);
			map_piece[0].map_number = Math.abs(r.nextInt() % map_number_length);
			for(int i=1; i<Map_size-1; i++){
				map_piece[i] = new Map_piece(i, i-1, i+1);
				map_piece[i].map_number = Math.abs(r.nextInt() % map_number_length);
			}
			map_piece[Map_size-1] = new Map_piece(Map_size-1, Map_size-2, 0);
			map_piece[Map_size-1].map_number = Math.abs(r.nextInt() % map_number_length);
		}
		
		public void generate_map(int size){
			Map_size = size;
			Calendar now = Calendar.getInstance();
			int seed = now.get(Calendar.MILLISECOND);
			Random r = new Random();
			r.setSeed(seed);
			
			map_piece[0] = new Map_piece(0, Map_size-1, 1);
			map_piece[0].map_number = Math.abs(r.nextInt() % map_number_length);
			for(int i=1; i<Map_size-1; i++){
				map_piece[i] = new Map_piece(i, i-1, i+1);
				map_piece[i].map_number = Math.abs(r.nextInt() % map_number_length);
			}
			map_piece[Map_size-1] = new Map_piece(Map_size-1, Map_size-2, 0);
			map_piece[Map_size-1].map_number = Math.abs(r.nextInt() % map_number_length);
		}
	}
	
	static class Dice{
		public Dice(){
		}
		
		public int exec(){
			Calendar now = Calendar.getInstance();
			int seed = now.get(Calendar.MILLISECOND);
			Random r = new Random();
			r.setSeed(seed);
			
			return Math.abs(r.nextInt() % dice_num);
		}
	}
}
