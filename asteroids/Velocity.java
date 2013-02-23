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
public class Velocity
{

	/**
	 * Return the x-component of this velocity.
	 */
	@Basic
	@Raw
	public double getXComponent()
	{
		return this.vx;
	}

	/**
	 * Check whether this velocity can have the given x-component as its
	 * x-component.
	 * 
	 * @param	vx
	 *          The x-component to check.
	 * @return 	True
	 */
	@Basic
	@Raw
	public boolean canHaveAsXComponent(double vx)
	{
		return (true);
	}

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
	public void setXComponent(double vx)
	{
		if (canHaveAsXComponent(vx))
		{
			this.vx = vx;
		}
	}

	/**
	 * A variable registering the x-component of this velocity in km/s.
	 */
	private double vx;

	/**
	 * Return the y-component of this velocity.
	 */
	@Basic
	@Raw
	public double getYComponent()
	{
		return this.vy;
	}

	/**
	 * Check whether this velocity can have the given y-component as its
	 * y-component.
	 * 
	 * @param 	vy
	 *          The y-component to check.
	 * @return 	True
	 */
	@Basic
	@Raw
	public boolean canHaveAsYComponent(double vy)
	{
		return (true);
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
	public void setYComponent(double vy)
	{
		if (canHaveAsXComponent(vy))
		{
			this.vy = vy;
		}
	}

	/**
	 * A variable registering the y-component of this velocity in km/s.
	 */
	private double vy;

	/**
	 * Initialize this new velocity with a given x-component and y-component.
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
		setXComponent(vx);
		setYComponent(vy);
	}

	/**
	 * Initialize this new velocity.
	 * 
	 * @effect Sets the x-component of this new velocity to 0.0.
	 * 
	 * @effect Sets the y-component of this new velocity to 0.0.
	 */
	public Velocity()
	{
		this(0, 0);
	}

	/**
	 * Return the total velocity of this velocity in km/s.
	 * 
	 * @return	The total velocity. The total velocity can never exceed the speed of light.
	 * 			| Util.fuzzyLessThanOrEqualTo(result,Velocity.getSpeedOfLight())
	 */
	@Basic
	@Raw
	public double getVelocity()
	{
		double totalVelocity = (Math.sqrt(Math.pow(this.getXComponent(), 2) + Math.pow(this.getYComponent(), 2)));
		if (Util.fuzzyLessThanOrEqualTo(totalVelocity, Velocity.getSpeedOfLight()))
		{
			return totalVelocity;
		} else
		{
			return Velocity.getSpeedOfLight();
		}
	}

	/**
	 * Return the speed of light in km/s.
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
