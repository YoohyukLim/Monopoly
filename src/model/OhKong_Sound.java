package model;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

public class OhKong_Sound {
	File normal_dir = new File("Resources/sounds/Ohkong/normal");
	File dead_dir = new File("Resources/sounds/Ohkong/dead");
	File defeat_dir = new File("Resources/sounds/Ohkong/defeat");
	
	File [] normal = normal_dir.listFiles();
	File [] dead = dead_dir.listFiles();
	File [] defeat = defeat_dir.listFiles();
	
	public OhKong_Sound(){
	}
	
	public void normal() throws Exception{
		Calendar now = Calendar.getInstance();
		int seed = now.get(Calendar.MILLISECOND);
		Random r = new Random();
		r.setSeed(seed);
		
		new Sound(String.valueOf(normal[Math.abs(r.nextInt() % normal.length)])).play();
	}
	
	public void dead() throws Exception{
		Calendar now = Calendar.getInstance();
		int seed = now.get(Calendar.MILLISECOND);
		Random r = new Random();
		r.setSeed(seed);
		
		new Sound(String.valueOf(dead[Math.abs(r.nextInt() % dead.length)])).play();
	}
	
	public void defeat() throws Exception{
		Calendar now = Calendar.getInstance();
		int seed = now.get(Calendar.MILLISECOND);
		Random r = new Random();
		r.setSeed(seed);
		
		new Sound(String.valueOf(defeat[Math.abs(r.nextInt() % defeat.length)])).play();
	}
}