package vector;

import model.Util;


/**
 * A class of acceleration vectors.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 1.0
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
	 * 			| Vector(v)
	 * @throws	NullPointerException
	 * 			The given vector is null.
	 * 			| v == null
	 */
	public Acceleration(Vector v) throws NullPointerException
	{
		super(v);
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

	/**
	 * Checks whether the given object is an acceleration and its respective components are equal to this acceleration's components
	 * 
	 * @param	o
	 * 			The given object.
	 * @return	True if and only if the given object is an acceleration and the respective components of the given object and this acceleration are equal.
	 * 			| result =(o != null) && (getClass() != o.getClass()) && Util.fuzzyEquals(getXComponent(), ((Vector) o).getXComponent()) && Util.fuzzyEquals(getYComponent(), ((Vector) o).getYComponent()))
	 */
	@Override
	public boolean equals(Object o)
	{
		if (o == null)
		{
			return false;
		}
		if (getClass() != o.getClass())
		{
			return false;
		}
		Vector other = (Vector) o;
		return (Util.fuzzyEquals(getXComponent(), other.getXComponent()) && Util.fuzzyEquals(getYComponent(), other.getYComponent()));
	}
}
