package gui;

import gameClient.monoClient;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListModel;

@SuppressWarnings({ "rawtypes", "serial" })
public class Lobby extends JFrame {
	
	private JList roomList;
	private JList userList;
	private JButton roomCreateButton;
	private DefaultListModel rooms;
	private DefaultListModel clients;
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
		rooms = new DefaultListModel<>();
		clients = new DefaultListModel<>();
		roomList = new JList((ListModel) rooms);
		userList = new JList((ListModel) clients);
		roomCreateButton = new JButton();
		roomCreateButton.setText("방 만들기");
		roomCreateButton.addActionListener(new makeRoomHandler());
		
		cp.setLayout(null);
		cp.add(roomList, "room");
		cp.add(userList, "user");
		cp.add(roomCreateButton);
		
		roomList.setBounds(30, 30, 140, 240);
		userList.setBounds(200, 30, 100, 140);
		roomCreateButton.setBounds(200, 200, 100, 70);
	}
	
	public void refreshClients(ArrayList<String> clients){
		int length = clients.size();
		this.clients.removeAllElements();
		for(int i=0; i<length; i++)
			this.clients.addElement(clients.get(i));
		userList.setModel(this.clients);
		
		cp.removeAll();
		cp.add(roomList, "room");
		cp.add(userList, "user");
		cp.add(roomCreateButton);
	}
	
	public void refreshRooms(ArrayList<String> rooms){
		int length = rooms.size();
		this.rooms.removeAllElements();
		for(int i=0; i<length; i++)
			this.rooms.addElement(rooms.get(i));
		roomList.setModel(this.rooms);
		
		cp.removeAll();
		cp.add(roomList, "room");
		cp.add(userList, "user");
		cp.add(roomCreateButton);
	}
	
	class makeRoomHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			new CreateRoom(monoClient);
		}
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
