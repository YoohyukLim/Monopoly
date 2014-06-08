package model;

import java.util.Calendar;
import java.util.Random;

public class Dice {
	static int Dice_num = 6;

	public Dice() {
	}

	public int exec(int seed_num) {
		Calendar now = Calendar.getInstance();
		int seed = now.get(Calendar.MILLISECOND) + seed_num;
		Random r = new Random();
		r.setSeed(seed);

		return Math.abs(r.nextInt() % Dice_num) + 1;
	}
}