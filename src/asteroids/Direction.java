package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of Directions extending vector, involving an angle. A direction is a unit vector.
 * 
 * @author Syd & Xavier
 * @version 0.0
 * 
 * @Invar	The magnitude of this direction is equal to 1.
 * 			| Util.fuzzyEquals(getMagnitude(), 1.0)
 * @Invar	The angle of this direction is a valid angle.
 * 			| canHaveAngle(getAngle())
 */
public class Direction extends Vector
{
	/**
	 * Returns the angle of this direction.
	 */
	@Basic
	@Raw
	public Angle getAngle()
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
	public boolean canHaveAsAngle(Angle angle)
	{
		return (angle != null);
	}

	/**
	 * Sets the angle of this direction to the given angle.
	 *
	 * @param	angle
	 *			The new angle for this direction.
	 * @Pre		The given angle is not null.
	 * 			| angle != null
	 * @post	If this direction can have the given angle as its angle,
	 * 			then the angle of this direction is now equal to the given angle.
	 * 			| new.getAngle() == angle
	 */
	@Basic
	@Raw
	public void setAngle(Angle angle)
	{
		assert (canHaveAsAngle(angle));
		this.angle = angle;
		assert (getAngle().equals(angle));
	}

	/**
	 * A variable referencing the angle of this Direction.
	 */
	private Angle angle;

	/**
	 * Returns the x component of this Direction.
	 */
	@Override
	@Basic
	@Raw
	public double getXComponent()
	{
		return getAngle().getCos();
	}

	/**
	 * Returns the y component of this Direction.
	 */
	@Override
	@Basic
	@Raw
	public double getYComponent()
	{
		return getAngle().getSin();
	}

	/**
	 * Initializes this new direction with a given angle.
	 *
	 * @param   angle
	 *          The given angle.
	 * @Pre		The given angle is not null.
	 * 			| angle != null
	 * @post    A unit vector reflecting the direction will be
	 *          computed based on the angle provided.
	 */
	public Direction(Angle angle)
	{
		assert (angle != null);
		setAngle(angle);
	}

	/**
	 * Initializes this new direction with default values.
	 */
	public Direction()
	{
		this(new Angle());
	}

	/**
	 * Checks whether the given object is a direction and it is equal to this direction.
	 * 
	 * @param	o
	 * 			The given object.
	 * @return	True if and only if the given object is a direction and it is equal to this direction.
	 * 			| ((result = o != null) && (getAngle().equals(o))
	 */
	@Override
	public boolean equals(Object o)
	{
		if (o == null)
		{
			return false;
		}
		if (!(o instanceof Direction))
		{
			return false;
		}
		return getAngle().equals(((Direction) o).getAngle());
	}

	/**
	 * Rotates this direction by the given angle.
	 * 
	 * @pre		The given angle is not null.
	 * 			| angle != null
	 * @param	angle
	 * 			The given angle.
	 */
	public void rotate(Angle angle)
	{
		assert (angle != null);
		getAngle().add(angle);
	}
}
