package pacman;

import java.util.Random;

public abstract class GhostState {
	
	public abstract boolean isVulnerable();
	
	public abstract void move(Ghost ghost, Random random);
	
	public GhostState hitBy(Ghost ghost, PacMan pacMan) {
		return this.hitBy(ghost, pacMan);
	}

}
