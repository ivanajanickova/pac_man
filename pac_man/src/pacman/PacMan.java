package pacman;

/**
 * @mutable
 * Each instance of this class represents the player-controlled Pac-Man character in a Pac-Man maze.
 * @invar | getSquare()!= null
 * @invar |	getNbLives() >= 0
 */
public class PacMan {
	
	/**
	 * @representationObject
	 * @invar | nbLives >=0
	 */
	
	private int nbLives;
	
	/**
	 * @representationObject
	 * @invar square != null
	 */
	
	private Square square;
	
	/**
	 * Returns the current square where the maze is found.
	 * @basic
	 */
	
	public Square getSquare() { 
		return square;
	}
	
	/**
	 * Returns the current number of lives that the maze has left.
	 * @basic
	 */
	
	public int getNbLives() { 
		return nbLives;
	}
	
	/**
	 * @inspects | this
	 * 
	 * @pre | square != null
	 * @pre | nbLives >0
	 * 
	 * @post | nbLives >=0
	 * @post | nbLives == getNbLives()
	 * @post | square == getSquare()
	 */

	public PacMan(int nbLives, Square square) {
		if (square == null) {
			throw new IllegalArgumentException("`square` is null");}
		if (nbLives < 0) {
			throw new IllegalArgumentException("`nbLives` is a negative number");}

		this.nbLives= nbLives;
		this.square=square;
	}
	
	/**
	 * @mutates | this
	 * @inspects | other
	 * @throws IllegalArgumentException | square == null
	 */
	
	//Something about checking that the new square is not out of the maze map ?
	
	public void setSquare(Square square) { 
		if (square == null) {
			throw new IllegalArgumentException("`square` is null");}
		this.square = square;
		

	}
	
	/**
	 * Decreases this Pac-Man character's number of lives by one.
	 * @mutates | this
	 * 
	 * @throws IllegalArgumentException | nbLives < 1
	 * 
	 * @pre | nbLives >=0 
	 * @post | this.nbLives == old(nbLives) - 1
	 */
	
	public void die() { 
		if (nbLives < 1) {
			throw new IllegalArgumentException("`nbLives` is 0");}
		this.nbLives = nbLives - 1;
		
	}


}