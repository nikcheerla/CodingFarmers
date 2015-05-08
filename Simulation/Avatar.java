
import java.util.*;
public class Avatar {
	int pos;
	Board board;
	Player player;
	ArrayList<Integer> prevpos = new ArrayList<Integer>();

	public Avatar(){
		pos = 0;
	}
	public void setBoard(Board b){
		board = b;
	}
	public void setPlayer(Player p){
		player = p;
	}
	public void act(Card c, int diceRoll){
		prevpos.add(pos);
		c.modify(this, board, diceRoll);
		pos = Math.max(pos, 0);
		pos = Math.min(pos, board.boardSize + 1);
	}
	public void backtrack(){
		while(!board.getFeatureList(pos).contains("restarea") && pos > 0){
			pos--;
		}
	}
	public void rewind(){
		pos = prevpos.remove(prevpos.size() - 1);
	}
}