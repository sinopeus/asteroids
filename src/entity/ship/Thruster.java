package entity.ship;

import vector.Acceleration;
import vector.Force;
import Utilities.Mechanics;
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
	 * @effect	This thruster is not active
	 * 			| deactivate()
	 * @throws	IllegalArgumentException
	 * 			The owner ship is null.
	 */
	public Thruster(double maximumThrust, Ship owner) throws IllegalArgumentException
	{
		setMaximumThrustPerSecond(maximumThrust);
		setOwner(owner);
	}

	/**
	 * Gets the maximum thrust per second of this thruster.
	 */
	@Basic
	@Raw
	public double getMaximumThrustPerSecond()
	{
		return maximumThrustPerSecond;
	}

	/**
	 * Checks whether this thruster can have the given maximum amount of thrust per second as its maximum amount of thrust per second
	 * 
	 * @param	maximumThrust
	 * 			The given maximum amount of thrust.
	 * @return	True if and only if the given amount of maximum thrust is positive.
	 * 			| result == (maximum >= 0)
	 */
	@Basic
	@Raw
	protected boolean canHaveAsMaximumThrust(double maximumThrust)
	{
		return (maximumThrust >= 0);
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
	private void setMaximumThrustPerSecond(double maximumThrust) throws IllegalArgumentException
	{
		if (!canHaveAsMaximumThrust(maximumThrust))
		{
			throw new IllegalArgumentException("Illegal maximum thrust provided.");
		}
		this.maximumThrustPerSecond = maximumThrust;
	}

	/**
	 * A variable registering the maximum amount of thrust that can be exerted by this thruster in N/s.
	 */
	private double maximumThrustPerSecond;

	/**
	 * Checks whether this thruster is activated.
	 */
	@Basic
	@Raw
	public boolean isActivated()
	{
		return isActivated;
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
		this.isActivated = true;
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
		this.isActivated = false;
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
		this.isActivated = !this.isActivated;
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
		return this.ownerShip;
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
		return owner != null;
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
		if (!canHaveAsOwner(owner))
		{
			throw new IllegalArgumentException("Illegal owner ship provided.");
		}
		this.ownerShip = owner;
	}

	/**
	 * A variable referencing the ship that owns this thruster.
	 */
	private Ship ownerShip;

	/**
	 * Makes the refference to the owner ship null.
	 */
	public void terminate() //TODO make this into a state?
	{
		this.ownerShip = null;
	}

	/**
	 * Thrusts the owner ship with the maximum amount of thrust of this thruster during a given amount of time.
	 * 
	 * @param	duration
	 * 			The given amount of time.
	 */
	//TODO @EFFECT
	public void thrust(double duration)
	{
		thrust(getMaximumThrustPerSecond(), duration);
	}

	/**
	 * Thrusts the owner ship of this thruster with the given amount of thrust.
	 * 
	 * @param	thrustPerSecond
	 * 			The given amount of thrust.
	 * @param	duration
	 * 			The given amount of time. 
	 */
	//TODO @EFFECT
	public void thrust(double thrustPerSecond, double duration)
	{
		if (duration < 0)
		{
			duration = 0;
		}
		if (thrustPerSecond < 0)
		{
			thrustPerSecond = 0;
		}
		if (thrustPerSecond > getOwner().getThrustPerSecond())
		{
			thrustPerSecond = getOwner().getThrustPerSecond();
		}
		Acceleration a = Mechanics.Newtons_secondLaw_CalculateAcceleration(new Force(getOwner().getDirection().getScaledBy(thrustPerSecond * duration)), getOwner().getMass());
		getOwner().getVelocity().accelerateBy(a, duration);
	}
	
	
	@Override
	public String toString()
	{
		return "thruster_" + hashCode() + " of " + getOwner();
	}
}