package pacman;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;
import pacman.Ghost;

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
	
	/**
	 * @representationObject
	 */
	private Set<Wormhole> wormholes = new HashSet<Wormhole>();

	public MazeMap getMap() { return map; }
	
	public PacMan getPacMan() { return pacMan; }
	
	public Ghost[] getGhosts() { return ghosts.clone(); }
	
	public FoodItem[] getFoodItems() { return foodItems.clone(); }
	
	/**
	 * @basic
	 * @creates | result
	 */
	
	public DeparturePortal[] getDeparturePortals() { 
		Arrays.sort(departurePortals);
		return departurePortals.clone();
		}
	/**
	 * @basic
	 * @creates | result
	 */
	public ArrivalPortal[] getArrivalPortals() {
		Arrays.sort(departurePortals);
		return arrivalPortals.clone();}
		
	/**
	 * @basic
	 * @creates | result
	 */
	
	public Set<Wormhole> getWormholes(){ return Set.copyOf(wormholes); } 
		
	/**
	 * @throws IllegalArgumentException | Arrays.asList(arrivalPortals).contains(wormhole.getArrivalPortal())
	 * @throws IllegalArgumentException | Arrays.asList(departurePortals).contains(wormhole.getDeparturePortal())
	 * @mutates | this
	 * @post | getWormholes().equals(LogicalSet.plus(old(getWormholes()), wormhole)
	 */
	public void addWormhole(Wormhole wormhole) {
		
		if (Arrays.asList(arrivalPortals).contains(wormhole.getArrivalPortal()))
			throw new IllegalArgumentException("The arrival portal is already in the maze's list");
		
		if (Arrays.asList(departurePortals).contains(wormhole.getDeparturePortal()))
			throw new IllegalArgumentException("The departure portal is already in the maze's list");
		
		wormholes.add(wormhole);
	}
	
	public Maze(Random random, MazeMap map, PacMan pacMan, Ghost[] ghosts, FoodItem[] foodItems, 
			ArrivalPortal[] arrivalPortals, DeparturePortal[] departurePortals) {
		this.random = random;
		this.map = map;
		this.pacMan = pacMan;
		this.ghosts = ghosts.clone();
		this.foodItems = foodItems.clone();
		this.arrivalPortals = arrivalPortals.clone(); //clone?
		this.departurePortals = departurePortals.clone(); //clone?
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
				
		if(Arrays.asList(departurePortals).contains(newSquare)){
			
			//Ghost newGhost = new Ghost(newGhost.getSquare(), direction);

			Set<Wormhole> wormholePortals = new HashSet<>();
			
			for (int i = 0; i< wormholes.toArray().length; i++){
				if (((Wormhole) wormholes.toArray()[i]).getDeparturePortal().equals(newSquare))
					wormholePortals.add((Wormhole) wormholes.toArray()[i]);
				}
			
			int index = random.nextInt(wormholePortals.toArray().length);
			pacMan.setSquare(((Wormhole) wormholes.toArray()[index]).getArrivalPortal().getSquare());
			
			checkPacManDamage();

		}
		
		
		
	}
	
}
	





