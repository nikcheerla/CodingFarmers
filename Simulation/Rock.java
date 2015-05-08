public class Rock implements Feature{
	int x;
	public Rock(int y){
		x = y;
	}
	public boolean affects(int pos){
		return x == pos;
	}
	public void modify(Avatar a){
		if(!affects(a.pos)) return;
		a.backtrack();
	}
	public String name(){
		return "rock";
	}
	public String toString(){
		return "\nRock at " + x;
	}
}