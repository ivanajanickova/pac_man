package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.Square;
import pacman.Ghost;
import pacman.Direction;

class GhostTest {

	@Test
	void test() {
		MazeMap map = new MazeMap(3, 2, new boolean[] {false, true, true, false, true, true});
		Square square = Square.of(map, 1, 0);
		Direction direction = Direction.LEFT;
		
		Ghost ghost = new Ghost(square, direction);
		assertEquals(true, Square.of(map, 1, 0).equals(ghost.getSquare()));
		assertEquals(true, direction.equals(ghost.getDirection()));
		
		direction = Direction.UP; //checking if there is cloning of enum necessary
		assertEquals(Direction.LEFT, ghost.getDirection());
		
		Square square2 = Square.of(map, 1, 1);
		ghost.setSquare(square2);
		assertEquals(true, ghost.getSquare().equals(square2));
		
		Direction direction2 = Direction.RIGHT;
		ghost.setDirection(direction2);
		assertEquals(true, ghost.getDirection().equals(direction2));


	}
}