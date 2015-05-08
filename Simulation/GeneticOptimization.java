
import java.util.*;
public class GeneticOptimization {

	/*
	static final int[]  mixvar1 = {3, 3, 2, 3, 2, 2, 2, 3, 2, 2, 2, 4, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 1, 34, 59, 2, 4, 27, 34, 41, 47, 6, 23, 7, 24, 15, 21, 12, 26, 33, 31, 49, 13, 25, 37, 22, 31, 8, 16, 30, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 2, 0, 2, 2, 1, 2, 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 2, 0, 2, 2, 2, 2, 2, 0, 0, 0, 0};
	static final int[]  mixvar2 = {3, 3, 2, 4, 2, 1, 2, 1, 3, 2, 1, 1, 1, 1, 3, 1, 2, 2, 1, 2, 2, 2, 2, 2, 1, 35, 60, 2, 5, 28, 33, 39, 44, 4, 26, 8, 10, 12, 19, 13, 27, 33, 35, 22, 9, 23, 36, 24, 41, 9, 17, 29, 39, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 2, 0, 2, 2, 2, 2, 2, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 2, 0, 2, 2, 2, 2, 2, 0, 0, 0, 0};
	static final int[]  mixvar3 = {3, 3, 2, 3, 2, 1, 3, 2, 3, 2, 1, 2, 1, 1, 3, 1, 3, 3, 1, 2, 2, 1, 2, 3, 1, 35, 60, 2, 4, 31, 31, 37, 43, 5, 25, 8, 16, 12, 20, 14, 24, 23, 27, 39, 3, 21, 38, 27, 41, 7, 10, 28, 36, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 2, 0, 2, 2, 2, 2, 2, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 2, 0, 2, 2, 2, 2, 2, 0, 1, 1, 0};
	*/

	static final int CARRYING_CAPACITY = 130;
	static final int GENERATIONS = 500;
	static final double SURVIVAL_RATE = 0.30;
	static Random r = new Random();
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args){
		State s = findBestState();
		System.out.println(s);
	}
	public static State findBestState(){
		return findBestState(GENERATIONS);
	}
	public static State findBestState(int gens){
		ArrayList<State> population = new ArrayList<State>();
		State best = new State();
		int turns = 0;

		while(population.size() < CARRYING_CAPACITY){
				population.add(new State());
		}

		for(int T = 0; T < gens; T++){
			//Collections.sort(population);
			System.out.println(T + ": " + population.get(new Random().nextInt(population.size())).fitness());
			int die = (int)(CARRYING_CAPACITY - CARRYING_CAPACITY*SURVIVAL_RATE);
			for(int i = 0; i < die && population.size() >= 10; i++){
				population.remove(population.size() - 1);
			}
			
			while(population.size() < CARRYING_CAPACITY){
				if(r.nextInt(3) == 1) population.add(new State());
				else {
					State p1 = population.get(r.nextInt(population.size()));
					State p2 = population.get(r.nextInt(population.size()));
					population.add(State.cross(p1, p2));
				}
			}
			//Collections.sort(population);
			if(best.fitness() > population.get(0).fitness()){
				best = population.get(0);
				population.set(0, new State(best.vars));
				turns = 0;
			}
			else turns++;

			
			if(turns == gens/10 ){
				break;
			}
		}
		return best;
	}
}