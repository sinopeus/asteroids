package asteroids;

import be.kuleuven.cs.som.annotate.Basic;
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
	 * A variable registering the x-component of this Velocity.
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
	 * A variable registering the y-component of this Velocity.
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

}
