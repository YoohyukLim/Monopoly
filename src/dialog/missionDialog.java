package dialog;

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

import standard.GameController;

public class missionDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	missionDialog missionDialog;
	GameController gameController;
	JFrame frame;
	JButton ok;
	int playerNumber;

	public missionDialog(JFrame parent, GameController gameController, int playerNumber) {
		super(parent, "GAME OVER", true);
		this.missionDialog = this;
		this.gameController = gameController;
		this.playerNumber = playerNumber;
		this.setSize(new Dimension(350, 200));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.ok = new JButton("»Æ¿Œ");
		this.init();
	}

	public void init() {
		JLabel text1 = new JLabel("VICTORY");
		JLabel text2 = new JLabel(gameController.Players.get(playerNumber).getName());
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
		this.setVisible(true);
	}

	class okbuttonhandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			missionDialog.setVisible(false);
			gameController.board.frame.setVisible(false);
			System.exit(0);
		}
	}
}
