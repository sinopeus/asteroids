package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
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
	 * Sets the angle of this angle to the given angle.
	 *
	 * @param	angle
	 *			The new angle for this angle.
	 * @post	If this angle can have the given angle as its angle,
	 * 			then the angle of this angle is now equal to the given angle.
	 * 			| if canHaveAsAngle(angle)
	 * 			|	then new.getAngle() == angle
	 */
	@Basic
	@Raw
	public void setAngle(double angle)
	{
		if (canHaveAsAngle(angle))
		{
			this.angle = angle;
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
		System.out.println(Math.cos(getAngle()));
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
		setAngle(angle % (2 * Math.PI));
	}

	/**
	 * Initializes this new angle by setting it's value to 0.
	 */
	public Angle()
	{
		setAngle(0.0);
	}
}
