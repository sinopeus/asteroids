package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A superclass for vectorial properties, such as position, velocity,
 * acceleration, direction... Implemented for the sake of economy.
 *
 *
 * @author Syd & Xavier
 * @author
 * @version 0.0
 */

public class Vector
{

	/**
	 * Gets the x component of the vector.
	 *
	 * @return The x component of the vector as a double precision floating point number.
	 */
	@Basic
	@Raw
	public double getXComponent()
	{
		return this.x;
	}

	/**
	 * Sets the x component of the vector.
	 *
	 * @param x The chosen double precision floating point value for the x component of the vector.
	 */
	@Basic
	@Raw
	public void setXComponent(double x)
	{
		if (canHaveAsComponent(x))
		{
			this.x = x;
		}
	}

	/**
	 * The x component of the vector.
	 */
	protected double x;

	/**
	 * Gets the y component of the vector.
	 *
	 * @return The y component of the vector as a double precision floating point number.
	 */
	@Basic
	@Raw
	public double getYComponent()
	{
		return this.y;
	}

	/**
	 * Sets the y component of the vector.
	 *
	 * @param y The chosen double precision floating point value for the y component of the vector.
	 */
	@Basic
	@Raw
	public void setYComponent(double y)
	{
		if (canHaveAsComponent(y))
		{
			this.y = y;
		}
	}

	/**
	 * The y component of the vector.
	 */
	protected double y;

	/**
	 * Checks if the input can be a vector component.
	 *
	 * @param x A double precision floating point number.
	 * @return Will return true if the formal argument is a possible value for a vector component.
	 */
	public boolean canHaveAsComponent(double x)
	{
		return (true);
	}

	/*
	 * Basic operations on elements of commutative groups.
	 */

	/**
	 * Adds two vectors.
	 *
     * @param v An instance of the class Vector which we want to add to the
     * instance on which it is called.
	 * @return A new instance of the class Vector whose components are the sums
	 * of the respective components of the two Vector objects given in the
	 * implicit and explicit parameter.
	 */
	public Vector getSum(Vector v)
	{
		return new Vector(getXComponent() + v.getXComponent(), getYComponent() + v.getYComponent());
	}

	/**
	 * Subtracts one vector from another.
	 *
	 * @param v An instance of the class Vector which we want to subtract from the instance on which it is called.
	 * @return A new instance of the class Vector whose components are the
	 * differences of the respective components of the two Vector objects given
	 * in the implicit and explicit parameter.
	 */
	public Vector getDifference(Vector v)
	{
		return new Vector((getXComponent() - v.getXComponent()), (getYComponent() - v.getYComponent()));
	}

	/*
	 * Operations on elements of commutative groups equipped with scalar
	 * multiplication.
	 */

	/**
	 * Scales a vector by a given factor.
	 *
	 * @param scalar
	 * @return A new Vector object with each of the components scaled by the
	 * factor provided in the explicit parameter.
	 */
	public Vector scaleBy(double scalar)
	{
		return new Vector(getXComponent() * scalar, getYComponent() * scalar);
	}

	/*
	 * Basic operations on elements of inner product spaces.
	 */

	/**
	 * Computes the inner product of two vectors.
	 *
	 * @param v A Vector object of which we want to compute the inner product with the Vector object upon which the method is called.
	 * @return A double precision floating point value representing the inner product of the Vector objects in the implicit and explicit parameters.
	 */
	public double dotProduct(Vector v)
	{
		return (getXComponent() * v.getXComponent()) + (getYComponent() * v.getYComponent());
	}

	/**
	 * Computes the magnitude of a vector.
	 *
	 * @param v A Vector object whose magnitude we want to compute.
	 * @return A double precision floating point value representing the
	 * magnitude of the Vector object.
	 */
	public double getMagnitude()
	{
		return Math.sqrt(this.dotProduct(this));
	}

	/**
	 * Returns a unit vector denoting the direction of the vector.
	 *
	 * @return A unit vector.
	 */
	public Vector GetUnitVectorInSameDirection()
	{
		return this.scaleBy(1.0 / getMagnitude());
	}

	/**
	 * Calculates the angle in radians from the x axis.
	 *
	 * @return The angle in radians from the x axis in radians.
	 */
	public Angle angleToXAxis()
	{
		return new Angle(Math.asin(getYComponent()));
	}

	/**
	 * Calculates the angle between this vector and the argument vector.
	 *
	 * @param v A vector.
	 * @return The angle between the two vectors in radians.
	 */
	public Angle angleTo(Vector v)
	{
		return new Angle(Math.acos((((getXComponent() * v.getXComponent()) + (getYComponent() * v.getYComponent())) / (getMagnitude() * v.getMagnitude()))));
	}

	/**
	 * Computes the Euclidean distance between two vectors.
	 *
	 * @param v The vector to which we want to measure the Euclidean distance.
	 * @return The distance between the two vectors in double precision floating point representation.
	 */
	public double distanceTo(Vector v)
	{
		return this.getDifference(v).getMagnitude();
	}

	/**
	 * A constructor for the vector class.
	 * @param x The x component of the vector we want to construct.
	 * @param y The y component of the vector we want to construct.
	 **/

	public Vector(double x, double y)
	{
		setXComponent(x);
		setYComponent(y);
	}

	/**
	 * A constructor for the zero vector.
	 *
	 * {@inheritDoc}
	 * @see Object#Vector()
	 */
	public Vector()
	{
		this(0, 0);
	}

}
