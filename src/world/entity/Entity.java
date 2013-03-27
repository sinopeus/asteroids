package world.entity;

import world.World;
import world.physics.Mass;
import world.physics.geometry.Angle;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of entities involving a position, velocity, direction, speed limit.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 *
 * @invar	The direction of this entity is a valid direction.
 * 			| canHaveAsDirection(getDirection())
 * @invar	The position of this entity is a valid position.
 * 			| canHaveAsPosition(getPosition())
 * @invar	The speedLimit of this entity is a valid speed limit.
 * 			| canHaveAsSpeedLimit(getSpeedLimit())
 * @invar	The velocity of this entity is a valid velocity.
 * 			| canHaveAsVelocity(getVelocity())
 */
public class Entity
{
	/**
	 * Initializes this new entity with a given direction, position, shape, mass, speed limit and velocity.
	 * 
	 * @param	direction
	 * 			The given direction.
	 * @param	position
	 * 			The given position.
	 * @param	speedLimit
	 * 			The given speed limit.
	 * @param	velocity
	 * 			The given velocity
	 * @param	shape
	 * 			The given circle shape
	 * @param	mass
	 * 			The given mass
	 * @effect	The direction of this entity is set to the given direction.
	 * 			| setDirection(direction)
	 * @effect	The position of this entity is set to the given position.
	 * 			| setPosition(position)
	 * @effect	The speed limit of this entity is set to the given speed limit.
	 * 			| setSpeedLimit(speedLimit)
	 * @effect	The velocity of this entity is set to the given velocity.
	 * 			| setVelocity(velocity)
	 * @effect	The circle shape of this entity is set to the given circle shape.
	 * 			| this.shape = shape
	 * @effect	The mass of this entity is set to the given mass.
	 * 			| setMass(mass)
	 * @throws	NullPointerException
	 * 			| Any of the parameters is null.
	 */
	//TODO document
	public Entity (Direction direction, Position position, double speedLimit, Velocity velocity, CircleShape shape, Mass mass) throws NullPointerException
	{
		//TODO add throws from setters.
		setDirection(direction);
		setPosition(position);
		setSpeedLimit(speedLimit);
		setVelocity(velocity);
		if (!canHaveAsShape(shape))
		{
			throw new IllegalArgumentException("Invalid circle shape provided");
		} else
		{
			this.shape = shape;
		}
		setMass(mass);
		isTerminated = false;
	}

	/**
	 * Initializes this new entity with default values.
	 * 
	 * @effect	Initializes this new entity with the extended entity constructor and default values.
	 * 			| this(new Direction(), new Position(), Velocity.getSpeedOfLight(), new Velocity())
	 */
	public Entity ()//TODO document
	{
		//TODO add throws from setters.
		this(new Direction(), new Position(), Velocity.getSpeedOfLight(), new Velocity(), new CircleShape(40), new Mass(5E15));
	}

	/**
	 * Gets the position of this entity.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public Position getPosition ()
	{
		return this.position;
	}

	/**
	 * Checks whether this entity can have the given position as its position.
	 * 
	 * @param 	position
	 * 			The position to check.
	 * @return	True if and only if the given position is not null.
	 * 			| result == (position != null)
	 */
	@Basic
	@Raw
	protected boolean canHaveAsPosition (Position position)
	{
		return (position != null);
	}

	/**
	 * Sets the position of this entity to the given position.
	 *
	 * @param	position
	 *			The new position for this entity.
	 * @post	The position of this entity is now equal to the given position.
	 * 			| new.getPosition() == position
	 * @throws	IllegalArgumentException
	 * 			The this entity can't have the given position as its position,.
	 * 			| !canHaveAsPosition(position)
	 * @throws	IllegalStateException
	 * 			| isTerminated()
	 */
	@Basic
	@Raw
	public void setPosition (Position position) throws IllegalArgumentException, IllegalStateException
	{
		if (this.isTerminated()) { throw new IllegalStateException("This entity is terminated."); }
		if (!canHaveAsPosition(position))
		{
			throw new IllegalArgumentException("Invalid position provided.");
		} else
		{
			this.position = position;
		}
	}

	/**
	 * A variable referencing the position of this entity.
	 */
	protected Position	position;

	/**
	 * Gets the velocity of this entity.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public Velocity getVelocity ()
	{
		return this.velocity;
	}

	/**
	 * Checks whether this entity can have the given velocity as its velocity.
	 * 
	 * @param 	velocity
	 * 			The velocity to check.
	 * @return	True if and only if the given velocity is not null and its magnitude is at most the speed limit.
	 * 			| result = ((velocity != null) && (velocity.getVelocity() <= getSpeedLimit()))
	 */
	@Basic
	@Raw
	protected boolean canHaveAsVelocity (Velocity velocity)
	{
		return ( (velocity != null) && (velocity.get() <= getSpeedLimit()));
	}

	/**
	 * Sets the velocity of this entity to the given velocity.
	 *
	 * @param	velocity
	 *			The new velocity for this entity.
	 * @post	If this entity can have the given velocity as its velocity,
	 * 			then the velocity of this entity is now equal to the given velocity.
	 * 			| if canHaveAsVelocity(velocity)
	 * 			|	then new.getVelocity() == velocity
	 * @throws	IllegalStateException
	 * 			| isTerminated()
	 */
	@Basic
	@Raw
	public void setVelocity (Velocity velocity) throws IllegalStateException
	{
		if (this.isTerminated()) { throw new IllegalStateException("This entity is terminated."); }
		if (canHaveAsVelocity(velocity))
		{
			this.velocity = velocity;
		}
	}

	/**
	 * A variable referencing the velocity of this entity.
	 */
	protected Velocity	velocity;

	/**
	 * Gets the direction of this entity.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public Direction getDirection ()
	{
		return this.direction;
	}

	/**
	 * Checks whether this entity can have the given direction as its direction.
	 * 
	 * @param 	direction
	 * 			The direction to check.
	 * @return	True if and only if the direction is not null.
	 * 			| result == (direction != null)
	 */
	@Basic
	@Raw
	protected boolean canHaveAsDirection (Direction direction)
	{
		return (direction != null);
	}

	/**
	 * Sets the direction of this entity to the given direction.
	 *
	 * @param	direction
	 *			The new direction for this entity.
	 * @pre		The given direction must be a valid direction.
	 * 			| canHaveAsDirection(direction)
	 * @post	The direction of this entity is now equal to the given direction.
	 * 			| new.getDirection() == direction
	 * @throws	IllegalStateException
	 * 			| isTerminated()
	 */
	@Basic
	@Raw
	public void setDirection (Direction direction) throws IllegalStateException
	{
		if (this.isTerminated()) { throw new IllegalStateException("This entity is terminated."); }
		assert (canHaveAsDirection(direction));
		this.direction = direction;
		assert (getDirection().equals(direction));
	}

	/**
	 * A variable referencing the direction of this entity.
	 */
	protected Direction	direction;

	/**
	 * Gets a shape equal to the shape of this ship.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public CircleShape getShape ()
	{
		return new CircleShape(shape.getRadius());
	}

	/**
	 * Checks whether this ship can have the given shape as its shape.
	 * 
	 * @param 	shape
	 * 			The shape to check.
	 * @return	True if and only if the given shape is not null and has a range of at least the minimum radius for ships.
	 * 			| result = (shape != null)
	 */
	@Basic
	@Raw
	protected boolean canHaveAsShape (@Raw CircleShape shape)
	{
		return (shape != null);
	}

	/**
	 * A variable referencing the shape of this ship.
	 */
	private final CircleShape	shape;

	/**
	 * Returns the speed limit of this entity.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public double getSpeedLimit ()
	{
		return this.speedLimit;
	}

	/**
	 * Checks whether this entity can have the given speed limit as its speed limit.
	 * 
	 * @param 	speedLimit
	 * 			The speed limit to check.
	 * @return	True if and only if the given speed limit is positive and at most the speed of light.
	 * 			| result = ((speedLimit >= 0) && (speedLimit <= Velocity.getSpeedOfLight()))
	 */
	@Basic
	@Raw
	protected boolean canHaveAsSpeedLimit (double speedLimit)
	{
		return ( (speedLimit >= 0) && (speedLimit <= Velocity.getSpeedOfLight()));
	}

	/**
	 * Sets the speed limit of this entity to the given speed limit.
	 *
	 * @param	speedLimit
	 *			The new speed limit for this entity.
	 * @post	If this entity can have the given speed limit as its speed limit,
	 * 			then the speed limit of this entity is now equal to the given speed limit.
	 * 			| if canHaveAsSpeedLimit(speedLimit)
	 * 			|	then new.getSpeedLimit() == speedLimit
	 * @throws	IllegalStateException
	 * 			| isTerminated()
	 */
	@Basic
	@Raw
	public void setSpeedLimit (double speedLimit) throws IllegalStateException
	{
		if (this.isTerminated()) { throw new IllegalStateException("This entity is terminated."); }
		if (canHaveAsSpeedLimit(speedLimit))
		{
			this.speedLimit = speedLimit;
		} else
		{
			this.speedLimit = Velocity.getSpeedOfLight();
		}
	}

	/**
	 * A variable registering the speed limit of this entity.
	 */
	protected double	speedLimit;

	/**
	 * Gets the mass of this ship.
	 */
	@Basic
	@Raw
	public Mass getMass ()
	{
		return mass;
	}

	/**
	 * Checks whether this entity can have the given mass as its mass.
	 * 
	 * @param	mass
	 * 			The given mass
	 * @return	True if and only if the given mass is not null
	 */
	@Basic
	@Raw
	private boolean canHaveAsMass (Mass mass)
	{
		return mass != null;
	}

	//TODO TESTERS
	/**
	 * Sets the mass of this entity to the given mass.
	 * @param	mass
	 * 			The given mass.
	 * @post	The mass of this ship is now equal to the given mass.
	 * 			| new.getMass() == mass
	 * @throws	IllegalArgumentException
	 * 			The given mass is not a valid mass
	 * 			| !canHaveAsMass(mass)
	 */
	@Basic
	@Raw
	private void setMass (Mass mass) throws IllegalArgumentException
	{
		if (!canHaveAsMass(mass)) { throw new IllegalArgumentException("Invalid mass provided"); }
		this.mass = mass;
	}

	/**
	 * A variable referencing the mass of this ship.
	 */
	private Mass	mass;

	/**
	 * Returns the world of this entity.
	 */
	@Basic
	@Raw
	public World getWorld ()
	{
		return this.world;
	}

	/**
	 * Checks whether this entity can have the given world as its world.
	 * 
	 * @param 	world
	 * 			The world to check.
	 * @return	True if and only if the given world is not null.
	 * 			| result = (world != null)
	 */
	@Basic
	@Raw
	private boolean canHaveAsWorld (World world)
	{
		return (world != null);
	}

	/**
	 * Sets the world of this entity to the given world.
	 *
	 * @param 	world
	 *        	The new world for this entity.
	 * @throws	IllegalArgumentException
	 * 			The given world is not a valid world
	 * 			| !canHaveAsWorld(world)
	 */
	@Basic
	@Raw
	public void setWorld (World world) throws IllegalArgumentException
	{
		if (!canHaveAsWorld(world)) { throw new IllegalArgumentException("Illegal world provided."); }
		this.world = world;
	}

	protected World	world;

	/**
	 * Checks whether this entity is terminated.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public boolean isTerminated ()
	{
		return this.isTerminated;
	}

	/**
	 * Terminates this entity.
	 * 
	 * @effect	Removes this entity from the world.
	 * 			| getWorld().remove(this);
	 * @post	Makes the reference to the world null.
	 * 			| new.getWorld() == null
	 * @post	This entity is now terminated.
	 * 			| isTerminated()
	 */
	public void terminate ()
	{
		getWorld().remove(this);
		this.world = null;
		isTerminated = true;
	}

	/**
	 * A variable registering whether this entity is terminated.
	 */
	protected boolean	isTerminated;

	//TODO DOCUMENT & TEST
	public void advance (double dt)
	{
		move(dt);
	}

	/**
	 * Changes the position of this entity based on the current position, velocity and a given duration of the movement.
	 * 
	 * @param	duration
	 * 			The given duration of the movement.
	 * @effect	Sets the position of this entity to the position after moving.
	 * 			| getPosittion().moveBy(getVelocity(), duration)
	 * @throws	ArithmeticException
	 * 			Any of the resulting vector components is not a number.
	 * 			| ((Double.isNaN(getPosition.getXComponent()) || Double.isNaN(getPosition.getYComponent()))
	 * @throws	IllegalArgumentException
	 * 			The given duration is strictly negative.
	 * 			| duration <= 0
	 * @throws	IllegalStateException
	 * 			| isTerminated()	
	 */
	public void move (double duration) throws ArithmeticException, IllegalArgumentException, IllegalStateException
	{
		if (this.isTerminated()) { throw new IllegalStateException("This entity is terminated."); }
		if (duration < 0)
		{
			duration = 0;
		}
		this.position.moveBy(getVelocity(), duration);
	}

	/**
	 * Changes the direction of this entity based on the current angle and a given angle.
	 * 
	 * @param	angle
	 * 			The given angle
	 * @pre		The given angle is not null.
	 * 			| angle != null
	 * @effect	The angle of this entity is modified by the given angle.
	 * 			| this.direction.rotate(angle);
	 * @throws	IllegalStateException
	 * 			| isTerminated()
	 */
	public void turn (Angle angle) throws IllegalStateException
	{
		if (this.isTerminated()) { throw new IllegalStateException("This entity is terminated."); }
		assert (angle != null);
		this.direction.rotate(angle);
	}

	public String toString ()
	{
		return " at " + getPosition() + "   with velocity " + getVelocity() + "   and shape " + getShape() + "  ";
	};
}
