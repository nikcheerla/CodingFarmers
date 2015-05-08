
import java.util.*;
public class Player{
	static final int HAND_SIZE = 3;

	Avatar cow;
	Deck hand;
	Board board;
	String name;
	Random dice = new Random();
	ArrayList<Card> chosen = new ArrayList<Card>();
	ArrayList<Integer> dice2 = new ArrayList<Integer>();
	Strategy strategy;

	public Player(Deck h){
		cow = new Avatar();
		cow.setPlayer(this);
		hand = new Deck(HAND_SIZE, false);
		hand.fillFrom(h);
		name = "";
	}
	public Player(Board b){
		this(b.deck);
		setBoard(b);
		name = "";
	}
	public Player(Board b, String n){
		this(b);
		name = n;
	}
	public void setBoard(Board b){
		board = b;
		cow.setBoard(b);
	}
	public void setStrategy(Strategy str){
		strategy = str;
	}
	public void setName(String n){
		name = n;
	}
	public void makeMove(){
		if(strategy == null) strategy = new RandomStrategy();
		int roll = dice.nextInt(6) + 1;
		dice2.add(roll);

		Card best = strategy.chooseBestCard(cow, hand, board, roll);

		chosen.add(best);
		if(best == null) cow.backtrack();
		else cow.act(best, roll);
		hand.fillFrom(board.deck);
		board.deck.add(best);
	}
	public Avatar getAvatar(){
		return cow;
	}
	public String toString(){
		return "Player Name: " + name + "\n Avatar at: " + cow.pos + "\n Hand of Cards" + hand.cards;
	}
}

