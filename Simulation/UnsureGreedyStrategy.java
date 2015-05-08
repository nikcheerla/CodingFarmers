import java.util.*;
public class UnsureGreedyStrategy implements Strategy{
	Random r = new Random();
	public Card chooseBestCard(Avatar a, Deck hand, Board board, int diceRoll){
		int bestpos = 0;
		int best = -1;
		for(int i = 0; i < hand.cards.size(); i++){
			if(r.nextInt(4) == 1) continue;
			Avatar sim = new Avatar(); sim.pos = a.pos; sim.setBoard(board);
			sim.act(hand.get(i), diceRoll);
			for(Feature f: board.features){
				if(f.affects(sim.pos)) f.modify(sim);
			}
			if(sim.pos >= bestpos){
				bestpos = sim.pos;
				best = i;
			}
		}

		if(best != -1) {
			Card c = hand.deal(best);
			return c;
		}
		else{
			return null;
		}
	}
	public String name(){
		return "unsure";
	}
}