package vector;

/**
 * A class of force vectors.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 1.0
 */
public class Force extends Vector
{
	/**
   * The extended constructor for this class.
   *
	 * @param x  The x component of this force.
	 * @param y  The y component of this force.
	 * @effect 	 Initializes this acceleration by calling the extended vector constructor.
	 * 			| Vector(x,y)
	 */
	public Force(double x, double y)
	{
		//TODO  
	}

	/**
   * The vector constructor for this class.
   *
	 * @param v  The vector we want to replicate as a force vector.
	 * @effect	Initializes this acceleration by calling the by vector vector constructor.
	 * 			| Vector(v)
	 * @throws	NullPointerException
	 * 			The given vector is null.
	 * 			| v == null
	 */
	public Force(Vector v) throws NullPointerException
	{
		//TODO  
	}

	/**
	 * Initializes this force vector with default values.
   *
	 * @effect	Initializes this acceleration by calling the simple vector constructor.
	 */
	public Force()
	{
		//TODO  
	}
}
