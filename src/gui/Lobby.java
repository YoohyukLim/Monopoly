package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

@SuppressWarnings({ "rawtypes", "serial" })
public class Lobby extends JPanel {
	
	private JList roomList;
	private JList userList;
	private JButton roomCreateButton;
	private String[] room = {"room1", "room2", "room3"};
	private String[] user = {"user1", "user2", "user3"};
	public static JFrame f;
	
	@SuppressWarnings("unchecked")
	public Lobby() {
		roomList = new JList(room);
		userList = new JList(user);
		roomCreateButton = new JButton();
		roomCreateButton.setText("방 만들기");
		
		this.add(roomList);
		this.add(userList);
		this.add(roomCreateButton);
		
		roomList.setBounds(30, 30, 140, 240);
		userList.setBounds(200, 30, 100, 140);
		roomCreateButton.setBounds(200, 200, 100, 70);
		
		this.setLayout(null);
		this.setVisible(true);

	}
	
	public static void main(String[] args) {
		f = new JFrame();
		f.setSize(330, 340);
		f.setResizable(false);
		f.getContentPane().add(new Lobby());
		f.setVisible(true);
	}

}
