package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CreateRoom extends JPanel {
	private JTextField roomId;
	private JButton createButton;
	private JButton cancelButton;
	private JLabel createRoomLabel = new JLabel("방 제목을 입력하세요!");
	private Container cp;
	public static JFrame f;
	
	public CreateRoom() {
		f = new JFrame();
		f.setSize(380,150);
		f.setResizable(false);
		f.setVisible(true);
		cp = f.getContentPane();
		
		roomId = new JTextField(10);
		createButton = new JButton();
		cancelButton = new JButton();
		createRoomLabel.setFont(new Font("굴림", Font.BOLD, 15));
		cp.add(roomId);
		cp.add(createButton);
		cp.add(cancelButton);
		cp.add(createRoomLabel);
		createRoomLabel.setBounds(100, 25, 180, 25);
		roomId.setBounds(40,75,130,25);
		createButton.setBounds(190,75,70,25);
		cancelButton.setBounds(280,75,70,25);
		createButton.setText("확인");
		cancelButton.setText("취소");
		
		cp.setLayout(null);
	}


	public static void main(String[] args) {
		new CreateRoom();
	}

}
