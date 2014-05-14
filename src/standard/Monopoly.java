package standard;

import java.util.Calendar;
import java.util.Random;

public class Monopoly {
	static int dice_num = 6;
	
	public static void main(){
	}
	
	static class Map{
		public void generate_map(){
			
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
