package asteroids;

/**
 * A class of acceleration vectors extending vectors.
 * 
 * @author Syd & Xavier
 * @version 0.0
 */
public class Acceleration extends Vector
{
	/**
	 * Initializes this new acceleration with a given x component and y component.
	 * 
	 * @param	x
	 * 			The given x component.
	 * @param 	y
	 * 			The given y component.
	 * @effect	Initializes this acceleration by calling the extended vector constructor.
	 * 			| Vector(x,y)
	 */
	public Acceleration(double x, double y)
	{
		super(x, y);
	}

	/**
	 * Initializes this new acceleration with a given vector.
	 * 
	 * @param	v
	 * 			The given vector.
	 * @effect	Initializes this acceleration by calling the by vector vector constructor.
	 */
	public Acceleration(Vector v)
	{
		super(v.getXComponent(), v.getYComponent());
	}

	/**
	 * Initializes this new acceleration with default values.
	 * 
	 * @effect	Initializes this acceleration by calling the simple vector constructor.
	 */
	public Acceleration()
	{
		super();
	}
}