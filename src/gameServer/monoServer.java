package gameServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import protocol.ChatProtocol;
import protocol.GameProtocol;
import protocol.LobbyProtocol;
import protocol.Protocol;

public class monoServer {
	HashMap<String, ClientManager> clients;
	ArrayList<String> clientsName;
	HashMap<String, RoomManager> roomList;
	ArrayList<String> roomsName;
	
	Protocol data;

	public static void main(String args[]) {
		new monoServer().start();
	}

	public monoServer() {
		clients = new HashMap<String, ClientManager>();
		clientsName = new ArrayList<String>();
		roomList = new HashMap<String, RoomManager>();
		roomsName = new ArrayList<String>();
		//Collections.synchronizedMap(clients);
		//Collections.synchronizedList(clientsName);
		//Collections.synchronizedList(roomsName);
	}

	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다.");

			while (true) {
				System.out.println("연결요청을 기다립니다.");
				socket = serverSocket.accept();

				System.out.println("[" + socket.getInetAddress() + ":"
						+ socket.getPort() + "]" + "에서 접속하였습니다.");
				new ServerReceiver(socket).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// start()

	class ServerReceiver extends Thread {
		Socket socket;
		ObjectInputStream in;
		ObjectOutputStream out;
		String name;

		ServerReceiver(Socket socket) {
			this.socket = socket;

			try {
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
			} catch (Exception e) {
			}
		}

		public void run() {
			try {
				data = (Protocol) in.readObject();
				name = data.getName();

				if (data instanceof LobbyProtocol){
					addClient(name, out);
				}else
					System.exit(0);

				System.out.println(name + "님이 접속하셨습니다.");
				System.out.println("현재 접속자 수는 " + clients.size() + "입니다.");
				SendToAll(new LobbyProtocol(clientsName, LobbyProtocol.SEND_USER_LIST));
				out.writeObject(new LobbyProtocol(roomsName, LobbyProtocol.SEND_ROOM_LIST));

				while (in != null) {
					data = (Protocol) in.readObject();
					if (data instanceof LobbyProtocol)
						analysisLobbyProtocol((LobbyProtocol) data);
					else if (data instanceof ChatProtocol)
						analysisChatProtocol((ChatProtocol) data);
					else if (data instanceof GameProtocol)
						analysisGameProtocol((GameProtocol) data);
				}
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				subClient(name);
				System.out.println("[" + socket.getInetAddress() + ":"
						+ socket.getPort() + "]" + "에서 접속을 종료하였습니다.");
				System.out.println("현재 접속자 수는 " + clients.size() + "입니다.");
				SendToAll(new LobbyProtocol(clientsName, LobbyProtocol.SEND_USER_LIST));
			}
		}// run()
		
		private void analysisLobbyProtocol(LobbyProtocol data){
			name = data.getName();
			short state = data.getProtocol();
			
			try {
				if (state == LobbyProtocol.CREATE_ROOM) {

					String roomName = data.getRoomName();
					addRoom(roomName, name);
					SendToAll(new LobbyProtocol(roomsName,
							LobbyProtocol.SEND_ROOM_LIST));

					LobbyProtocol roomdata = new LobbyProtocol(name, state);
					roomdata.setRoomName(roomName);
					sendToClient(roomdata);
					out.writeObject(new LobbyProtocol(roomsName, LobbyProtocol.SEND_ROOM_LIST));
					NoticeRoomPlayers(roomName);
				} else if (state == LobbyProtocol.OUT_ROOM) {

					String roomName = data.getRoomName();
					breakRoom(roomName, name);
					SendToAll(new LobbyProtocol(roomsName, LobbyProtocol.SEND_ROOM_LIST));

				} else if (state == LobbyProtocol.ENTER_ROOM) {
					String roomName = data.getRoomName();
					enterRoom(roomName, name);
				} else if (state == LobbyProtocol.GAME_START_MASTER) {
					
				} else if (state == LobbyProtocol.GAME_START_USER) {
					String roomName = data.getRoomName();
					RoomManager room = roomList.get(roomName);
					room.isReady = true;
					
					sendToClient(null);
				} else if (state == LobbyProtocol.GAME_READY_CANCEL) {
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void analysisGameProtocol(GameProtocol data) {
		}

		private void analysisChatProtocol(ChatProtocol data) {
		}
		
		private void sendToClient(Protocol data){
			try {
				out.writeObject(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}// ServerReceiver()
	
	public synchronized void addRoom(String Rname, String name){
		roomList.put(Rname, new RoomManager(Rname, name));
		roomsName.add(Rname);
		clients.get(name).enterRoom(Rname, true);
	}
	
	public synchronized void enterRoom(String RoomName, String name){
		ObjectOutputStream oos = this.clients.get(name).getOuputStream();
		try {
			if (roomList.get(RoomName).enterRoom(name)) {
				clients.get(name).enterRoom(RoomName, false);
				LobbyProtocol room = new LobbyProtocol(name, LobbyProtocol.ENTER_ROOM);
				room.setRoomName(RoomName);
				oos.writeObject(room);
				oos.reset();
				NoticeRoomPlayers(RoomName);
			} else {
				oos.writeObject(new LobbyProtocol(name, LobbyProtocol.ENTER_FAIL));
				oos.reset();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void breakRoom(String RoomName, String name){
		if(clients.get(name).isMaster == true){
			destroyRoom(RoomName);
		} else{
			ObjectOutputStream oos = this.clients.get(name).getOuputStream();
			try {
				oos.writeObject(new LobbyProtocol(name, LobbyProtocol.OUT_ROOM));
				oos.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
			clients.get(name).outRoom();
			roomList.get(RoomName).outClient(name);
			NoticeRoomPlayers(RoomName);
		}
	}
	
	public synchronized void destroyRoom(String RoomName){
		RoomManager room = roomList.get(RoomName);
		ArrayList<String> clientList = room.getClients();
		
		for(int i=0; i<clientList.size(); i++){
			ObjectOutputStream oos = this.clients.get(clientList.get(i)).getOuputStream();
			try {
				oos.writeObject(new LobbyProtocol(clientList.get(i), LobbyProtocol.EXIT_ROOM));
				oos.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.clients.get(clientList.get(i)).outRoom();
		}
		roomList.get(RoomName).outClientAll();
		roomList.remove(RoomName);
		roomsName.remove(RoomName);
	}
	
	public synchronized void addClient(String name, ObjectOutputStream out){
		clients.put(name, new ClientManager(name, out));
		clientsName.add(name);
	}
	
	public synchronized void subClient(String name){
		clients.remove(name);
		clientsName.remove(name);
	}
	
	public void NoticeRoomPlayers(String roomName){
		ArrayList<String> players = roomList.get(roomName).getClients();
		for(int i = 0 ; i<players.size(); i++){
			ObjectOutputStream oos = clients.get(players.get(i)).getOuputStream();
			try {
				oos.writeObject(new LobbyProtocol(players, LobbyProtocol.SEND_PLAYER_LIST));
				oos.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void SendToAll(Protocol data) {
		Iterator it = clients.keySet().iterator();
		while (it.hasNext()) {
			try {
				ObjectOutputStream out = ((ClientManager) clients.get((String) it.next())).getOuputStream();
				out.writeObject(data);
				out.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}// sendToAll
}
