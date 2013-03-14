package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A wrapper class for angles.
 * 
 * @author Syd & Xavier
 * @version 0.0
 * 
 * @Invar	The angle value of this angle is a valid angle.
 * 			| canHaveAsAngle(getAngle())
 */
public class Angle
{
	/**
	 * Initializes this new angle with a given angle.
	 * 
	 * @param	angle
	 * 			The given angle.
	 * 
	 * @effect	The angle of this angle is set to the given angle.
	 * 			| setAngle(angle)
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
	 * 
	 * @effect	Calls the extended constructor with default values.
	 * 			| this(0.0)
	 */
	public Angle()
	{
		this(0.0);
	}

	/**
	 * Returns the wrapped angle of this angle.
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
	 * @return	True if and only if the given angle is a finite number.
	 * 			| result = ((!Double.isNaN(angle)) && (!Double.isInfinite(angle)))
	 */
	@Basic
	@Raw
	public boolean canHaveAsAngle(double angle)
	{
		return ((!Double.isNaN(angle)) && (!Double.isInfinite(angle)));
	}

	/**
	 * Sets the angle of this angle to the corresponding positive angle of the given angle.
	 *
	 * @param	angle
	 *			The new angle for this angle.
	 * @post	If this angle can have the given angle as its angle,
	 * 			then the angle of this angle is now equal to the given angle modulo 2Pi.
	 * 			| (getAngle() >= 0) && (getAngle() <= 2*Math.PI))
	 */
	@Basic
	public void setAngle(double angle)
	{
		assert (canHaveAsAngle(angle));
		if (angle >= 0)
		{
			this.angle = angle % (2 * Math.PI);
		} else
		{
			this.angle = (angle % (2 * Math.PI)) + (2 * Math.PI);
		}
		assert ((getAngle() >= 0) && (getAngle() <= 2 * Math.PI));
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
	public double getSin()
	{
		return Math.sin(getAngle());
	}

	/**
	 * Returns the cosine of this angle.
	 * 
	 * @return	The cosine of this angle
	 * 			| result = Math.cos(getAngle())
	 */
	public double getCos()
	{
		return Math.cos(getAngle());
	}

	/**
	 * Checks whether the given object is an angle and it is equal to this angle.
	 * 
	 * @param	o
	 * 			The given object.
	 * @return	True if and only if the given object is an angle and it is equal to this angle.
	 * 			| result = ((o != null) && (Util.fuzzyEquals(((Angle) o).getAngle(),getAngle())))
	 */
	@Override
	@Raw
	public boolean equals(Object o)
	{
		if (o == null)
		{
			return false;
		}
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
	 * @effect	Adds the value of the given angle to this angle.
	 * 			| setAngle(getAngle() + a.getAngle())
	 */
	@Raw
	public void add(@Raw Angle a)
	{
		setAngle(getAngle() + a.getAngle());
	}
}
