package pacman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

import logicalcollections.LogicalSet;

// Set conditions for wormholes to be not null? 'The maze shall initially have no wormholes'

public class Maze {
	
	private Random random;
	private MazeMap map;
	private PacMan pacMan;
	private Ghost[] ghosts;
	private FoodItem[] foodItems;
	private ArrivalPortal[] arrivalPortals;
	private DeparturePortal[] departurePortals;
	private Wormhole[] wormholes;
	

	public MazeMap getMap() { return map; }
	
	public PacMan getPacMan() { return pacMan; }
	
	public Ghost[] getGhosts() { return ghosts.clone(); }
	
	public FoodItem[] getFoodItems() { return foodItems.clone(); }
	
	/**
	 * @basic
	 * @creates | result
	 */
	public DeparturePortal[] getDeparturePortals() { return this.departurePortals.clone(); }
	
	
	/**
	 * @basic
	 * @creates | result
	 */
	public ArrivalPortal[] getArrivalPortals() { return this.arrivalPortals.clone();}
	
	
	/**
	 * @basic
	 * @creates | result
	 */
	
	public Wormhole[] getWormholes() { return wormholes.clone();}
	
	
	public void addWormhole(Wormhole wormhole) {
		if (Arrays.asList(arrivalPortals).contains(wormhole.getArrivalPortal()) == false|| 
				Arrays.asList(departurePortals).contains(wormhole.getDeparturePortal()) == false) {
			throw new IllegalArgumentException("The wormhole is not valid.");
		}
		else {
			if (wormholes == null ) { 
				this.wormholes = new Wormhole[] {wormhole}; 
				}
			else {
				Wormhole[] newWormholes = new Wormhole[wormholes.length + 1];
				for(int i = 0; i < wormholes.length; i++) {
					newWormholes[i] = wormholes[i];
				}
				newWormholes[newWormholes.length - 1] = wormhole;
				this.wormholes = newWormholes;
			}
		} 
		
	}
	
	public Maze(Random random, MazeMap map, PacMan pacMan, Ghost[] ghosts, FoodItem[] foodItems, 
			ArrivalPortal[] arrivalPortals, DeparturePortal[] departurePortals) {
		this.random = random;
		this.map = map;
		this.pacMan = pacMan;
		this.ghosts = ghosts.clone();
		this.foodItems = foodItems.clone();
		this.arrivalPortals = arrivalPortals.clone();
		this.departurePortals = departurePortals.clone();
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
	
	public void pacManAtePowerPellet() {
		for (Ghost ghost : ghosts)
			ghost.pacManAtePowerPellet();
	}
	
	private void removeFoodItemsAtIndex(int index) {
		FoodItem[] newFoodItems = new FoodItem[foodItems.length - 1];
		System.arraycopy(foodItems, 0, newFoodItems, 0, index);
		System.arraycopy(foodItems, index + 1, newFoodItems, index, newFoodItems.length - index);
		foodItems = newFoodItems;
	}
	
	private void checkFoodItemCollision(Square square) {
		for (int i = 0; i < foodItems.length; i++) {
			if (foodItems[i].getSquare().equals(square)) {
				foodItems[i].eatenByPacMan(this);
				removeFoodItemsAtIndex(i);
				return;
			}
		}
	}

	public void movePacMan(Direction direction) {
		Square newSquare = pacMan.getSquare().getNeighbor(direction);
		if (newSquare.isPassable()) {
			pacMan.setSquare(newSquare);
			checkFoodItemCollision(newSquare);
			checkPacManDamage();
		}
			
		 DeparturePortal portal = Arrays.stream(departurePortals)
				.filter(s -> s.getSquare().equals(newSquare) && s.getWormholes().size () != 0)
				.findFirst()
				.orElse(null);
		
		if(portal != null) {
			int index = random.nextInt(portal.getWormholes().size());
			Square nextSquare = ((Wormhole) portal.getWormholes().toArray()[index]).getArrivalPortal().getSquare();
			pacMan.setSquare(nextSquare);
			checkPacManDamage(); 
		 }
	}
	
}
	





