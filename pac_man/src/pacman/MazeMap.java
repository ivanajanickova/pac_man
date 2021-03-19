package pacman;

import java.util.Arrays;
import java.util.stream.IntStream;


/**
 * Each instance of this class represents a maze layout, specifying the width and height of the maze
 * and, for each position in the maze, whether it is passable or not.
 * 
 * @invar | 1 <= getWidth()
 * @invar | 1 <= getHeight() 
 * 
 * @immutable
 */
public class MazeMap {
	
	/**
	 * @invar | elements != null
	 * @invar | 1 <= elements.length
	 * @invar | Arrays.stream(elements).allMatch(row -> row != null)
	 */
	
		
	/**
	 * @representationObject
	 * @representationObjects
	 */
	private boolean[][] elements;
	
	/**
	 * Returns the width (i.e. the number of columns) of this maze map.
	 * @basic
	 */
	public int getWidth() { 
		return elements[0].length;
	}
	
	/**
	 * Returns the height (i.e. the number of rows) of this maze map.
	 * @basic
	 */
	public int getHeight() { 
		return elements.length;
		}

	
	/**
	 * Returns whether the square in this maze at row index {@code row} and column index {@code column} is passable.
	 * The square in the top-left corner of the maze has row index 0 and column index 0.
	 * 
	 * @inspects | this
	 * 
	 * @throws IllegalArgumentException | 0 > rowIndex  
	 * @throws IllegalArgumentException | rowIndex >= getHeight()
	 * @throws IllegalArgumentException | 0 > columnIndex
	 * @throws IllegalArgumentException | columnIndex >= getWidth()
	 */	
	public boolean isPassable(int rowIndex, int columnIndex) { 
		if (0 > rowIndex) {
			throw new IllegalArgumentException("`rowIndex` is less than 0");
		}
		if (rowIndex >= getHeight()) {
			throw new IllegalArgumentException("`rowIndex` is out of range");
		}
		if (0 > columnIndex) {
			throw new IllegalArgumentException("`columnIndex` is less than 0");
		}
		if (columnIndex >= getWidth()) {
			throw new IllegalArgumentException("`columnIndex` is out of range");
		}
		return elements[rowIndex][columnIndex]; 
	}

	
	/**
	 * Initializes this object so that it represents a maze layout with the given width, height, and
	 * passable positions. The passable positions are given in row-major order (i.e. the first {@code width} elements
	 * of {@code passable} specify the passability of the maze positions in the first row of the maze). 
	 * 
	 * @inspects | passable
	 * 
	 * @throws IllealArgumentException | width < 1
	 * @throws IllealArgumentException | height < 1
	 * @throws IllealArgumentException | passable == null
	 * @throws IllealArgumentException | passable.length != width * height
	 * 
	 * @post | getWidth() == width
	 * @post | getHeight() == height
	 * @post | IntStream.range(0, passable.length-1).allMatch(i -> passable[i] == isPassable(i/width, i%width))
	 */
	public MazeMap(int width, int height, boolean[] passable) {
		if (width < 1) {
			throw new IllegalArgumentException("`width` is less than 1");
		}
		if (height < 1) {
			throw new IllegalArgumentException("`height` is less than 1");
		}
		if (passable == null) {
			throw new IllegalArgumentException("`passable` is null");
		}
		if (width * height != passable.length) {
			throw new IllegalArgumentException("length of `passable` is wrong");
		}
		
		this.elements = new boolean[height][width];
		for (int rowIndex = 0; rowIndex < elements.length; rowIndex++)
			for (int columnIndex = 0; columnIndex < width; columnIndex++)
				elements[rowIndex][columnIndex] = passable[rowIndex * width + columnIndex];
	}
	
}
















