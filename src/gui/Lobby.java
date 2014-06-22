package gui;

import gameClient.monoClient;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

@SuppressWarnings({ "rawtypes", "serial" })
public class Lobby extends JFrame {
	
	private JList roomList;
	private JList userList;
	private JButton roomCreateButton;
	private String[	] room = {"room1", "room2", "room3"};
	private String[] user = {"user1", "user2", "user3"};
	private Container cp;
	private monoClient monoClient;
	public static JFrame f;
	
	@SuppressWarnings("unchecked")
	public Lobby(monoClient monoClient) {
		this.monoClient = monoClient;
		
		f = new JFrame();
		f.setSize(330, 340);
		f.setResizable(false);
		f.addWindowListener(new windowHandler());
		f.setVisible(true);
		cp = f.getContentPane();
		roomList = new JList(room);
		userList = new JList(user);
		roomCreateButton = new JButton();
		roomCreateButton.setText("방 만들기");
		
		cp.setLayout(null);
		cp.add(roomList);
		cp.add(userList);
		cp.add(roomCreateButton);
		
		roomList.setBounds(30, 30, 140, 240);
		userList.setBounds(200, 30, 100, 140);
		roomCreateButton.setBounds(200, 200, 100, 70);
	}
	
	class windowHandler extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			e.getWindow().setVisible(false);
			e.getWindow().dispose();
			monoClient.exit();
		}
	}
	
	public static void main(String[] args) {
		new Lobby(null);
	}

}
