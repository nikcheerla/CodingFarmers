import java.util.*;
public class State implements Comparable<State>{

	static final int NUM_PLAYERS = 4;

	
	/*
	static final int[] DEFAULT_VARS = {5, 3, 2, 4, 2, 1, 1, 1, 3, 2, 1, 2, 1, 1, 1, 2, 2, 2, 1, 2, 2, 2, 1, 1, 2, 4,
		FarmBoard.DEFAULT_DECK_SIZE, FarmBoard.DEFAULT_BOARD_SIZE - 10, 
		3, 8, 20, 22, 30, 36, 8, 20, 9, 4, 18, 30, 21, 23, 17, 9, 11, 19, 29, 32, 33, 40, 5, 10, 25, 35,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 2, 0, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 2, 0, 2, 2, 2, 2, 2, 0, 0, 0, 0};

	static final int[] DEFAULT_VARS2 = {3, 3, 2, 3, 3, 2, 4, 3, 3, 2, 1, 1, 1, 1, 3, 1, 3, 3, 1, 2, 2, 2, 3, 3, 1,
		FarmBoard.DEFAULT_DECK_SIZE + 5, FarmBoard.DEFAULT_BOARD_SIZE + 10, 
		2, 4, 35, 38, 44, 49, 2, 30, 9, 11, 12, 20, 14, 28, 33, 35, 51, 3, 21, 43, 27, 45, 10, 20, 30, 40,
		0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 2, 0, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 2, 1, 2, 2, 2, 2, 2, 0, 1, 1, 0};

	static final int[] DEFAULT_VARS3 = {1, 1, 1, 3, 3, 3, 2, 2, 2, 3, 3, 4, 1, 1, 3, 2, 4, 2, 1, 1, 1, 1, 1, 2, 3,
		FarmBoard.DEFAULT_DECK_SIZE + 2, FarmBoard.DEFAULT_BOARD_SIZE, 
		7, 10, 15, 28, 34, 48, 9, 20, 7, 41, 16, 19, 8, 15, 19, 22, 2, 4, 31, 23, 17, 25, 8, 16, 32, 40,
		1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 2, 0, 2, 2, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 2, 0, 2, 2, 2, 2, 2, 0, 0, 1, 1};
	*/
	
	static final int cardsPos = 0;
	static final int boardPos = Card.NUM_TYPES + 1;
	static final int streamPos = Card.NUM_TYPES + 2;
	static final int bridgePos = streamPos + 6;
	static final int rockPos = bridgePos + 10;
	static final int restPos = rockPos + 5;
	static final int colorPos = restPos + 4;
	static final int varSize = colorPos + 120;

	static final int [] weights = {100, 60, 40, 25, 30, 50, 100};

	private static Random r = new Random();

	int[] vars = new int[varSize]; //36
	int fitsave = -1;
	boolean[] occupied;

	public State(int[] vars){
		this.vars = vars.clone();
		correct();
	}
	public State(){

		for(int i = cardsPos; i < boardPos - 1; i++){
			vars[i] = Probability.random(new int[]{0, 1, 2, 3, 4, 5, 6}, new int[]{1, 3, 4, 3, 2, 1, 1});
			if(Card.name(i - cardsPos + 1).contains("flow")) vars[i] -= r.nextInt(2);
			vars[i] = Math.max(vars[i], 0);
		}
		vars[boardPos] = Probability.normal(80, 15);
		occupied = new boolean[200];
		//fix();
		
		for(int i = streamPos; i < bridgePos; i++){
			if(i == streamPos) vars[i] = Probability.normal(7, 5);
			else vars[i] = vars[i - 1] + Probability.normal(16, 12);
			if(vars[i] > vars[boardPos]) vars[i] = vars[boardPos];
			occupied[vars[i]] = true;
		}
		for(int i = bridgePos; i < rockPos; i+= 2){
			int d;
			do {vars[i] = r.nextInt(vars[boardPos] - 20) + 10; } while(occupied[vars[i]]);
			do {vars[i + 1] = vars[i] + Probability.tails(8, vars[boardPos] - 8);} while(occupied[vars[i + 1]]);
			if(vars[i + 1] > vars[boardPos]) vars[i + 1] = vars[boardPos];
			occupied[vars[i]] = true;
			occupied[vars[i + 1]] = true;
		}
		for(int i = rockPos; i < restPos; i++){
			do {vars[i] = r.nextInt(vars[boardPos]);} while(occupied[vars[i]]);
			occupied[vars[i]] = true;
		}
		for(int i = restPos; i < colorPos; i++){
			do {vars[i] = r.nextInt(vars[boardPos]);} while(occupied[vars[i]]);
			occupied[vars[i]] = true;
		}
		for(int i = colorPos; i < varSize; i++){
			vars[i] = Probability.random(new int[]{0, 1, 2, 3}, new int[]{10, 6, 3, 2});
		}
	}
	public Board genBoard(){
		int[] freq = new int[Card.NUM_TYPES];
		int sum = 0;
		for(int i = cardsPos; i < boardPos - 1; i++){
			freq[i - cardsPos] = vars[i];
			sum += vars[i];
		}

		int deckSize = sum;
		int boardSize = vars[boardPos];
		Board b = new Board(NUM_PLAYERS, deckSize, boardSize, freq);

		Integer[] stream = new Integer[6];
		for(int i = streamPos; i < bridgePos; i++){
			stream[i - streamPos] = vars[i];
		}
		b.addFeature(new Stream(stream));
		Board board = b;
		for(int i = bridgePos; i < rockPos; i+= 2){
			if(Math.abs(vars[i] - vars[i + 1]) >= 5 && !board.hasBridge(vars[i] + 1) && !board.hasBridge(vars[i] - 1) && !board.hasBridge(vars[i + 1] - 1) && !board.hasBridge(vars[i + 1] + 1))
				b.addFeature(new Bridge(vars[i], vars[i + 1]));
		}
		for(int i = rockPos; i < restPos; i++){
			b.addFeature(new Rock(vars[i]));
		}
		for(int i = restPos; i < colorPos; i++){
			b.addFeature(new RestArea(vars[i]));
		}
		for(int i = colorPos; i <= colorPos + boardSize; i++){
			switch(vars[i]){
				case 0: b.color[i - colorPos] = "yellow"; break;
				case 1: b.color[i - colorPos] = "green"; break;
				case 2: b.color[i - colorPos] = "tree"; break;
				case 3: b.color[i - colorPos] = "windmill"; break;
			}
		}
		return b;
	}
	public void mutate(){
		int num = r.nextInt(25);
		for(int j = 0; j < num; j++){
			int i = r.nextInt(varSize);
			if(r.nextInt(2) == 0) return;

			if(i >= colorPos) vars[i] = (vars[i] + 1)%3;
			else {
				vars[i] *= (100 + r.nextInt(41) - 20);
				vars[i] /= 100;
				if(vars[i] > vars[15]) vars[i] = vars[15];
				if(vars[i] < 1) vars[i] = 1;
				fitsave = -1;
			}
		}
		correct();
		
	}
	public int fitness(){
		if(fitsave != -1){
			return fitsave;
		}

		correct();
		Board b = genBoard();
		SimulationData data = Simulation.simulate(this);
		double[] norms = new double[weights.length];

		//having around 20 turns is ideal
		double mean = data.avgGameLength();
		double val = (15 - mean)*(15 - mean)*20;
		norms[0] = Probability.normalize(val, 300);

		//not much deviation in turns
		double dev = data.gameLengthDeviation();
		val = dev*dev;
		norms[1] = Probability.normalize(val, 200);

		//prefer more diversity in number of people in lead
		double leadSwap = data.avgLeadDiversity();
		norms[2] = 1.0 - Probability.normalize(leadSwap, 5);

		//hope that everybody gets a chance to be in lead
		double uLeadSwap = data.avgUniqueLeadDiversity();
		norms[3] = 1.0 - uLeadSwap/4.0;

		//not much advantage by going first vs last
		ArrayList<Double> wp = data.playerWinPercent();
		val = (wp.get(0) - wp.get(3));
		norms[4] = Math.abs(val)/100.0;

		//good return on playing smart, very hard to win while playing random
		norms[5] = data.strategyProbability("random")/100.0;
		double v1 = (data.strategyProbability("greedy"));
		double v2 = 2*data.strategyProbability("unsure");
		val = Math.pow( (data.strategyProbability("greedy") - 2*data.strategyProbability("unsure")), 2);
		norms[6] =  Probability.normalize(val, 400);

		int fitmes = 0;
		for(int i = 0; i < weights.length; i++){
			fitmes += (int)(weights[i]*norms[i]*100);
		}
		return fitsave = fitmes;
	}
	public static State cross(State s1, State s2){
		s1.correct(); s2.correct();
		int[] childvars = new int[s1.vars.length];
		State[] choices = {s1, s2};
		int cur = 0;
		for(int i = 0; i < s1.vars.length; i++){
			if(r.nextInt(5) == 0) cur = (cur + 1) % 3;
			if(cur < 2) childvars[i] = choices[cur].vars[i];
			else childvars[i] = (s1.vars[i] + s2.vars[i])/2;
		}
		return new State(childvars);
	}
	public String toString(){
		String s = "";//Genetic state for a FarmBoard game: ";
		
		Board board = genBoard();

		int ptr = 0;
		int[] freq = new int[Card.NUM_TYPES];
		int sum = 0;
		for(ptr = 0; ptr < Card.NUM_TYPES; ptr++){
			freq[ptr] = vars[ptr];
			sum += freq[ptr];
		}
		
		s += "\nBoard Size: " + board.boardSize;
		s += "\nDeck Size: " + board.deck.maxsize;
		s += "\n\nCard Frequencies: ";
		for(int i = 0; i < freq.length; i++){
			if(i % 3 == 0) s += "\n";
			s += String.format("%-9s", Card.name(i + 1)) + " : " + freq[i];
			s += "\t\t";
		}
		s += "\n\nFeatures: " + board.features;
		s += "\n\nColor Pattern: \n";
		for(int i = 1; i <= board.boardSize; i++){
			s += String.format("%-10s", board.color[i]);
			if(i % 6 == 0) s += "\n";
		}
		
		s += "\n" + Simulation.simulate(this);
		//fitsave = -1;
		s += "\n\nOverall fitness: " + fitness();
		return s + "\n\n";
		
	}
	public int compareTo(State s){
		return fitness() - s.fitness();
	}
	public void correct(){
		//fix();
		for(int i = streamPos; i < colorPos; i++){
			if(vars[i] > vars[boardPos]) vars[i] = vars[boardPos];
			if(vars[i] < 1) vars[i] = 1;
		}
	}

	public void fix(){
		occupied = new boolean[200];
		//fixes it all
		vars[boardPos] = 100;

		vars[streamPos] = 9;
		vars[streamPos + 1] = 26;
		vars[streamPos + 2] = 81;	
		vars[streamPos + 3] = 94;
		vars[streamPos + 4] = 98;
		vars[streamPos + 5] = 102;

		vars[bridgePos] = 12;
		vars[bridgePos + 1] = 30;
		vars[bridgePos + 2] = 33;
		vars[bridgePos + 3] = 47;
		vars[bridgePos + 4] = 60;
		vars[bridgePos + 5] = 68;
		vars[bridgePos + 6] = 82;
		vars[bridgePos + 7] = 95;

		occupied[9] = true;
		occupied[26] = true;
		occupied[81] = true;
		occupied[94] = true;
		occupied[98] = true;
		occupied[101] = true;
		occupied[12] = true;
		occupied[30] = true;
		occupied[33] = true;
		occupied[47] = true;
		occupied[60] = true;
		occupied[68] = true;
		occupied[82] = true;
		occupied[95] = true;
	}
	boolean checkOverlap(){
		HashSet<Integer> hsh = new HashSet<Integer>();
		int size = 0;
		for(int i = streamPos; i < colorPos; i++){
			if(vars[i] < vars[boardPos]) {
				hsh.add(vars[i]);
				size++;
			}
		}
		if(hsh.size() < size) {
			//System.out.println(hsh);
			return true;
		}
		return false;
	}
}