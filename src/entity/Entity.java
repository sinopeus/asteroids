package entity;

import vector.Direction;
import vector.Position;
import vector.Velocity;
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
 * @invar	The shape of this entity is a valid shape.
 * 			| canHaveAsShape(getShape())
 * @invar	The speedLimit of this entity is a valid speed limit.
 * 			| canHaveAsSpeedLimit(getSpeedLimit())
 * @invar	The velocity of this entity is a valid velocity.
 * 			| canHaveAsVelocity(getVelocity())
 */
public class Entity
{
	/**
	 * Gets a position equal to the position of this entity.
	 */
	@SuppressWarnings("javadoc")
	@Basic
	@Raw
	public Position getPosition()
	{
		return new Position(this.position);
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
	protected boolean canHaveAsPosition(Position position)
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
	public void setPosition(Position position) throws IllegalArgumentException, IllegalStateException
	{
		if (this.isTerminated())
		{
			throw new IllegalStateException("This entity is terminated.");
		}
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
	protected Position position;

	/**
	 * Gets a velocity equal to the velocity of this entity.
	 */
	@SuppressWarnings("javadoc")
	@Basic
	@Raw
	public Velocity getVelocity()
	{
		return new Velocity(this.velocity);
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
	protected boolean canHaveAsVelocity(Velocity velocity)
	{
		return ((velocity != null) && (velocity.getVelocity() <= getSpeedLimit()));
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
	public void setVelocity(Velocity velocity) throws IllegalStateException
	{
		if (this.isTerminated())
		{
			throw new IllegalStateException("This entity is terminated.");
		}
		if (canHaveAsVelocity(velocity))
		{
			this.velocity = velocity;
		}
	}

	/**
	 * A variable referencing the velocity of this entity.
	 */
	protected Velocity velocity;

	/**
	 * Gets a direction equal to the direction of this entity.
	 */
	@SuppressWarnings("javadoc")
	@Basic
	@Raw
	public Direction getDirection()
	{
		return new Direction(this.direction.getAngle());
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
	protected boolean canHaveAsDirection(Direction direction)
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
	public void setDirection(Direction direction) throws IllegalStateException
	{
		if (this.isTerminated())
		{
			throw new IllegalStateException("This entity is terminated.");
		}
		assert (canHaveAsDirection(direction));
		this.direction = direction;
		assert (getDirection().equals(direction));
	}

	/**
	 * A variable referencing the direction of this entity.
	 */
	protected Direction direction;

	/**
	 * Returns the speed limit of this entity.
	 */
	@SuppressWarnings("javadoc")
	@Basic
	@Raw
	public double getSpeedLimit()
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
	protected boolean canHaveAsSpeedLimit(double speedLimit)
	{
		return ((speedLimit >= 0) && (speedLimit <= Velocity.getSpeedOfLight()));
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
	public void setSpeedLimit(double speedLimit) throws IllegalStateException
	{
		if (this.isTerminated())
		{
			throw new IllegalStateException("This entity is terminated.");
		}
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
	protected double speedLimit;

	/**
	 * Checks whether this entity is terminated.
	 */
	@SuppressWarnings("javadoc")
	@Basic
	@Raw
	public boolean isTerminated()
	{
		return this.isTerminated;
	}

	/**
	 * Terminates this entity.
	 */
	public void terminate()
	{
		isTerminated = true;
	}

	/**
	 * A variable registering whether this entity is terminated.
	 */
	protected boolean isTerminated;

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
	public void move(double duration) throws ArithmeticException, IllegalArgumentException, IllegalStateException
	{
		if (this.isTerminated())
		{
			throw new IllegalStateException("This entity is terminated.");
		}
		if (duration < 0)
		{
			throw new IllegalArgumentException("The given duration is invalid.");
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
	public void turn(Angle angle) throws IllegalStateException
	{
		if (this.isTerminated())
		{
			throw new IllegalStateException("This entity is terminated.");
		}
		assert (angle != null);
		this.direction.rotate(angle);
	}
}