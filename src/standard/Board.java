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
	
	JPanel boardpanel, infopanel, chatpanel, dicepanel, topside, leftside, rightside, botside;
	JPanel playerside, cardside, cardpanel[], cards[][];
	JLabel Map_piece[];
	ArrayList<Card> playerCard;

	public JFrame frame;
	CardLayout cardlayout;

	public Board(Map map) throws Exception {
		this.map = map;
	}

	public void getController(GameController _gameController) throws Exception {
		this.gameController = _gameController;
		this.currentPlayer = _gameController.currentPlayer;
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
		
		//Card
		cardside.setLayout(cardlayout); 
		cardpanel = new JPanel[2];
		cards = new JPanel[2][5];
		for (int i = 0; i < cardpanel.length; i++) {
			cardpanel[i] = new JPanel();
			cardpanel[i].setLayout(new FlowLayout(0, 0, 0));
			cardpanel[i].setBackground(new Color(255, 0, 255));
			JLabel mycard_label = new JLabel();
			mycard_label.setOpaque(true);
			mycard_label.setText("My Card");
			mycard_label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
			mycard_label.setBackground(new Color(0, 0, 127));
			mycard_label.setForeground(new Color(255, 0, 0));
			mycard_label.setPreferredSize(new Dimension(320, 30));
			mycard_label.setMinimumSize(new Dimension(320, 30));
			mycard_label.setMaximumSize(new Dimension(320, 30));
			mycard_label.setHorizontalAlignment(JLabel.CENTER);
			cardpanel[i].add(mycard_label);
			for (int j = 0; j < 5; j++) {
				cards[i][j] = new JPanel();
				cards[i][j].setPreferredSize(new Dimension(320, 100));
				cards[i][j].setMinimumSize(new Dimension(320, 100));
				cards[i][j].setMaximumSize(new Dimension(320, 100));
				cards[i][j].setBackground(new Color(255-10 * j, 255-10 * j, 255-10 * j));
				cardpanel[i].add(cards[i][j]);
			}
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
	
	public void update(String msg){
		switch(msg){
		case "card":
			cardlayout.show(cardside, String.valueOf(currentPlayer));
			break;
		}
	}
	
	public void refreshCards(){
		playerCard = gameController.Players.get(currentPlayer).cardList;
		int length = playerCard.size();
		System.out.println("//"+gameController.Players.get(currentPlayer).getName()+"'s cards "+length+"//");
		
		for(int i=0; i<length; i++){
			Card temp = playerCard.get(i);
			int number = temp.getCardNumber();
			JLabel cardnumber = new JLabel("Card Num: "+String.valueOf(number));
			JLabel cardtext = new JLabel(temp.getTypeText(number));
			cards[currentPlayer][i].removeAll();
			cards[currentPlayer][i].add(cardnumber, 0);
			cards[currentPlayer][i].add(cardtext, 1);
		}
		update("card");
	}
	
	class DiceBtnHandler implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			gameController.getCard();
			refreshCards();
			gameController.setPlayerbyDice();
			gameController.MapExec();
			currentPlayer = gameController.changePlayer();
			refreshCards();
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
