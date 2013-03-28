package world.physics;

import world.physics.vector.Acceleration;
import world.physics.vector.Force;
import world.physics.vector.Velocity;

/**
 * A class dictating every used law in Newtonian physics.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 */
public class Mechanics
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
	 * 			One of the arguments is null.
	 * 			| m1 == null || m2 == null || a1 == null
	 */
	public static Acceleration Newtons_firstLaw_CalculateAcceleration (Mass m1, Mass m2, Acceleration a1) throws IllegalArgumentException
	{
		if (m1 == null || m2 == null || a1 == null) { throw new IllegalArgumentException("Null parameter provided."); }
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
	 * 			One of the arguments is null.
	 * 			| f == null || m == null
	 */
	public static Acceleration Newtons_secondLaw_CalculateAcceleration (Force f, Mass m) throws IllegalArgumentException
	{
		if (f == null || m == null) { throw new IllegalArgumentException("Null parameter provided."); }
		return new Acceleration(f.getScaledBy(1 / m.get()));
	}

	/**
	 * @param   f1
	 * 			The given force.
	 * @return  The opposite force.
	 *          | return f1.getScaledBy(-1.0)
	 * @throws 	IllegalArgumentException
	 * 			The given force is null.
	 * 			| f1 == null
	 */
	public static Force Newtons_thirdLaw_CalculateForce (Force f1) throws IllegalArgumentException
	{
		if (f1 == null) { throw new IllegalArgumentException("Null parameter provided."); }
		return new Force(f1.getScaledBy(-1.0));
	}

	/**
	 * Returns the velocity as dictated by the conservation of momentum, given the velocity and mass of the first object, and the mass of the second object.
	 * 
	 * @param 	v1
	 * 			The given velocity of the first object.
	 * @param 	m1
	 * 			The given mass of the first object.
	 * @param 	m2
	 * 			The given mass of the second object.
	 * @return	A velocity equal and oposite to the velocity of the first object, scaled according to the masses of the objects.
	 * 			| result == new Velocity(v1.getScaledBy(m1.get() / m2.get()));
	 * @throws 	IllegalArgumentException
	 * 			One of the arguments is null.
	 * 			| (v1 == null || m1 == null || m2 == null)
	 */
	public static Velocity conservationOfMomentum_CalculateVelocity (Velocity v1, Mass m1, Mass m2) throws IllegalArgumentException
	{
		if (v1 == null || m1 == null || m2 == null) { throw new IllegalArgumentException("Null parameter provided."); }
		return new Velocity(v1.getScaledBy(m1.get() / m2.get()));
	}
};
