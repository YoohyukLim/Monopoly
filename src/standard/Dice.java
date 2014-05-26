package standard;

import java.util.Calendar;
import java.util.Random;

public class Dice {
	static int Dice_num = 6;
	
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
