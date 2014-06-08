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
	int currentPlayer;
	URL url_dice = new File("Resources/Dice_button.png").toURI().toURL();
	Icon dice_icon = new ImageIcon(url_dice);
	JLabel Dice_button;

	JPanel boardpanel, infopanel, chatpanel, dicepanel, topside, leftside,
			rightside, botside;
	JPanel playerside, cardside, cardpanel[], cardstate[], cards[][];
	JLabel Map_piece[];
	MyPanel[][] playerPiece;
	ArrayList<Piece> players;
	ArrayList<Card> playerCard;

	public JFrame frame;
	CardLayout cardlayout;

	public Board(Map map) throws Exception {
		this.map = map;
	}

	public void getController(GameController _gameController) throws Exception {
		this.gameController = _gameController;
		this.currentPlayer = _gameController.currentPlayer;
		this.players = _gameController.Players;
		initialize();
		this.frame.setVisible(true);
	}

	private void initialize() throws Exception {
		frame = new JFrame();
		cardlayout = new CardLayout();

		boardpanel = new JPanel();
		infopanel = new JPanel();
		chatpanel = new JPanel();
		dicepanel = new JPanel();
		topside = new JPanel();
		leftside = new JPanel();
		rightside = new JPanel();
		botside = new JPanel();

		playerside = new JPanel();
		cardside = new JPanel();

		Map_piece = new JLabel[36];
		playerPiece = new MyPanel[36][2];

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
			playerPiece[i][0] = new MyPanel(Color.RED);
			playerPiece[i][1] = new MyPanel(Color.BLUE);
			PieceList.get(players.get(currentPlayer).getPosition()).add(playerPiece[i][0]);
			PieceList.get(players.get(currentPlayer).getPosition()).add(playerPiece[i][1]);
			if (i == 0) {
				playerPiece[i][0].setVisible(true);
				playerPiece[i][1].setVisible(true);
			} else {
				playerPiece[i][0].setVisible(false);
				playerPiece[i][1].setVisible(false);
			}
			
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
		Dice_button = new JLabel(dice_icon);
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

		// Card
		cardside.setLayout(cardlayout);
		cardpanel = new JPanel[2];
		cardstate = new JPanel[2];
		cards = new JPanel[2][5];
		for (int i = 0; i < cardpanel.length; i++) {
			cardpanel[i] = new JPanel();
			cardpanel[i].setLayout(new FlowLayout(0, 0, 0));
			cardpanel[i].setBackground(new Color(255, 0, 255));

			cardstate[i] = new JPanel();
			cardstate[i].setPreferredSize(new Dimension(320, 500));
			cardstate[i].setMinimumSize(new Dimension(320, 500));
			cardstate[i].setMaximumSize(new Dimension(320, 500));

			JLabel mycard_label = new JLabel();
			mycard_label.setOpaque(true);
			mycard_label.setText(players.get(i).getName() + "'s Cards");
			mycard_label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
			mycard_label.setBackground(new Color(0, 0, 127));
			mycard_label.setForeground(new Color(255, 0, 0));
			mycard_label.setPreferredSize(new Dimension(320, 30));
			mycard_label.setMinimumSize(new Dimension(320, 30));
			mycard_label.setMaximumSize(new Dimension(320, 30));
			mycard_label.setHorizontalAlignment(JLabel.CENTER);
			cardpanel[i].add(mycard_label, 0);
			cardpanel[i].add(cardstate[i], 1);
		}

		cardside.add(cardpanel[0], String.valueOf(0));
		cardside.add(cardpanel[1], String.valueOf(1));
		cardlayout.show(cardside, "0");

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

	public void update(String msg) {
		switch (msg) {
		case "card":
			cardlayout.show(cardside, String.valueOf(currentPlayer));
			break;
		}
	}

	public void refreshCards() {
		playerCard = gameController.Players.get(currentPlayer).cardList;
		int length = playerCard.size();
		System.out.println("//"
				+ gameController.Players.get(currentPlayer).getName()
				+ "'s cards " + length + "//");

		cardpanel[currentPlayer].remove(1);
		cardstate[currentPlayer].removeAll();

		for (int i = 0; i < length; i++) {
			Card temp = playerCard.get(i);
			int number = temp.getCardNumber();
			JLabel cardnumber = new JLabel("Card Num: "
					+ String.valueOf(number));
			JLabel cardtext = new JLabel(temp.getTypeText(number));
			cards[currentPlayer][i] = new JPanel();
			cards[currentPlayer][i].setPreferredSize(new Dimension(320, 100));
			cards[currentPlayer][i].setMinimumSize(new Dimension(320, 100));
			cards[currentPlayer][i].setMaximumSize(new Dimension(320, 100));
			cards[currentPlayer][i].setBackground(new Color(255 - 10 * i,
					255 - 10 * i, 255 - 10 * i));
			cards[currentPlayer][i].add(cardnumber, 0);
			cards[currentPlayer][i].add(cardtext, 1);

			cardstate[currentPlayer].add(cards[currentPlayer][i]);
		}

		cardpanel[currentPlayer].add(cardstate[currentPlayer], 1);
		cardside.remove(0);
		cardside.add(cardpanel[currentPlayer], String.valueOf(currentPlayer));		
	}

	class DiceBtnHandler implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			System.out.println("/********************************/");
			gameController.getCard();
			refreshCards();
			update("card");
			disappearPiece();
			gameController.setPlayerbyDice();
			showPiece();
			gameController.MapExec();

			currentPlayer = gameController.changePlayer();
			update("card");
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
	
	public void disappearPiece() {
		playerPiece[players.get(currentPlayer).getPosition()][currentPlayer].setVisible(false);
	}
	
	public void showPiece() {
		playerPiece[players.get(currentPlayer).getPosition()][currentPlayer].setVisible(true);
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
