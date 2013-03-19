package entity;

import model.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of circle shapes involving a finite positive radius
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 1.0
 * 
 * @invar	The radius of this circle shape is a valid radius
 * 			| canHaveAsRadius(getRadius)
 */
public class CircleShape
{
	/**
	 * Initializes this new circle shape with a given radius
	 * 
	 * @param	radius
	 * 			The radius for this new circle shape.
	 * @post	The radius of this new circle shape is now equal to the given radius.
	 * 			| Util.fuzzyEquals(new.getRadius(),radius)
	 * @throws	IllegalArgumentException
	 * 			The given radius is not a valid radius for this circle shape.
	 * 			| !canHaveAsRadius(radius)
	 */
	public CircleShape(double radius)
	{
		if (!canHaveAsRadius(radius))
		{
			throw new IllegalArgumentException("Invalid radius provided");
		} else
		{
			this.radius = radius;
		}
	}

	/**
	 * Initializes this new circle shape with a default radius.
	 * 
	 * @effect	Initializes this new circle shape with the extended constructor and default values.
	 * 			| CircleShape(0.0)
	 */
	public CircleShape()
	{
		this(0.0);
	}
	
	/**
	 * Gets the radius of this circle shape.
	 */
	@SuppressWarnings("javadoc")
	@Basic
	@Raw
	public double getRadius()
	{
		return this.radius;
	}

	/**
	 * Checks whether this circle shape can have the given radius as its radius.
	 * 
	 * @param 	radius
	 * 			The radius to check.
	 * @return	True if and only if the given radius is at least 0 and a number.
	 * 			| result == ((radius >= 0) && !Double.isNaN(radius))
	 */
	@Basic
	@Raw
	protected boolean canHaveAsRadius(double radius)
	{
		return (!Double.isNaN(radius) && (radius >= 0));
	}

	/**
	 * A variable registering the radius of this circle shape in km/s.
	 */
	private final double radius;

	/**
	 * Checks whether the given object's is a circle shape and it's radius is equal to the radius of this circle shape
	 * 
	 * @param	o
	 * 			The given object.
	 * @return	True if and only if the given object is a non null circle shape and its radius is equal to the radius of this circle shape.
	 * 			| result == ((o != null) && (getClass() == o.getClass()) && (Util.fuzzyEquals(getXComponent(), ((Vector) o).getXComponent()) && Util.fuzzyEquals(getYComponent(), ((Vector) o).getYComponent())))
	 */
	@Override
	@Raw
	public boolean equals(Object o)
	{
		if (o == null){
			return false;
		}
		if(!(getClass() == o.getClass())){
			return false;
		}
		CircleShape other = (CircleShape) o;
		return (Util.fuzzyEquals(getRadius(), other.getRadius()));
	}
}
