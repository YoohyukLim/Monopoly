package gui;

import gameClient.monoClient;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
	private JPanel masterPanel;
	private JPanel joinerPanel;
	private JButton startButton;
	private JButton	exitButton;
	
	private monoClient monoClient;
	
	public Room(monoClient monoclient) {
		this.monoClient = monoclient;
		f = this;
		
		this.setSize(300,300);
		this.setResizable(false);
		
		cp = this.getContentPane();
		panel = new JPanel();
		masterLabel = new JLabel();
		joinerLabel = new JLabel();
		masterPanel = new JPanel();
		joinerPanel = new JPanel();
		startButton = new JButton();
		exitButton = new JButton();
		
		masterLabel.setText("방장");
		joinerLabel.setText("도전자");
		masterPanel.setBackground(Color.BLACK);
		joinerPanel.setBackground(Color.BLACK);
		startButton.setText("시작");
		exitButton.setText("나가기");
		masterLabel.setHorizontalAlignment(JLabel.CENTER);
		joinerLabel.setHorizontalAlignment(JLabel.CENTER);
		
		masterLabel.setBounds(30, 40, 100, 30);
		joinerLabel.setBounds(160, 40, 100, 30);
		masterPanel.setBounds(30, 75, 100, 100);
		joinerPanel.setBounds(160, 75, 100, 100);
		startButton.setBounds(50, 200, 80, 30);
		exitButton.setBounds(160, 200, 80, 30);
		
		exitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				LobbyProtocol room = new LobbyProtocol(monoClient.name, LobbyProtocol.OUT_ROOM);
				room.setRoomName(monoClient.roomName);
				monoClient.lobby.f.setVisible(true);
			}
		});
		
		cp.setLayout(null);
		cp.add(masterLabel);
		cp.add(joinerLabel);
		cp.add(masterPanel);
		cp.add(joinerPanel);
		cp.add(startButton);
		cp.add(exitButton);
		
		this.setVisible(true);
	}
	
	class windowHandler extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			e.getWindow().setVisible(false);
			e.getWindow().dispose();
			monoClient.exit();
		}
	}

	public static void main(String[] args) {
		new Room(null);
	}

}
