package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A wrapper class for angles.
 * 
 * 
 * @author Syd & Xavier
 * @version 0.0
 */
public class Angle
{
	/**
	 * Returns the angle of this angle.
	 */
	@Basic
	@Raw
	public double getAngle()
	{
		return this.angle;
	}

	/**
	 * Checks whether this angle can have the given angle as its angle.
	 * 
	 * @param 	angle
	 * 			The angle to check.
	 * @return	True if and only if the given angle is a number.
	 * 			| result = (!Double.isNaN(angle))
	 */
	@Basic
	@Raw
	public boolean canHaveAsAngle(double angle)
	{
		return (!Double.isNaN(angle));
	}

	/**
	 * Sets the angle of this angle to the corresponding positive angle of the given angle.
	 *
	 * @param	angle
	 *			The new angle for this angle.
	 * @post	If this angle can have the given angle as its angle,
	 * 			then the angle of this angle is now equal to the given angle modulo 2Pi.
	 * 			| if canHaveAsAngle(angle)
	 * 			|	then new.getAngle() == angle % (2*Math.PI)
	 * 			| else
	 * 			| 	new.getAngle() == angle % (2*Math.PI) + (2*Math.PI)
	 */
	@Basic
	@Raw
	public void setAngle(double angle)
	{
		if (canHaveAsAngle(angle))
		{
			if (angle >= 0)
			{
				this.angle = angle % (2 * Math.PI);
			} else
			{
				this.angle = (angle % (2 * Math.PI)) + (2 * Math.PI);
			}
		}
	}

	/**
	 * A variable registering the value of this angle.
	 */
	private double angle;

	/**
	 * Returns the sine of this angle.
	 * 
	 * @return	The sine of this angle
	 * 			| result = Math.sin(getAngle())
	 */
	public double sin()
	{
		return Math.sin(getAngle());
	}

	/**
	 * Returns the cosine of this angle.
	 * 
	 * @return	The cosine of this angle
	 * 			| result = Math.cos(getAngle())
	 */
	public double cos()
	{
		return Math.cos(getAngle());
	}

	/**
	 * Initializes this new angle with a given angle.
	 * 
	 * @param	angle
	 * 			The given angle.
	 * 
	 * @post	The given angle is a valid angle.
	 * 			| new.canHaveAsAngle(angle)
	 */
	public Angle(double angle)
	{
		setAngle(angle);
	}

	/**
	 * Initializes this new angle with default values.
	 */
	public Angle()
	{
		this(0.0);
	}

	/**
	 * Checks whether the given object is an angle and it is equal to this angle.
	 * 
	 * @param	o
	 * 			The given object.
	 * @return	True if and only if the given object is an angle and it is equal to this angle.
	 * 			| result = Util.fuzzyEquals(((Angle) o).getAngle(),getAngle())
	 */
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Angle)
		{
			return (Util.fuzzyEquals(((Angle) o).getAngle(), getAngle()));
		}
		return false;
	}

	/**
	 * Adds a given angle to this angle.
	 * 
	 * @param	a
	 * 			The given angle.
	 */
	public void sum(Angle a)
	{
		setAngle(getAngle() + a.getAngle());
	}
}
