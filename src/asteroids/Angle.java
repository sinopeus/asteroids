package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A wrapper class for angles.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 1.0
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
	public Angle(double angle)
	{
		assert (canHaveAsAngle(angle));
		setAngle(angle);
		assert (canHaveAsAngle(getAngle()));
	}

	/**
	 * Initializes this new angle with a default angle value.
	 * 
	 * @effect	Calls the extended constructor with default values.
	 * 			| this(0.0)
	 * @post	The given angle is a valid angle.
	 * 			| new.canHaveAsAngle(angle)
	 */
	public Angle()
	{
		this(0.0);
		assert (canHaveAsAngle(getAngle()));
	}

	/**
	 * Gets the angle value of this angle.
	 */
	@SuppressWarnings("javadoc")
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
	 * 			| result == (!Double.isNaN(angle))
	 */
	@Basic
	@Raw
	protected boolean canHaveAsAngle(double angle)
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
	 * 			| result == Math.sin(getAngle())
	 */
	public double getSin()
	{
		return Math.sin(getAngle());
	}

	/**
	 * Returns the cosine of this angle.
	 * 
	 * @return	The cosine of this angle
	 * 			| result == Math.cos(getAngle())
	 */
	public double getCos()
	{
		return Math.cos(getAngle());
	}

	/**
	 * Adds a given angle to this angle.
	 * 
	 * @param	a
	 * 			The given angle.
	 * @effect	Adds the value of the given angle to this angle.
	 * 			| setAngle(getAngle() + a.getAngle())
	 */
	public void add(Angle a)
	{
		setAngle(getAngle() + a.getAngle());
	}
	
	/**
	 * Checks whether the given object is an angle and it is equal to this angle.
	 * 
	 * @param	o
	 * 			The given object.
	 * @return	True if and only if the given object is an angle and it is equal to this angle.
	 * 			| result == ((o != null) && (Util.fuzzyEquals(((Angle) o).getAngle(),getAngle())))
	 */
	@Override
	@Raw
	public boolean equals(Object o)
	{
		if (o == null)
		{
			return false;
		}
		if (!(o instanceof Angle))
		{
			return false;
		}
		Angle other = (Angle) o;
		return (Util.fuzzyEquals(other.getAngle(), getAngle()));
	}
}
