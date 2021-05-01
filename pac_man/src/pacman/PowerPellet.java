package pacman;

/**
 * Each instance of this class represents a dot, located at a fixed position in a Pac-Man maze.
 * A dot serves as the food for Pac-Man.
 * 
 * @invar | getSquare() != null
 * 
 * @immutable
 */
public class PowerPellet extends FoodItem {
	
	/**
	 * @invar | square != null
	 */
	private Square square;
	
	/**
	 * @basic
	 */
	@Override
	public Square getSquare() { return square; }
	
	/**
	 * @throws IllegalArgumentException | square == null
	 * 
	 * @post | getSquare() == square
	 */
	public PowerPellet(Square square) {
		if (square == null)
			throw new IllegalArgumentException("`square` is null");
		this.square = square;
	}

	public int getSize() {
		return 2;
	}

	@Override
	public boolean isPowerPellet() {
		return true;
	}
}
