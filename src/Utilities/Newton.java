package Utilities;

import vector.Acceleration;
import vector.Force;
import entity.ship.Mass;

/**
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 */
public class Newton
{
	/**
	 * @param 	m1
	 * 			The first mass. 
	 * @param 	m2
	 * 			The second mass.
	 * @param 	a1
	 * 			The first acceleration.
	 * @return	The second acceleration, as calculated from the arguments.
	 *        	| return new Acceleration((a1.getScaledBy(m1.get() / m2.get()))
	 * @throws 	IllegalArgumentException
	 * 			One of the arguments are null
	 */
	public static Acceleration firstLaw_CalculateAcceleration(Mass m1, Mass m2, Acceleration a1) throws IllegalArgumentException
	{
		if (m1 == null || m2 == null || a1 == null)
		{
			throw new IllegalArgumentException("Null parameter provided.");
		}
		return new Acceleration(a1.getScaledBy(m1.get() / m2.get()));
	}

	/**
	 * @param 	f
	 * 			The given force.
	 * @param	m
	 * 			The given mass.
	 * @return  The acceleration corresponding to this force and mass.
	 *          | return (f.getScaledBy(m.get()))
	 * @throws 	IllegalArgumentException
	 * 			One of the arguments are null
	 */
	public static Acceleration secondLaw_CalculateAcceleration(Force f, Mass m) throws IllegalArgumentException
	{
		if (f == null || m == null)
		{
			throw new IllegalArgumentException("Null parameter provided.");
		}
		return new Acceleration(f.getScaledBy(1/m.get()));
	}

	/**
	 * @param   f1
	 * 			The given force.
	 * @return  The opposite force.
	 *          | return f1.getScaledBy(-1.0)
	 * @throws 	IllegalArgumentException
	 * 			The given force is null
	 */
	public static Force thirdLaw_CalculateForce(Force f1) throws IllegalArgumentException
	{
		if (f1 == null)
		{
			throw new IllegalArgumentException("Null parameter provided.");
		}
		return new Force(f1.getScaledBy(-1.0));
	}
}
;