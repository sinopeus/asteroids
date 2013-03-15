package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @author Syd & Xavier
 * @version 0.0
 * 
 * @Invar	The direction of this ship is a valid direction.
 * 			| canHaveAsDirection(getDirection())
 * @Invar	The position of this ship is a valid position.
 * 			| canHaveAsPosition(getPosition())
 * @Invar	The shape of this ship is a valid shape.
 * 			| canHaveAsShape(getShape())
 * @Invar	The speedLimit of this ship is a valid speed limit.
 * 			| canHaveAsSpeedLimit(getSpeedLimit())
 * @Invar	The velocity of this ship is a valid velocity.
 * 			| canHaveAsVelocity(getVelocity())
 */
public class Ship implements IShip
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
	 * @Effect	The direction of this ship is set to the given direction.
	 * 			| setDirection(direction)
	 * @Effect	The position of this ship is set to the given position.
	 * 			| setPosition(position)
	 * @Effect	The shape of this ship is set to the given shape.
	 * 			| setShape(shape)
	 * @Effect	The speed limit of this ship is set to the given speed limit.
	 * 			| setSpeedLimit(speedLimit)
	 * @Effect	The velocity of this ship is set to the given velocity.
	 * 			| setVelocity(velocity)
	 * @throws	IllegalArgumentException
	 * 			| The given shape is not a legal shape.
	 * @throws	NullPointerException
	 * 			| Any of the parameters is null.
	 */
	@Raw
	public Ship(Direction direction, Position position, CircleShape shape, double speedLimit, Velocity velocity) throws IllegalArgumentException, NullPointerException
	{
		setDirection(direction);
		setPosition(position);
		if (!canHaveAsShape(shape))
		{
			throw new IllegalArgumentException("Invalid circle shape provided");
		} else
		{
			this.shape = shape;
		}
		setSpeedLimit(speedLimit);
		setVelocity(velocity);
		isTerminated = false;
	}

	/**
	 * Initializes this new ship.
	 * 
	 * @Effect	Uses the extended constructor to initialize this new ship with default values.
	 * 			| this(new Direction(),new Position(),new CircleShape(Ship.getMinimumRadius()),Velocity.getSpeedOfLight(),new Velocity())
	 */
	public Ship()
	{
		this(new Direction(), new Position(), new CircleShape(Ship.getMinimumRadius()), Velocity.getSpeedOfLight(), new Velocity());
	}

	/**
	 * Returns the position of this ship.
	 */
	@Basic
	@Raw
	public Position getPosition()
	{
		return this.position;
	}

	/**
	 * Checks whether this ship can have the given position as its position.
	 * 
	 * @param 	position
	 * 			The position to check.
	 * @return	True if and only if the given position is not null.
	 * 			| result == (position != null)
	 */
	@Basic
	@Raw
	public boolean canHaveAsPosition(Position position)
	{
		return (position != null);
	}

	/**
	 * Sets the position of this ship to the given position.
	 *
	 * @param	position
	 *			The new position for this ship.
	 * @post	The position of this ship is now equal to the given position.
	 * 			| new.getPosition() == position
	 * @throws	IllegalArgumentException
	 * 			The this ship can't have the given position as its position,.
	 * 			| !canHaveAsPosition(position)
	 */
	@Basic
	@Raw
	public void setPosition(Position position) throws IllegalArgumentException
	{
		if (!canHaveAsPosition(position))
		{
			throw new IllegalArgumentException("Invalid position provided.");
		} else
		{
			this.position = position;
		}
	}

	/**
	 * A variable referencing the position of this ship.
	 */
	private Position position;

	/**
	 * Returns the velocity of this ship.
	 */
	@Basic
	@Raw
	public Velocity getVelocity()
	{
		return this.velocity;
	}

	/**
	 * Checks whether this ship can have the given velocity as its velocity.
	 * 
	 * @param 	velocity
	 * 			The velocity to check.
	 * @return	True if and only if the given velocity is not null and its magnitude is at most the speed limit.
	 * 			| result = ((velocity != null) && (velocity.getVelocity() <= getSpeedLimit()))
	 */
	@Basic
	@Raw
	public boolean canHaveAsVelocity(Velocity velocity)
	{
		return ((velocity != null) && (velocity.getVelocity() <= getSpeedLimit()));
	}

	/**
	 * Sets the velocity of this ship to the given velocity.
	 *
	 * @param	velocity
	 *			The new velocity for this ship.
	 * @post	If this ship can have the given velocity as its velocity,
	 * 			then the velocity of this ship is now equal to the given velocity.
	 * 			| if canHaveAsVelocity(velocity)
	 * 			|	then new.getVelocity() == velocity
	 */
	@Basic
	@Raw
	public void setVelocity(Velocity velocity)
	{
		if (canHaveAsVelocity(velocity))
		{
			this.velocity = velocity;
		}
	}

	/**
	 * A variable referencing the velocity of this ship.
	 */
	private Velocity velocity;

	/**
	 * Returns the speed limit of this ship.
	 */
	@Basic
	@Raw
	public double getSpeedLimit()
	{
		return this.speedLimit;
	}

	/**
	 * Checks whether this ship can have the given speed limit as its speed limit.
	 * 
	 * @param 	speedLimit
	 * 			The speed limit to check.
	 * @return	True if and only if the given speed limit is positive and at most the speed of light.
	 * 			| result = ((speedLimit >= 0) && (speedLimit <= Velocity.getSpeedOfLight()))
	 */
	@Basic
	@Raw
	public boolean canHaveAsSpeedLimit(double speedLimit)
	{
		return ((speedLimit >= 0) && (speedLimit <= Velocity.getSpeedOfLight()));
	}

	/**
	 * Sets the speed limit of this ship to the given speed limit.
	 *
	 * @param	speedLimit
	 *			The new speed limit for this ship.
	 * @post	If this ship can have the given speed limit as its speed limit,
	 * 			then the speed limit of this ship is now equal to the given speed limit.
	 * 			| if canHaveAsSpeedLimit(speedLimit)
	 * 			|	then new.getSpeedLimit() == speedLimit
	 */
	@Basic
	@Raw
	public void setSpeedLimit(double speedLimit)
	{
		if (canHaveAsSpeedLimit(speedLimit))
		{
			this.speedLimit = speedLimit;
		} else
		{
			this.speedLimit = Velocity.getSpeedOfLight();
		}
	}

	/**
	 * A variable registering the speed limit of this ship.
	 */
	private double speedLimit;

	/**
	 * Returns the direction of this ship.
	 */
	@Basic
	@Raw
	public Direction getDirection()
	{
		return this.direction;
	}

	/**
	 * Checks whether this ship can have the given direction as its direction.
	 * 
	 * @param 	direction
	 * 			The direction to check.
	 * @return	True if and only if the direction is not null.
	 * 			| result == (direction != null)
	 */
	@Basic
	@Raw
	public boolean canHaveAsDirection(Direction direction)
	{
		return (direction != null);
	}

	/**
	 * Sets the direction of this ship to the given direction.
	 *
	 * @param	direction
	 *			The new direction for this ship.
	 * @pre		The given direction must be a valid direction.
	 * 			| canHaveAsDirection(direction)
	 * @post	The direction of this ship is now equal to the given direction.
	 * 			| new.getDirection() == direction
	 */
	@Basic
	@Raw
	public void setDirection(Direction direction)
	{
		assert (canHaveAsDirection(direction));
		this.direction = direction;
		assert (getDirection().equals(direction));
	}

	/**
	 * A variable referencing the direction of this ship.
	 */
	private Direction direction;

	/**
	 * Returns the shape of this ship.
	 */
	@Basic
	@Raw
	public CircleShape getShape()
	{
		return this.shape;
	}

	/**
	 * Checks whether this ship can have the given shape as its shape.
	 * 
	 * @param 	shape
	 * 			The shape to check.
	 * @return	True if and only if the given shapeis not null and has a range of at least the minimum radius for ships.
	 * 			| result = ((shape != null) && (shape.getRadius() >= Ship.getMinimumRadius()))
	 */
	@Basic
	@Raw
	public boolean canHaveAsShape(CircleShape shape)
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
	 * Checks whether this ship is terminated.
	 */
	@Basic
	@Raw
	public boolean isTerminated()
	{
		return this.isTerminated;
	}

	/**
	 * Terminates this ship.
	 */
	public void terminate()
	{
		isTerminated = true;
	}

	/**
	 * A variable registering whether this ship is terminated.
	 */
	private boolean isTerminated;

	/**
	 * Changes the position of this ship based on the current position, velocity and a given duration of the movement.
	 * 
	 * @param	duration
	 * 			The given duration of the movement.
	 * @effect	Sets the position of this ship the position after moving.
	 * 			| getPosittion().moveBy(getVelocity(), duration)
	 * @throws	ArithmeticException
	 * 			Any of the resulting vector components is not a number.
	 * 			| ((Double.isNaN(getPosition.getXComponent()) || Double.isNaN(getPosition.getYComponent()))
	 * @throws	IllegalArgumentException
	 * 			The given duration is strictly negative.
	 * 			| duration <= 0
	 * 			
	 */
	public void move(double duration) throws ArithmeticException, IllegalArgumentException
	{
		if (duration < 0)
		{
			throw new IllegalArgumentException("The given duration is invalid.");
		}
		getPosition().moveBy(getVelocity(), duration);
	}

	/**
	 * Changes the direction of this ship based on the current angle and a given angle.
	 * 
	 * @param	angle
	 * 			The given angle
	 * @Pre		The given angle is not null.
	 * 			| angle != null
	 */
	public void turn(Angle angle)
	{
		assert (angle != null);
		getDirection().rotate(angle);
	}

	/**
	 * Changes the velocity of this ship based on the current velocity, and a given acceleration.
	 * 
	 * @param 	acceleration
	 * 			The given acceleration
	 */
	public void thrust(double acceleration)
	{
		if (acceleration < 0)
		{
			acceleration = 0;
		}
		Acceleration a = new Acceleration(getDirection().scaleBy(acceleration));
		getVelocity().accelerateBy(a, 1);
		if (getVelocity().getMagnitude() > this.getSpeedLimit())
		{
			Vector v = getVelocity().scaleBy(this.getSpeedLimit() / getVelocity().getMagnitude());
			getVelocity().setXComponent(v.getXComponent());
			getVelocity().setYComponent(v.getYComponent());
		}
	}
}
