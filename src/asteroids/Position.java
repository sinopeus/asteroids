package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class implementing simple positional information in two dimensions for
 * objects in the game.
 * 
 * @author Syd & Xavier
 * @version 0.0
 */
public class Position extends Vector
{

	/**
	 * Sets the x component of this position to the given x component.
	 * 
	 * @param   x
	 *          The new x-component for this velocity.
	 * @post    If this velocity can have the given x-component as its x-component,
	 *          then the x-component of this velocity is now equal to the given
	 *          x-component. | if canHaveAsXComponent(vx) | then
	 *          | new.getXComponent() == vx
	 * @throws	IllegalArgumentException
	 * 			The given x-component is not a valid component.
	 * 			| !canHaveAsComponent(x)
	 */
	@Basic
	@Raw
	public void setXComponent(double x)
	{
		if (!canHaveAsComponent(x))
		{
			throw new IllegalArgumentException("Invalid vector component provided");
		} else
		{
			this.x = x;
		}
	}

	/**
	 * Sets the y component of this position to the given y component.
	 * 
	 * @param   y
	 *          The new y-component for this velocity.
	 * @post    If this velocity can have the given y-component as its y-component,
	 *          then the y-component of this velocity is now equal to the given
	 *          y-component. | if canHaveAsYComponent(vy) | then
	 *          | new.getYComponent() == vy
	 * @throws	IllegalArgumentException
	 * 			The given y-component is not a valid component.
	 * 			| !canHaveAsComponent(y)
	 */
	@Basic
	@Raw
	public void setYComponent(double y)
	{
		if (!canHaveAsComponent(y))
		{
			throw new IllegalArgumentException("Invalid vector component provided");
		} else
		{
			this.y = y;
		}
	}

	/**
	 * Initializes this new Position with a given x-coordinate and y-coordinate.
	 * 
	 * @param   x
	 *          The given x coordinate
	 * @param   y
	 *          The given y coordinate
	 * @effect	Initializes this new position with the extended constructor of vector.
	 * 			| super(x, y)
	 */
	public Position(double x, double y)
	{
		super(x, y);
	}

	/**
	 * Initialize this new position with a given vector
	 * 
	 * @param	v
	 * 			The given vector.
	 * @effect	Initializes this new position with the by-vector constructor of vector.
	 * 			| super(v)
	 * @throws	NullPointerException
	 * 			The given vector is null
	 * 			| v == null
	 */
	public Position(Vector v) throws NullPointerException
	{
		super(v);
	}

	/**
	 * Initializes this new position with default values.
	 */
	public Position()
	{
		super();
	}

	/**
	 * Gets the sum of this Position and the given vector.
	 * 
	 * @param	v
	 * 			The given vector.
	 * @return	The sum of this position and the given vector.
	 * 			| result = new Position(super.getSum(v))
	 * @throws	IllegalArgumentException
	 * 			The given vector is null
	 * 			| v == null
	 * @throws	ArithmeticException
	 * 			Any of the resulting components is not a number
	 * 			| ((Double.isNaN(getXComponent()) || (Double.isNaN(getYComponent())) 
	 */
	@Override
	public Position getSum(Vector v) throws ArithmeticException, IllegalArgumentException
	{
		if (v == null)
		{
			throw new IllegalArgumentException("Invalid vector provided.");
		}
		return new Position(super.getSum(v));
	}

	/**
	 * Moves this position using a given velocity during a given duration.
	 * 
	 * @param	v
	 * 			The given velocity.
	 * @param	duration
	 * 			The given duration.
	 * @throws	IllegalArgumentException
	 * 			The given vector is null or duration is strictly negative.
	 * 			| ((v == null) || (duration < 0))
	 * @throws	ArithmeticException
	 * 			Any of the resulting components is not a valid component.
	 * 			| ((Double.isNaN(getXComponent()) || (Double.isNaN(getYComponent()))
	 * @see		#canHaveAsComponent(double)
	 * @post	Moves this position to the calculated destination.
	 * 			| new.equals(getSum(v.scaleBy(duration)))
	 */
	public void moveBy(Velocity v, double duration) throws ArithmeticException, IllegalArgumentException
	{
		if ((v == null) || (duration < 0))
		{
			throw new IllegalArgumentException("Invalid parameter provided.");
		}
		Position p = getSum(v.scaleBy(duration));
		setXComponent(p.getXComponent());
		setYComponent(p.getYComponent());
	}
}
