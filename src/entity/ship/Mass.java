package entity.ship;

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
		//TODO
	}

	/**
	 * Returns the mass value of this object.
	 */
	@Basic
	@Raw
	public double get()
	{
		//TODO
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
		//TODO
	}

	/**
	 * Sets 	the mass value of this object.
	 * 
	 * @param 	mass
	 * @post 	| if (canHaveAsMass()) new.mass = mass
	 */
	@Basic
	@Raw
	private void set(double mass)
	{
		//TODO
	}

	/**
	 * A variable registering the value of this mass in kg.
	 */
	private final double mass;

	/**
	 * Checks if the given mass is the same as this one.
	 * 
	 * @param   o
	 * @return  | (o != null && o.getClass()==getClass() && Util.fuzzyEquals(o.get(),this.get()))
	 */
	@Override
	public boolean equals(Object o)
	{
		//TODO
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		//TODO
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		//TODO
	}
}
