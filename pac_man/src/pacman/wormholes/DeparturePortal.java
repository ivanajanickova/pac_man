package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;

import pacman.Square;


/**
 * @invar | true
 * @invar | getWormholes() != null
 * @invar | getWormholes().stream().allMatch(s -> s != null && s.getDeparturePortal() == this)
 */
public class DeparturePortal {
	
	Square square;
	
	/**
	 * @invar | wormholes != null // Phase 1 representation invariant
	 * @invar | wormholes.stream().allMatch(s -> s != null && s.departurePortal == this) // Phase 2 representation invariant
	 * @invar | wormholes.stream().allMatch(s -> s.arrivalPortal.square != this.square)
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
	public DeparturePortal(Square square) {
		
		if(square == null) { throw new IllegalArgumentException("`square` is null");}
		
		this.square = square;
	}

}
