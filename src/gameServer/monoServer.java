package gameServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import protocol.ChatProtocol;
import protocol.GameProtocol;
import protocol.LobbyProtocol;
import protocol.Protocol;

public class monoServer {
	HashMap<String, ObjectOutputStream> clients;
	ArrayList<String> clientsName;
	Protocol data;

	public static void main(String args[]) {
		new monoServer().start();
	}

	public monoServer() {
		clients = new HashMap<String, ObjectOutputStream>();
		clientsName = new ArrayList<String>();
		//Collections.synchronizedMap(clients);
		//Collections.synchronizedList(clientsName);
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
				UpdateClient(new LobbyProtocol(clientsName));

				while (in != null) {
					data = (Protocol) in.readObject();

					if (data instanceof ChatProtocol)
						analysisChatProtocol((ChatProtocol) data);
					else if (data instanceof GameProtocol)
						analysisGameProtocol((GameProtocol) data);
				}
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				subClient(name);
				UpdateClient(new LobbyProtocol(clientsName));
				System.out.println("[" + socket.getInetAddress() + ":"
						+ socket.getPort() + "]" + "에서 접속을 종료하였습니다.");
				System.out.println("현재 접속자 수는 " + clients.size() + "입니다.");
			}
		}// run()

		private void analysisGameProtocol(GameProtocol data) {
		}

		private void analysisChatProtocol(ChatProtocol data) {
		}
	}// ServerReceiver()
	
	public synchronized void addClient(String name, ObjectOutputStream out){
		clients.put(name, out);
		clientsName.add(name);
	}
	
	public synchronized void subClient(String name){
		clients.remove(name);
		clientsName.remove(name);
	}
	
	public void UpdateClient(LobbyProtocol data) {
		Iterator it = clients.keySet().iterator();
		while (it.hasNext()) {
			try {
				ObjectOutputStream out = (ObjectOutputStream) clients.get((String) it.next());
				out.writeObject(data);
				out.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}// sendToAll
}
