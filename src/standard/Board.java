package standard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board {

	public JFrame frame;

	public Board() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		JPanel boardpanel = new JPanel();
		JPanel infopanel = new JPanel();
		JPanel chatpanel = new JPanel();
		JPanel dicepanel = new JPanel();
		JPanel topside = new JPanel();
		JPanel leftside = new JPanel();
		JPanel rightside = new JPanel();
		JPanel botside = new JPanel();

		frame.setTitle("Monopoly");
		frame.setBounds(0, 0, 1006, 900);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		topside.setLayout(new BoxLayout(topside, BoxLayout.LINE_AXIS));
		botside.setLayout(new BoxLayout(botside, BoxLayout.LINE_AXIS));
		leftside.setLayout(new BoxLayout(leftside, BoxLayout.PAGE_AXIS));
		rightside.setLayout(new BoxLayout(rightside, BoxLayout.PAGE_AXIS));

		ArrayList<JPanel> PieceList = new ArrayList<JPanel>();

		for (int i = 0; i < 36; i++) {
			PieceList.add(new JPanel());
			// 더티코딩 ㅈㅅ 나중에 바꾸면 될거임
			if (i == 0 || i == 9 || i == 10 || i == 19) {
				PieceList.get(i).setPreferredSize(new Dimension(100, 100));
				PieceList.get(i).setMaximumSize(new Dimension(100, 100));
				PieceList.get(i).setMinimumSize(new Dimension(100, 100));
				PieceList.get(i).setBackground(new Color(0, 255, 255));
			} else if (i < 20) {
				PieceList.get(i).setPreferredSize(new Dimension(60, 100));
				PieceList.get(i).setMaximumSize(new Dimension(60, 100));
				PieceList.get(i).setMinimumSize(new Dimension(60, 100));
				if (i % 2 == 0)
					PieceList.get(i).setBackground(new Color(127, 255, 0));
				else
					PieceList.get(i).setBackground(new Color(0, 255, 127));
			} else {
				PieceList.get(i).setPreferredSize(new Dimension(100, 60));
				PieceList.get(i).setMaximumSize(new Dimension(100, 60));
				PieceList.get(i).setMinimumSize(new Dimension(100, 60));
				if (i % 2 == 0)
					PieceList.get(i).setBackground(new Color(127, 255, 0));
				else
					PieceList.get(i).setBackground(new Color(0, 255, 127));
			}
		}

		for (int i = 0; i < 10; i++) {
			topside.add(PieceList.get(i));
		}
		for (int i = 10; i < 20; i++) {
			botside.add(PieceList.get(i));
		}
		for (int i = 20; i < 28; i++) {
			leftside.add(PieceList.get(i));
		}
		for (int i = 28; i < 36; i++) {
			rightside.add(PieceList.get(i));
		}

		boardpanel.setBackground(new Color(255, 255, 255));
		boardpanel.setPreferredSize(new Dimension(680, 680));
		boardpanel.setLayout(new BorderLayout());
		boardpanel.add(topside, BorderLayout.NORTH);
		boardpanel.add(botside, BorderLayout.SOUTH);
		boardpanel.add(leftside, BorderLayout.WEST);
		boardpanel.add(rightside, BorderLayout.EAST);

		infopanel.setBackground(new Color(127, 127, 127));
		infopanel.setPreferredSize(new Dimension(320, 680));

		chatpanel.setBackground(new Color(255, 0, 255));
		chatpanel.setPreferredSize(new Dimension(680, 320));

		dicepanel.setBackground(new Color(0, 255, 0));
		dicepanel.setPreferredSize(new Dimension(320, 320));

		frame.getContentPane().add(boardpanel);
		frame.getContentPane().add(infopanel);
		frame.getContentPane().add(chatpanel);
		frame.getContentPane().add(dicepanel);

	}

}
