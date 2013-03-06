package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of velocities involving an x-component, and an y-component,
 * that is able to compute everything necessary to implement velocities.
 * 
 * @author Syd & Xavier
 * @version 0.0
 */
public class Velocity extends Vector
{
	/**
	 * Sets the x-component of the velocity to a given x-component.
	 *
	 * @param	x
	 * 			The given x-component.
	 */
	@Basic
	@Raw
	@Override
	public void setXComponent(double x)
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
	 */
	@Basic
	@Raw
	@Override
	public void setYComponent(double y)
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
	 * Checks whether the given component can be a component of this velocity.
	 *
	 * @param	x
	 * 			The given component.
	 * @return	True if and only if the given component is at most the speed of light
	 * 			| result = (Util.fuzzyLessThanOrEqualTo(x, getSpeedOfLight()) && super.canHaveAsComponent(x))
	 */
	@Basic
	@Raw
	@Override
	public boolean canHaveAsComponent(double x)
	{
		return (Util.fuzzyLessThanOrEqualTo(x, getSpeedOfLight()) && super.canHaveAsComponent(x));
	}

	/**
	 * Initializes this new velocity with a given x-component and y-component.
	 * 
	 * @param	vx
	 * 			The x-component for this new velocity
	 * @param	vy
	 * 			The y-component for this new velocity
	 * @effect	Sets the x-component of this new velocity to the given x-component
	 * 			| setXComponent(vx)
	 * @effect	Sets the y-component of this new velocity to the given y-component
	 * 			| setYComponent(vy)
	 */
	public Velocity(double vx, double vy)
	{
		super(vx, vy);
		if (this.getMagnitude() > Velocity.getSpeedOfLight())
		{
			setXComponent(0);
			setYComponent(0);
		}
	}

	/**
	 * Initialize this new position with a given vector
	 * 
	 * @param	v
	 * 			The given vector.
	 */
	public Velocity(Vector v)
	{
		super(v.getXComponent(), v.getYComponent());
	}

	/**
	 * Initializes this new velocity.
	 * 
	 * @effect	Sets the x-component of this new velocity to 0.0.
	 * 			| setXComponent(0)
	 * @effect	Sets the y-component of this new velocity to 0.0.
	 * 			| setXComponent(0)
	 */
	public Velocity()
	{
		super();
	}

	/**
	 * Returns the total velocity of this velocity in km/s.
	 * 
	 * @return	The total velocity. 
	 */
	@Basic
	@Raw
	public double getVelocity()
	{
		return getMagnitude();
	}

	/**
	 * Gets the sum of this velocity and the given vector.
	 * 
	 * @param	v
	 * 			The given vector.
	 * @return	The sum of this velocity and the given vector.
	 * 			| result = new Velocity(super.getSum(v))
	 */
	@Override
	public Velocity getSum(Vector v)
	{
		return new Velocity(super.getSum(v));
	}

	/**
	 * accelerates this velocity using a given acceleration during a given duration.
	 * 
	 * @param	a
	 * 			The given acceleration.
	 * @param	duration
	 * 			The given duration.
	 * @throws	IllegalArgumentException
	 * 			The given duration is strictly negative.
	 * 			| duration < 0
	 * @post	Moves this position to the calculated destination.
	 * 			| new.equals(getSum(v.scaleBy(duration)))
	 */
	public void accelerateBy(Acceleration a, double duration)
	{
		if (duration < 0)
		{
			throw new IllegalArgumentException("Invalid duration provided");
		}
		Vector v = new Vector(this.getXComponent(),this.getYComponent());
		v = v.getSum(a.scaleBy(duration));
		if (v.getMagnitude() >= Velocity.getSpeedOfLight()){
			v = v.scaleBy(Velocity.getSpeedOfLight()/v.getMagnitude());
		}
		setXComponent(v.getXComponent());
		setYComponent(v.getYComponent());
	}

	/**
	 * Returns the speed of light in km/s.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getSpeedOfLight()
	{
		return Velocity.SPEED_OF_LIGHT;
	}

	private static double SPEED_OF_LIGHT = 300000;
}
