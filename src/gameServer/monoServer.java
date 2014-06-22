package gameServer;

import java.io.ObjectOutputStream;
import java.util.HashMap;

import standard.Map;

public class monoServer {
	HashMap<String, ObjectOutputStream> clients;
	Map map;
	
	public monoServer(){
		clients = new HashMap<String, ObjectOutputStream>();
	}
}
