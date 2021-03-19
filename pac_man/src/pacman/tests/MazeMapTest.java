package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;

class MazeMapTest {

	@Test
	void test() {
		MazeMap map = new MazeMap(3, 2, new boolean[] {false, true, true, false, true, true});
		assertEquals(2, map.getHeight());
		assertEquals(3, map.getWidth());
		assertEquals(true, map.isPassable(1, 1));
	}

}
