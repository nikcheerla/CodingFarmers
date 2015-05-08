
import java.util.*;
public class Deck {

	ArrayList<Card> cards = new ArrayList<Card> ();
	int maxsize = 0;
	Random rnd = new Random();

	public Deck(int max, boolean fill){
		maxsize = max;
		if(fill){
			for(int i = 0; i < maxsize; i++){
				cards.add(new Card(rnd.nextInt(Card.NUM_TYPES) + 1));
			}
		}
	}
	public Deck(int max){
		this(max, true);
	}
	public Deck(int max, int[] freq){
		maxsize = max;
		for(int i = 0; i < Card.NUM_TYPES; i++){
			for(int j = 0; j < freq[i]; j++){
				cards.add(new Card(j + 1));
			}
		}
	}
	public void fillFrom(Deck d){
		while(cards.size() < maxsize && d.cards.size() > 0){
			add(d.deal());
		}
	}
	public Card deal(){
		if(cards.size() <= 0) return null;
		return cards.remove(rnd.nextInt(cards.size()));
	}
	public Card get(int i){
		if(i > cards.size()) return null;
		return cards.get(i);
	}
	public Card deal(int i){
		if(i > cards.size()) return null;
		return cards.remove(i);
	}
	public void add(Card c){
		if(cards.size() < maxsize){
			cards.add(c);
		}
	}
}