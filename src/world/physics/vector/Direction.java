package world.physics.vector;

import world.physics.geometry.Angle;
import Utilities.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of directions extending vector, involving an angle. A direction is a unit vector.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 * 
 * @invar	The magnitude of this direction is equal to 1. (It is a unit vector)
 * 			| Util.fuzzyEquals(getMagnitude(), 1.0)
 * @invar	The angle of this direction is a valid angle.
 * 			| canHaveAngle(getAngle())
 */
public class Direction extends Vector
{
	/**
	 * Initializes this new direction with a given angle.
	 *
	 * @param   angle
	 *          The given angle.
	 * @pre		The given angle is not null.
	 * 			| angle != null
	 * @post    The magnitude of this new direction is equal to one.
	 * 			| Util.fuzzyEquals(getMagnitude(), 1.0)
	 * @post	The angle of this direction is a valid angle.
	 * 			| canHaveAsAngle(getAngle())
	 */
	public Direction (Angle angle)
	{
		assert (angle != null);
		setAngle(angle);
		assert (Util.fuzzyEquals(getMagnitude(), 1.0));
		assert (canHaveAsAngle(getAngle()));
	}

	/**
	 * Initializes this angle with a given angle in double format.
	 * 
	 * @param angle		The given angle.
	 */
	public Direction (double angle)
	{
		this(new Angle(angle));
	}

	/**
	 * Initializes this new direction with a default angle.	
	 * 
	 * @post    The magnitude of this new direction is equal to one.
	 * 			| Util.fuzzyEquals(getMagnitude(), 1.0)
	 * @post	The angle of this direction is a valid angle.
	 * 			| canHaveAsAngle(getAngle())
	 */
	public Direction ()
	{
		this(new Angle());
		assert (Util.fuzzyEquals(getMagnitude(), 1.0));
		assert (canHaveAsAngle(getAngle()));
	}

	/**
	 * Gets the angle of this direction.
	 */
	@Basic
	@Raw
	public Angle getAngle ()
	{
		return this.angle;
	}

	/**
	 * Checks whether this direction can have the given angle as its angle.
	 * 
	 * @param 	angle
	 * 			The angle to check.
	 * @return	True if and only if the given angle is not null
	 * 			| result == (angle != null)
	 */
	@Basic
	@Raw
	protected boolean canHaveAsAngle (@Raw Angle angle)
	{
		return (angle != null);
	}

	/**
	 * Sets the angle of this direction to the given angle.
	 *
	 * @param	angle
	 *			The new angle for this direction.
	 * @pre		The given angle is not null.
	 * 			| angle != null
	 * @post	If this direction can have the given angle as its angle,
	 * 			then the angle of this direction is now equal to the given angle.
	 * 			| new.getAngle() == angle
	 * @post    The magnitude of this new direction is equal to one.
	 * 			| Util.fuzzyEquals(getMagnitude(), 1.0)
	 * @post	The angle of this direction is a valid angle.
	 * 			| canHaveAsAngle(getAngle())
	 */
	@Basic
	@Raw
	public void setAngle (Angle angle)
	{
		assert (canHaveAsAngle(angle));
		this.angle = angle;
		assert (getAngle().equals(angle));
		assert (Util.fuzzyEquals(getMagnitude(), 1.0));
		assert (canHaveAsAngle(getAngle()));
	}

	/**
	 * A variable referencing the angle of this Direction.
	 */
	private Angle	angle;

	/**
	 * Gets the x component of this Direction.
	 */
	@Override
	@Basic
	@Raw
	public double _X ()
	{
		return getAngle().getCos();
	}

	/**
	 * Gets the y component of this Direction.
	 */
	@Override
	@Basic
	@Raw
	public double _Y ()
	{
		return getAngle().getSin();
	}

	/**
	 * Rotates this direction by the given angle.
	 * 
	 * @pre		The given angle is not null.
	 * 			| angle != null
	 * @param	angle
	 * 			The given angle.
	 * @post    The magnitude of this new direction is equal to one.
	 * 			| Util.fuzzyEquals(getMagnitude(), 1.0)
	 * @post	The angle of this direction is a valid angle.
	 * 			| canHaveAsAngle(getAngle())
	 */
	public void rotate (Angle angle)
	{
		assert (angle != null);
		getAngle().add(angle);
		assert (Util.fuzzyEquals(getMagnitude(), 1.0));
		assert (canHaveAsAngle(getAngle()));
	}

	@Override
	public int hashCode ()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( (angle == null) ? 0 : angle.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj)
	{
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		Direction other = (Direction) obj;
		if (angle == null)
		{
			if (other.angle != null) return false;
		} else if (!angle.equals(other.angle)) return false;
		return true;
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return A representation of this object in String format.
	 */
	@Override
	public String toString ()
	{
		return "D_" + hashCode() + " = " + super.toString();
	}
}
