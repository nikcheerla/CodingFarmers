public interface Strategy {
	public Card chooseBestCard(Avatar a, Deck hand, Board board, int diceRoll);
	public String name();
}