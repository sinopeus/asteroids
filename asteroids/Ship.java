package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @author Syd & Xavier
 * 
 */
public class Ship implements IShip
{

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
	 * @return	True if and only if the given position is effective.
	 * 			| result = (position != null)
	 */
	@Basic
	@Raw
	public boolean canHaveAsPosition(Position position)
	{
		return (position != null);//FIXME
	}

	/**
	 * Sets the position of this ship to the given position.
	 *
	 * @param	position
	 *			The new position for this ship.
	 * @post	If this ship can have the given position as its position,
	 * 			then the position of this ship is now equal to the given position.
	 * 			| if canHaveAsPosition(position)
	 * 			|	then new.getPosition() == position
	 * @Throws	IllegalArgumentException
	 * 			The this ship can't have the given position as its position,.
	 * 			| !canHaveAsPosition(position)
	 */
	@Basic
	@Raw
	public void setPosition(Position position) throws IllegalArgumentException
	{
		if (!canHaveAsPosition(position))
		{
			throw new IllegalArgumentException();
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
	 * @return	True if and only if the given velocity is effective.
	 * 			| result = (velocity != null)
	 */
	@Basic
	@Raw
	public boolean canHaveAsVelocity(Velocity velocity)
	{
		return (velocity != null);//FIXME
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
	 * @return	True if and only if the given direction is effective.
	 * 			| result = (direction != null)
	 */
	@Basic
	@Raw
	public boolean canHaveAsDirection(Direction direction)
	{
		return (direction != null);//FIXME
	}

	/**
	 * Sets the direction of this ship to the given direction.
	 *
	 * @param	direction
	 *			The new direction for this ship.
	 * @pre		The given direction must be effective
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
		assert (getDirection() == direction);
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
	 * @return	True if and only if the given shape is effective.
	 * 			| result = (shape != null)
	 */
	@Basic
	@Raw
	public boolean canHaveAsShape(CircleShape shape)
	{
		return (shape != null);//FIXME
	}

	/**
	 * Sets the shape of this ship to the given shape.
	 *
	 * @param	shape
	 *			The new shape for this ship.
	 * @post	If this ship can have the given shape as its shape,
	 * 			then the shape of this ship is now equal to the given shape.
	 * 			| if canHaveAsShape(shape)
	 * 			|	then new.getShape() == shape
	 * @Throws	IllegalArgumentException
	 * 			The this ship can't have the given shape as its shape,.
	 * 			| !canHaveAsShapeposition)
	 */
	@Basic
	@Raw
	public void setShape(CircleShape shape) throws IllegalArgumentException
	{
		if (canHaveAsShape(shape))
		{
			throw new IllegalArgumentException();
		} else
		{
			this.shape = shape;
		}
	}

	/**
	 * A variable referencing the shape of this ship.
	 */
	private CircleShape shape;

}
