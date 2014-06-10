package standard;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Card;
import model.Map_piece;
import model.Piece;

public class Board {
	Map map;
	GameController gameController;
	static ArrayList<JLayeredPane> PieceList = new ArrayList<JLayeredPane>();
	int currentPlayer, currentTurn;
	URL url_dice = new File("Resources/image/Dice_button.png").toURI().toURL();
	Icon dice_icon = new ImageIcon(url_dice);
	Icon backimage = new ImageIcon("Resources/image/background.jpg");
	Icon stoneImage0 = new ImageIcon("Resources/image/Stone0.jpg");
	Icon stoneImage1 = new ImageIcon("Resources/image/Stone1.jpg");
	Icon stoneImage2 = new ImageIcon("Resources/image/Stone2.jpg");
	JLabel Dice_button, background, stone;

	JPanel boardpanel, infopanel, chatpanel, topside, leftside, rightside,
			botside;
	JLayeredPane dicepanel;
	JPanel infoside, cardside, cardpanel[], cardstate[], cards[][];
	JLabel Map_piece[];
	MyPanel[][] playerPiece;
	JPanel[] playerPiecePanel;
	JPanel[] cardPiece;
	ArrayList<Piece> players;
	ArrayList<Card> playerCard;

	public JFrame frame;
	CardLayout cardlayout;

	public boolean lessThanFive = true;
	public boolean cardtime = false;

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
		dicepanel = new JLayeredPane();
		topside = new JPanel();
		leftside = new JPanel();
		rightside = new JPanel();
		botside = new JPanel();

		infoside = new JPanel();
		cardside = new JPanel();

		Map_piece = new JLabel[36];
		playerPiece = new MyPanel[36][2];
		playerPiecePanel = new JPanel[36];
		cardPiece = new JPanel[36];

		frame.setTitle("Monopoly");
		frame.setBounds(0, 0, 1006, 900);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		topside.setLayout(new BoxLayout(topside, BoxLayout.LINE_AXIS));
		botside.setLayout(new BoxLayout(botside, BoxLayout.LINE_AXIS));
		leftside.setLayout(new BoxLayout(leftside, BoxLayout.PAGE_AXIS));
		rightside.setLayout(new BoxLayout(rightside, BoxLayout.PAGE_AXIS));
		infoside.setLayout(new GridLayout(3, 1));

		MapBtnHandler MBHandler = new MapBtnHandler();
		// MapPieces
		for (int i = 0; i < 36; i++) {
			PieceList.add(new JLayeredPane());
			playerPiecePanel[i] = new JPanel();
			playerPiece[i][0] = new MyPanel(Color.RED);
			playerPiece[i][1] = new MyPanel(Color.BLUE);
			playerPiecePanel[i].setLayout(new GridBagLayout());
			playerPiecePanel[i].setOpaque(false);
			playerPiecePanel[i].add(playerPiece[i][0]);
			playerPiecePanel[i].add(playerPiece[i][1]);

			cardPiece[i] = new JPanel();
			JLabel cardnum = new JLabel(String.valueOf(i));
			cardnum.setVisible(false);
			cardPiece[i].add(cardnum, 0);
			cardPiece[i].setVisible(false);

			gameController.cardmap[i][0] = 0;
			gameController.cardmap[i][1] = 0;
			gameController.cardmap[i][2] = 0;

			// PieceList.get(i).setLayout(new GridBagLayout());
			// PieceList.get(i).add(playerPiece[i][0]);
			// PieceList.get(i).add(playerPiece[i][1]);
			PieceList.get(i).setLayout(
					new BoxLayout(PieceList.get(i), BoxLayout.PAGE_AXIS));
			PieceList.get(i).add(playerPiecePanel[i]);
			if (i != 0)
				PieceList.get(i).add(cardPiece[i]);

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
				playerPiecePanel[i].setPreferredSize(new Dimension(100, 50));
				playerPiecePanel[i].setMaximumSize(new Dimension(100, 50));
				playerPiecePanel[i].setMinimumSize(new Dimension(100, 50));
				// PieceList.get(i).
				Map_piece[i] = new JLabel(String.valueOf(Map.Map_pieces[i]
						.get_map_number()));
				Map_piece[i].setVisible(false);

				stone = new JLabel(stoneImage0);
				PieceList.get(i).add(stone, new Integer(1));
				stone.setBounds(0, 0, stoneImage0.getIconWidth(),
						stoneImage0.getIconHeight());
				PieceList.get(i).add(Map_piece[i], new Integer(2));

			} else if (i < 10 || (i >= 18 && i <= 27)) {
				PieceList.get(i).setPreferredSize(new Dimension(60, 100));
				PieceList.get(i).setMaximumSize(new Dimension(60, 100));
				PieceList.get(i).setMinimumSize(new Dimension(60, 100));
				/*playerPiecePanel[i].setPreferredSize(new Dimension(60, 50));
				playerPiecePanel[i].setMaximumSize(new Dimension(60, 50));
				playerPiecePanel[i].setMinimumSize(new Dimension(60, 50));
*/
				cardPiece[i].setPreferredSize(new Dimension(60, 50));
				cardPiece[i].setMaximumSize(new Dimension(60, 50));
				cardPiece[i].setMinimumSize(new Dimension(60, 50));

				// Map Piece Panel
				Map_piece[i] = new JLabel(String.valueOf(Map.Map_pieces[i]
						.get_map_number()));
				Map_piece[i].setVisible(false);

				stone = new JLabel(stoneImage1);
				PieceList.get(i).add(stone, new Integer(1));
				stone.setBounds(0, 0, stoneImage0.getIconWidth(),
						stoneImage0.getIconHeight());
				PieceList.get(i).add(Map_piece[i], new Integer(2));

				// Map_piece[i].setPreferredSize(new Dimension(30, 100));
				// Map_piece[i].setMaximumSize(new Dimension(30, 100));
				// Map_piece[i].setMinimumSize(new Dimension(30, 100));

				PieceList.get(i).add(Map_piece[i], 0);
				PieceList.get(i).addMouseListener(MBHandler);
			/*
			 * 	if (i % 2 == 0)
					PieceList.get(i).setBackground(new Color(127, 255, 0));
				else
					PieceList.get(i).setBackground(new Color(0, 255, 127));
					*/
			} else {
				PieceList.get(i).setPreferredSize(new Dimension(100, 60));
				PieceList.get(i).setMaximumSize(new Dimension(100, 60));
				PieceList.get(i).setMinimumSize(new Dimension(100, 60));
			/*	playerPiecePanel[i].setPreferredSize(new Dimension(100, 30));
				playerPiecePanel[i].setMaximumSize(new Dimension(100, 30));
				playerPiecePanel[i].setMinimumSize(new Dimension(100, 30));
*/
				cardPiece[i].setPreferredSize(new Dimension(100, 30));
				cardPiece[i].setMaximumSize(new Dimension(100, 30));
				cardPiece[i].setMinimumSize(new Dimension(100, 30));

				// Map Piece Panel
				Map_piece[i] = new JLabel(String.valueOf(Map.Map_pieces[i]
						.get_map_number()));
				Map_piece[i].setVisible(false);
				 Map_piece[i].setPreferredSize(new Dimension(100, 30));
				 Map_piece[i].setMaximumSize(new Dimension(100, 30));
				 Map_piece[i].setMinimumSize(new Dimension(100, 30));

				PieceList.get(i).add(Map_piece[i], 0);
				PieceList.get(i).addMouseListener(MBHandler);
				
				stone = new JLabel(stoneImage2);
				stone.setPreferredSize(new Dimension(100, 60));
				stone.setMaximumSize(new Dimension(100, 60));
				stone.setMinimumSize(new Dimension(100, 60));
				PieceList.get(i).add(stone, new Integer(1));
				stone.setBounds(0, 0, stoneImage0.getIconWidth(),
						stoneImage0.getIconHeight());
				PieceList.get(i).add(Map_piece[i], new Integer(2));

			/*	if (i % 2 == 0)
					PieceList.get(i).setBackground(new Color(127, 255, 0));
				else
					PieceList.get(i).setBackground(new Color(0, 255, 127));
					*/
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
		background = new JLabel(backimage);
		dicepanel.add(background, new Integer(1));
		Dice_button = new JLabel(dice_icon);
		DiceBtnHandler DBhandler = new DiceBtnHandler();
		Dice_button.addMouseListener(DBhandler);
		dicepanel.add(Dice_button, new Integer(2));
		background.setBounds(0, 0, backimage.getIconWidth(),
				backimage.getIconHeight());
		Dice_button.setBounds(165, 300, dice_icon.getIconWidth(),
				dice_icon.getIconHeight());

		boardpanel.setBackground(new Color(255, 255, 255));
		boardpanel.setPreferredSize(new Dimension(680, 680));
		boardpanel.setLayout(new BorderLayout(0, 0));
		boardpanel.add(topside, BorderLayout.NORTH);
		boardpanel.add(botside, BorderLayout.SOUTH);
		boardpanel.add(leftside, BorderLayout.WEST);
		boardpanel.add(rightside, BorderLayout.EAST);
		boardpanel.add(dicepanel, BorderLayout.CENTER);
		infopanel.setLayout(new BorderLayout(0, 0));
		infopanel.add(infoside, BorderLayout.NORTH);

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
			cardstate[i].setLayout(new FlowLayout(0, 0, 0));
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
		infoside.setPreferredSize(new Dimension(320, 150));
		infoside.setBackground(new Color(0, 0, 0));
		cardside.setPreferredSize(new Dimension(320, 530));
		cardside.setBackground(new Color(0, 127, 255));

		chatpanel.setBackground(new Color(255, 0, 255));
		chatpanel.setPreferredSize(new Dimension(680, 320));

		frame.getContentPane().add(boardpanel);
		frame.getContentPane().add(infopanel);
		frame.getContentPane().add(chatpanel);

		refreshInfo();
	}

	private Color getImage(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(String msg) {
		switch (msg) {
		case "card":
			cardlayout.show(cardside, String.valueOf(currentPlayer));
			break;
		}
	}

	public void refreshInfo() {
		JLabel playerNameLabel = new JLabel();
		JLabel currentTurnLabel = new JLabel();
		JLabel rotationCntLabel = new JLabel();

		currentTurn = gameController.getTurn();
		players = gameController.Players;

		infoside.removeAll();
		playerNameLabel
				.setText("ÇÃ·¹ÀÌ¾î: " + players.get(currentPlayer).getName());
		playerNameLabel.setForeground(Color.WHITE);
		playerNameLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		currentTurnLabel.setText("ÅÏ ¼ö: "
				+ String.valueOf(gameController.getTurn()));
		currentTurnLabel.setForeground(Color.WHITE);
		currentTurnLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		rotationCntLabel.setText("µ¹Àº ¹ÙÄû ¼ö: "
				+ String.valueOf(players.get(currentPlayer).rotationCnt));
		rotationCntLabel.setForeground(Color.WHITE);
		rotationCntLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));

		infoside.add(playerNameLabel);
		infoside.add(currentTurnLabel);
		infoside.add(rotationCntLabel);
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
			JLabel cardtype = new JLabel("Card Num: " + String.valueOf(number));
			JLabel cardtext = new JLabel(temp.getTypeText(number));

			cards[currentPlayer][i] = new JPanel();
			cards[currentPlayer][i].setPreferredSize(new Dimension(320, 100));
			cards[currentPlayer][i].setMinimumSize(new Dimension(320, 100));
			cards[currentPlayer][i].setMaximumSize(new Dimension(320, 100));
			cards[currentPlayer][i].setBackground(new Color(255 - 10 * i,
					255 - 10 * i, 255 - 10 * i));
			cards[currentPlayer][i].add(cardtype, 0);
			cards[currentPlayer][i].add(cardtext, 1);

			cardstate[currentPlayer].add(cards[currentPlayer][i]);
		}

		cardpanel[currentPlayer].add(cardstate[currentPlayer], 1);
		cardside.remove(cardpanel[currentPlayer]);
		cardside.add(cardpanel[currentPlayer], String.valueOf(currentPlayer));
	}

	public void deleteCards() {
		playerCard = gameController.Players.get(currentPlayer).cardList;
		int length = playerCard.size();
		DeleteCardBtnHandler cardhandler = new DeleteCardBtnHandler();

		System.out.println("Let's Delete Card!");

		cardpanel[currentPlayer].remove(1);
		cardstate[currentPlayer].removeAll();

		for (int i = 0; i < length; i++) {
			Card temp = playerCard.get(i);
			int number = temp.getCardNumber();
			JLabel cardtype = new JLabel("Card Num: " + String.valueOf(number));
			JLabel cardtext = new JLabel(temp.getTypeText(number));
			JLabel cardnumber = new JLabel(String.valueOf(i));
			cardnumber.setVisible(false);

			cards[currentPlayer][i] = new JPanel();
			cards[currentPlayer][i].setPreferredSize(new Dimension(320, 100));
			cards[currentPlayer][i].setMinimumSize(new Dimension(320, 100));
			cards[currentPlayer][i].setMaximumSize(new Dimension(320, 100));
			cards[currentPlayer][i].setBackground(new Color(255 - 10 * i,
					255 - 10 * i, 255 - 10 * i));
			cards[currentPlayer][i].add(cardtype, 0);
			cards[currentPlayer][i].add(cardtext, 1);
			cards[currentPlayer][i].add(cardnumber, 2);
			cards[currentPlayer][i].addMouseListener(cardhandler);

			cardstate[currentPlayer].add(cards[currentPlayer][i]);
		}

		cardpanel[currentPlayer].add(cardstate[currentPlayer], 1);
		cardside.remove(cardpanel[currentPlayer]);
		cardside.add(cardpanel[currentPlayer], String.valueOf(currentPlayer));
		cardlayout.show(cardside, String.valueOf(currentPlayer));
	}

	public void executableCards() {
		playerCard = gameController.Players.get(currentPlayer).cardList;
		int length = playerCard.size();
		CardBtnHandler cardhandler = new CardBtnHandler();
		cardtime = true;

		System.out.println("Let's use Card!");

		cardpanel[currentPlayer].remove(1);
		cardstate[currentPlayer].removeAll();

		for (int i = 0; i < length; i++) {
			Card temp = playerCard.get(i);
			int number = temp.getCardNumber();
			JLabel cardtype = new JLabel("Card Num: " + String.valueOf(number));
			JLabel cardtext = new JLabel(temp.getTypeText(number));
			JLabel cardnumber = new JLabel(String.valueOf(i));
			cardnumber.setVisible(false);

			cards[currentPlayer][i] = new JPanel();
			cards[currentPlayer][i].setPreferredSize(new Dimension(320, 100));
			cards[currentPlayer][i].setMinimumSize(new Dimension(320, 100));
			cards[currentPlayer][i].setMaximumSize(new Dimension(320, 100));
			cards[currentPlayer][i].setBackground(new Color(255 - 10 * i,
					255 - 10 * i, 255 - 10 * i));
			cards[currentPlayer][i].add(cardtype, 0);
			cards[currentPlayer][i].add(cardtext, 1);
			cards[currentPlayer][i].add(cardnumber, 2);
			cards[currentPlayer][i].addMouseListener(cardhandler);

			cardstate[currentPlayer].add(cards[currentPlayer][i]);
		}

		cardpanel[currentPlayer].add(cardstate[currentPlayer], 1);
		cardside.remove(cardpanel[currentPlayer]);
		cardside.add(cardpanel[currentPlayer], String.valueOf(currentPlayer));
		cardlayout.show(cardside, String.valueOf(currentPlayer));
	}

	public void dorest() {
		refreshCards();
		update("card");
		disappearPiece(currentPlayer);
		gameController.setPlayerbyDice();
		refreshInfo();
		// showPiece(currentPlayer);
		// disappearPiece(currentPlayer);
		/*
		 * try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e1) {
		 * // TODO Auto-generated catch block e1.printStackTrace(); }
		 */
		gameController.CardExec();
		gameController.MapExec();
		refreshInfo();
		/*
		 * try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e1) {
		 * // TODO Auto-generated catch block e1.printStackTrace(); }
		 */
		// showPiece(currentPlayer);
		// disappearPiece(currentPlayer);
		gameController.catching();
		showPiece(currentPlayer);

		executableCards();
	}

	public void dofinal() {
		gameController.missionCheck();
		currentPlayer = gameController.changePlayer();
		update("card");
		refreshInfo();
		cardtime = false;
	}

	class DiceBtnHandler implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			System.out.println("/********************************/");
			if (lessThanFive == false) {
				JOptionPane.showMessageDialog(null, "Áö¿ï Ä«µå¸¦ Å¬¸¯ÇØ¾ß ÇÕ´Ï´Ù.");
				return;
			} else if (cardtime == true) {
				JOptionPane.showMessageDialog(null, "Ä«µå¸¦ »ç¿ëÇÏÁö ¾Ê°í ³Ñ¾î°©´Ï´Ù.");

				dofinal();
				return;
			}

			lessThanFive = gameController.getCard();

			if (lessThanFive == true) {
				dorest();
			}
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

	class MapBtnHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			JPanel panel = (JPanel) e.getComponent();
			JLabel map_piece = (JLabel) panel.getComponent(0);
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

	class DeleteCardBtnHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			JPanel card = (JPanel) e.getComponent();
			JLabel cardNumber = (JLabel) card.getComponent(2);
			int number = Integer.parseInt(cardNumber.getText());
			System.out.println("Deleting card has clicked!");
			gameController.deleteCard(number);
			gameController.addCard(gameController.tempcard);

			dorest();

			lessThanFive = true;
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

	class CardBtnHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			JPanel card = (JPanel) e.getComponent();
			JLabel cardNumber = (JLabel) card.getComponent(2);
			int number = Integer.parseInt(cardNumber.getText());
			System.out.println("Using this card!");
			if (gameController.useCard(number)) {
				dofinal();
				cardtime = false;
			}
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

	public void disappearPiece(int Player) {
		playerPiece[players.get(Player).getPosition()][Player]
				.setVisible(false);
	}

	public void showPiece(int Player) {
		players = gameController.Players;
		playerPiece[players.get(Player).getPosition()][Player].setVisible(true);
	}

	public void disappearCard(int card) {
		gameController.cardmap[card][0] = 0;
		gameController.cardmap[card][1] = 0;
		gameController.cardmap[card][2] = 0;

		cardPiece[card].setVisible(false);
	}

	public void showCard(int card) {
		cardPiece[card].setVisible(true);
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
