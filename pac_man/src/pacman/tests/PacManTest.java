package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.PacMan;
import pacman.Square;

class PacManTest {

	@Test
	void test() {
		MazeMap map = new MazeMap(3, 2, new boolean[] {false, true, true, false, true, true});
		Square square = Square.of(map, 1, 0);
		PacMan pacman = new PacMan(3, square);
		
		assertEquals(true, pacman.getSquare().equals(square)); 
		assertEquals(3,pacman.getNbLives());
		pacman.die();
		
		Square square2 = Square.of(map, 1, 1);
		pacman.setSquare(square2);
		assertEquals(true, pacman.getSquare().equals(square2));
		
		PacMan pacman2 = new PacMan(1, square);
		pacman2.die();
		assertEquals(0, pacman2.getNbLives());
	}

}
