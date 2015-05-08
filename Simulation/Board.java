
import java.util.*;
public class Board{

	ArrayList<Player> players;
	ArrayList<Feature> features;
	ArrayList<String> leads;
	String[] color;
	Deck deck;
	int boardSize;
	int turns = 0;
	Player playerWon;

	public Board(int num, int decksize, int size, int[] freq){
		this(num, decksize, size);
		deck = new Deck(decksize, freq);
	}
	public Board(int num, int decksize, int size){
		color = new String[size + 200];
		for(int i = 0; i < size + 200; i++) color[i] = "white";
		deck = new Deck(decksize);
		boardSize = size;
		playerWon = null;
		players = new ArrayList<Player>();
		for(int i = 0; i < num; i++){
			players.add(new Player(this, "Player " + i));
		}
		features = new ArrayList<Feature>();
		leads = new ArrayList<String>();
	}
	public void addFeature(Feature f){
		features.add(f);
	}
	public void playTurn(){
		for(Player p: players){
			p.makeMove();
			for(Feature f: features){
				if(f.affects(p.getAvatar().pos)){
					f.modify(p.getAvatar());
				}
			}
			for(Player p2: players){
				if(p != p2 && p.getAvatar().pos == p2.getAvatar().pos){
					p2.getAvatar().backtrack();
				}
			}
			if(p.getAvatar().pos >= boardSize) playerWon = p;
			else if(p.getAvatar().pos <= 0) p.getAvatar().pos = 0;

			if(leads.size() == 0 || !leads.get(leads.size() - 1).equals(inLead().name) ) leads.add(inLead().name);
		}
		turns++;
	}
	public ArrayList<String> getFeatureList(int pos){
		ArrayList<String> a = new ArrayList<String>();
		for(Feature f: features){
			if(f.affects(pos)) a.add(f.name());
		}
		return a;
	}
	public boolean hasWon(){
		return playerWon != null;
	}
	public Player inLead(){
		Player best = players.get(0);
		for(Player p: players){
			if(p.getAvatar().pos > best.getAvatar().pos){
				best = p;
			}
		}
		return best;
	}

	public boolean isGreen(int pos){
		if(pos < 0 || pos > boardSize) return false;
		return color[pos].equals("green");
	}
	public boolean isYellow(int pos){
		if(pos < 0 || pos > boardSize) return false;
		return color[pos].equals("yellow");
	}
	public boolean isTree(int pos){
		if(pos < 0 || pos > boardSize) return false;
		return color[pos].equals("tree");
	}
	public boolean isWindmill(int pos){
		if(pos < 0 || pos > boardSize) return false;
		return color[pos].equals("windmill");
	}

	public boolean hasPerson(int pos){
		for(Player p: players){
			if(p.getAvatar().pos == pos) return true;
		}
		return false;
	}
	public boolean hasStream(int pos){
		return getFeatureList(pos).contains("stream");
	}
	public boolean hasRestArea(int pos){
		return getFeatureList(pos).contains("restarea");
	}
	public boolean hasBridge(int pos){
		return getFeatureList(pos).contains("bridge");
	}
	public boolean hasRock(int pos){
		return getFeatureList(pos).contains("rock");
	}
}