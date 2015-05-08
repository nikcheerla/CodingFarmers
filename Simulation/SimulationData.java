
import java.util.*;
public class SimulationData{
	int num;
	int games;
	Board template;

	int[] turnCount; 
	int[] freq;
	int[] playersInLead;
	int[] uniquePlayersInLead;

	public SimulationData(Board temp, int numgames){
		template = temp;
		games = numgames;
		turnCount = new int[games];
		num = 0;
		freq = new int[temp.players.size()];
		playersInLead = new int[games];
		uniquePlayersInLead = new int[games];
	}
	public void update(Board finishedGame){
		turnCount[num] = finishedGame.turns;

		if(finishedGame.playerWon != null) 
			freq[finishedGame.playerWon.name.charAt(finishedGame.playerWon.name.length() - 1) - '0']++;
		
		playersInLead[num] = finishedGame.leads.size();
		uniquePlayersInLead[num] = new HashSet(finishedGame.leads).size();

		num++;
	}
	public double avgGameLength(){
		if(num < games) return 0.0;

		double sum = 0.0;
		for(int turn: turnCount){
			sum += turn;
		}
		sum /= (games);
		return round2(sum);
	}
	public double gameLengthDeviation(){
		if(num < games) return -1.0;

		double mean = avgGameLength();
		double diffSquared = 0.0;
		for(int turn: turnCount){
			diffSquared += (turn - mean)*(turn - mean);
		}
		return round2(Math.sqrt(diffSquared/games));
	}
	public double avgLeadDiversity(){
		if(num < games) return 0.0;

		double sum = 0.0;
		for(int num: playersInLead){
			sum += num;
		}
		sum /= (games);
		return round2(sum);
	}
	public double avgUniqueLeadDiversity(){
		if(num < games) return 0.0;

		double sum = 0.0;
		for(int num: uniquePlayersInLead){
			sum += num;
		}
		sum /= (games);
		return round2(sum);
	}
	public ArrayList<Double> playerWinPercent (){
		if(num < games) return null;

		ArrayList<Double> list = new ArrayList<Double>();
		for(int cum: freq){
			list.add( 100.0*cum/games);
		}

		return list;
	}
	public HashMap<String, Double> strategyWinRatios (){
		if(num < games) return null;

		ArrayList<Double> percents = playerWinPercent();
		HashMap<String, Double> winratios = new HashMap<String, Double>();
		HashMap<String, Integer> gamesr = new HashMap<String, Integer>();
		for(int i = 0; i < template.players.size(); i++){
			Player cur = template.players.get(i);
			if(winratios.get(cur.strategy.name()) == null){
				winratios.put(cur.strategy.name(), 0.0);
				gamesr.put(cur.strategy.name(), 0);
			}
			winratios.put(cur.strategy.name(), winratios.get(cur.strategy.name()) + percents.get(i));
			gamesr.put(cur.strategy.name(), gamesr.get(cur.strategy.name()) + 1);
		}

		int types = winratios.keySet().size();
		double sum = 0.0;
		for(String s: winratios.keySet()){
			sum += (1.0*winratios.get(s))/gamesr.get(s);
			winratios.put(s, (1.0*winratios.get(s))/gamesr.get(s));
		}
		for(String s: winratios.keySet()){
			winratios.put(s, round2(100.0*winratios.get(s)/sum));
		}
		return winratios;
	}
	public double strategyProbability(Strategy s){
		return strategyProbability(s.name());
	}
	public double strategyProbability(String s){
		if(strategyWinRatios().get(s) == null) return 0.0;
		return strategyWinRatios().get(s);
	}
	public double round2(double d){
		return ((int) (d*100.0))/100.0;
	}
	public String toString(){
		String s = "\n\nSimulation data for this game over " + games + " trials: \n";
		s += "Mean game length: " + avgGameLength();
		s += "\nDeviation in game length: " + gameLengthDeviation();
		s += "\nNumber of players in the lead: " + avgLeadDiversity();
		s += "\nNumber of unique players in the lead: " + avgUniqueLeadDiversity();
		s += "\nProbability of each player winning: " + playerWinPercent();
		s += "\nProbability of winning with random strategy: " + strategyProbability("random");
		s += "\nProbability of winning with unsure strategy: " + strategyProbability("unsure");
		s += "\nProbability of winning with smart strategy: " + strategyProbability("greedy");
		return s + "\n\n";
	}
}

