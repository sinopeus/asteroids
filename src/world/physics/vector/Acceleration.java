package world.physics.vector;


/**
 * A class of acceleration vectors.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
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
	public Acceleration (double x, double y)
	{
		super(x, y);
	}

	/**
	 * Initializes this new acceleration with a given vector.
	 * 
	 * @param	v
	 * 			The given vector.
	 * @effect	Initializes this acceleration by calling the by vector vector constructor.
	 * 			| Vector(v)
	 * @throws	IllegalArgumentException
	 * 			The given vector is null.
	 * 			| v == null
	 */
	public Acceleration (Vector v) throws IllegalArgumentException
	{
		super(v);
	}

	/**
	 * Initializes this new acceleration with default values.
	 * 
	 * @effect	Initializes this acceleration by calling the simple vector constructor.
	 */
	public Acceleration ()
	{
		super();
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return A representation of this object in String format.
	 */
	@Override
	public String toString ()
	{
		return "A_" + hashCode() + " = " + super.toString() + " m/s²";
	}
}
