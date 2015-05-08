
import java.util.*;
public class Stream implements Feature{
	ArrayList<Integer> crossPts;
	public Stream(Integer[] cross){
		crossPts = new ArrayList<Integer>(Arrays.asList(cross));
		Collections.sort(crossPts);
	}
	public boolean affects(int pos){
		return crossPts.contains(pos);
	}
	public void modify(Avatar a){
		if(!affects(a.pos)) return;
		for(int i = 1; i < crossPts.size(); i++){
			if(crossPts.get(i) == a.pos) {
				a.pos = crossPts.get(i - 1);
			}
		}
	}
	public String name(){
		return "stream";
	}
	public String toString(){
		return "\nStream going through: " + crossPts;
	}
}