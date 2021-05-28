package pacman.wormholes.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.Square;
import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

class WormholeTest {

	@Test
	void test() {
		MazeMap mazeMap = new MazeMap(4, 3, new boolean[] {
				true, false, true, true,
				true, true, false, true,
				false, true, true, true
		 	});
		
		DeparturePortal depPort = new DeparturePortal(Square.of(mazeMap, 1, 2));
		ArrivalPortal arrPort = new ArrivalPortal(Square.of(mazeMap, 1, 3));
		Wormhole wormhole = new Wormhole(depPort, arrPort);
		assertEquals(depPort, wormhole.getDeparturePortal());
		assertEquals(arrPort, wormhole.getArrivalPortal());
		assertEquals(true, depPort.getWormholes().stream().allMatch(s -> s.equals(wormhole)));
		assertEquals(true, depPort.getWormholes().stream().allMatch(s -> (DeparturePortal) s.getDeparturePortal() == depPort));

		DeparturePortal depPort2 = new DeparturePortal(Square.of(mazeMap, 1, 1));	
		wormhole.setDeparturePortal(depPort2);
		assertEquals(depPort2, wormhole.getDeparturePortal());
		
		ArrivalPortal arrPort2 = new ArrivalPortal(Square.of(mazeMap, 0, 3));
		wormhole.setArrivalPortal(arrPort2);
		wormhole.setArrivalPortal(arrPort2);
		assertEquals(arrPort2, wormhole.getArrivalPortal());
		
		Wormhole wormhole2 = new Wormhole(depPort2, arrPort2);

		
		
	}

}
