package pacman.wormholes;

import logicalcollections.LogicalSet;

/**
 * 
 * 
 *@invar | getDeparturePortal() != null && getDeparturePortal().getWormholes().contains(this)
 *@invar | getArrivalPortal() != null && getArrivalPortal().getWormholes().contains(this)
 *
 */
public class Wormhole {
	
	/**
	 * @invar | true  // Phase 1 representation invariant
	 * @invar | departurePortal != null && departurePortal.wormholes.contains(this) // Phase 2 representation invariant
	 * @invar | departurePortal.square != arrivalPortal.square
	 * 
	 * @peerObject
	 * @representationObject
	 */
	DeparturePortal departurePortal;
	
	/**
	 * @invar | true  // Phase 1 representation invariant
	 * @invar | arrivalPortal != null && arrivalPortal.wormholes.contains(this) // Phase 2 representation invariant
	 * @invar | departurePortal.square != arrivalPortal.square
	 * 
	 * @peerObject
	 * @representationObject
	 */
	ArrivalPortal arrivalPortal;
	
	
	/**
	 * 
	 * @basic
	 */
	public DeparturePortal getDeparturePortal() { return departurePortal;}
	
	
	/**
	 * 
	 * @basic
	 */
	public ArrivalPortal getArrivalPortal() {return arrivalPortal; }
	
	/**
	 * 
	 * @throws IllegalArgumentExeption | newDeparturePortal != null
	 * @throws IllegalArgumetException | newDeparturePortal.getSquare().equals(getArrivalPortal().getSquare())
	 * @post | getDeparturePortal().equals(newDeparturePortal)
	 * @post | newDeparturePortal.getWormholes().equals(LogicalSet.plus(old(newDeparturePortal.getWormholes()), this)) 
	 * 
	 * @mutates | this, newDeparturePortal
	 * @mutates_properties | getArrivalPortal(), newDeparturePortal.getWormholes()
	 */
	public void setDeparturePortal(DeparturePortal newDeparturePortal) {
		if (newDeparturePortal == null) { throw new IllegalArgumentException("`newDeparturePortal` is null");}
		if (newDeparturePortal.getSquare().equals(arrivalPortal.getSquare())) { 
			throw new IllegalArgumentException("`departurePortal` and `arrivalPortal` have the same placement");}
		this.departurePortal = newDeparturePortal;
		newDeparturePortal.wormholes.add(this);
	}
	
	//@post | newArrivalPortal.getWormholes().equals(LogicalSet.plus(old(newArrivalPortal.getWormholes()), this)) 
	/**
	 * 
	 * @throws IllegalArgumentExeption | newArrivalPortal != null
	 * @throws IllegalArgumetException | newArrivalPortal.getSquare().equals(getDeparturePortal().getSquare())
	 * @post | getArrivalPortal().equals(newArrivalPortal)
	 * 
	 * 
	 * @mutates | this, newArrivalPortal
	 * @mutates_properties | getArrivalPortal(), newArrivalPortal.getWormholes()
	 */
	public void setArrivalPortal(ArrivalPortal newArrivalPortal) {
		if (newArrivalPortal == null) { throw new IllegalArgumentException("newArrivalPortal` is null");}
		if (newArrivalPortal.getSquare().equals(departurePortal.getSquare())) { 
			throw new IllegalArgumentException("`departurePortal` and `arrivalPortal` have the same placement");}
		
		newArrivalPortal.wormholes.add(this);
		this.arrivalPortal = newArrivalPortal;
		
	}
	
	
	/**
	 * @inspects | departurePortal, arrivalPortal
	 * @mutates | departurePortal, arrivalPortal
	 * @mutates_properties | arrivalPortal.getWormholes(), departurePortal.getWormholes()
	 * 
	 * @throws IllegalArgumentException | departurePortal == null
	 * @throws IllegalArgumentException | arrivalPortal == null
	 * @throws IllegalArgumentException | departurePortal.getSquare().equals(arrivalPortal.getSquare())
	 * 
	 * @post | getDeparturePortal() == departurePortal && departurePortal.getWormholes()
	 *       |		.equals(LogicalSet.plus(old(departurePortal.getWormholes()), this))
	 * @post | getArrivalPortal() == arrivalPortal && arrivalPortal.getWormholes()
	 *       |		.equals(LogicalSet.plus(old(arrivalPortal.getWormholes()), this))
	 * 
	 */
	public Wormhole(DeparturePortal departurePortal, ArrivalPortal arrivalPortal) {
		
		if (departurePortal == null) { throw new IllegalArgumentException("`departurePortal` is null");}
		if (arrivalPortal == null) { throw new IllegalArgumentException("`arrivalPortal` is null");}
		if (departurePortal.getSquare().equals(arrivalPortal.getSquare())) { 
			throw new IllegalArgumentException("`departurePortal` and `arrivalPortal` have the same placement");}
		
		this.arrivalPortal = arrivalPortal;
		this.departurePortal = departurePortal;
		arrivalPortal.wormholes.add(this);
		departurePortal.wormholes.add(this);
	}
	
	

}
