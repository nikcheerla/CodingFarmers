public class FarmBoard extends Board{
	static final int DEFAULT_DECK_SIZE = 30;
	static final int DEFAULT_BOARD_SIZE = 50;

	public FarmBoard(int num, int decksize, int boardsize, int[] freq){
		super(num, decksize, boardsize, freq);
		addFeature(new RestArea(0));
	}
	public FarmBoard(int num, int decksize, int boardsize){
		super(num, decksize, boardsize);
	}
	public FarmBoard(int num, int decksize){
		this(num, decksize, DEFAULT_BOARD_SIZE);
	}
	public FarmBoard(int num){
		this(num, DEFAULT_DECK_SIZE);
	}
}