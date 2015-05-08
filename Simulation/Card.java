
import java.util.*;
public class Card {

	final static int NUM_TYPES = 26;

	int type;


	public Card(int t){
		type = t;
	}
	public void modify(Avatar a, Board b, int diceRoll){
		switch(type){
			case 1: basic1(a, b, diceRoll); break;
			case 2: basic2(a, b, diceRoll); break;
			case 3: basic3(a, b, diceRoll); break;
			case 4: basic4(a, b, diceRoll); break;
			case 5: basic5(a, b, diceRoll); break;
			case 6: basic6(a, b, diceRoll); break;
			
			case 7: control1(a, b, diceRoll); break;
			case 8: control2(a, b, diceRoll); break;
			case 9: control3(a, b, diceRoll); break;
			case 10: control4(a, b, diceRoll); break;
			case 11: control5(a, b, diceRoll); break;
			case 12: control6(a, b, diceRoll); break;
			case 13: control7(a, b, diceRoll); break;
			case 14: control8(a, b, diceRoll); break;
			case 15: control9(a, b, diceRoll); break;
			case 16: control10(a, b, diceRoll); break;
			case 17: flow1(a, b, diceRoll); break;
			case 18: flow2(a, b, diceRoll); break;
			case 19: flow3(a, b, diceRoll); break;
			case 20: flow4(a, b, diceRoll); break;
			case 21: flow5(a, b, diceRoll); break;
			case 22: flow6(a, b, diceRoll); break;
			case 23: flow7(a, b, diceRoll); break;
			case 24: flow8(a, b, diceRoll); break;
			case 25: combine1(a, b, diceRoll); break;
			case 26: combine2(a, b, diceRoll); break;
			
		}
	}
	public static String name(int type){
		if(type < 7) return "basic" + (type);
		else if(type < 17) return "control" + (type - 7 + 1);
		else if(type < 25) return "flow" + (type - 17 + 1);
		return "combine" + (type - 25 + 1);
	}

	//Basic Statements
	public void basic1(Avatar avatar, Board board, int diceRoll){
		avatar.pos += diceRoll;
	}
	public void basic2(Avatar avatar, Board board, int diceRoll){
		avatar.pos -= diceRoll;
	}
	public void basic3(Avatar avatar, Board board, int diceRoll){
		avatar.pos += 2*diceRoll;
	}
	public void basic4(Avatar avatar, Board board, int diceRoll){
		avatar.pos += diceRoll - 1;
	}
	public void basic5(Avatar avatar, Board board, int diceRoll){
		avatar.pos -= 2;
	}
	public void basic6(Avatar avatar, Board board, int diceRoll){
		avatar.pos += 1;
	}
	
	//Control Statements
	public void control1(Avatar avatar, Board board, int diceRoll){
		if(diceRoll > 2) avatar.pos += 2;
	}
	public void control2(Avatar avatar, Board board, int diceRoll){
		if(diceRoll < 3) avatar.pos -= 3;
		else avatar.pos += 3;
	}
	public void control3(Avatar avatar, Board board, int diceRoll){
		if(diceRoll == 1) avatar.pos += 6;
		else avatar.pos -= 1;
	}
	public void control4(Avatar avatar, Board board, int diceRoll){
		if(board.isGreen(avatar.pos)) 
			avatar.pos += diceRoll*2;
	}
	public void control5(Avatar avatar, Board board, int diceRoll){
		if(board.isTree(avatar.pos) || board.isTree(avatar.pos - 1)) 
			avatar.pos += diceRoll + 2;
	}
	public void control6(Avatar avatar, Board board, int diceRoll){
		if(board.isYellow(avatar.pos) && board.isYellow(avatar.pos + 1) && board.isYellow(avatar.pos + 2)) 
			avatar.pos += 6;
	}
	public void control7(Avatar avatar, Board board, int diceRoll){
		if(board.isYellow(avatar.pos)) 
			avatar.pos += 4;
		else avatar.pos -= 4;
	}
	public void control8(Avatar avatar, Board board, int diceRoll){
		if(avatar.pos < 20) avatar.pos = 20;
	}
	public void control9(Avatar avatar, Board board, int diceRoll){
		avatar.pos += diceRoll;
		if(board.hasStream(avatar.pos))
			avatar.pos += 1;
	}
	public void control10(Avatar avatar, Board board, int diceRoll){
		avatar.pos += diceRoll;
		if(board.hasRock(avatar.pos));
			avatar.pos += 1;
	}

	//Flow Statements
	public void flow1(Avatar avatar, Board board, int diceRoll){
		while(!board.hasRestArea(avatar.pos) && avatar.pos <= board.boardSize && avatar.pos >= 0){
			avatar.pos++;
		}
	}
	public void flow2(Avatar avatar, Board board, int diceRoll){
		while(!board.hasBridge(avatar.pos) && avatar.pos <= board.boardSize && avatar.pos >= 0){
			avatar.pos++;
		}
	}
	public void flow3(Avatar avatar, Board board, int diceRoll){
		while(!board.isYellow(avatar.pos) && avatar.pos <= board.boardSize && avatar.pos >= 0){
			avatar.pos++;
		}
	}
	public void flow4(Avatar avatar, Board board, int diceRoll){
		while(avatar.pos < 20){
			avatar.pos++;
		}
	}
	public void flow5(Avatar avatar, Board board, int diceRoll){
		while(board.isGreen(avatar.pos) && avatar.pos <= board.boardSize && avatar.pos >= 0){
			avatar.pos += 1;
		}
	}
	public void flow6(Avatar avatar, Board board, int diceRoll){
		while(board.isTree(avatar.pos) && avatar.pos <= board.boardSize && avatar.pos >= 0){
			avatar.pos += 2;
		}
	}
	public void flow7(Avatar avatar, Board board, int diceRoll){
		while(!board.hasStream(avatar.pos + 1) && avatar.pos <= board.boardSize && avatar.pos >= 0){
			avatar.pos++;
		}
		avatar.pos += 2;
	}
	public void flow8(Avatar avatar, Board board, int diceRoll){
		while(!board.hasRock(avatar.pos + 1) && avatar.pos <= board.boardSize && avatar.pos >= 0){
			avatar.pos++;
		}
		avatar.pos += 2;
	}

	//Combination Statements
	public void combine1(Avatar avatar, Board board, int diceRoll){
		if(diceRoll <= 3){
			while(board.isGreen(avatar.pos) && avatar.pos <= board.boardSize && avatar.pos >= 0){
				avatar.pos += diceRoll;
			}
		}
	}
	public void combine2(Avatar avatar, Board board, int diceRoll){
		avatar.pos += 5;
		if(!board.hasRestArea(avatar.pos)){
			while(board.hasRock(avatar.pos - 1) && avatar.pos <= board.boardSize && avatar.pos > 0){
				avatar.pos--;
			}
		}
	}
	
	public String toString(){
		return "Card " + name(type);
	}
}