package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;

import pacman.Square;

/**
 * @invar | getWormholes() != null
 * @invar | getWormholes().stream().allMatch(s -> s != null && s.getArrivalPortal() == this)
 * @invar | getSquare() != null
 * 
 */
public class ArrivalPortal {
	

	/**
	 * @invar | square != null  
	 * @represebtationObject
	 */
	Square square;
	
	/**
	 * 
	 * @invar | wormholes != null // Phase 1 representation invariant
	 * @invar | wormholes.stream().allMatch(s -> s != null && s.arrivalPortal == this) // Phase 2 representation invariant
	 * @invar | wormholes.stream().allMatch(s -> s.departurePortal.square != this.square)
	 * @representationObject
	 * @peerObjects
	 */
	Set<Wormhole> wormholes = new HashSet<Wormhole>();
	
	/**
	 * @basic
	 */
	public Square getSquare() {return this.square; }
	
	
	/**
	 * @post | result != null
	 * @basic
	 * @creates | result
	 * @peerObjects
	 */
	public Set<Wormhole> getWormholes() { return Set.copyOf(wormholes); }
	
	/**
	 * 
	 * @throws IllegalArgumentException | square == null
	 * 
	 * @post | getSquare().equals(square)
	 * @inspects | square
	 */
	public ArrivalPortal(Square square) {
		
		if(square == null) { throw new IllegalArgumentException("`square` is null");}
		
		this.square = square;
	}


}
