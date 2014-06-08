package standard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Card;

public class cardDialog extends JDialog {
	cardDialog carddialog;
	GameController gameController;
	JFrame frame;
	JButton ok, cancel;
	Card card;
	
	public cardDialog(JFrame parent, GameController gameController, Card card){
		super(parent, "Watch out!!",true);
		this.carddialog = this;
		this.gameController = gameController;
		this.card = card;
		this.setSize(new Dimension(350, 200));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.ok = new JButton("기존의 카드 버리기");
		this.cancel = new JButton("새로운 카드 버리기");
		this.init();
	}
	
	public void init(){
		JLabel text1 = new JLabel("카드의 개수가 소지할 수 있는 범위를 초과했습니다.");
		JLabel text2 = new JLabel("기존에 있던 카드를 버리시겠습니까?");
		JPanel textPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());

		ok.addActionListener(new okbuttonhandler());
		cancel.addActionListener(new cancelbuttonhandler());
		
		textPanel.add(text1, BorderLayout.NORTH);
		textPanel.add(text2, BorderLayout.SOUTH);
		buttonPanel.add(ok);
		buttonPanel.add(cancel);
		
		this.getContentPane().add(textPanel, BorderLayout.NORTH);
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	class okbuttonhandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class cancelbuttonhandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			carddialog.setVisible(false);
		}
	}
}
