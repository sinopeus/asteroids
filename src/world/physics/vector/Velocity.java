package world.physics.vector;

import Utilities.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of velocity vectors.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 * 
 * @invar 	The magnitude of the velocity is always at most the speed of light.
 * 			| this.getVelocity <= Velocity.getSpeedOfLight()
 */
public class Velocity extends Vector //TODO make total
{
	/**
	 * Initializes this new velocity with a given x-component and y-component.
	 * 
	 * @param	x
	 * 			The x-component for this new velocity
	 * @param	y
	 * 			The y-component for this new velocity
	 * @effect	Sets the x-component of this new velocity to the given x-component, scaled not to exceed the speed of light.
	 * 			| setXComponent(x)
	 * @effect	Sets the y-component of this new velocity to the given y-component, scaled not to exceed the speed of light.
	 * 			| setYComponent(y)
	 * @post	The magnitude of the velocity is always at most the speed of light.
	 * 			| this.getVelocity <= Velocity.getSpeedOfLight()
	 */
	public Velocity (double x, double y)
	{
		super(x, y);
		if (this.getMagnitude() > Velocity.getSpeedOfLight())
		{
			Vector downScaled = this.getScaledBy(Velocity.getSpeedOfLight() / this.getMagnitude());
			setX(downScaled._X());
			setY(downScaled._Y());
		}
	}

	/**
	 * Initialize this new position with a given vector
	 * 
	 * @param	v
	 * 			The given vector.
	 * @effect	Initializes this new Velocity with the extended velocity constructor.
	 * 			| this(v.getXComponent(),v.getYComponent());
	 * @post	The magnitude of the velocity is always at most the speed of light.
	 * 			| this.getVelocity <= Velocity.getSpeedOfLight()
	 * @throws	IllegalArgumentException
	 * 			The given vector is null.
	 * 			| v == null
	 */
	public Velocity (Vector v) throws IllegalArgumentException
	{
		this(v._X(), v._Y());
	}

	/**
	 * Initializes this new velocity with default values.
	 * 
	 * @effect	Initializes this new velocity with the simple vector constructor.
	 * 			| Vector()
	 */
	public Velocity ()
	{
		super();
	}

	/**
	 * Sets the x-component of the velocity to a given x-component.
	 *
	 * @param	x
	 * 			The given x-component.
	 * @post	If the given x-component is a valid component, then the x-component of this vector is now equal to the given x-component, otherwise it is equal to zero.
	 * 			| if(canHaveAsComponent(x))
	 * 			|  then Util.fuzzyEquals(new.getXComponent(),x)
	 * 			| else
	 * 			|  Util.fuzzyEquals(new.getXComponent,0)
	 */
	@Basic
	@Raw
	@Override
	public void setX (double x)
	{
		if (canHaveAsComponent(x))
		{
			this.x = x;
		} else
		{
			this.x = 0;
		}
	}

	/**
	 * Sets the y-component of the velocity to a given y-component.
	 *
	 * @param	y
	 * 			The given y-component.
	 * @post	If the given y-component is a valid component, then the y-component of this vector is now equal to the given y-component, otherwise it is equal to zero.
	 * 			| if(canHaveAsComponent(y))
	 * 			|  then Util.fuzzyEquals(new.getXComponent(),y)
	 * 			| else
	 * 			|  Util.fuzzyEquals(new.getXComponent,0)
	 */
	@Basic
	@Raw
	@Override
	public void setY (double y)
	{
		if (canHaveAsComponent(y))
		{
			this.y = y;
		} else
		{
			this.y = 0;
		}
	}

	/**
	 * Gets the total velocity of this velocity in km/s.
	 * 
	 * @return	The total velocity. 
	 */
	@Basic
	@Raw
	public double get ()
	{
		return getMagnitude();
	}

	/**
	 * Gets the sum of this velocity and a given vector.
	 *
	 * @param	v
	 * 			The given vector.
	 * @return	A velocity whose components are the sums of the respective components of this velocity and the given vector.
	 * 			| result == new Velocity(getXComponent() + v.getXComponent(), getYComponent() + v.getYComponent())
	 * @throws	ArithmeticException
	 * 			One of the components of the resulting velocity is not a number.
	 * 			| Double.isNaN(result.getXComponent) || Double.isNaN(result.getYComponent)	 
	 */
	@Override
	public Velocity getSum (Vector v) throws ArithmeticException
	{
		return new Velocity(super.getSum(v));
	}

	/**
	 * Accelerates this velocity using a given acceleration during a given duration.
	 * 
	 * @param	a
	 * 			The given acceleration.
	 * @param	duration
	 * 			The given duration.
	 * @post	Accelerates this Velocity to the calculated velocity.
	 * 			| new.equals(getSum(v.scaleBy(duration)))
	 * @throws	IllegalArgumentException
	 * 			The given duration is strictly negative.
	 * 			| duration < 0
	 * @throws	ArithmeticException
	 * 			Any of the resulting components is not a valid component.
	 * 			| (!canHaveAsComponent(getXComponent()) || ! canHaveAsComponent(getYComponent()))
	 */
	public void accelerateBy (Acceleration a, double duration) throws ArithmeticException
	{
		if (duration < 0) { throw new IllegalArgumentException("Invalid duration provided"); }
		Vector v = new Vector(this._X(), this._Y());
		v = v.getSum(a.getScaledBy(duration));
		if (v.getMagnitude() >= Velocity.getSpeedOfLight())
		{
			v = v.getScaledBy(Velocity.getSpeedOfLight() / v.getMagnitude());
		}
		setX(v._X());
		setY(v._Y());
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return A representation of this object in String format.
	 */
	@Override
	public String toString ()
	{
		return "V_" + hashCode() + " = " + super.toString() + " m/s";
	}

	/**
	 * Checks whether the given object is a velocity and its respective components are equal to this velocity's components
	 * 
	 * @param	o
	 * 			The given object.
	 * @return	True if and only if the given object is a velocity and the respective components of the given object and this velocity are equal.
	 * 			| result =(o != null) && (getClass() != o.getClass()) && Util.fuzzyEquals(getXComponent(), ((Velocity) o).getXComponent()) && Util.fuzzyEquals(getYComponent(), ((Velocity) o).getYComponent()))
	 */
	@Override
	@Raw
	public boolean equals (Object o)
	{
		if (o == null) { return false; }
		if (getClass() != o.getClass()) { return false; }
		Velocity other = (Velocity) o;
		return (Util.fuzzyEquals(this._X(), other._X()) && Util.fuzzyEquals(this._Y(), other._Y()));
	}

	/**
	 * Gets the speed of light in km/s.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	@Immutable
	public static double getSpeedOfLight ()
	{
		return Velocity.SPEED_OF_LIGHT;
	}

	private static double	SPEED_OF_LIGHT	= 300000;
}
