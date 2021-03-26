package pacman.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.MazeMap;
import pacman.Square;

class SquareTest {

	@Test
	void test() {
		MazeMap map = new MazeMap(3, 2, new boolean[] {false, true, true, false, true, true});
		Square square = Square.of(map, 1, 0);
		assertEquals(1, square.getRowIndex());
		assertEquals(0, square.getColumnIndex());
		assertEquals(false, square.isPassable());
		assertEquals(2, square.getMazeMap().getHeight());	
		assertEquals(3, square.getMazeMap().getWidth());
		
		assertEquals(true, square.getNeighbor(Direction.UP).equals(Square.of(map, 0, 0)));
		assertEquals(true, square.canMove(Direction.RIGHT));
		assertEquals(false, square.canMove(Direction.DOWN));
		assertArrayEquals(new Direction[] {Direction.RIGHT, Direction.LEFT}, square.getPassableDirectionsExcept(Direction.DOWN));
		assertArrayEquals(new Direction[] {Direction.LEFT}, square.getPassableDirectionsExcept(Direction.RIGHT));
		assertEquals(true, square.equals(Square.of(map, 1, 0)));
		
	}

}
