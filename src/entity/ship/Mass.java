package entity.ship;

import Utilities.Util;
import be.kuleuven.cs.som.annotate.Basic;
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
public class Mass // TODO document
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
	public Mass(double mass) throws IllegalArgumentException
	{
		set(mass);
	}

	/**
	 * Returns the mass value of this object.
	 */
	@Basic
	@Raw
	public double get()
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
	protected boolean canHaveAsMass(double mass)
	{
		return mass > 0;
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
	private void set(double mass) throws IllegalArgumentException
	{
		if (!canHaveAsMass(mass))
		{
			throw new IllegalArgumentException("Illegal mass provided");
		}
		this.mass = mass;
	}

	/**
	 * A variable registering the value of this mass in kg.
	 */
	private double mass;

	/**
	 * Checks if the given mass is the same as this one.
	 * 
	 * @param   o
	 * @return  | (o != null && o.getClass()==getClass() && Util.fuzzyEquals(o.get(),this.get()))
	 */
	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null)
		{
			return false;
		}
		if (getClass() != o.getClass())
		{
			return false;
		}
		Mass other = (Mass) o;
		return Util.fuzzyEquals(other.get(), this.get());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return ("m_" + hashCode() + " = " + this.get() + "kg");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return ("" + get()).hashCode();
	}
}
