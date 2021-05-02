package pacman;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Maze {
	
	private Random random;
	private MazeMap map;
	private PacMan pacMan;
	private Ghost[] ghosts;
	private FoodItem[] foodItems;
	
	public MazeMap getMap() { return map; }
	
	public PacMan getPacMan() { return pacMan; }
	
	public Ghost[] getGhosts() { return ghosts.clone(); }
	
	public FoodItem[] getFoodItems() { return foodItems.clone(); }
	
	public Maze(Random random, MazeMap map, PacMan pacMan, Ghost[] ghosts, FoodItem[] foodItems) {
		this.random = random;
		this.map = map;
		this.pacMan = pacMan;
		this.ghosts = ghosts.clone();
		this.foodItems = foodItems.clone();
	}
	
	public boolean isCompleted() {
		return foodItems.length == 0;
	}
	
	private void checkPacManDamage() {
		for (Ghost ghost : ghosts)
			if (ghost.getSquare().equals(pacMan.getSquare()))
				ghost.hitBy(pacMan);
	}
	
	public void moveGhosts() {
		for (Ghost ghost : ghosts)
			ghost.move(random);
		checkPacManDamage();
	}
	
	private void removeFoodItemAtIndex(int index) {
		if (foodItems[index].isPowerPellet()) {
			IntStream.range(0, ghosts.length).forEach(n -> ghosts[n].pacManAtePowerPellet());
		}
		FoodItem[] newFoodItem = new FoodItem[foodItems.length - 1];
		System.arraycopy(foodItems, 0, newFoodItem, 0, index);
		System.arraycopy(foodItems, index + 1, newFoodItem, index, newFoodItem.length - index);
		foodItems = newFoodItem;
	}
	
	private void removeFoodItemAtSquare(Square square) {
		for (int i = 0; i < foodItems.length; i++) {
			if (foodItems[i].getSquare().equals(square)) {
				removeFoodItemAtIndex(i);
				return;
			}
		}
	}
	
	public void movePacMan(Direction direction) {
		Square newSquare = pacMan.getSquare().getNeighbor(direction);
		if (newSquare.isPassable()) {
			pacMan.setSquare(newSquare);
			removeFoodItemAtSquare(newSquare);
			checkPacManDamage();
		}
	}
	
}
