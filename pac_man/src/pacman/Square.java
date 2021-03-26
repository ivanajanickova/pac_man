package pacman;

import java.util.HashMap;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 * 
 * @immutable
 * 
 * @invar | getRowIndex() >= 0
 * @invar | getRowIndex() < getMazeMap().getHeight()
 * @invar | getColumnIndex() >= 0
 * @invar | getMazeMap() != null
 */
public class Square {
	

	/**
	 * @invar | mazeMap != null
	 * @invar | coordinates[0] >= 0
	 * @invar | coordinates[0] < mazeMap.getHeight()
	 * @invar | coordinates[1] >= 0
	 * @invar | coordinates[1] < mazeMap.getWidth()
	 */
	
	/**
	 * @represetantionObject
	 */
	private MazeMap mazeMap;
	
	/**
	 * Stores coordinates of a square as [row, column]
	 * @representationObject
	 */
	private int[] coordinates;
	
	/**
	 * @basic
	 */
	public MazeMap getMazeMap() { 
		boolean[] passable = new boolean[mazeMap.getHeight() * mazeMap.getWidth()];
		int index = 0;
		for (int i = 0; i < mazeMap.getHeight(); i ++) {
			for (int j = 0; j < mazeMap.getWidth(); j ++) {
				passable[index] = mazeMap.isPassable(i, j);
				index++;
			}
		}
		MazeMap clone = new MazeMap(mazeMap.getWidth(), mazeMap.getHeight(), passable);
		
		return clone;
		}
	
	/**
	 * @post | result < getMazeMap().getHeight()
	 */
	public int getRowIndex() { 
		return coordinates[0];
		}
	
	/**
	 * @post | result < getMazeMap().getWidth()
	 */
	public int getColumnIndex() { 
		return coordinates[1];
		}
	
	/**
	 * @inspects | this
	 * 
	 * @throws IllealArgumentException | getRowIndex() < 0
	 * @throws IllealArgumentException | getColumnIndex() < 0
	 * @throws IllealArgumentException | getMazeMap() == null
	 * 
	 */
	public boolean isPassable() { 
		if (getRowIndex() < 0) {
			throw new IllegalArgumentException("`rowIndex` is smaller than 0");
		}
		if (getColumnIndex() < 0) {
			throw new IllegalArgumentException("`columnIndex` is smaller than 0");
		}
		if (getMazeMap() == null) {
			throw new IllegalArgumentException("`mazeMap` is null");
		}
		return this.mazeMap.isPassable(this.coordinates[0], this.coordinates[1]);
		}
	

	private Square(MazeMap mazeMap, int rowIndex, int columnIndex) {
		this.coordinates = new int[2];
		this.coordinates[0] = rowIndex;
		this.coordinates[1] = columnIndex;
		
		boolean[] passable = new boolean[mazeMap.getHeight() * mazeMap.getWidth()];
		int index = 0;
		for (int i = 0; i < mazeMap.getHeight(); i ++) {
			for (int j = 0; j < mazeMap.getWidth(); j ++) {
				passable[index] = mazeMap.isPassable(i, j);
				index ++;
			}
		}
		
		MazeMap clone = new MazeMap(mazeMap.getWidth(), mazeMap.getHeight(), passable);
		this.mazeMap = clone;
	}
	
	 /**
	  * @inspects | mazeMap
	  * 
	  * @throws IllealArgumentException | rowIndex < 0
	  * @throws IllealArgumentException | rowIndex >= mazeMap.getHeight()
	  * @throws IllealArgumentException | columnIndex < 0
	  * @throws IllealArgumentException | columnIndex >= mazeMap.getWidth()
	  * @throws IllealArgumentException | mazeMap.equals(null)
	  * 
	  * @post | result != null
	  * @post | result.getRowIndex() == rowIndex
	  * @post | result.getColumnIndex() == columnIndex
	  * @post | result.isPassable() == mazeMap.isPassable(rowIndex, columnIndex) 
	  */
	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) {
		if (rowIndex < 0 ) {
			throw new IllegalArgumentException("`rowIndex` is smaller than 0");
		}
		if (rowIndex >= mazeMap.getHeight()  ) {
			throw new IllegalArgumentException("`rowIndex` is out of range");
		}
		if (columnIndex < 0) {
			throw new IllegalArgumentException("`columnIndex` is smaller than 0");
		}
		if (columnIndex >= mazeMap.getWidth() ) {
			throw new IllegalArgumentException("`columnIndex` is out of range");
		}
		if (mazeMap.equals(null)) {
			throw new IllegalArgumentException("`mazeMap` is null");
		}
		
		return new Square(mazeMap, rowIndex, columnIndex);
	}

	/**
	 * Returns this square's neighbor in the given direction.
	 * If this square has no neighbor in the given direction, return the square that is furthest away in the opposite direction.
	 */
	// No formal documentation required
	public Square getNeighbor(Direction direction) {
		int[] newCoordinates = coordinates.clone();
		switch(direction) {
			case LEFT -> newCoordinates[1] = coordinates[1] - 1 >= 0 ? coordinates[1] - 1 : mazeMap.getWidth() - 1;
			case RIGHT -> newCoordinates[1] = coordinates[1] + 1 < mazeMap.getWidth() ? coordinates[1] + 1 : 0;	
			case UP -> newCoordinates[0] = coordinates[0] - 1 >= 0 ? coordinates[0] - 1 : mazeMap.getHeight() - 1;
			case DOWN -> newCoordinates[0] = coordinates[0] + 1 < mazeMap.getHeight() ? coordinates[0] + 1 : 0;
		}		
		return new Square(this.getMazeMap(), newCoordinates[0], newCoordinates[1]);
	}

	/**
	 * Returns whether this square's neighbor in the given direction is passable.
	 */
	// No formal documentation required
	public boolean canMove(Direction direction) {
		return this.getNeighbor(direction).isPassable();
	}
	

	/**
	 * Returns the directions that are different from the given excluded direction and such that the neighbor in that direction is passable.
	 * The returned array shall have no null elements and shall have no duplicates.
	 */
	// No formal documentation required
	public Direction[] getPassableDirectionsExcept(Direction excludedDirection) {
		HashMap <Direction, Square> directions = new HashMap<>();
				directions.put(Direction.LEFT, this.getNeighbor(Direction.LEFT));
				directions.put(Direction.RIGHT, this.getNeighbor(Direction.RIGHT));
				directions.put(Direction.UP, this.getNeighbor(Direction.UP));
				directions.put(Direction.DOWN, this.getNeighbor(Direction.DOWN));
		
		Direction[] directionsArray = new Direction[] {Direction.LEFT, Direction.RIGHT, Direction.DOWN, Direction.UP};
		
		for (Direction d : directionsArray) {
			if (!directions.get(d).isPassable() || d.equals(excludedDirection)) {
				directions.remove(d);
			}
		}
		return directions.keySet().toArray(new Direction[directions.keySet().size()]);
	}
	

	/**
	 * Returns whether the given square refers to the same {@code MazeMap} object and has the same row and column index as this square. 
	 * 
	 * @inspects | other
	 * @throws IllegalArgumentException | other == null
	 * 
	 */
	public boolean equals(Square other) {
		
		if (other == null) {
			throw new IllegalArgumentException("`other` is null");
		}
		int count = 0;
		if (coordinates[0] == other.getRowIndex() && coordinates[1] == other.getColumnIndex() && 
				mazeMap.getHeight() == other.getMazeMap().getHeight() && mazeMap.getWidth() == other.getMazeMap().getWidth()) {
			
			//checking if the two MazeMaps of Squares are equal (same `passable` pattern)
			for (int i = 0; i < mazeMap.getHeight(); i ++) {
				for (int j = 0; j < mazeMap.getWidth(); j ++) {
					if(mazeMap.isPassable(i, j) == other.getMazeMap().isPassable(i, j)) {
						count ++;
					}
				}
			}
		}
		return count == mazeMap.getWidth() * mazeMap.getHeight();
				
	}	
}

