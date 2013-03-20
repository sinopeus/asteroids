package entity;

import vector.Acceleration;
import vector.Direction;
import vector.Position;
import vector.Vector;
import vector.Velocity;
import model.IShip;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of ships involving its position, velocity, direction, speed limit and shape.
 *
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 1.0
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
	 * Initializes this new ship with a given direction, position, shape, speedLimit and velocity.
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
	 * @throws	IllegalArgumentException
	 * 			| The given shape is not a legal shape.
	 * @throws	NullPointerException
	 * 			| Any of the parameters is null.
	 */
	public Ship(Direction direction, Position position, CircleShape shape, double speedLimit, Velocity velocity) throws IllegalArgumentException, NullPointerException
	{
		//TODO add throws from setters
		super(direction, position, speedLimit, velocity);
		if (!canHaveAsShape(shape))
		{
			throw new IllegalArgumentException("Invalid circle shape provided");
		} else
		{
			this.shape = shape;
		}
	}

	/**
	 * Initializes this new ship with default values.
	 * 
	 * @effect	Initializes this new ship with the extended ship constructor and default values.
	 * 			| this(new Direction(),new Position(),new CircleShape(Ship.getMinimumRadius()),Velocity.getSpeedOfLight(),new Velocity())
	 */
	public Ship()
	{
		//TODO add throws from setters
		this(new Direction(), new Position(), new CircleShape(Ship.getMinimumRadius()), Velocity.getSpeedOfLight(), new Velocity());
	}

	/**
	 * Gets a shape equal to the shape of this ship.
	 */
	@SuppressWarnings("javadoc")
	@Basic
	@Raw
	public CircleShape getShape()
	{
		return new CircleShape(shape.getRadius());
	}

	/**
	 * Checks whether this ship can have the given shape as its shape.
	 * 
	 * @param 	shape
	 * 			The shape to check.
	 * @return	True if and only if the given shape is not null and has a range of at least the minimum radius for ships.
	 * 			| result = ((shape != null) && (shape.getRadius() >= Ship.getMinimumRadius()))
	 */
	@Basic
	@Raw
	protected boolean canHaveAsShape(@Raw CircleShape shape)
	{
		return ((shape != null) && (shape.getRadius() >= Ship.getMinimumRadius()));
	}

	/**
	 * A variable referencing the shape of this ship.
	 */
	private final CircleShape shape;

	/**
	 * Returns the minimum radius of ships.
	 */
	@SuppressWarnings("javadoc")
	@Basic
	@Raw
	public static double getMinimumRadius()
	{
		return Ship.minimumRadius;
	}

	/**
	 * A variable registering the minimum radius of this ship.
	 */
	private static double minimumRadius = 10;

	/**
	 * Changes the velocity of this ship based on the current velocity, and a given acceleration.
	 * 
	 * @param 	acceleration
	 * 			The given acceleration
	 * @effect	The velocity is modified by the given acceleration
	 * 			| this.velocity.accelerateBy(a, 1)
	 * @throws	IllegalStateException
	 * 			This ship is terminated
	 * 			| isTerminated()
	 */
	public void thrust(double acceleration) throws IllegalStateException
	{
		if (this.isTerminated())
		{
			throw new IllegalStateException("This ship is terminated.");
		}
		if (acceleration < 0)
		{
			acceleration = 0;
		}
		Acceleration a = new Acceleration(getDirection().getScaledBy(acceleration));
		this.velocity.accelerateBy(a, 1);
		if (getVelocity().getMagnitude() > this.getSpeedLimit())
		{
			Vector v = getVelocity().getScaledBy(this.getSpeedLimit() / getVelocity().getMagnitude());
			this.velocity.setXComponent(v.getXComponent());
			this.velocity.setYComponent(v.getYComponent());
		}
	}
}
