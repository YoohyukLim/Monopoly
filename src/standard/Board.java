package standard;

import gameClient.monoClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.Card;
import model.Map_piece;
import model.Piece;
import model.Sound;

public class Board {
	Map map;
	GameController gameController;
	ArrayList<JPanel> PieceList = new ArrayList<JPanel>();
	int currentPlayer, currentTurn;
	URL url_dice = new File("Resources/image/Dice_on.png").toURI().toURL();
	URL url_next = new File("Resources/image/Next.png").toURI().toURL();
	Icon dice_icon = new ImageIcon(url_dice);
	Icon next_icon = new ImageIcon(url_next);
	Icon backimage = new ImageIcon("Resources/image/background.jpg");
	JLabel Dice_button, Next_button, background;

	JPanel boardpanel, infopanel, topside, leftside, rightside, botside;
	IPanel chatpanel, consolepanel;
	TextArea chatRead;
	TextField chatWrite;

	JLayeredPane dicepanel;
	JPanel infoside, cardside, cardpanel, cardstate, cards[];
	JLabel Map_piece[];
	IPanel[][] playerPiece;
	JPanel[] playerPiecePanel;
	IPanel[] cardPiece;
	ArrayList<Piece> players;
	ArrayList<Card> playerCard;

	public JFrame frame;

	public boolean lessThanFive = true;
	public boolean cardtime = false;

	public Sound bgm = new Sound("Resources/sounds/game/ChmpSlct_DraftMode.wav");
	monoClient monoClient;

	public Board(Map map) throws Exception {
		this.map = map;
	}

	public void getController(GameController _gameController) throws Exception {
		this.gameController = _gameController;
		this.currentPlayer = _gameController.currentPlayer;
		this.players = _gameController.Players;
	}

	public void start() {
		try {
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.frame.setVisible(true);
	}

	private void initialize() throws Exception {
		Calendar now = Calendar.getInstance();
		int seed = now.get(Calendar.MILLISECOND);
		Random r = new Random();
		r.setSeed(seed);
		String cardfile[] = new String[3];
		cardfile[0] = new String("Resources/image/card0.png");
		cardfile[1] = new String("Resources/image/card1.png");
		cardfile[2] = new String("Resources/image/card2.png");

		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new windowHandler());

		boardpanel = new JPanel();
		infopanel = new JPanel();
		chatpanel = new IPanel("Resources/image/chat.png");
		dicepanel = new JLayeredPane();
		topside = new JPanel();
		leftside = new JPanel();
		rightside = new JPanel();
		botside = new JPanel();

		infoside = new JPanel();
		cardside = new JPanel();

		Map_piece = new JLabel[36];
		playerPiece = new IPanel[36][2];
		playerPiecePanel = new JPanel[36];
		cardPiece = new IPanel[36];

		frame.setTitle("Monopoly");
		frame.setBounds(0, 0, 1006, 928);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		topside.setLayout(new BoxLayout(topside, BoxLayout.LINE_AXIS));
		botside.setLayout(new BoxLayout(botside, BoxLayout.LINE_AXIS));
		leftside.setLayout(new BoxLayout(leftside, BoxLayout.PAGE_AXIS));
		rightside.setLayout(new BoxLayout(rightside, BoxLayout.PAGE_AXIS));
		infoside.setLayout(new GridLayout(4, 1));

		MapBtnHandler MBHandler = new MapBtnHandler();
		// MapPieces
		for (int i = 0; i < 36; i++) {
			if (i == 0 || i == 9 || i == 18 || i == 27)
				PieceList.add(new IPanel("Resources/image/Stone0.png"));
			else if (i < 10 || (i >= 18 && i <= 27))
				PieceList.add(new IPanel("Resources/image/Stone1.png"));
			else
				PieceList.add(new IPanel("Resources/image/Stone2.png"));

			playerPiecePanel[i] = new JPanel();
			playerPiece[i][0] = new IPanel("Resources/image/ohkong.jpg");
			playerPiece[i][1] = new IPanel("Resources/image/masteryi.jpg");
			playerPiece[i][0].setPreferredSize(new Dimension(30, 30));
			playerPiece[i][0].setMaximumSize(new Dimension(30, 30));
			playerPiece[i][0].setMinimumSize(new Dimension(30, 30));
			playerPiece[i][1].setPreferredSize(new Dimension(30, 30));
			playerPiece[i][1].setMaximumSize(new Dimension(30, 30));
			playerPiece[i][1].setMinimumSize(new Dimension(30, 30));

			playerPiecePanel[i].setLayout(new GridBagLayout());
			playerPiecePanel[i].setOpaque(false);
			playerPiecePanel[i].add(playerPiece[i][0]);
			playerPiecePanel[i].add(playerPiece[i][1]);

			cardPiece[i] = new IPanel(cardfile[Math.abs(r.nextInt() % 3)]);
			cardPiece[i].setOpaque(false);
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
				// PieceList.get(i).setBackground(new Color(0, 255, 255));
				playerPiecePanel[i].setPreferredSize(new Dimension(100, 50));
				playerPiecePanel[i].setMaximumSize(new Dimension(100, 50));
				playerPiecePanel[i].setMinimumSize(new Dimension(100, 50));

				Map_piece[i] = new JLabel(String.valueOf(Map.Map_pieces[i]
						.get_map_number()));
				Map_piece[i].setVisible(false);
				PieceList.get(i).add(Map_piece[i]);
			} else if (i < 10 || (i >= 18 && i <= 27)) {
				PieceList.get(i).setPreferredSize(new Dimension(60, 100));
				PieceList.get(i).setMaximumSize(new Dimension(60, 100));
				PieceList.get(i).setMinimumSize(new Dimension(60, 100));
				playerPiecePanel[i].setPreferredSize(new Dimension(60, 50));
				playerPiecePanel[i].setMaximumSize(new Dimension(60, 50));
				playerPiecePanel[i].setMinimumSize(new Dimension(60, 50));

				cardPiece[i].setPreferredSize(new Dimension(60, 50));
				cardPiece[i].setMaximumSize(new Dimension(60, 50));
				cardPiece[i].setMinimumSize(new Dimension(60, 50));

				// Map Piece Panel
				Map_piece[i] = new JLabel(String.valueOf(Map.Map_pieces[i]
						.get_map_number()));
				Map_piece[i].setVisible(false);
				// Map_piece[i].setPreferredSize(new Dimension(30, 100));
				// Map_piece[i].setMaximumSize(new Dimension(30, 100));
				// Map_piece[i].setMinimumSize(new Dimension(30, 100));

				PieceList.get(i).add(Map_piece[i], 0);
				PieceList.get(i).addMouseListener(MBHandler);
				/*
				 * if (i % 2 == 0) PieceList.get(i).setBackground(new Color(127,
				 * 255, 0)); else PieceList.get(i).setBackground(new Color(0,
				 * 255, 127));
				 */
			} else {
				PieceList.get(i).setPreferredSize(new Dimension(100, 60));
				PieceList.get(i).setMaximumSize(new Dimension(100, 60));
				PieceList.get(i).setMinimumSize(new Dimension(100, 60));
				playerPiecePanel[i].setPreferredSize(new Dimension(100, 30));
				playerPiecePanel[i].setMaximumSize(new Dimension(100, 30));
				playerPiecePanel[i].setMinimumSize(new Dimension(100, 30));

				cardPiece[i].setPreferredSize(new Dimension(100, 30));
				cardPiece[i].setMaximumSize(new Dimension(100, 30));
				cardPiece[i].setMinimumSize(new Dimension(100, 30));

				// Map Piece Panel
				Map_piece[i] = new JLabel(String.valueOf(Map.Map_pieces[i]
						.get_map_number()));
				Map_piece[i].setVisible(false);
				// Map_piece[i].setPreferredSize(new Dimension(100, 30));
				// Map_piece[i].setMaximumSize(new Dimension(100, 30));
				// Map_piece[i].setMinimumSize(new Dimension(100, 30));

				PieceList.get(i).add(Map_piece[i], 0);
				PieceList.get(i).addMouseListener(MBHandler);
				/*
				 * if (i % 2 == 0) PieceList.get(i).setBackground(new Color(127,
				 * 255, 0)); else PieceList.get(i).setBackground(new Color(0,
				 * 255, 127));
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
		botside.setOpaque(false);
		leftside.setOpaque(false);
		topside.setOpaque(false);
		rightside.setOpaque(false);

		// DicePanel
		/*
		 * background = new JLabel(backimage); dicepanel.add(background, new
		 * Integer(1));
		 */
		Dice_button = new JLabel(dice_icon);
		Next_button = new JLabel(next_icon);
		DiceBtnHandler DBhandler = new DiceBtnHandler();
		Dice_button.addMouseListener(DBhandler);
		Next_button.addMouseListener(DBhandler);
		Next_button.setVisible(false);
		dicepanel.add(Dice_button, 0);
		dicepanel.add(Next_button, 1);
		/*
		 * background.setBounds(0, 0, backimage.getIconWidth(),
		 * backimage.getIconHeight());
		 */
		Dice_button.setBounds(100, 340, dice_icon.getIconWidth(),
				dice_icon.getIconHeight());
		Next_button.setBounds(100, 340, next_icon.getIconWidth(),
				dice_icon.getIconHeight());

		// boardpanel.setBackground(new Color(0, 255, 255));
		boardpanel.setPreferredSize(new Dimension(680, 680));
		boardpanel.setLayout(new BorderLayout(0, 0));
		boardpanel.setOpaque(false);
		boardpanel.add(topside, BorderLayout.NORTH);
		boardpanel.add(botside, BorderLayout.SOUTH);
		boardpanel.add(leftside, BorderLayout.WEST);
		boardpanel.add(rightside, BorderLayout.EAST);
		boardpanel.add(dicepanel, BorderLayout.CENTER);
		infopanel.setLayout(new BorderLayout(0, 0));
		infopanel.add(infoside, BorderLayout.NORTH);

		// Card
		cardside.setLayout(new BoxLayout(cardside, BoxLayout.PAGE_AXIS));
		cardside.setBackground(new Color(0, 0, 0));
		cardside.setOpaque(false);
		cardpanel = new JPanel();
		cardstate = new JPanel();
		cards = new IPanel[5];

		cardpanel = new JPanel();
		cardpanel.setLayout(new FlowLayout(0, 0, 0));
		cardpanel.setBackground(new Color(0, 0, 0));
		cardpanel.setOpaque(false);

		cardstate = new JPanel();
		cardstate.setLayout(new FlowLayout(0, 0, 0));
		cardstate.setPreferredSize(new Dimension(320, 500));
		cardstate.setMinimumSize(new Dimension(320, 500));
		cardstate.setMaximumSize(new Dimension(320, 500));
		cardstate.setOpaque(false);

		JLabel mycard_label = new JLabel();
		mycard_label.setOpaque(true);
		mycard_label.setText(gameController.getMyName() + "'s Cards");
		mycard_label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		mycard_label.setBackground(new Color(0, 0, 127));
		mycard_label.setForeground(new Color(255, 0, 0));
		mycard_label.setPreferredSize(new Dimension(320, 30));
		mycard_label.setMinimumSize(new Dimension(320, 30));
		mycard_label.setMaximumSize(new Dimension(320, 30));
		mycard_label.setHorizontalAlignment(JLabel.CENTER);
		cardpanel.add(mycard_label, 0);
		cardpanel.add(cardstate, 1);
		cardside.add(cardpanel);

		infopanel.add(cardside, BorderLayout.SOUTH);

		infopanel.setBackground(new Color(127, 127, 127));
		infopanel.setPreferredSize(new Dimension(320, 680));
		infoside.setPreferredSize(new Dimension(320, 150));
		infoside.setBackground(new Color(0, 0, 0));
		cardside.setPreferredSize(new Dimension(320, 530));
		cardside.setBackground(new Color(0, 127, 255));

		// chatpanel.setBackground(new Color(255, 0, 255));
		chatRead = new TextArea();
		chatWrite = new TextField();
		chatpanel.setPreferredSize(new Dimension(680, 220));
		chatRead.setPreferredSize(new Dimension(675, 190));
		chatWrite.setPreferredSize(new Dimension(675, 20));
		chatRead.setEditable(false);
		chatWrite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!chatWrite.getText().trim().equals(""))
					gameController.sendMessage(chatWrite.getText());
				chatWrite.setText("");
			}
		});

		chatpanel.add("chatRead", chatRead);
		chatpanel.add("chatWrite", chatWrite);
		consolepanel = new IPanel("Resources/image/info.png");
		consolepanel.setPreferredSize(new Dimension(320, 220));

		IPanel finalboard = new IPanel("Resources/image/background.jpg");
		finalboard.setPreferredSize(new Dimension(680, 680));
		finalboard.setMaximumSize(new Dimension(680, 680));
		finalboard.setMinimumSize(new Dimension(680, 680));
		finalboard.add(boardpanel);
		frame.getContentPane().add(finalboard);
		frame.getContentPane().add(infopanel);
		frame.getContentPane().add(chatpanel);
		frame.getContentPane().add(consolepanel);

		refreshInfo(gameController.currentPlayer);

		bgm.loop();
	}

	private Color getImage(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void refreshInfo(int currentPlayer) {
		JLabel playerNameLabel = new JLabel();
		JLabel currentTurnLabel = new JLabel();
		JLabel rotationCntLabel = new JLabel();
		JLabel caughtCntLabel = new JLabel();

		currentTurn = gameController.getTurn();
		players = gameController.Players;

		infoside.removeAll();
		playerNameLabel
				.setText("ÇÃ·¹ÀÌ¾î: " + players.get(currentPlayer).getName());
		playerNameLabel.setForeground(Color.WHITE);
		playerNameLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 27));
		currentTurnLabel.setText("ÅÏ ¼ö: "
				+ String.valueOf(gameController.getTurn()));
		currentTurnLabel.setForeground(Color.WHITE);
		currentTurnLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 23));
		rotationCntLabel.setText("µ¹Àº ¹ÙÄû ¼ö: "
				+ String.valueOf(monoClient.currentRotationCnt));
		rotationCntLabel.setForeground(Color.WHITE);
		rotationCntLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		caughtCntLabel.setText("ÀâÀº ¼ö: "
				+ String.valueOf(monoClient.currentCatchCnt));
		caughtCntLabel.setForeground(Color.WHITE);
		caughtCntLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));

		infoside.add(playerNameLabel);
		infoside.add(currentTurnLabel);
		infoside.add(rotationCntLabel);
		infoside.add(caughtCntLabel);
	}

	public void refreshCards() throws Exception {
		playerCard = gameController.Players.get(gameController.getMyNumber()).cardList;
		int length = playerCard.size();
		System.out.println("//"
				+ gameController.Players.get(currentPlayer).getName()
				+ "'s cards " + length + "//");

		cardpanel.remove(1);
		cardstate.removeAll();

		for (int i = 0; i < length; i++) {
			Card temp = playerCard.get(i);
			int number = temp.getCardNumber();
			JLabel cardimage = new JLabel(new ImageIcon(new File("Resources/image/cards/card"+number+".png").toURI().toURL()));
			cardimage.setPreferredSize(new Dimension(64, 100));
			cardimage.setMinimumSize(new Dimension(64, 100));
			cardimage.setMaximumSize(new Dimension(64, 100));
			JPanel cardinfo = new JPanel();
			cardinfo.setOpaque(false);
			cardinfo.setPreferredSize(new Dimension(256, 100));
			cardinfo.setMinimumSize(new Dimension(256, 100));
			cardinfo.setMaximumSize(new Dimension(256, 100));
			JLabel cardtype = new JLabel(temp.getCardType(number));
			JLabel cardtext = new JLabel(temp.getTypeText(number));
			cardtype.setHorizontalAlignment(JLabel.CENTER);
			cardtext.setHorizontalAlignment(JLabel.CENTER);
			cardtype.setVerticalAlignment(JLabel.CENTER);
			cardtext.setVerticalAlignment(JLabel.CENTER);
			JLabel cardnumber = new JLabel(String.valueOf(i));
			cardnumber.setVisible(false);

			cards[i] = new IPanel("Resources/image/cardpage.jpg");
			cards[i].setPreferredSize(new Dimension(320, 100));
			cards[i].setMinimumSize(new Dimension(320, 100));
			cards[i].setMaximumSize(new Dimension(320, 100));
			cards[i].setBackground(new Color(255 - 10 * i, 255 - 10 * i,
					255 - 10 * i));
			cards[i].setLayout(new FlowLayout(0, 0, 0));
			cardinfo.add(cardtype, 0);
			cardinfo.add(cardtext, 1);
			cardinfo.add(cardnumber, 2);
			cards[i].add(cardimage);
			cards[i].add(cardinfo);
			cardstate.add(cards[i]);
		}

		cardpanel.add(cardstate, 1);
		cardside.removeAll();
		cardside.add(cardpanel);
		cardside.setVisible(false);
		cardside.setVisible(true);
	}

	public void deleteCards() throws Exception {
		playerCard = gameController.Players.get(gameController.getMyNumber()).cardList;
		int length = playerCard.size();
		DeleteCardBtnHandler cardhandler = new DeleteCardBtnHandler();

		System.out.println("Let's Delete Card!");

		cardpanel.remove(1);
		cardstate.removeAll();

		for (int i = 0; i < length; i++) {
			Card temp = playerCard.get(i);
			int number = temp.getCardNumber();
			JLabel cardimage = new JLabel(new ImageIcon(new File("Resources/image/cards/card"+number+".png").toURI().toURL()));
			cardimage.setPreferredSize(new Dimension(64, 100));
			cardimage.setMinimumSize(new Dimension(64, 100));
			cardimage.setMaximumSize(new Dimension(64, 100));
			JPanel cardinfo = new JPanel();
			cardinfo.setOpaque(false);
			cardinfo.setPreferredSize(new Dimension(256, 100));
			cardinfo.setMinimumSize(new Dimension(256, 100));
			cardinfo.setMaximumSize(new Dimension(256, 100));
			JLabel cardtype = new JLabel(temp.getCardType(number));
			JLabel cardtext = new JLabel(temp.getTypeText(number));
			cardtype.setHorizontalAlignment(JLabel.CENTER);
			cardtext.setHorizontalAlignment(JLabel.CENTER);
			cardtype.setVerticalAlignment(JLabel.CENTER);
			cardtext.setVerticalAlignment(JLabel.CENTER);
			JLabel cardnumber = new JLabel(String.valueOf(i));
			cardnumber.setVisible(false);

			cards[i] = new IPanel("Resources/image/cardpage.jpg");
			cards[i].setPreferredSize(new Dimension(320, 100));
			cards[i].setMinimumSize(new Dimension(320, 100));
			cards[i].setMaximumSize(new Dimension(320, 100));
			cards[i].setBackground(new Color(255 - 10 * i, 255 - 10 * i,
					255 - 10 * i));
			cards[i].setLayout(new BoxLayout(cards[i], BoxLayout.LINE_AXIS));
			cardinfo.add(cardtype, 0);
			cardinfo.add(cardtext, 1);
			cardinfo.add(cardnumber, 2);
			cards[i].add(cardimage);
			cards[i].add(cardinfo);
			cards[i].addMouseListener(cardhandler);
			cardstate.add(cards[i]);
		}

		cardpanel.add(cardstate, 1);
		cardside.remove(cardpanel);
		cardside.add(cardpanel);
		cardside.setVisible(false);
		cardside.setVisible(true);
	}

	public void executableCards() throws Exception {
		playerCard = gameController.Players.get(gameController.getMyNumber()).cardList;
		int length = playerCard.size();
		CardBtnHandler cardhandler = new CardBtnHandler();
		cardtime = true;

		System.out.println("Let's use Card!");

		cardpanel.remove(1);
		cardstate.removeAll();

		for (int i = 0; i < length; i++) {
			Card temp = playerCard.get(i);
			int number = temp.getCardNumber();
			JLabel cardimage = new JLabel(new ImageIcon(new File("Resources/image/cards/card"+number+".png").toURI().toURL()));
			cardimage.setPreferredSize(new Dimension(64, 100));
			cardimage.setMinimumSize(new Dimension(64, 100));
			cardimage.setMaximumSize(new Dimension(64, 100));
			JPanel cardinfo = new JPanel();
			cardinfo.setOpaque(false);
			cardinfo.setPreferredSize(new Dimension(256, 100));
			cardinfo.setMinimumSize(new Dimension(256, 100));
			cardinfo.setMaximumSize(new Dimension(256, 100));
			JLabel cardtype = new JLabel(temp.getCardType(number));
			JLabel cardtext = new JLabel(temp.getTypeText(number));
			cardtype.setHorizontalAlignment(JLabel.CENTER);
			cardtext.setHorizontalAlignment(JLabel.CENTER);
			cardtype.setVerticalAlignment(JLabel.CENTER);
			cardtext.setVerticalAlignment(JLabel.CENTER);
			JLabel cardnumber = new JLabel(String.valueOf(i));
			cardnumber.setVisible(false);

			cards[i] = new IPanel("Resources/image/cardpage.jpg");
			cards[i].setPreferredSize(new Dimension(320, 100));
			cards[i].setMinimumSize(new Dimension(320, 100));
			cards[i].setMaximumSize(new Dimension(320, 100));
			cards[i].setBackground(new Color(255 - 10 * i, 255 - 10 * i,
					255 - 10 * i));
			cards[i].setLayout(new BoxLayout(cards[i], BoxLayout.LINE_AXIS));
			cardinfo.add(cardtype, 0);
			cardinfo.add(cardtext, 1);
			cardinfo.add(cardnumber, 2);
			cards[i].add(cardimage);
			cards[i].add(cardinfo);
			cards[i].addMouseListener(cardhandler);

			cardstate.add(cards[i]);
		}

		cardpanel.add(cardstate, 1);
		cardside.remove(cardpanel);
		cardside.add(cardpanel);
		cardside.setVisible(false);
		cardside.setVisible(true);
	}

	public void gameOver() {
		System.out.println("GAMEOVER");
		if(bgm!=null){
			bgm.stop();
			bgm=null;
		}
		this.frame.setVisible(false);
		this.frame.dispose();
	}

	class windowHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			monoClient.outGame();
		}
	}

	class DiceBtnHandler implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			if (!gameController.getMyName().equals(
					gameController.Players.get(gameController.currentPlayer)
							.getName())) {
				JOptionPane.showMessageDialog(null, "»ó´ë¹æ ÅÏÀÔ´Ï´Ù.");
				return;
			}
			gameController.diceButton();
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
			if (!gameController.getMyName().equals(
					gameController.Players.get(gameController.currentPlayer)
							.getName())) {
				JOptionPane.showMessageDialog(null, "»ó´ë¹æ ÅÏÀÔ´Ï´Ù.");
				return;
			}

			JPanel card = (JPanel) e.getComponent();
			JLabel cardNumber = (JLabel) card.getComponent(2);
			int number = Integer.parseInt(cardNumber.getText());

			System.out.println("Deleting card has clicked!");
			gameController.deleteCard(number);
			gameController.addCard(gameController.tempcard);
			try {
				new Sound("Resources/sounds/game/global-menu_close.wav").play();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				gameController.dorest();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

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
			if (!gameController.getMyName().equals(
					gameController.Players.get(gameController.currentPlayer)
							.getName())) {
				JOptionPane.showMessageDialog(null, "»ó´ë¹æ ÅÏÀÔ´Ï´Ù.");
				return;
			}

			JPanel card = (JPanel) e.getComponent();
			JLabel cardNumber = (JLabel) card.getComponent(2);
			int number = Integer.parseInt(cardNumber.getText());
			try {
				new Sound("Resources/sounds/game/global-menu_close.wav").play();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			if (gameController.useCard(number)) {
				try {
					refreshCards();
					gameController.sendCardtoServer();
					System.out.println("The card was used");
					gameController.dofinal();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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

	public void getClient(monoClient monoClient) {
		this.monoClient = monoClient;
	}

	class IPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private ImageIcon image = null;

		public IPanel(String filename) throws Exception {
			URL url = new File(filename).toURI().toURL();
			image = new ImageIcon(url);
			this.setOpaque(false);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (image != null) {
				g.drawImage(image.getImage(), 0, 0, null);
			}
		}
	}
}