import java.util.*;
public class Simulation{

	static final int DEFAULT_TRIALS = 100;
	static final int MAX_TURNS = 1000;
	static final String[] DEFAULT_INTELLIGENCES = new String[] {"greedy", "unsure", "random", "greedy"};
	static Random r = new Random();

	public static void main(String[] args){
		System.out.println(new State());
	}
	public static SimulationData simulate(State s, int trials, String[] smart){
		Board board = createPopulatedBoard(s, smart);
		SimulationData record = new SimulationData(board, trials);
		for(int i = 0; i < trials; i++){
			board = createPopulatedBoard(s, smart);
			for(int j = 0; j < MAX_TURNS && !board.hasWon(); j++)
				board.playTurn();

			if(board.playerWon == null){ 
				board.playerWon = board.players.get(r.nextInt(board.players.size()));
				board.turns = MAX_TURNS;
			}

			record.update(board);
		}
		return record;
	}
	public static SimulationData simulate(State s, int trials){
		return simulate(s, trials, DEFAULT_INTELLIGENCES);
	}
	public static SimulationData simulate(State s){
		return simulate(s, DEFAULT_TRIALS);
	}
	public static Board createPopulatedBoard(State s, String[] smart){
		Board board = s.genBoard();
		for(int i = 0; i < board.players.size(); i++){
			Player cur = board.players.get(i);
			if(smart[i].equals("greedy")) cur.setStrategy(new GreedyStrategy());
			else if(smart[i].equals("unsure")) cur.setStrategy(new UnsureGreedyStrategy());
			else cur.setStrategy(new RandomStrategy());
		}
		return board;
	}
}


// card frequencies
// deck size
// board size
// placement of stream and bridges
// types of cards used (tested using quizlet and survey)
// 
