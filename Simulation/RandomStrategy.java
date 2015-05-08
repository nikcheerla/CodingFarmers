
import java.util.*;
public class RandomStrategy implements Strategy{
	public Card chooseBestCard(Avatar a, Deck hand, Board board, int diceRoll){
		return hand.deal();
	}
	public String name(){
		return "random";
	}
}