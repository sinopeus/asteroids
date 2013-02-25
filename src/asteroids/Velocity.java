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
	 * Sets the x-component of this velocity to the given x-component.
	 * 
	 * @param 	vx
	 *          The new x-component for this velocity.
	 * @post 	If this velocity can have the given x-component as its x-component,
	 *       	then the x-component of this velocity is now equal to the given
	 *       	x-component. | if canHaveAsXComponent(vx) | then
	 *       	new.getXComponent() == vx
	 */
	@Basic
	@Raw
	@Override
	public void setXComponent(double vx)
	{
		if (super.canHaveAsComponent(vx))
		{
			super.setXComponent(vx);
		}
	}

	/**
	 * Sets the y-component of this velocity to the given y-component.
	 * 
	 * @param	vy
	 *          The new y-component for this velocity.
	 * @post 	If this velocity can have the given y-component as its y-component,
	 *       	then the y-component of this velocity is now equal to the given
	 *       	y-component. | if canHaveAsYComponent(vy) | then
	 *       	new.getYComponent() == vy
	 */
	@Basic
	@Raw
	@Override
	public void setYComponent(double vy)
	{
		if (super.canHaveAsComponent(vy))
		{
			super.setYComponent(vy);
		}
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
