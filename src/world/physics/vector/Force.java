package world.physics.vector;

/**
 * A class of force vectors.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
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
	public Force (double x, double y)
	{
		super(x, y);
	}

	/**
	* The vector constructor for this class.
	*
	 * @param v  The vector we want to replicate as a force vector.
	 * @effect	Initializes this acceleration by calling the by vector vector constructor.
	 * 			| Vector(v)
	 * @throws	IllegalArgumentException
	 * 			The given vector is null.
	 * 			| v == null
	 */
	public Force (Vector v) throws NullPointerException
	{
		super(v);
	}

	/**
	 * Initializes this force vector with default values.
	*
	 * @effect	Initializes this acceleration by calling the simple vector constructor.
	 */
	public Force ()
	{
		super();
	}

	/**
	 * @see world.physics.vector.Vector#toString()
	 */
	@Override
	public String toString ()
	{
		return "F_" + hashCode() + " = " + super.toString() + " N";
	}
}
