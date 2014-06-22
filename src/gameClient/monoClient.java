package gameClient;

import gui.Lobby;
import gui.NicknameSet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import protocol.ChatProtocol;
import protocol.GameProtocol;
import protocol.LoginProtocol;
import protocol.Protocol;

public class monoClient {
	public static String name;
	String serverIp = "127.0.0.1";

	static Socket socket;
	public static ObjectInputStream in;
	public static ObjectOutputStream out;
	static Protocol data;

	public static JFrame m_frame;
	public static Lobby lobby;

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

			/*out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());*/

			System.out.println("서버에 연결되었습니다.");

			Thread sender = new Thread(new ClientSender(name));
			Thread receiver = new Thread(new ClientReceiver());
			sender.start();
			receiver.start();

			lobby = new Lobby(this);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void exit() {
		System.out.println("exit");
		try {
			socket.close();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static class ClientSender extends Thread {
		ObjectOutputStream out;
		String name;

		ClientSender(String name) {
			this.name = name;

			try {
				out = new ObjectOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}// 생성자

		public void run() {
			try {
				out.writeObject(new LoginProtocol(name, LoginProtocol.LOGIN_CHECK));

				while (out != null) {
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}// run()
	}// ClientSender()

	static class ClientReceiver extends Thread {
		ObjectInputStream in;

		ClientReceiver() {
			try {
				in = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
			}
		}// 생성자

		public void run() {

			try {
				while (in != null) {
					data = (Protocol) in.readObject();

					if (data instanceof ChatProtocol)
						analysisChatProtocol((ChatProtocol) data);
					else if (data instanceof GameProtocol)
						analysisGameProtocol((GameProtocol) data);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		public void analysisChatProtocol(ChatProtocol data) {
		}

		public void analysisGameProtocol(GameProtocol data) {
		}
	}// ClientReceiver()
}
