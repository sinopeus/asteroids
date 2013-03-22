package entity.ship;

import entity.CircleShape;
import entity.Entity;
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
	 */ //TODO document
	public Ship(Direction direction, Position position, double speedLimit, Velocity velocity,CircleShape shape) throws IllegalArgumentException, NullPointerException
	{
		//TODO add throws from setters
		super(direction, position, speedLimit, velocity,shape);
	  //TODO add thruster construction	
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
		this(new Direction(), new Position(), Velocity.getSpeedOfLight(), new Velocity(),new CircleShape(getMinimumRadius()));
	}
	
	//TODO document
	@Override
	protected boolean canHaveAsShape(@Raw CircleShape shape)
	{
		return (super.canHaveAsShape(shape) && (shape.getRadius() >= Ship.getMinimumRadius()));
	}

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

  private final Thruster thruster;
}
