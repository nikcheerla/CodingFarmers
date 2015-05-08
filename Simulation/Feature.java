public interface Feature{
	boolean affects(int pos);
	void modify(Avatar a);
	String name();
	String toString();
}