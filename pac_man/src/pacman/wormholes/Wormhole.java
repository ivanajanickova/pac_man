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
	
	private DeparturePortal departurePortal;
	
	private ArrivalPortal arrivalPortal;
	
	/**
	 * 
	 * @invar | getArrivalPortalInternal() == null || getArrivalPortalInternal().getWormholesInternal().contains(this) 
	 * 
	 * 
	 * @peerObject
	 * @representationObject
	 */
	ArrivalPortal getArrivalPortalInternal() {return this.arrivalPortal;}
	
	
	/**
	 * 
	 * @invar | getDeparturePortalInternal() == null || getDeparturePortalInternal().getWormholesInternal().contains(this) 
	 * 
	 * 
	 * @peerObject
	 * @representationObject
	 */
	DeparturePortal getDeparturePortalInternal() {return this.departurePortal;}
	
	/**
	 * @peerObject
	 * @basic
	 */
	public DeparturePortal getDeparturePortal() { return departurePortal;}
	
	
	/**
	 * @peerObject
	 * @basic
	 */
	public ArrivalPortal getArrivalPortal() {return arrivalPortal; }
	
	/**
	 * 
	 * @throws IllegalArgumentExeption | newDeparturePortal != null
	 * @throws IllegalArgumetException | newDeparturePortal.getSquare().equals(getArrivalPortal().getSquare())
	 * 
	 * @post | getDeparturePortal().equals(newDeparturePortal)
	 * @post | newDeparturePortal != old(getDeparturePortal()) ? 
	 * 		 |		newDeparturePortal.getWormholes().equals(LogicalSet.plus(old(newDeparturePortal.getWormholes()), this)) :
	 *       | 		newDeparturePortal.getWormholes().equals(old(newDeparturePortal.getWormholes()))
	 * 
	 * @inspects | newDeparturePortal
	 * @mutates | this, newDeparturePortal
	 * @mutates_properties | getDeparturePortal(), getDeparturePortal().getWormholes(), newDeparturePortal.getWormholes()
	 */
	public void setDeparturePortal(DeparturePortal newDeparturePortal) {
		if (newDeparturePortal == null) { throw new IllegalArgumentException("`newDeparturePortal` is null");}
		if (newDeparturePortal == getDeparturePortal()) {throw new IllegalArgumentException("newDeparturePortal` is the same as current");}
		if (newDeparturePortal.getSquare().equals(arrivalPortal.getSquare())) { 
			throw new IllegalArgumentException("`departurePortal` and `arrivalPortal` have the same placement");}
		
		this.departurePortal.removeWormhole(this);
		this.departurePortal = newDeparturePortal;
		newDeparturePortal.addWormhole(this);
	}
	

	/**
	 * 
	 * @throws IllegalArgumentExeption | newArrivalPortal != null
	 * @throws IllegalArgumetException | newArrivalPortal.getSquare().equals(getDeparturePortal().getSquare())
	 * 
	 * @post | getArrivalPortal().equals(newArrivalPortal)
	 * @post | newArrivalPortal != old(getArrivalPortal()) ? 
	 * 		 |		newArrivalPortal.getWormholes().equals(LogicalSet.plus(old(newArrivalPortal.getWormholes()), this)) :
	 *       | 		newArrivalPortal.getWormholes().equals(old(newArrivalPortal.getWormholes()))
	 * 
	 * @inspects | newArrivalPortal
	 * @mutates | this, newArrivalPortal
	 * @mutates_properties | getArrivalPortal(), getArrivalPortal().getWormholes(), newArrivalPortal.getWormholes()
	 */
	public void setArrivalPortal(ArrivalPortal newArrivalPortal) {
		if (newArrivalPortal == null) { throw new IllegalArgumentException("newArrivalPortal` is null");}
		if (newArrivalPortal.getSquare().equals(departurePortal.getSquare())) { 
			throw new IllegalArgumentException("`departurePortal` and `arrivalPortal` have the same placement");}
		
		this.arrivalPortal.removeWormhole(this);
		this.arrivalPortal = newArrivalPortal;
		newArrivalPortal.addWormhole(this);	
	}
	
	
	/**
	 * @inspects | departurePortal, arrivalPortal
	 * @mutates | departurePortal, arrivalPortal
	 * @mutates_properties | arrivalPortal.getWormholes(), departurePortal.getWormholes()
	 * 
	 * @throws IllegalArgumentException | departurePortal == null
	 * @throws IllegalArgumentException | arrivalPortal == null
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
		
		this.arrivalPortal = arrivalPortal;
		this.departurePortal = departurePortal;
		
		departurePortal.addWormhole(this);
		arrivalPortal.addWormhole(this);
				
	}


}
