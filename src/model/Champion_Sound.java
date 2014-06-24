package model;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

public class Champion_Sound {
	String champ;
	
	File normal_dir;
	File dead_dir;
	File defeat_dir;
	
	File [] normal;
	File [] dead;
	File [] defeat;
	
	public Champion_Sound(int type){
		switch(type){
		case 0:
			champ = new String("Ohkong");
			break;
		case 1:
			champ = new String("MasterYi");
			break;
		}
		
		normal_dir = new File("Resources/sounds/"+champ+"/normal");
		dead_dir = new File("Resources/sounds/"+champ+"/dead");
		defeat_dir = new File("Resources/sounds/"+champ+"/defeat");
		
		normal = normal_dir.listFiles();
		dead = dead_dir.listFiles();
		defeat = defeat_dir.listFiles();
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
