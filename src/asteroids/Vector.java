package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A superclass for vectorial properties, such as position, velocity,
 * acceleration, direction... Implemented for the sake of economy.
 *
 *
 * @author Syd & Xavier
 * @version 0.0
 */

public class Vector
{
	/**
	 * Initializes this new vector with a given x-component and y-component.
	 * @param	x
	 * 			The given x-component.
	 * @param	y
	 * 			The given y-component.
	 * @Effect	Sets the x-vector of this new vector to the given x-vector.
	 * 			| setXComponent(x)
	 * @Effect	Sets the y-vector of this new vector to the given x-vector.
	 * 			| setYComponent(y)
	 **/

	public Vector(double x, double y)
	{
		setXComponent(x);
		setYComponent(y);
	}

	/**
	 * Initializes this new vector with a given vector to duplicate from.
	 * 
	 * @param	v
	 * 			The given vector
	 */
	public Vector(Vector v)
	{
		this(v.getXComponent(), v.getYComponent());
	}

	/**
	 * Initializes this vector as the origin.
	 */
	public Vector()
	{
		this(0, 0);
	}

	/**
	 * Gets the x component of the vector.
	 */
	@Basic
	@Raw
	public double getXComponent()
	{
		return this.x;
	}

	/**
	 * Sets the x-component of the vector to a given x-component.
	 *
	 * @param	x
	 * 			The given x-component.
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
	 */
	@Basic
	@Raw
	public double getYComponent()
	{
		return this.y;
	}

	/**
	 * Sets the y-component of this vector to the given y-component.
	 *
	 * @param	y
	 * 			The given y-component.
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
	 * Checks whether the given component can be a component of this vector.
	 *
	 * @param	x
	 * 			The given component.
	 * @return	True if and only if the given component is a number.
	 * 			| result = (!Double.isNaN(x))
	 */
	@Raw
	@Basic
	public boolean canHaveAsComponent(double x)
	{
		return (!Double.isNaN(x));
	}

	/*
	 * Basic operations on elements of commutative groups.
	 */

	/**
	 * Adds a given vector to this vector and returns the result as a new vector.
	 *
	 * @param	v
	 * 			The given vector.
	 * @return	A vector whose components are the sums of the respective components of this vector and the given vector.
	 * 			| result = new Vector(getXComponent() + v.getXComponent(), getYComponent() + v.getYComponent())
	 */
	public Vector getSum(Vector v) throws ArithmeticException //TODO add throws
	{
		double xComp = getXComponent() + v.getXComponent();
		double yComp = getYComponent() + v.getYComponent();
		if (Double.isNaN(xComp) || Double.isNaN(yComp))
		{
			throw new ArithmeticException("A component of the resulting sum vector is not a number");
		}
		return new Vector(xComp, yComp);
	}

	/**
	 * Subtracts a given vector from this vector and returns the result as a new vector.
	 *
	 * @param	v
	 * 			The given vector.
	 * @return	A vector whose components are the differences of the respective components of this vector and the given vector.
	 * 			| result = new Vector((getXComponent() - v.getXComponent()), (getYComponent() - v.getYComponent()))
	 */
	public Vector getDifference(Vector v) throws ArithmeticException //TODO add throws
	{
		double xComp = getXComponent() - v.getXComponent();
		double yComp = getYComponent() - v.getYComponent();
		if (Double.isNaN(xComp) || Double.isNaN(yComp))
		{
			throw new ArithmeticException("A component of the resulting sum vector is not a number");
		}
		return new Vector(xComp, yComp);
	}

	/*
	 * Operations on elements of commutative groups equipped with scalar
	 * multiplication.
	 */

	/**
	 * Scales this vector by a given factor.
	 *
	 * @param	scalar
	 * 			The given factor
	 * @return	A vector with each of the components scaled by the factor provided in the explicit parameter.
	 * 			| result = new Vector(getXComponent() * scalar, getYComponent() * scalar)
	 */
	public Vector scaleBy(double scalar) throws ArithmeticException //TODO add throws
	{
		double xComp = getXComponent() * scalar;
		double yComp = getYComponent() * scalar;
		if (Double.isNaN(xComp) || Double.isNaN(yComp))
		{
			throw new ArithmeticException("A component of the resulting sum vector is not a number");
		}
		return new Vector(xComp, yComp);
	}

	/*
	 * Basic operations on elements of inner product spaces.
	 */

	/**
	 * Returns the inner product of this vector and a given vector.
	 *
	 * @param	v
	 * 			The given vector
	 * @return	The inner product of this vector and the given vector.
	 * 			| result = (getXComponent() * v.getXComponent()) + (getYComponent() * v.getYComponent())
	 */
	public double dotProduct(Vector v) throws ArithmeticException //TODO add throws
	{
		double result = (getXComponent() * v.getXComponent()) + (getYComponent() * v.getYComponent());
		if (Double.isNaN(result))
		{
			throw new ArithmeticException("The result is not a number");
		}
		return result;
	}

	/**
	 * Returns the magnitude of this vector.
	 *
	 * @return	The magnitude of this vector.
	 * 			| result = Math.sqrt(this.dotProduct(this))
	 */
	public double getMagnitude()
	{
		return Math.sqrt(this.dotProduct(this));
	}

	/**
	 * Returns a unit vector denoting the direction of the vector.
	 *
	 * @return	A unit vector in the same direction as this vector.
	 * 			| result = this.scaleBy(1.0 / getMagnitudeTrue())
	 */
	public Vector GetUnitVectorInSameDirection() throws ArithmeticException //TODO add throws
	{
		if (Util.fuzzyEquals(getMagnitude(), 0))
		{
			throw new ArithmeticException("This vector is a null vector.");
		}
		return this.scaleBy(1.0 / getMagnitude());
	}

	/**
	 * Computes the Euclidean distance between this vector and a given vector.
	 *
	 * @param	v
	 * 			The given vector.
	 * @return	The distance between this vector and the given vector.
	 * 			| result = this.getDifference(v).getMagnitude()
	 */
	public double distanceTo(Vector v) throws ArithmeticException //TODO add throws
	{
		return this.getDifference(v).getMagnitude();
	}

	/**
	 * Checks whether the given object is a vector and its respective components are equal to this vector's components
	 * 
	 * @param	o
	 * 			The given object.
	 * @return	True if and only if the given object is a vector and the respective components of the given object and this vector are equal.
	 * 			| result =(o instanceof Vector && Util.fuzzyEquals(getXComponent(), ((Vector) o).getXComponent()) && Util.fuzzyEquals(getYComponent(), ((Vector) o).getYComponent()))
	 */
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Vector)
		{
			return (Util.fuzzyEquals(getXComponent(), ((Vector) o).getXComponent()) && Util.fuzzyEquals(getYComponent(), ((Vector) o).getYComponent()));
		}
		return false;
	}
}
