package standard;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class Board {
	Map map;
	GameController gameController;
	static ArrayList<JPanel> PieceList = new ArrayList<JPanel>();

	public JFrame frame;

	public Board(Map map) throws Exception {
		this.map = map;
	}

	public void getController(GameController _gameController) throws Exception {
		this.gameController = _gameController;
		initialize();
		this.frame.setVisible(true);
	}

	private void initialize() throws Exception {
		frame = new JFrame();
		JPanel boardpanel = new JPanel();
		JPanel infopanel = new JPanel();
		JPanel chatpanel = new JPanel();
		JPanel dicepanel = new JPanel();
		JPanel topside = new JPanel();
		JPanel leftside = new JPanel();
		JPanel rightside = new JPanel();
		JPanel botside = new JPanel();

		JPanel playerside = new JPanel();
		JPanel cardside = new JPanel();

		JLabel Map_piece[] = new JLabel[36];

		frame.setTitle("Monopoly");
		frame.setBounds(0, 0, 1006, 900);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		topside.setLayout(new BoxLayout(topside, BoxLayout.LINE_AXIS));
		botside.setLayout(new BoxLayout(botside, BoxLayout.LINE_AXIS));
		leftside.setLayout(new BoxLayout(leftside, BoxLayout.PAGE_AXIS));
		rightside.setLayout(new BoxLayout(rightside, BoxLayout.PAGE_AXIS));

		MapButtonHandler MBHandler = new MapButtonHandler();
		// MapPieces
		for (int i = 0; i < 36; i++) {
			PieceList.add(new JPanel());
			if (i == 0 || i == 9 || i == 18 || i == 27) {
				PieceList.get(i).setPreferredSize(new Dimension(100, 100));
				PieceList.get(i).setMaximumSize(new Dimension(100, 100));
				PieceList.get(i).setMinimumSize(new Dimension(100, 100));
				PieceList.get(i).setBackground(new Color(0, 255, 255));

				Map_piece[i] = new JLabel(String.valueOf(Map.Map_pieces[i]
						.get_map_number()));
				PieceList.get(i).add(Map_piece[i]);
			} else if (i < 10 || (i >= 18 && i <= 27)) {
				PieceList.get(i).setPreferredSize(new Dimension(60, 100));
				PieceList.get(i).setMaximumSize(new Dimension(60, 100));
				PieceList.get(i).setMinimumSize(new Dimension(60, 100));

				// Map Piece Panel
				Map_piece[i] = new JLabel(String.valueOf(Map.Map_pieces[i]
						.get_map_number()));
				// Map_piece[i].setPreferredSize(new Dimension(30, 100));
				// Map_piece[i].setMaximumSize(new Dimension(30, 100));
				// Map_piece[i].setMinimumSize(new Dimension(30, 100));
				Map_piece[i].addMouseListener(MBHandler);

				PieceList.get(i).add(Map_piece[i]);
				if (i % 2 == 0)
					PieceList.get(i).setBackground(new Color(127, 255, 0));
				else
					PieceList.get(i).setBackground(new Color(0, 255, 127));
			} else {
				PieceList.get(i).setPreferredSize(new Dimension(100, 60));
				PieceList.get(i).setMaximumSize(new Dimension(100, 60));
				PieceList.get(i).setMinimumSize(new Dimension(100, 60));

				// Map Piece Panel
				Map_piece[i] = new JLabel(String.valueOf(Map.Map_pieces[i]
						.get_map_number()));
				// Map_piece[i].setPreferredSize(new Dimension(100, 30));
				// Map_piece[i].setMaximumSize(new Dimension(100, 30));
				// Map_piece[i].setMinimumSize(new Dimension(100, 30));
				Map_piece[i].addMouseListener(MBHandler);

				PieceList.get(i).add(Map_piece[i]);
				if (i % 2 == 0)
					PieceList.get(i).setBackground(new Color(127, 255, 0));
				else
					PieceList.get(i).setBackground(new Color(0, 255, 127));
			}
			MyPanel a = new MyPanel(Color.RED);
			MyPanel b = new MyPanel(Color.BLUE);
			PieceList.get(i).add(a);
			PieceList.get(i).add(b);
		}

		for (int i = 9; i >= 0; i--) {
			botside.add(PieceList.get(i));
		}
		for (int i = 17; i >= 10; i--) {
			leftside.add(PieceList.get(i));
		}
		for (int i = 18; i < 28; i++) {
			topside.add(PieceList.get(i));
		}
		for (int i = 28; i < 36; i++) {
			rightside.add(PieceList.get(i));
		}

		// DicePanel
		dicepanel.setBackground(new Color(255, 255, 0));
		URL url = new File("Resources/Dice_button.png").toURI().toURL();
		// URL url = new File("Resources/0002.gif").toURI().toURL();
		Icon icon = new ImageIcon(url);
		JLabel Dice_button = new JLabel(icon);
		DiceBtnHandler DBhandler = new DiceBtnHandler();
		Dice_button.addMouseListener(DBhandler);
		Dice_button.setPreferredSize(new Dimension(480, 480));
		Dice_button.setMaximumSize(new Dimension(480, 480));
		Dice_button.setMinimumSize(new Dimension(480, 480));
		dicepanel.add(Dice_button);
		Dice_button.setHorizontalAlignment(JLabel.CENTER);
		Dice_button.setVerticalAlignment(JLabel.CENTER);

		boardpanel.setBackground(new Color(255, 255, 255));
		boardpanel.setPreferredSize(new Dimension(680, 680));
		boardpanel.setLayout(new BorderLayout(0, 0));
		boardpanel.add(topside, BorderLayout.NORTH);
		boardpanel.add(botside, BorderLayout.SOUTH);
		boardpanel.add(leftside, BorderLayout.WEST);
		boardpanel.add(rightside, BorderLayout.EAST);
		boardpanel.add(dicepanel, BorderLayout.CENTER);
		infopanel.setLayout(new BorderLayout(0, 0));
		infopanel.add(playerside, BorderLayout.NORTH);
		cardside.setLayout(new CardLayout());

		JPanel[][] cardpanel = new JPanel[2][6];
		for (int i = 1; i < cardpanel.length; i++) {
			cardpanel[i][0] = new JPanel();
			cardpanel[i][0].setLayout(new FlowLayout(0, 0, 0));
			cardpanel[i][0].setBackground(new Color(255, 0, 255));
			JLabel mycard_label = new JLabel();
			mycard_label.setOpaque(true);
			mycard_label.setText("My Card");
			mycard_label.setFont(new Font("���� ����", Font.BOLD, 15));
			mycard_label.setBackground(new Color(0, 0, 127));
			mycard_label.setForeground(new Color(255, 0, 0));
			mycard_label.setPreferredSize(new Dimension(320, 30));
			mycard_label.setMinimumSize(new Dimension(320, 30));
			mycard_label.setMaximumSize(new Dimension(320, 30));
			mycard_label.setHorizontalAlignment(JLabel.CENTER);
			cardpanel[i][0].add(mycard_label);
			for (int j = 1; j <= 5; j++) {
				cardpanel[i][j] = new JPanel();
				cardpanel[i][j].setPreferredSize(new Dimension(320, 100));
				cardpanel[i][j].setMinimumSize(new Dimension(320, 100));
				cardpanel[i][j].setMaximumSize(new Dimension(320, 100));
				cardpanel[i][j]
						.setBackground(new Color(50 * j, 50 * j, 50 * j));
				cardpanel[i][0].add(cardpanel[i][j]);
			}
			cardside.add(cardpanel[i][0]);
		}

		infopanel.add(cardside, BorderLayout.SOUTH);

		infopanel.setBackground(new Color(127, 127, 127));
		infopanel.setPreferredSize(new Dimension(320, 680));
		playerside.setPreferredSize(new Dimension(320, 150));
		playerside.setBackground(new Color(0, 0, 0));
		cardside.setPreferredSize(new Dimension(320, 530));
		cardside.setBackground(new Color(0, 127, 255));

		chatpanel.setBackground(new Color(255, 0, 255));
		chatpanel.setPreferredSize(new Dimension(680, 320));

		frame.getContentPane().add(boardpanel);
		frame.getContentPane().add(infopanel);
		frame.getContentPane().add(chatpanel);
	}

	/********************************************************/
	/*
	 * public void refreshPositionPlayer(int position) { JLabel newPositionLabel
	 * = null; String pathImage = "Resources/Piece_1.JPG";
	 * 
	 * if(position == 0) { newPositionLabel = new JLabel(new
	 * ImageIcon(pathImage)); newPositionLabel.setBounds(500, 480, 41, 30); }
	 * else if(position == 10 &&
	 * gameStateController.getPlayer(gameStateController
	 * .getaQuiLeTour()).getPrison().isInPrison()==false) { newPositionLabel =
	 * new JLabel(new ImageIcon(pathImage));
	 * newPositionLabel.setBounds(gameStateController.getaQuiLeTour() * 41, 500,
	 * 41, 30); } else if(position == 10 &&
	 * gameStateController.getPlayer(gameStateController
	 * .getaQuiLeTour()).getPrison().isInPrison()==true) { newPositionLabel =
	 * new JLabel(new ImageIcon(pathImage)); newPositionLabel.setBounds(30 +
	 * gameStateController.getaQuiLeTour() * 30, 475, 41, 30); } else
	 * if(position == 20) { newPositionLabel = new JLabel(new
	 * ImageIcon(pathImage)); newPositionLabel.setBounds(20, 10 +
	 * gameStateController.getaQuiLeTour() * 30, 41, 30); } else if(position ==
	 * 30) { newPositionLabel = new JLabel(new ImageIcon(pathImage));
	 * newPositionLabel.setBounds(500, 20 + gameStateController.getaQuiLeTour()
	 * * 30, 41, 30); } else if(position > 0 && position < 10) {
	 * newPositionLabel = new JLabel(new ImageIcon(pathImage));
	 * newPositionLabel.setBounds(476 - (position * 45), 480 +
	 * gameStateController.getaQuiLeTour() * 30, 41, 30); } else if(position >
	 * 10 && position < 20) { newPositionLabel = new JLabel(new
	 * ImageIcon(pathImage));
	 * newPositionLabel.setBounds(gameStateController.getaQuiLeTour() * 35, 476
	 * - ((position-10) * 45), 41, 30); } else if(position > 20 && position <
	 * 30) { newPositionLabel = new JLabel(new ImageIcon(pathImage));
	 * newPositionLabel.setBounds(72 + ((position-21) * 45),
	 * gameStateController.getaQuiLeTour() * 30, 41, 30); } else if(position >
	 * 30 && position < 40) { newPositionLabel = new JLabel(new
	 * ImageIcon(pathImage)); newPositionLabel.setBounds(480 +
	 * gameStateController.getaQuiLeTour() * 35, 72 + ((position-31) * 45), 41,
	 * 30); }
	 * 
	 * System.out.println("Affichage nouvelle location pion : ");
	 * System.out.println("x : " + newPositionLabel.getLocation().x);
	 * System.out.println("y : " + newPositionLabel.getLocation().y);
	 * this.arrayPion[gameStateController.getaQuiLeTour()].setVisible(false);
	 * this.arrayPion[gameStateController.getaQuiLeTour()] = newPositionLabel;
	 * imagePlateau.remove(gameStateController.getaQuiLeTour());
	 * imagePlateau.add(this.arrayPion[gameStateController.getaQuiLeTour()],
	 * gameStateController.getaQuiLeTour()); }
	 */
	/********************************************************/

	class DiceBtnHandler implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			gameController.setPlayerbyDice();
			gameController.MapExec();
			gameController.changePlayer();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}

	class MapButtonHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel map_piece = (JLabel) e.getComponent();
			int map_number = Integer.parseInt(map_piece.getText());
			new Map_piece().getTypeText(map_number);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}

	class MyPanel extends JPanel {
		public MyPanel(Color color) {
			this.setBackground(color);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawRect(10, 10, 15, 15);
		}
	}
}
