package pacman;

/**
 * Each instance of this class represents the player-controlled Pac-Man character in a Pac-Man maze.
 * @invar | getSquare()!= null
 * @invar | getNbLives() >= 0
 */
public class PacMan {
	
	/**
	 * @invar | nbLives >=0
	 * @invar | square != null
	 */
	
	/**
	 *
	 * @representationObject
	 */
	private Square square;
	
	private int nbLives;
	
	/**
	 * Returns the current square where the maze is found.
	 * @basic
	 */
	public Square getSquare() { 
		return Square.of(square.getMazeMap(), square.getRowIndex(), square.getColumnIndex()); 
	}

	
	/**
	 * Returns the current number of lives that the maze has left.
	 * @basic
	 */
	public int getNbLives() { 
		return nbLives; 
	}
	
	/**
	 * @inspects | square 
	 * 
	 * @throws IllegalArgumentException | square == null 
	 * @throws IllegalArgumentExveption | nbLives < 0
	 * 
	 * @post | nbLives >=0 //this is a precondition
	 * @post | nbLives == getNbLives()
	 * @post | square.equals(getSquare())
	 */
	public PacMan(int nbLives, Square square) {
		if (square == null) {
			throw new IllegalArgumentException("`square` is null");}
		if (nbLives < 0) {
			throw new IllegalArgumentException("`nbLives` is a negative number");}

		this.nbLives= nbLives;
		this.square = Square.of(square.getMazeMap(), square.getRowIndex(), square.getColumnIndex());
	}
	
	/**
	 * @mutates | this
	 * @inspects | square
	 * @throws IllegalArgumentException | square == null
	 */
	public void setSquare(Square square) { 
		if (square == null) {
			throw new IllegalArgumentException("`square` is null");}
		this.square = Square.of(square.getMazeMap(), square.getRowIndex(), square.getColumnIndex());
		

	}
	
	/**
	 * Decreases this Pac-Man character's number of lives by one.
	 * @mutates | this
	 * 
	 * @throws IllegalArgumentException | getNbLives() < 1
	 * 
	 * @post | getNbLives() == old(getNbLives()) - 1
	 */
	public void die() { 
		if (nbLives < 1) {
			throw new IllegalArgumentException("`nbLives` is 0");}
		this.nbLives = nbLives - 1;
		
	}


}
