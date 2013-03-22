package entity.ship;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 */
public class Thruster
{
	/**
	 * Initializes this new thruster with a given maximum amount of thrust per second to be exerted and the owner ship of this thruster
	 * 
	 * @param	maximumThrust
	 * 			The given maximum amount of thrust per second to exert.
	 * @param	owner
	 * 			The given owner ship of this thruster
	 * @effect	The maximum amount of thrust per second of this thruster is set to the given maximum amount of thrust.
	 * 			| setMaximumThrust(maximumThrust)
	 * @effect	The owner ship of this thruster is set to the given owner ship.
	 * 			| setOwner(owner)
	 * @throws	IllegalArgumentException
	 * 			The owner ship is null.
	 */
	public Thruster(double maximumThrust, Ship owner) throws IllegalArgumentException
	{

	}

	/**
	 * Gets the maximum thrust per second of this thruster.
	 */
	@Basic
	@Raw
	public double getMaximumThrust()
	{

	}

	/**
	 * Checks whether this thruster can have the given maximum amount of thrust per second as its maximum amount of thrust per second
	 * 
	 * @param	maximumThrust
	 * 			The given maximum amount of thrust.
	 * @return	True if and only if the given amount of maximum thrust is positive.
	 * 			| result == (maximum > 0)
	 */
	@Basic
	@Raw
	protected boolean canHaveAsMaximumThrust(double maximumThrust)
	{

	}

	/**
	 * Sets the maximum amount of thrust per second of this thruster to the given maximum amount of thrust per second.
	 *.
	 * @param	maximumThrust
	 * 			The given maximum amount of thrust per second.
	 * @post	The maximum amount of thrust per second of this thruster is now equal to the given maximum amount of thrust per second.
	 * 			| new.getMaximumThrust() == maximumThrust
	 * @throws	IllegalArgumentException
	 * 			The given maximum amount of thrust is an illegal maximum amount of thrust.
	 * 			| !canHaveAsMaximumThrust(maximumTrust)
	 */
	@Basic
	@Raw
	private void setMaximumThrust(double maximumThrust) throws IllegalArgumentException
	{

	}

	/**
	 * A final variable registering the maximum amount of thrust that can be exerted by this thruster in N/s.
	 */
	private final double maximumThrustPerSecond;

	/**
	 * Checks whether this thruster is activated.
	 */
	@Basic
	@Raw
	public boolean isActivated()
	{
	}

	/**
	 * Activates this thruster.
	 * 
	 * @post	This thruster is activated.
	 * 			| new.isActivated()
	 */
	@Basic
	@Raw
	public void activate()
	{

	}

	/**
	 * Deactivates this thruster.
	 * 
	 * @post	This thruster is not activated.
	 * 			| !new.isActivated()
	 */
	@Basic
	@Raw
	public void deactivate()
	{

	}

	/**
	 * Toggles the state of activation of this thruster.
	 * 
	 * @post	The state of the thruster is now opposite to the state before toggling.
	 * 			| isActivated() && !new.isActivated()
	 */
	@Basic
	@Raw
	public void toggleActivation()
	{

	}

	/**
	 * A variable registering the activation of thist thruster.
	 */
	private boolean isActivated;

	/**
	 * Gets the owner of this thruster
	 */
	@Basic
	@Raw
	public Ship getOwner()
	{
	}

	/**
	 * Checks whether this thruster can have the given owner ship as it's owner ship.
	 * 
	 * @param	owner
	 * 			The given owner ship
	 * @return	True if and only if the given ship is not null
	 * 			| owner != null
	 */
	@Basic
	@Raw
	protected boolean canHaveAsOwner(Ship owner)
	{

	}

	/**
	 * Sets the owner ship of this thruster to the given owner ship.
	 * 
	 * @param	owner
	 * 			The given owner ship
	 * @post	The owner of thruster is now equal to the given owner ship.
	 * 			| new.getOwner() == owner
	 * @throws	IllegalArgumentException
	 * 			The given owner ship is null
	 * 			| owner == null
	 */
	@Basic
	@Raw
	public void setOwner(Ship owner) throws IllegalArgumentException
	{
	}

	/**
	 * A variable referencing the ship that owns this thruster.
	 */
	private Ship ownerShip;

	/**
	 * Thrusts the owner ship with the maximum amount of thrust of this thruster during a given amount of time.
	 * @param	duration
	 * 			The given amount of time.
	 */
	//TODO @EFFECT
	public void thrust(double duration)
	{
		//TODO TOTAL, change duration to 0 if negative
	}

	/**
	 * Thrusts the owner ship of this thruster with the given amount of thrust.
	 * @param	thrust
	 * 			The given amount of thrust.
	 * @param	duration
	 * 			The given amount of time. 
	 */
	//TODO @EFFECT
	public void thrust(double thrust, double duration)
	{
		//TODO TOTAL, change duration to 0 if negative.
		//TODO TOTAL, change thrust to maximum thrust if greater, to 0 if negative
	}

}