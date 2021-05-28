package pacman.wormholes;

import java.util.HashSet;
import java.util.Set;
import logicalcollections.LogicalSet;
import pacman.Square;

/**
 * @invar | getWormholes() != null
 * @invar | getWormholes().stream().allMatch(s -> s == null || s.getArrivalPortal() == this)
 * @invar | getSquare() != null
 * 
 */
public class ArrivalPortal {
	

	/**
	 * @invar | square != null  
	 * @representationObject
	 */
	private Square square;
	
	/**
	 * 
	 * @invar | wormholes != null 
	 * @invar | wormholes.stream().allMatch(s -> s != null)
	 * 
	 * @representationObject
	 */
	private Set<Wormhole> wormholes = new HashSet<Wormhole>();
	
	/**
	 * 
	 * @invar | getWormholesInternal().stream().allMatch(s -> s.getArrivalPortalInternal() == this)
	 * 
	 * 
	 * @post | result != null && result.stream().allMatch(s -> s != null)
	 * @creates | result
	 * @representationObject
	 * @peerObjects
	 */
	Set<Wormhole> getWormholesInternal() {return Set.copyOf(wormholes);}
	
	/**
	 * @basic
	 */
	public Square getSquare() {return this.square; }
	
	
	/**
	 * @post | result != null && result.stream().allMatch(s -> s != null)
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
	
	
	/**
	* Adds the given student to this team's set of students.
	*
	* @throws IllegalArgumentException | wormhole == null
	* @mutates | this
	* @mutates_properties | getWormholes(), getWormholesInternal()
	* @post | getWormholesInternal().equals(LogicalSet.plus(old(getWormholesInternal()), wormhole)) //if the wormhole is already added this would still hold as `wormholes` is a SET
	*
	*/
	void addWormhole(Wormhole wormhole) {
		if(wormhole == null) {
			throw new IllegalArgumentException("wormhole is null");
		}
		wormholes.add(wormhole);
	
	}
	
	/**
	* Adds the given student to this team's set of students.
	*
	* @throws IllegalArgumentException if {@code wormhole} is null | wormhole == null
	* 
	* @mutates | this
	* @mutates_properties | getWormholes(), getWormholesInternal()
	* @post | getWormholesInternal().equals(LogicalSet.minus(old(getWormholesInternal()), wormhole))
	*
	*/
	void removeWormhole(Wormhole wormhole) {
		if(wormhole == null) {
			throw new IllegalArgumentException("wormhole is null");
		}
		wormholes.remove(wormhole);
	}



}
