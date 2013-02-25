package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
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
	 * @param v An instance of the class Vector which we want to add to the instance on which it is called.
	 * @return A new instance of the class Vector whose components are the sums
	 * of the respective components of the two Vector objects given in the
	 * implicit and explicit parameter.
	 */
	public Vector sum(Vector v)
	{
		double xComponent = getXComponent() + v.getXComponent();
		double yComponent = getYComponent() + v.getYComponent();
		return new Vector(xComponent, yComponent);
	}

	/**
	 * Subtracts one vector from another.
	 *
	 * @param v An instance of the class Vector which we want to subtract from the instance on which it is called.
	 * @return A new instance of the class Vector whose components are the
	 * differences of the respective components of the two Vector objects given
	 * in the implicit and explicit parameter.
	 */
	public Vector difference(Vector v)
	{
		double xComponent = getXComponent() + v.getXComponent();
		double yComponent = getYComponent() + v.getYComponent();
		return new Vector(xComponent, yComponent);
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
	public Vector scale(double scalar)
	{
		double xComponent = getXComponent() * scalar;
		double yComponent = getYComponent() * scalar;
		return new Vector(xComponent, yComponent);
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
	public double dot(Vector v)
	{
		double otherX = v.getXComponent();
		double otherY = v.getYComponent();
		return (getXComponent() * otherX) + (getYComponent() * otherY);
	}

	/**
	 * Computes the magnitude of a vector.
	 *
	 * @param v A Vector object whose magnitude we want to compute.
	 * @return A double precision floating point value representing the
	 * magnitude of the Vector object.
	 */
	public double magnitude()
	{
		return Math.sqrt(this.dot(this));
	}

	/**
	 * Returns a unit vector denoting the direction of the vector.
	 *
	 * @return A unit vector.
	 */
	public Vector direction()
	{
		return this.scale(1.0 / magnitude());
	}

	/**
	 * Calculates the angle in radians from the x axis.
	 *
	 * @return The angle in radians from the x axis in radians.
	 */
	public Angle angle()
	{
		//TODO
	}

	/**
	 * Calculates the agle between this vector and the argument vector.
	 *
	 * @param v A vector.
	 * @return The angle between the two vectors in radians.
	 */
	public Angle angle(Vector v)
	{
		//TODO
	}

	/**
	 * Computes the Euclidean distance between two vectors.
	 *
	 * @param v The vector to which we want to measure the Euclidean distance.
	 * @return The distance between the two vectors in double precision floating point representation.
	 */
	public double distanceTo(Vector v)
	{
		return this.difference(v).magnitude();
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
