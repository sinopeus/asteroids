package asteroids;

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
	 * Sets the x component of this direction to the given x component
	 * 
	 * @param	x
	 * 			The given x component.
	 * @pre		This direction can have the given x component as its component.
	 * 			| canHaveAsComponent(x)
	 * @post	The x component of this direction is now equal to the given x component.
	 */
	@Override
	public void setXComponent(double x)
	{
		assert (canHaveAsComponent(x));
		this.x = x;
		assert (getXComponent() == x);
	}

	/**
	 * Sets the y component of this direction to the given y component
	 * 
	 * @param	y
	 * 			The given y component.
	 * @pre		This direction can have the given y component as its component.
	 * 			| canHaveAsComponent(y)
	 * @post	The y component of this direction is now equal to the given y component.
	 */
	@Override
	public void setYComponent(double y)
	{
		assert (canHaveAsComponent(y));
		this.y = y;
		assert (getYComponent() == y);
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
		setXComponent(angle.cos());
		setYComponent(angle.sin());
	}

	/**
	 * {@inheritDoc}
	 * @see Direction(Angle angle)
	 */
	public Direction()
	{
		this(new Angle());
	}
}
