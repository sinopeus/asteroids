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
	}

	/**
	 * Initializes this new velocity.
	 * 
	 * @effect Sets the x-component of this new velocity to 0.0.
	 * 
	 * @effect Sets the y-component of this new velocity to 0.0.
	 */
	public Velocity()
	{
		super();
	}

	/**
	 * Returns the total velocity of this velocity in km/s.
	 * 
	 * @return	The total velocity. The total velocity can never exceed the speed of light.
	 * 			| Util.fuzzyLessThanOrEqualTo(result,Velocity.getSpeedOfLight())
	 */
	@Basic
	@Raw
	public double getVelocity()
	{
		double totalVelocity = (Math.sqrt(Math.pow(super.getXComponent(), 2) + Math.pow(super.getYComponent(), 2)));
		if (Util.fuzzyLessThanOrEqualTo(totalVelocity, Velocity.getSpeedOfLight()))
		{
			return totalVelocity;
		} else
		{
			return Velocity.getSpeedOfLight();
		}
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
