package dialog;

import gameClient.monoClient;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import protocol.GameProtocol;

import model.Sound;

import standard.GameController;

public class missionDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	missionDialog missionDialog;
	GameController gameController;
	JFrame frame;
	JButton ok;
	String me;
	String winner;

	public missionDialog(JFrame parent, GameController gameController, String me, String winner) throws Exception {
		super(parent, "GAME OVER", true);
		this.missionDialog = this;
		this.gameController = gameController;
		this.me = me;
		this.winner = winner;
		this.setSize(new Dimension(350, 200));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.ok = new JButton("»Æ¿Œ");
		this.init();
	}

	public void init() throws Exception {
		Sound sound;
		JLabel text1;
		if(me.equals(winner)){
			sound = new Sound("Resources/sounds/game/victory.wav");
			text1 = new JLabel("VICTORY");
		}else{
			sound = new Sound("Resources/sounds/game/lose.wav");
			text1 = new JLabel("LOSE");
		}
		JLabel text2 = new JLabel("Winner-"+winner);
		JPanel textPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());

		ok.addActionListener(new okbuttonhandler());

		text1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 25));
		text1.setHorizontalAlignment(JLabel.CENTER);
		text2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 30));
		text2.setHorizontalAlignment(JLabel.CENTER);
		textPanel.add(text1, BorderLayout.NORTH);
		textPanel.add(text2, BorderLayout.SOUTH);
		buttonPanel.add(ok);

		this.getContentPane().add(textPanel, BorderLayout.NORTH);
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		gameController.board.bgm.stop();
		sound.play();
		this.setVisible(true);
	}

	class okbuttonhandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			missionDialog.dispose();
			gameController.monoClient.outGameServer();
			GameProtocol data = new GameProtocol(me, GameProtocol.GAME_OVER);
			data.setRoomName(gameController.monoClient.roomName);
			gameController.monoClient.sendToServer(data);
		}
	}
}
