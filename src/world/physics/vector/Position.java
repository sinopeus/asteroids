package world.physics.vector;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of positions vectors.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 */
public class Position extends Vector
{
	/**
	 * Initializes this new Position with a given x-coordinate and y-coordinate.
	 * 
	 * @param   x
	 *          The given x coordinate
	 * @param   y
	 *          The given y coordinate
	 * @effect	Initializes this new position with the extended constructor of vector.
	 * 			| Vector(x, y)
	 */
	public Position (double x, double y)
	{
		super(x, y);
	}

	/**
	 * Initialize this new position with the components of the given vector.
	 * 
	 * @param	v
	 * 			The given vector.
	 * @effect	Initializes this new position with the by-vector constructor of vector.
	 * 			| Vector(v)
	 * @throws	IllegalArgumentException
	 * 			The given vector is null
	 * 			| v == null
	 */
	public Position (Vector v) throws IllegalArgumentException
	{
		super(v);
	}

	/**
	 * Initializes this new position with default components.
	 * 
	 * @effect	Initializes this new position with the simple constructor of vector.
	 * 			| Vector()
	 */
	public Position ()
	{
		super();
	}

	/**
	 * Sets the x component of this position to the given x component.
	 * 
	 * @param   x
	 *          The new x-component for this velocity.
	 * @post    The x-component of this velocity is now equal to the given y component
	 *          | new.getXComponent() == x
	 * @throws	IllegalArgumentException
	 * 			The given x-component is not a valid component.
	 * 			| !canHaveAsComponent(x)
	 */
	@Basic
	@Raw
	public void setX (double x)
	{
		if (!canHaveAsComponent(x)) throw new IllegalArgumentException("Invalid vector component provided");
		this.components[0] = x;
	}

	/**
	 * Sets the y component of this position to the given y component.
	 * 
	 * @param   y
	 *          The new y-component for this velocity.
	 * @post    The y-component of this velocity is now equal to the given y component
	 *          | new.getXComponent() == y
	 * @throws	IllegalArgumentException
	 * 			The given y-component is not a valid component.
	 * 			| !canHaveAsComponent(y)
	 */
	@Basic
	@Raw
	public void setY (double y)
	{
		if (!canHaveAsComponent(y)) throw new IllegalArgumentException("Invalid vector component provided");
		this.components[1] = y;
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
	 * 			One of the resulting components is not a number
	 * 			| ((Double.isNaN(getXComponent()) || (Double.isNaN(getYComponent())) 
	 */
	@Override
	public Position getSum (Vector v) throws ArithmeticException, IllegalArgumentException
	{
		if (v == null) { throw new IllegalArgumentException("Invalid vector provided."); }
		return new Position(super.getSum(v));
	}

	/**
	 * Moves this position using a given velocity during a given duration.
	 * 
	 * @param	v
	 * 			The given velocity.
	 * @param	duration
	 * 			The given duration.
	 * @post	Moves this position to the calculated destination.
	 * 			| new.equals(getSum(v.scaleBy(duration)))
	 * @throws	IllegalArgumentException
	 * 			The given vector is null or duration is strictly negative.
	 * 			| ((v == null) || (duration < 0))
	 * @throws	ArithmeticException
	 * 			One of the resulting components is not a valid component.
	 * 			| ((Double.isNaN(getXComponent()) || (Double.isNaN(getYComponent()))
	 */
	public void moveBy (Velocity v, double duration) throws ArithmeticException, IllegalArgumentException
	{
		if ( (v == null) || (duration < 0)) { throw new IllegalArgumentException("Invalid parameter provided."); }
		Position p = getPositionAfterMove(v, duration);
		setX(p._X());
		setY(p._Y());
	}

	/**
	 * Returns a position, equal to this position moved by the a velocity during the a duration.
	 * 
	 * @param	v
	 * 			The given velocity.
	 * @param	duration
	 * 			The given duration.
	 * @return	A position, equal to this position moved by the given velocity during the given duration.
	 * 			| result.equals(getSum(v.scaleBy(duration)))
	 * @throws	IllegalArgumentException
	 * 			The given vector is null or duration is strictly negative.
	 * 			| ((v == nulls) || (duration < 0))
	 * @throws	ArithmeticException
	 * 			One of the resulting components is not a valid component.
	 * 			| ((Double.isNaN(getXComponent()) || (Double.isNaN(getYComponent()))
	 */
	public Position getPositionAfterMove (Velocity v, double duration) throws ArithmeticException, IllegalArgumentException
	{
		if ( (v == null) || (duration < 0)) { throw new IllegalArgumentException("Invalid parameter provided."); }
		return getSum(v.getScaledBy(duration));
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return A representation of this object in String format.
	 */
	@Override
	public String toString ()
	{
		return "P_" + hashCode() + " = " + super.toString() + " m";
	}
}
