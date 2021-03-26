package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.Dot;
import pacman.MazeMap;
import pacman.Square;

class DotTest {

	@Test
	void test() {
		MazeMap map = new MazeMap(3, 2, new boolean[] {false, true, true, false, true, true});
		Square square = Square.of(map, 1, 0);
		Dot dot = new Dot(square);
		assertEquals(true, Square.of(map, 1, 0).equals(dot.getSquare()));
	}

}
