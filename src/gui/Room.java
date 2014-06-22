package gui;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	public Room() {
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
		
		cp.setLayout(null);
		cp.add(masterLabel);
		cp.add(joinerLabel);
		cp.add(masterPanel);
		cp.add(joinerPanel);
		cp.add(startButton);
		cp.add(exitButton);
	}

	public static void main(String[] args) {
		f = new Room();
		f.setSize(300,300);
		f.setResizable(false);
		f.setVisible(true);
	}

}
