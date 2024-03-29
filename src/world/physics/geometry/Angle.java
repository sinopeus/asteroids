package world.physics.geometry;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A wrapper class for angles.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 * 
 * @invar	The angle value of this angle is a valid angle.
 * 			| canHaveAsAngle(getAngle())
 * @invar	The angle value of this angle is between 0 and 2Pi
 * 			| ((getAngle() >= 0) && (getAngle() <= 2 * Math.PI))
 */
public class Angle
{
	/**
	 * Initializes this new angle with a given angle value.
	 * 
	 * @param	angle
	 * 			The given angle.
	 * @effect	The angle of this angle is set to the given angle.
	 * 			| setAngle(angle)
	 * @pre		The given angle value is a valid angle.
	 * 			| canHaveAsAngle(angle)
	 * @post	The angle value of this new Angle is a valid angle value.
	 * 			| new.canHaveAsAngle(getAngle)
	 */
	public Angle (double angle)
	{
		assert (canHaveAsAngle(angle));
		set(angle);
		assert (canHaveAsAngle(get()));
	}

	/**
	 * Initializes this new angle with a default angle value.
	 * 
	 * @effect	Calls the extended constructor with default values.
	 * 			| this(0.0)
	 * @post	The given angle is a valid angle.
	 * 			| new.canHaveAsAngle(angle)
	 */
	public Angle ()
	{
		this(0.0);
		assert (canHaveAsAngle(get()));
	}

	/**
	 * Gets the angle value of this angle.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public double get ()
	{
		return this.angle;
	}

	/**
	 * Checks whether this angle can have the given angle as its angle.
	 * 
	 * @param 	angle
	 * 			The angle to check.
	 * @return	True if and only if the given angle is a number.
	 * 			| result == (!Double.isNaN(angle))
	 */
	@Basic
	@Raw
	protected boolean canHaveAsAngle (double angle)
	{
		return (!Double.isNaN(angle));
	}

	/**
	 * Sets the angle value of this angle to the corresponding positive angle value of the given angle value.
	 *
	 * @param	angle
	 *			The new angle for this angle.
	 * @pre		The given angle value is a valid angle value.
	 * 			| canHaveAsAngle(angle)
	 * @post	The angle value of this angle is now equal to the given angle value modulo 2Pi.
	 * 			| (getAngle() >= 0) && (getAngle() <= 2*Math.PI))
	 */
	@Basic
	@Raw
	public void set (double angle)
	{
		assert (canHaveAsAngle(angle));
		if (angle >= 0) this.angle = angle % (2 * Math.PI);
		else this.angle = (angle % (2 * Math.PI)) + (2 * Math.PI);
		assert ( (get() >= 0) && (get() <= 2 * Math.PI));
	}

	/**
	 * A variable registering the value of this angle.
	 */
	private double	angle;

	/**
	 * Returns the sine of this angle.
	 * 
	 * @return	The sine of this angle
	 * 			| result == Math.sin(getAngle())
	 */
	public double getSin ()
	{
		return Math.sin(get());
	}

	/**
	 * Returns the cosine of this angle.
	 * 
	 * @return	The cosine of this angle
	 * 			| result == Math.cos(getAngle())
	 */
	public double getCos ()
	{
		return Math.cos(get());
	}

	/**
	 * Adds a given angle to this angle.
	 * 
	 * @param	a
	 * 			The given angle.
	 * @effect	Adds the value of the given angle to this angle.
	 * 			| setAngle(getAngle() + a.getAngle())
	 */
	public void add (Angle a)
	{
		set(get() + a.get());
	}

	@Override
	public String toString ()
	{
		return "angle_" + hashCode() + " = " + get() + " rad";
	}

	@Override
	public int hashCode ()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(angle);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals (Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Angle other = (Angle) obj;
		if (Double.doubleToLongBits(angle) != Double.doubleToLongBits(other.angle)) return false;
		return true;
	}
}
