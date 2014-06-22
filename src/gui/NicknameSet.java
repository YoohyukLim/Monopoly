package gui;

import gameClient.monoClient;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NicknameSet extends JPanel {
	private JTextField inputId;
	private JButton loginButton;
	private JLabel inputIdLabel = new JLabel("닉네임을 입력하세요!");
	public static JFrame f;
	public static JPanel p;
	
	public NicknameSet() {
		f = new JFrame();
		f.setSize(405,150);
		f.setResizable(false);
		
		p = new JPanel();

		inputId = new JTextField(7);
		loginButton = new JButton();
		inputIdLabel.setFont(new Font("굴림", Font.BOLD, 15));
		p.add(inputId);
		p.add(inputIdLabel);
		p.add(loginButton);
		inputIdLabel.setBounds(130, 25, 150, 25);
		inputId.setBounds(130,75,100,25);
		loginButton.setBounds(250,75,70,25);
		loginButton.setText("확인");
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!inputId.getText().equals("")) {
					new monoClient(inputId.getText());
					f.setVisible(false);
				}
			}
		});

		p.setLayout(null);
		p.setVisible(true);
		
		f.getContentPane().add(p);
		f.setVisible(true);
	}


	public static void main(String[] args) {
		f = new JFrame();
		f.setSize(405,150);
		f.setResizable(false);
		f.getContentPane().add(new NicknameSet());
		f.setVisible(true);
	}

}
