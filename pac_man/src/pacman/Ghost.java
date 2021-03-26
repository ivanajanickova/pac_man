package pacman;

import java.util.Random;

/**
 * 
 * Each instance of this class represents a ghost in a Pac-Man maze.
 * @invar | getSquare()!= null
 * @invar | getDirection() != null
 */
public class Ghost {
	
	/**
	 * @invar | square != null
	 * @representationObject
	 */
	
	private Square square;
	
	/**
	 * @invar | direction != null
	 * @representationObject
	 */
	
	private Direction direction;
	
	/**
	 * @basic
	 */
	
	public Square getSquare() {
		return Square.of(square.getMazeMap(), square.getRowIndex(), square.getColumnIndex());
	}
	
	/**
	 * Returns the direction in which this ghost will preferably move next.
	 * @basic
	 */
	
	public Direction getDirection() {
		return this.direction;
	}
	
	/**
	 * @inspects | square 
	 * @inspects | direction
	 * 
	 * @throws IllegalArgumentException | square == null 
	 * @post | square.equals(getSquare())
	 * 
	 * @throws IllegalArgumentException | direction == null 
	 * @post | direction.equals(getDirection())
	 */
	
	public Ghost(Square square, Direction direction) { 
		if (square == null) {
			throw new IllegalArgumentException("`square` is null");}
		if (direction == null) {
			throw new IllegalArgumentException("`direction` is null");}
		
		this.square = Square.of(square.getMazeMap(), square.getRowIndex(), square.getColumnIndex());
		this.direction = direction;
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
	 * @mutates | this
	 * @inspects | direction
	 * @throws IllegalArgumentException | direction == null
	 */
	
	public void setDirection(Direction direction) { 
		if (direction == null) {
			throw new IllegalArgumentException("`direction` is null");}
		this.direction = direction;
	}
	
	private static int MOVE_FORWARD_PREFERENCE = 10;
	
	// No formal document required
	public Direction chooseNextMoveDirection(Random random) {
		int moveForwardPreference = getSquare().canMove(getDirection()) ? MOVE_FORWARD_PREFERENCE : 0;
		Direction[] passableDirections = getSquare().getPassableDirectionsExcept(getDirection().getOpposite());
		if (passableDirections.length == 0)
			return getDirection().getOpposite();
		int result = random.nextInt(moveForwardPreference + passableDirections.length);
		if (result < moveForwardPreference)
			return getDirection();
		return passableDirections[result - moveForwardPreference];
	}
	
	// No formal document required
	public void move(Random random) {
		setDirection(chooseNextMoveDirection(random));
		setSquare(getSquare().getNeighbor(getDirection()));
	}
}
