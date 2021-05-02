package pacman;

import java.util.Random;

public class VulnerableGhostState extends GhostState {
	
	private int moveDelay = 1;
	private int count = 0;
	
	@Override
	public boolean isVulnerable() {
		return true;
	}

	@Override
	public void move(Ghost ghost, Random random) {
		
		if(count <= 6) {
			if (moveDelay == 1) {
				moveDelay = 0;
				count ++;
				return;
			}
			else {
				ghost.reallyMove(random);
				moveDelay = 1;
				count ++;
				return;
			}
		}
		else {
			ghost.setGhostState(new RegularGhostState());
		}
	}

	@Override
	public GhostState hitBy(Ghost ghost, PacMan pacMan) {
		ghost.setSquare(ghost.getOriginalSquare());
		return new RegularGhostState();
	}
	

}
