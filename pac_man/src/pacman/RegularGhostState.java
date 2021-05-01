package pacman;

import java.util.Random;

public class RegularGhostState extends GhostState {

	@Override
	public boolean isVulnerable() {
		return false;
	}
	
	@Override
	public void move(Ghost ghost, Random random) {
		ghost.reallyMove(random);		
	}
	
}
