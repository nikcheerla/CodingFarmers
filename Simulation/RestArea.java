public class RestArea implements Feature{
	int x;
	public RestArea(int y){
		x = y;
	}
	public boolean affects(int pos){
		return x == pos;
	}
	public void modify(Avatar a){
		if(!affects(a.pos)) return;
		if(a.player != null) a.player.makeMove();
	}
	public String name(){
		return "restarea";
	}
	public String toString(){
		return "\nRest Area at " + x;
	}
}
