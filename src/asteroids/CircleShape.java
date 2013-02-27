package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of circle shapes involving a finite radius
 * @author Syd & Xavier
 */
public class CircleShape
{
	/**
	 * Returns the radius of this circle shape.
	 */
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
	 * @return	True if and only if the given radius is at least 0.
	 * 			| result = (radius > 0)
	 */
	@Basic
	@Raw
	public boolean canHaveAsRadius(double radius)
	{
		return (radius >= 0);
	}

	/**
	 * A variable registering the radius of this circle shape in km/s.
	 */
	private final double radius;

	/*
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
	 * @Effect	Uses the extended constructor to initialize this new circle shape.
	 * 			| this(0)
	 */
	public CircleShape()
	{
		this(0);
	}
}
