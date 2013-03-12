package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class for storing information on the orientation of the spaceship.
 * 
 * 
 * @author Syd & Xavier
 * @version 0.0
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
	 * @return	True
	 * 			| result = true
	 */
	@Basic
	@Raw
	public boolean canHaveAsAngle(Angle angle)
	{
		return true;
	}

	/**
	 * Sets the angle of this direction to the given angle.
	 *
	 * @param	angle
	 *			The new angle for this direction.
	 * @post	If this direction can have the given angle as its angle,
	 * 			then the angle of this direction is now equal to the given angle.
	 * 			| if canHaveAsAngle(angle)
	 * 			|	then new.getAngle() == angle
	 */
	@Basic
	@Raw
	public void setAngle(Angle angle)
	{
		if (canHaveAsAngle(angle))
		{
			this.angle = angle;
		}
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
	 * @post    A unit vector reflecting the direction will be correctly
	 *          computed based on the angle provided.
	 */
	public Direction(Angle angle)
	{
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
	 * 			| result = getAngle().equals(o)
	 */
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Direction)
		{
			return getAngle().equals(((Direction) o).getAngle());
		}
		return false;
	}

	/**
	 * Rotates this direction by the given angle.
	 * 
	 * @param	angle
	 * 			The given angle.
	 */
	public void rotate(Angle angle)
	{
		getAngle().add(angle);
	}
}
