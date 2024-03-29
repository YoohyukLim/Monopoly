package gui;

import gameClient.monoClient;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import protocol.LobbyProtocol;

public class Room extends JFrame {
	
	public static JFrame f;
	
	private Container cp;
	private JPanel panel;
	private JLabel masterLabel;
	private JLabel joinerLabel;
	private JLabel masterNick;
	private JLabel joinerNick;
	private JPanel masterPanel;
	private JPanel joinerPanel;
	private JButton startButton;
	private JButton	exitButton;
	
	private monoClient monoClient;
	
	public Room(monoClient monoclient, String roomName) {
		this.monoClient = monoclient;
		f = this;
		
		this.setSize(300,300);
		this.setResizable(false);
		this.addWindowListener(new windowHandler());
		
		cp = this.getContentPane();
		panel = new JPanel();
		masterLabel = new JLabel();
		joinerLabel = new JLabel();
		masterNick = new JLabel();
		joinerNick = new JLabel();
		masterPanel = new JPanel();
		joinerPanel = new JPanel();
		startButton = new JButton();
		exitButton = new JButton();
		
		masterLabel.setText("방장");
		joinerLabel.setText("도전자");
		masterNick.setText("Player1");
		joinerNick.setText("Player2");
		masterPanel.setBackground(Color.BLACK);
		joinerPanel.setBackground(Color.BLACK);
		startButton.setText("시작");
		exitButton.setText("나가기");
		masterLabel.setHorizontalAlignment(JLabel.CENTER);
		joinerLabel.setHorizontalAlignment(JLabel.CENTER);
		masterNick.setHorizontalAlignment(JLabel.CENTER);
		joinerNick.setHorizontalAlignment(JLabel.CENTER);
		
		masterLabel.setBounds(30, 10, 100, 30);
		joinerLabel.setBounds(160, 10, 100, 30);
		masterNick.setBounds(30, 40, 100, 30);
		joinerNick.setBounds(160, 40, 100, 30);
		masterPanel.setBounds(30, 75, 100, 100);
		joinerPanel.setBounds(160, 75, 100, 100);
		startButton.setBounds(50, 200, 80, 30);
		exitButton.setBounds(160, 200, 80, 30);
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LobbyProtocol temp;
				String command = e.getActionCommand();
				if (command.equals("시작")) {
					if (monoClient.roomMaster && monoClient.players.size() == 2) {
						temp = new LobbyProtocol(monoClient.name, LobbyProtocol.GAME_START_MASTER);
						temp.setRoomName(monoClient.roomName);
						monoClient.sendToServer(temp);
					} else if (monoClient.players.size() == 2) {
						temp = new LobbyProtocol(monoClient.name, LobbyProtocol.GAME_START_USER);
						temp.setRoomName(monoClient.roomName);
						monoClient.sendToServer(temp);
					}
				} else if (command.equals("대기 중")) {
					temp = new LobbyProtocol(monoClient.name, LobbyProtocol.GAME_READY_CANCEL);
					temp.setRoomName(monoClient.roomName);
					monoClient.sendToServer(temp);
				}
			}
		});
		
		exitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				monoClient.outRoom();
			}
		});
		
		cp.setLayout(null);
		cp.add(masterLabel);
		cp.add(joinerLabel);
		cp.add(masterNick);
		cp.add(joinerNick);
		cp.add(masterPanel);
		cp.add(joinerPanel);
		cp.add(startButton);
		cp.add(exitButton);
		
		this.setVisible(true);
	}
	
	public void refresh(ArrayList <String> players){
		String enemy = null;
		masterNick.setText("Player1");
		joinerNick.setText("Player2");
		for(int i=0 ;i<players.size(); i++)
			if(!monoClient.name.equals(players.get(i)))
				enemy = players.get(i);
		
		if(monoClient.roomMaster == true){
			masterNick.setText(monoClient.name);
			if(enemy!=null)
				joinerNick.setText(enemy);
		}else{
			if(enemy!=null)
				masterNick.setText(enemy);
			joinerNick.setText(monoClient.name);
		}
	}
	
	public void getReady() {
		startButton.setText("대기 중");
	}
	
	public void cancelReady() {
		startButton.setText("시작");
	}
	
	class windowHandler extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			e.getWindow().setVisible(false);
			e.getWindow().dispose();
			monoClient.outRoom();
		}
	}

	public static void main(String[] args) {
		new Room(null, null);
	}

}
