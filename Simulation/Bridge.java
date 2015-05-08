public class Bridge implements Feature{
	int p1, p2;
	public Bridge(int x1, int x2){
		p1 = x1; p2 = x2;
	}
	public boolean affects(int pos){
		return p1 == pos || p2 == pos;
	}
	public void modify(Avatar a){
		if(!affects(a.pos)) return;
		if(a.pos == p1) a.pos = p2;
		else a.pos = p1;
	}
	public String name(){
		return "bridge";
	}
	public String toString(){
		return "\nBridge from " + p1 + " to " + p2;
	}
}