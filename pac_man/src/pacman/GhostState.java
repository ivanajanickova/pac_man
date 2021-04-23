package pacman;

import java.util.Random;

public abstract class GhostState {
	
	public abstract boolean isVunerable();
	
	public abstract void move(Ghost ghost, Random random);

}
