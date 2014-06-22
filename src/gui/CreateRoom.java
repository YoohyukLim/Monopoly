package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CreateRoom extends JPanel {
	private JTextField roomId;
	private JButton createButton;
	private JButton cancelButton;
	private JLabel createRoomLabel = new JLabel("방 제목을 입력하세요!");
	public static JFrame f;
	
	public CreateRoom() {
		roomId = new JTextField(10);
		createButton = new JButton();
		cancelButton = new JButton();
		createRoomLabel.setFont(new Font("굴림", Font.BOLD, 15));
		this.add(roomId);
		this.add(createButton);
		this.add(cancelButton);
		this.add(createRoomLabel);
		createRoomLabel.setBounds(130, 25, 180, 25);
		roomId.setBounds(70,75,130,25);
		createButton.setBounds(220,75,70,25);
		cancelButton.setBounds(320,75,70,25);
		createButton.setText("확인");
		cancelButton.setText("취소");
		
		this.setLayout(null);
		this.setVisible(true);

	}


	public static void main(String[] args) {
		f = new JFrame();
		f.setSize(405,150);
		f.setResizable(false);
		f.getContentPane().add(new CreateRoom());
		f.setVisible(true);
	}

}
