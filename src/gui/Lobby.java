package gui;

import gameClient.monoClient;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import protocol.LobbyProtocol;

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
		
		cp = f.getContentPane();
		rooms = new DefaultListModel<>();
		clients = new DefaultListModel<>();
		JScrollPane roomPane = new JScrollPane();
		roomList = new JList(new DefaultListModel<>());
		roomList.addMouseListener(new roomHandler());
		roomPane.setViewportView(roomList);

		JScrollPane userPane = new JScrollPane();
		userList = new JList(new DefaultListModel<>());
		roomCreateButton = new JButton();
		roomCreateButton.setText("방 만들기");
		roomCreateButton.addActionListener(new makeRoomHandler());
		userPane.setViewportView(userList);

		cp.setLayout(null);
		cp.add(roomPane);
		cp.add(userPane);
		cp.add(roomCreateButton);

		roomPane.setBounds(30, 30, 140, 240);
		userPane.setBounds(200, 30, 100, 140);
		roomCreateButton.setBounds(200, 200, 100, 70);
		
		monoClient.sendToServer(new LobbyProtocol(monoClient.name, LobbyProtocol.SEND_ROOM_LIST));
		f.setVisible(true);
	}

	public void refreshClients(ArrayList<String> clients) {
		int length = clients.size();
		this.clients = (DefaultListModel) userList.getModel();
		this.clients.removeAllElements();
		for (int i = 0; i < length; i++) {
			if (monoClient.name.equals(clients.get(i)))
				this.clients.addElement(clients.get(i) + "<나>");
			else
				this.clients.addElement(clients.get(i));
		}
	}

	public void refreshRooms(ArrayList<String> rooms) {
		int length = rooms.size();
		this.rooms = (DefaultListModel) roomList.getModel();
		this.rooms.removeAllElements();
		for (int i = 0; i < length; i++)
			this.rooms.addElement(rooms.get(i));
	}

	class roomHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			JList list = (JList) e.getSource();

			if (e.getClickCount() == 2) {
				int num = list.locationToIndex(e.getPoint());
				String RoomName = list.getModel().getElementAt(num).toString();
				LobbyProtocol data = new LobbyProtocol(monoClient.name,
						LobbyProtocol.ENTER_ROOM);
				data.setRoomName(RoomName);
				monoClient.sendToServer(data);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}

	class makeRoomHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new CreateRoom(monoClient);
		}
	}

	class windowHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			e.getWindow().dispose();
			monoClient.exit();
		}
	}

	public static void main(String[] args) {
		new Lobby(null);
	}

}
