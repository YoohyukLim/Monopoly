package gameClient;

import gui.Lobby;
import gui.NicknameSet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;

import protocol.ChatProtocol;
import protocol.GameProtocol;
import protocol.LobbyProtocol;
import protocol.Protocol;

public class monoClient extends Thread{
	public static String name;
	String serverIp = "127.0.0.1";

	static Socket socket;
	static ObjectOutputStream out;
	static ObjectInputStream in;
	Protocol data;

	public static JFrame m_frame;
	public static Lobby lobby;
	
	public ArrayList<String> clients;

	public static void main(String args[]) {
		new monoClient();
	}// main()

	public monoClient() {
		new NicknameSet();
	}

	public monoClient(String name) {
		try {
			this.name = name;
			System.out.println("서버에 연결 중 IP: " + serverIp);
			socket = new Socket(serverIp, 7777);

			System.out.println("서버에 연결되었습니다.");
		
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			out.writeObject(new LobbyProtocol(name, LobbyProtocol.ENTER));
			
			lobby = new Lobby(this);
			this.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(in!=null){
			try {
				data = (Protocol) in.readObject();
				System.out.println("From Server : " + data);
				
				if(data instanceof LobbyProtocol)
					analysisLoginProtocol((LobbyProtocol) data);
				else if (data instanceof ChatProtocol)
					analysisChatProtocol((ChatProtocol) data);
				else if (data instanceof GameProtocol)
					analysisGameProtocol((GameProtocol) data);
			} catch (ClassNotFoundException e) {
				System.out.println("Class Not Found");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	public void analysisLoginProtocol(LobbyProtocol data){
		if(data.getProtocol() == LobbyProtocol.SEND_USER_LIST){
			refreshClients(data.getUserlist());
		}
	}
	
	public void analysisChatProtocol(ChatProtocol data){
	}
	
	public void analysisGameProtocol(GameProtocol data){
	}
	
	public void refreshClients(ArrayList<String> clients){
		if(this.clients!=null)
			this.clients.clear();
		this.clients = new ArrayList<String>(clients);
		
		lobby.refreshClients(this.clients);
	}

	public void exit() {
		System.exit(0);
	}
}
