
import java.util.*;
public class BoardBuilder{
	public static void main(String[] args){
		
		State best = null;
		for(int i = 0; i < 200; i++){
			State cur = GeneticOptimization.findBestState(500);
			FileIO.writeTo("simdata/Board" + cur.fitness() + ".txt", cur.toString() + "\n\nVariable Data: " + Arrays.toString(cur.vars));
			System.out.println(cur);
			if(best == null || cur.fitness() > best.fitness())
				best = cur;
		}
		System.out.println("\n\n\nBest Board Found: ");
		System.out.println(best);
	}
}