package world.physics;

import Utilities.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;

/**
 * A value class of masses involving a mass value.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 * 
 * @invar	The mass value of a mass is a valid mass value.
 * 			| canHaveAsMass(getMass)
 */
@Value
public class Mass
{
	/**
	 * Creates a new mass.
	 *
	 * @param	mass
	 *       	| The mass value we want to assign.
	 * @effect  This mass is set using the given mass value.
	 *          | set(mass)
	 * @throws 	IllegalArgumentException
	 *         	| !canHaveAsMass(mass)
	 */
	public Mass (double mass) throws IllegalArgumentException
	{
		set(mass);
	}

	/**
	 * Returns the mass value of this object.
	 */
	@Basic
	@Raw
	public double get ()
	{
		return mass;
	}

	/**
	 * Checks if the given value can be a mass value.
	 * 
	 * @return	| (mass > 0)
	 */
	@Basic
	@Raw
	@Model
	protected boolean canHaveAsMass (double mass)
	{
		return ( (!Double.isNaN(mass)) && (mass > 0));
	}

	/**
	 * Sets the mass value of this mass to the given mass.
	 * 
	 * @param 	mass
	 * 			The given mass
	 * @post 	| if (canHaveAsMass()) new.mass = mass
	 */
	@Basic
	@Raw
	@Model
	protected void set (double mass) throws IllegalArgumentException
	{
		if (!canHaveAsMass(mass)) { throw new IllegalArgumentException("Illegal mass provided"); }
		this.mass = mass;
	}

	/**
	 * A variable registering the value of this mass in kg.
	 */
	private double	mass;

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return A representation of this object in String format.
	 */
	@Override
	public String toString ()
	{
		return ("m_" + hashCode() + " = " + this.get() + "kg");
	}

	@Override
	public int hashCode ()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(mass);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals (Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Mass other = (Mass) obj;
		if (Double.doubleToLongBits(mass) != Double.doubleToLongBits(other.mass)) return false;
		return true;
	}
}
