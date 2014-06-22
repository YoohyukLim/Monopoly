package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NicknameSet extends JPanel {
	private JTextField inputId;
	private JButton loginButton;
	private JLabel inputIdLabel = new JLabel("닉네임을 입력하세요!");
	public static JFrame f;
	
	public NicknameSet() {

		inputId = new JTextField(7);
		loginButton = new JButton();
		inputIdLabel.setFont(new Font("굴림", Font.BOLD, 15));
		this.add(inputId);
		this.add(inputIdLabel);
		this.add(loginButton);
		inputIdLabel.setBounds(130, 25, 150, 25);
		inputId.setBounds(130,75,100,25);
		loginButton.setBounds(250,75,70,25);
		loginButton.setText("확인");
		ActionListener al = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!inputId.getText().equals("")) {
//					new ClientLobby(inputId.getText());
					f.setVisible(false);
				}
			}
		};
		loginButton.addActionListener(al);

		this.setLayout(null);
		this.setVisible(true);

	}


	public static void main(String[] args) {
		f = new JFrame();
		f.setSize(405,150);
		f.setResizable(false);
		f.getContentPane().add(new NicknameSet());
		f.setVisible(true);
	}

}
