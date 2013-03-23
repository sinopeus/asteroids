package entity;

import vector.Position;
import vector.Velocity;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import entity.ship.Mass;
import entity.ship.Ship;

/**
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * TODO document
 */
public class Bullet extends Entity
{
	/**
	 * Initializes a new bullet.
	* 
	* @param   	ship
	*          	The ship which has fired this bullet.
	* @effect  	Initializes this new bullet using the properties of the given ship.
	* 			| this.super(ship.getDirection(), ship.getPosition(), ship.getSpeedLimit, initialVelocity, new CircleShape(radius), new Mass(shape.getRadius() * density));
	*           | setShooter(ship);
	* @throws	NullPointerException
	* 			the given ship is null
	 */
	public Bullet(Ship ship) throws NullPointerException
	{
		super(ship.getDirection(), ship.getPosition(), Velocity.getSpeedOfLight(), new Velocity(ship.getVelocity().getSum(ship.getDirection().getScaledBy(bulletInitialVelocity))), new CircleShape(
				bulletRadius), new Mass((4 * Math.PI * Math.pow(ship.getShape().getRadius(), 3) * density) / 3));
		setShooter(ship);
		setBounceCounter((byte) 0);
	}

	/**
	 * Calculates the initial position of a new bullet given the ship it is fired from.
	 * 
	 * @param	ship
	 * 			The given ship
	 * @return	The initial position of this new bullet. (it is placed right next to the ship.
	 * 			| 
	 */
	private Position getInitialPosition(Ship ship)
	{
		return null; //TODO
	}

	/**
	 * Returns the shooter of this bullet.
	*
	* @return shooter
	 */
	@Basic
	@Raw
	public Ship getShooter()
	{
		return shooter;
	}

	/**
	 * Checks whether this bullet can have the given shooter ship as its shooter ship.
	 * 
	 * @param 	shooter
	 * 			The shooter ship to check.
	 * @return	True if and only if the given shooter ship is not null.
	 * 			| result = (shooter != null)
	 */
	@Basic
	@Raw
	private boolean canHaveAsShooter(Ship shooter)
	{
		return (shooter != null);
	}

	/**
	 * Sets the shooter ship to the given shooter ship.
	 * 
	 * @param 	shooter
	 * 			The given shooter ship.
	 * @post	The shooter ship of this bullet is equal to the given shooter ship.
	 * 			| new.getShooter() == shooter
	 * @throws 	IllegalArgumentException
	 * 			The given shooter is null
	 * 			| shooter == null
	 */
	private void setShooter(Ship shooter) throws IllegalArgumentException
	{
		if (!canHaveAsShooter(shooter))
		{
			throw new IllegalArgumentException("You cannot provide a null ship.");
		}
		this.shooter = shooter;
	}

	/**
	 * Returns the the amount of times this bullet has bounced on a worlds edge of this bullet.
	 */
	@Basic
	@Raw
	public byte getBounceCounter()
	{
		return this.bounceCounter;
	}

	/**
	 * Checks whether this bullet can have the given bounceCounter as its bounceCounter.
	 * 
	 * @param 	bounceCounter
	 * 			The bounceCounter to check.
	 * @return	True if and only if the given bounceCounter is positive.
	 * 			| result = (bounceCounter > 0)
	 */
	@Basic
	@Raw
	protected boolean canHaveAsBounceCounter(byte bounceCounter)
	{
		return (bounceCounter >= 0);
	}

	/**
	 * Sets the bounce counter of this bullet to the given bounce counter.
	 *
	 * @param	bounceCounter
	 *			The new bounce counter for this bullet.
	 * @post	If this bullet can have the given bounce counter as its bounce counter,
	 * 			then the bounce counter of this bullet is now equal to the given bounce counter.
	 * 			| if canHaveAsBounceCounter(bounceCounter)
	 * 			|	then new.getBounceCounter() == bounceCounter
	 */
	@Basic
	@Raw
	public void setBounceCounter(byte bounceCounter) throws IllegalArgumentException
	{
		if (!canHaveAsBounceCounter(bounceCounter))
		{
			throw new IllegalArgumentException("Invalid bounce counter provided");
		}
		this.bounceCounter = bounceCounter;
	}

	/**
	 * A variable registering the amount of times this bullet has bounced on a worlds edge of this bullet.
	 */
	private byte bounceCounter;

	/**
	 * A variable keeping a reference to the ship which shot this bullet.
	 */
	private Ship shooter;

	/**
	 * @see #Entity.terminate()
	 */
	@Override
	public void terminate()
	{
		this.shooter = null;
	}

	/**
	 * A variable registering the radius of a bullet.
	 */
	private static double bulletRadius = 3;

	/**
	 * A variable registering the initial velocity of a bullet.
	 */
	private static double bulletInitialVelocity = 250;

	/**
	 * A variable registering the density of a bullet.
	 */
	private static double density = 7.8E12;
}
