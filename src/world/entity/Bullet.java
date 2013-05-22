package world.entity;

import world.entity.ship.Ship;
import world.physics.Mass;
import world.physics.collision.Border;
import world.physics.geometry.CircleShape;
import world.physics.vector.Position;
import world.physics.vector.Vector;
import world.physics.vector.Velocity;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A Class of Bullets extending entities Involving also a ship.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 */
public class Bullet extends Entity
{
	/**
	 * Initializes a new bullet.
	 * 
	 * @param   ship
	 *          The ship which has fired this bullet.
	 * @effect  Initializes this new bullet using the properties of the given ship.
	 * 			| this.super(ship.getDirection(), ship.getPosition(), ship.getSpeedLimit, initialVelocity, new CircleShape(radius), new Mass(shape.getRadius() * density));
	 *          | setShooter(ship);
	 * @throws	NullPointerException
	 * 			The given ship is null.
	 */
	public Bullet (Ship ship) throws NullPointerException
	{
		super(ship.getDirection(), getInitialPosition(ship), Velocity.getSpeedOfLight(), new Velocity(ship.getVelocity().getSum(ship.getDirection().getScaledBy(bulletInitialVelocity))), new CircleShape(bulletRadius), new Mass( (4 * Math.PI * Math.pow(bulletRadius, 3) * density) / 3.0));
		setShooter(ship);
		setBounceCounter((byte) 0);
	}

	/**
	 * Calculates the initial position of a new bullet given the ship it is fired from.
	 * 
	 * @param	ship
	 * 			The given ship
	 * @return	The initial position of this new bullet. (It is placed right next to the ship.)
	 * 			|  new Position(new Vector(ship.getPosition()).getSum(ship.getDirection().getScaledBy(ship.getShape().getRadius() + bulletRadius)));
	 */
	private static Position getInitialPosition (Ship ship)
	{
		return new Position(new Vector(ship.getPosition()).getSum(ship.getDirection().getScaledBy(ship.getShape().getRadius() + bulletRadius)));
	}

	/**
	 * Returns the shooter of this bullet.
	 *
	 * @return shooter
	 */
	@Basic
	@Raw
	public Ship getShooter ()
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
	private static boolean canHaveAsShooter (@Raw Ship shooter)
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
	@Basic
	@Raw
	private void setShooter (@Raw Ship shooter) throws IllegalArgumentException
	{
		if (!canHaveAsShooter(shooter)) { throw new IllegalArgumentException("You cannot provide a null ship."); }
		this.shooter = shooter;
	}

	/**
	 * Returns the the amount of times this bullet has bounced on a worlds edge of this bullet.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public byte getBounceCounter ()
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
	protected boolean canHaveAsBounceCounter (@Raw byte bounceCounter)
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
	 * @throws	IllegalArgumentException
	 * 			The given bounceCounter is not a valid bounce counter
	 * 			| !canHaveAsBounceCounter(bounceCounter)
	 */
	@Basic
	@Raw
	public void setBounceCounter (@Raw byte bounceCounter) throws IllegalArgumentException
	{
		if (!canHaveAsBounceCounter(bounceCounter)) { throw new IllegalArgumentException("Invalid bounce counter provided"); }
		this.bounceCounter = bounceCounter;
	}

	/**
	 * A variable registering the amount of times this bullet has bounced on a worlds edge of this bullet.
	 */
	private byte	bounceCounter;

	/**
	 * Has this Bullet collide with the given Asteroid.
	 * 
	 * @param 	that
	 * 			The given Asteroid to collide with.
	 * @effect	Terminate both this bullet and the given asteroid
	 * 			| that.terminate()
	 * 			| this.terminate()
	 */
	@Override
	public void collideWith (Asteroid that)
	{
		if (that == null) return;
		that.collideWith(this);
	}

	/**
	 * Has this Bullet collide with the given Bullet.
	 * 
	 * @param 	that
	 * 			The given Bullet to collide with.
	 * @effect	Terminate both this bullet and the other one.
	 * 			| this.terminate()
	 * 			| that.terminate()
	 */
	@Override
	public void collideWith (Bullet that)
	{
		if (that == null) return;
		this.terminate();
		that.terminate();
	}

	/**
	 * Has this Bullet collide with the given Ship.
	 * 
	 * @param 	that
	 * 			The given Ship to collide with.
	 * @effect	If this bullet was shot by the given ship, don't do anything.
	 * 			|
	 * @effect	Otherwise Terminate both this Ship and the given bullet.
	 * 			| this.terminate()
	 * 			| that.terminate()
	 */
	@Override
	public void collideWith (Ship that)
	{
		if (that == null) return;
		that.collideWith(this);
	}

	/**
	 * Has this Bullet collide with the given Border.
	 * 
	 * @param 	that
	 * 			The given border to collide with.
	 * @effect	Has this bullet bounce against the given border.
	 */
	@Override
	public void collideWith (Border that)
	{
		if (that == null) return;
		if (getBounceCounter() >= maximumBorderBounces)
		{
			terminate();
			return;
		}
		super.collideWith(that);
		bounceCounter++;
	}

	/**
	 * A variable keeping a reference to the ship which shot this bullet.
	 */
	private Ship	shooter;

	/**
	 * Unlinks this bullet from the ship which shot it.
	 * 
	 * @post	Set the reference to the shooter of this bullet to null. 
	 * 			| shooter == null;
	 */
	@Raw
	public void unlinkFromShooter ()
	{
		assert (this.shooter.getBulletList().contains(this));//TODO remove this
		this.shooter.getBulletList().remove(this);
		this.shooter = null;
	}

	/**
	 * @see #Entity.terminate()
	 */
	@Override
	@Raw
	public void terminate ()
	{
		this.unlinkFromShooter();
		super.terminate();
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return A representation of this object in String format.
	 */
	@Override
	public String toString ()
	{
		return "Bullet_" + hashCode() + super.toString();
	}

	@Override
	public int hashCode ()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + bounceCounter;
		result = prime * result + ( (shooter == null) ? 0 : shooter.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj)
	{
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		Bullet other = (Bullet) obj;
		if (bounceCounter != other.bounceCounter) return false;
		if (shooter == null)
		{
			if (other.shooter != null) return false;
		} else if (!shooter.equals(other.shooter)) return false;
		return true;
	}

	/**
	 * A variable registering the radius of a bullet.
	 */
	private static final double	bulletRadius			= 3;

	/**
	 * A variable registering the initial velocity of a bullet.
	 */
	private static final double	bulletInitialVelocity	= 250;

	/**
	 * A variable registering the density of a bullet.
	 */
	private static final double	density					= 7.8E10;	//TODO remove thisheb dit 100 keer kleiner gemaakt

	/**
	 * A variable registering the maximum amount of time a bullet can bounce off the boundaries of the world.
	 */
	public static final byte	maximumBorderBounces	= 1;
}
