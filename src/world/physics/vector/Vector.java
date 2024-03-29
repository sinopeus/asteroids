package world.physics.vector;

import Utilities.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of vectors involving a x and a y component.
 *
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 * 
 * @invar	Each of the components of this vector is a valid component.
 * 			| canHaveAsComponent(getXComponent()) && canHaveAsComponent(getXComponent())
 */

public class Vector
{
	/**
	 * Initializes this new vector with a given x-component and y-component.
	 * @param	x
	 * 			The given x-component.
	 * @param	y
	 * 			The given y-component.
	 * @effect	Sets the x-vector of this new vector to the given x-vector.
	 * 			| setXComponent(x)
	 * @effect	Sets the y-vector of this new vector to the given x-vector.
	 * 			| setYComponent(y)
	 **/
	public Vector (double x, double y)
	{
		components = new double[2];
		setX(x);
		setY(y);
	}

	/**
	 * Initializes this new vector with the components of the given vector.
	 * 
	 * @param	v
	 * 			The given vector.
	 * @effect	Initializes this new vector with the extended vector constructor
	 * 			| this(v.getXComponent(), v.getYComponent())
	 * @throws	IllegalArgumentException
	 * 			The given vector is null.
	 * 			| v == null
	 */
	public Vector (Vector v) throws NullPointerException
	{
		this(v._X(), v._Y());
	}

	/**
	 * Initializes this vector as the origin.
	 */
	public Vector ()
	{
		this(0, 0);
	}

	/**
	 * Gets the x component of the vector.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public double _X ()
	{
		return this.components[0];
	}

	/**
	 * Sets the x-component of the vector to a given x-component.
	 *
	 * @param	x
	 * 			The given x-component.
	 * @post	If the given x-component is a valid component, then the x-component of this vector is now equal to the given x-component.
	 * 			| if(canHaveAsComponent(x))
	 * 			|  then Util.fuzzyEquals(new.getXComponent(),x)
	 */
	@Basic
	@Raw
	public void setX (double x)
	{
		if (canHaveAsComponent(x)) this.components[0] = x;
	}

	/**
	 * The components of the vector.
	 */
	protected double[]	components;

	/**
	 * Gets the y component of the vector.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public double _Y ()
	{
		return this.components[1];
	}

	/**
	 * Sets the y-component of this vector to the given y-component.
	 *
	 * @param	y
	 * 			The given y-component.
	 * @post	If the given y-component is a valid component, then the y-component of this vector is now equal to the given y-component.
	 * 			| if(canHaveAsComponent(y))
	 * 			|  then Util.fuzzyEquals(new.getYComponent(),y)
	 */
	@Basic
	@Raw
	public void setY (double y)
	{
		if (canHaveAsComponent(y)) this.components[1] = y;
	}

	/**
	 * Checks whether the given component can be a component of this vector.
	 *
	 * @param	x
	 * 			The given component.
	 * @return	True if and only if the given component is a number.
	 * 			| result = (!Double.isNaN(x))
	 */
	@Raw
	@Basic
	protected boolean canHaveAsComponent (double x)
	{
		return (!Double.isNaN(x));
	}

	/*
	 * Basic operations on elements of commutative groups.
	 */

	/**
	 * Gets the sum of this vector and a given vector.
	 *
	 * @param	v
	 * 			The given vector.
	 * @return	A vector whose components are the sums of the respective components of this vector and the given vector.
	 * 			| result == new Vector(getXComponent() + v.getXComponent(), getYComponent() + v.getYComponent())
	 * @throws	ArithmeticException
	 * 			One of the components of the resulting vector is not a number.
	 * 			| Double.isNaN(result.getXComponent) || Double.isNaN(result.getYComponent)
	 */
	public Vector getSum (Vector v) throws ArithmeticException
	{
		double x = _X() + v._X();
		double y = _Y() + v._Y();

		if (Double.isNaN(x) || Double.isNaN(y)) { throw new ArithmeticException("A component of the resulting sum vector is not a number"); }

		return new Vector(x, y);
	}

	/**
	 * Gets the difference between this vector and a given vector.
	 *
	 * @param	v
	 * 			The given vector.
	 * @return	A vector whose components are the differences of the respective components of this vector and the given vector.
	 * 			| result == new Vector((getXComponent() - v.getXComponent()), (getYComponent() - v.getYComponent()))
	 * @throws	ArithmeticException
	 * 			One of the components of the resulting vectors is not a number.
	 * 			| Double.isNaN(result.getYComponent) || Double.isNaN(result.getXComponent)
	 */
	public Vector getDifference (Vector v) throws ArithmeticException
	{
		double x = _X() - v._X();
		double y = _Y() - v._Y();

		if (Double.isNaN(x) || Double.isNaN(y)) { throw new ArithmeticException("A component of the resulting sum vector is not a number"); }

		return new Vector(x, y);
	}

	/*
	 * Operations on elements of commutative groups equipped with scalar
	 * multiplication.
	 */

	/**
	 * Gets this vector, scaled by a given factor
	 *
	 * @param	factor
	 * 			The given factor
	 * @return	A vector with each of the components scaled by the factor provided in the explicit parameter.
	 * 			| result == new Vector(getXComponent() * scalar, getYComponent() * scalar)
	 * @throws	ArithmeticException
	 * 			One of the components of the resulting vector is not a number.
	 * 			| Double.isNaN(result.getXComponent) || Double.isNaN(result.getYComponent)
	 */
	public Vector getScaledBy (double factor) throws ArithmeticException
	{
		double x = _X() * factor;
		double y = _Y() * factor;

		if (Double.isNaN(x) || Double.isNaN(y)) { throw new ArithmeticException("A component of the resulting sum vector is not a number"); }

		return new Vector(x, y);
	}

	/*
	 * Basic operations on elements of inner product spaces.
	 */

	/**
	 * Gets the inner product of this vector and a given vector.
	 *
	 * @param	v
	 * 			The given vector
	 * @return	The inner product of this vector and the given vector.
	 * 			| result == (getXComponent() * v.getXComponent()) + (getYComponent() * v.getYComponent())
	 * @throws	ArithmeticException
	 * 			The result is not a number.
	 * 			| Double.isNaN(result)
	 */
	public double dotProduct (Vector v) throws ArithmeticException
	{
		double result = (_X() * v._X()) + (_Y() * v._Y());
		if (Double.isNaN(result)) { throw new ArithmeticException("The result is not a number"); }
		return result;
	}

	/**
	 * Gets the magnitude of this vector.
	 *
	 * @return	The magnitude of this vector.
	 * 			| result == Math.sqrt(this.dotProduct(this))
	 */
	public double getMagnitude ()
	{
		return Math.sqrt(this.dotProduct(this));
	}

	/**
	 * Gets a unit vector denoting the direction of the vector.
	 *
	 * @return	A unit vector in the same direction as this vector.
	 * 			| result == this.scaleBy(1.0 / getMagnitudeTrue())
	 * @throws	ArithmeticException
	 * 			This vector is a nil vector.
	 * 			| Util.fuzzyEquals(getMagnitude(), 0)
	 */
	public Vector getUnitVectorInDirection () throws ArithmeticException
	{
		if (Util.fuzzyEquals(getMagnitude(), 0)) { throw new ArithmeticException("This vector is a nil vector."); }
		return this.getScaledBy(1.0 / getMagnitude());
	}

	/**
	 * Get the Euclidean distance between this vector and a given vector.
	 *
	 * @param	v
	 * 			The given vector.
	 * @return	The distance between this vector and the given vector.
	 * 			| result == this.getDifference(v).getMagnitude()
	 * @throws	ArithmeticException
	 * 			The distance can't be calculated.
	 * 			| Double.isNaN(result)	 
	 */
	public double getDistanceTo (Vector v) throws ArithmeticException
	{
		return this.getDifference(v).getMagnitude();
	}

	/**
	 * Returns the quadrant this vector is in.
	 * 
	 * @return	The quadrant this vector is in.
	 * 			| if (_X() >= 0 && _Y() >= 0) result == Quadrant.QUADRANT_I
	 * 			| if (_X() < 0 && _Y() >= 0) result ==  Quadrant.QUADRANT_II
	 *			| if (_X() < 0 && _Y() < 0) result ==  Quadrant.QUADRANT_III
	 *			| if (_X() >= 0 && _Y() < 0) result ==  Quadrant.QUADRANT_IV
	 */
	public Quadrant getQuadrant ()
	{
		if (_X() >= 0 && _Y() >= 0) { return Quadrant.QUADRANT_I; }
		if (_X() < 0 && _Y() >= 0) { return Quadrant.QUADRANT_II; }
		if (_X() < 0 && _Y() < 0) { return Quadrant.QUADRANT_III; }
		if (_X() >= 0 && _Y() < 0) { return Quadrant.QUADRANT_IV; }
		throw new RuntimeException("Java should never reach this piece of code");
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return A representation of this object in String format.
	 */
	@Override
	public String toString ()
	{
		return "(" + (int) _X() + ", " + (int) _Y() + ")";
	}

	/**
	 * Checks whether the given object is a vector and its respective components are equal to this vector's components
	 * 
	 * @param	o
	 * 			The given object.
	 * @return	True if and only if the given object is a vector and the respective components of the given object and this vector are equal.
	 * 			| result ==( o != null) && (getClass() != o.getClass()) && Util.fuzzyEquals(getXComponent(), ((Vector) o).getXComponent()) && Util.fuzzyEquals(getYComponent(), ((Vector) o).getYComponent()))
	 */
	@Override
	@Raw
	public boolean equals (Object o)
	{
		if (o == null) { return false; }
		if (getClass() != o.getClass()) { return false; }
		Vector other = (Vector) o;
		return (Util.fuzzyEquals(_X(), other._X()) && Util.fuzzyEquals(_Y(), other._Y()));
	}

	/**
	 * Returns the hash code for this vector. Designed to be consistent with the equals method.
	 * 
	 * @return	A hash code for this vector.
	 * 			| result == prime * ( prime + (int) (Double.toLongBits(x) ^ (Double.toLongBits(x) >>> 32))) + (int) (Double.toLongBits(y) ^ (Double.toLongBits(y) >>> 32))
	 */
	@Override
	@Raw
	public int hashCode ()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(components[0]);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(components[1]);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
