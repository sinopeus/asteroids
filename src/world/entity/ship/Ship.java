package world.entity.ship;

import model.IShip;
import world.entity.Bullet;
import world.entity.Entity;
import world.physics.Mass;
import world.physics.Mechanics;
import world.physics.geometry.CircleShape;
import world.physics.vector.Acceleration;
import world.physics.vector.Direction;
import world.physics.vector.Force;
import world.physics.vector.Position;
import world.physics.vector.Velocity;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of ships involving its position, velocity, direction, speed limit and shape.
 *
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 *
 * @invar	The direction of this ship is a valid direction.
 * 			| canHaveAsDirection(getDirection())
 * @invar	The position of this ship is a valid position.
 * 			| canHaveAsPosition(getPosition())
 * @invar	The shape of this ship is a valid shape.
 * 			| canHaveAsShape(getShape())
 * @invar	The speedLimit of this ship is a valid speed limit.
 * 			| canHaveAsSpeedLimit(getSpeedLimit())
 * @invar	The velocity of this ship is a valid velocity.
 * 			| canHaveAsVelocity(getVelocity())
 */
public class Ship extends Entity implements IShip
{
	/**
	 * Initializes this new ship with a given direction, position, shape, mass, speed limit and velocity.
	 * 
	 * @param	direction
	 * 			The given direction.
	 * @param	position
	 * 			The given position.
	 * @param	shape
	 * 			The given shape.
	 * @param	speedLimit
	 * 			The given speed limit.
	 * @param	velocity
	 * 			The given velocity
	 * @param	shape
	 * 			The given circle shape
	 * @param	mass
	 * 			The given mass
	 * @effect	The direction of this ship is set to the given direction.
	 * 			| setDirection(direction)
	 * @effect	The position of this ship is set to the given position.
	 * 			| setPosition(position)
	 * @effect	The shape of this ship is set to the given shape.
	 * 			| setShape(shape)
	 * @effect	The speed limit of this ship is set to the given speed limit.
	 * 			| setSpeedLimit(speedLimit)
	 * @effect	The velocity of this ship is set to the given velocity.
	 * 			| setVelocity(velocity)
	 * @effect	The circle shape of this ship is set to the given circle shape.
	 * 			| this.shape = shape
	 * @effect	The mass of this ship is set to the given mass.
	 * 			| setMass(mass)
	 * @throws	IllegalArgumentException
	 * 			| The given shape is not a legal shape.
	 * @throws	NullPointerException
	 * 			| Any of the parameters is null.
	 */
	public Ship (Direction direction, Position position, double speedLimit, Velocity velocity, CircleShape shape, Mass mass) throws IllegalArgumentException, NullPointerException
	{
		//TODO add throws from setters
		super(direction, position, speedLimit, velocity, shape, mass);
		setThruster(new Thruster(getThrustPerSecond(), this));
	}

	/**
	 * Initializes this new ship with default values.
	 * 
	 * @effect	Initializes this new ship with the extended ship constructor and default values.
	 * 			| this(new Direction(),new Position(),Velocity.getSpeedOfLight(),new Velocity(), new CircleShape(40), new Mass(5E15))
	 */
	public Ship ()
	{
		//TODO add throws from setters
		this(new Direction(), new Position(), Velocity.getSpeedOfLight(), new Velocity(), new CircleShape(40), new Mass(5E15));
	}

	//TODO document
	@Override
	protected boolean canHaveAsShape (@Raw CircleShape shape)
	{
		return (super.canHaveAsShape(shape) && (shape.getRadius() >= Ship.getMinimumRadius()));
	}

	/**
	 * Returns the minimum radius of ships.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public static double getMinimumRadius ()
	{
		return Ship.minimumRadius;
	}

	/**
	 * A variable registering the minimum radius of this ship.
	 */
	private static double	minimumRadius	= 10;

	/**
	 * Gets the thruster of this ship.
	 */
	@Basic
	@Raw
	public Thruster getThruster ()
	{
		return thruster;
	}

	/**
	 * Checks whether this ship can have the given thruster as its thruster.
	 * 
	 * @param	thruster
	 * 			The given thruster
	 * @return	True if and only if the given thruster is not null.
	 * 			| result == (thruster != null)
	 */
	private boolean canHaveAsThruster (Thruster thruster)
	{
		return (thruster != null);
	}

	/**
	 * Sets the thrust of this ship to the given thruster.
	 * 
	 * @param	thruster
	 * 			The given thruster.
	 * @throws	IllegalArgumentException
	 * 			The given thruster is null.
	 * 			| thruster == null
	 */
	private void setThruster (Thruster thruster) throws IllegalArgumentException
	{
		if (!canHaveAsThruster(thruster)) { throw new IllegalArgumentException("Invalid thruster provided."); }
		this.thruster = thruster;
	}

	/**
	 * A variable referencing the thruster of this ship.
	 */
	private Thruster	thruster;

	/**
	 * Terminates this ship.
	 */
	@Override
	public void terminate ()
	{
		getThruster().terminate();
		super.terminate();
	}

	//TODO DOCUMENT & TEST
	@Override
	public void advance (double dt)
	{
		if (getThruster().isActivated())
		{
			getThruster().thrust(dt);
		}
		super.advance(dt);
	}

	/**
	 * A method for firing a bullet from this ship.
	 * 
	 * @effect Creates a new Bullet object in the World to which this Ship belongs.
	 */
	public void fire ()
	{
		Bullet b = new Bullet(this);
		getWorld().add(b);
		
		//RECOIL
		Velocity recoil = Mechanics.ConserVationOfMomentum_CalculateVelocity(b.getVelocity(), b.getMass(), getMass());
		this.setVelocity(new Velocity(getVelocity().getDifference(recoil)));//TODO something is wrong.
		System.out.println(getVelocity());
//		Acceleration a = Mechanics.Newtons_secondLaw_CalculateAcceleration(new Force(getDirection().getScaledBy(thrustPerSecond)),getMass());
//		getOwner().getVelocity().accelerateBy(a, duration);
	}

	@Override
	public String toString ()
	{
		return "Ship_" + hashCode() + super.toString();
	}

	public double getThrustPerSecond ()
	{
		return Ship.thrustPerSecond;
	}

	/**
	 * A variable registering the thrust that a ship's thruster can exert in one second.
	 */
	private static double	thrustPerSecond	= 1.1E18;
}
