package Utilities;

import vector.Acceleration;
import vector.Force;
import vector.Position;
import vector.Vector;
import entity.Entity;
import entity.ship.Mass;

/**
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
	 * 			One of the arguments are null
	 */
	public static Acceleration Newtons_firstLaw_CalculateAcceleration(Mass m1, Mass m2, Acceleration a1) throws IllegalArgumentException
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
	public static Acceleration Newtons_secondLaw_CalculateAcceleration(Force f, Mass m) throws IllegalArgumentException
	{
		if (f == null || m == null)
		{
			throw new IllegalArgumentException("Null parameter provided.");
		}
		return new Acceleration(f.getScaledBy(1 / m.get()));
	}

	/**
	 * @param   f1
	 * 			The given force.
	 * @return  The opposite force.
	 *          | return f1.getScaledBy(-1.0)
	 * @throws 	IllegalArgumentException
	 * 			The given force is null
	 */
	public static Force Newtons_thirdLaw_CalculateForce(Force f1) throws IllegalArgumentException
	{
		if (f1 == null)
		{
			throw new IllegalArgumentException("Null parameter provided.");
		}
		return new Force(f1.getScaledBy(-1.0));
	}

	/**
	 * Gets the time to collision between two entities.
	 *
	 * @param   entity1
	 *          The first entity.
	 * @param   entity2
	 *          The second entity.
	 * @return  The time to collision between the two entities.
	 *			
	 * @throws  IllegalArgumentException
	 *          One of the given entities is null.
	 *          | ((entity1 == null) || (entity2 == null))
	 */
	public static double getTimeToCollision(Entity entity1, Entity entity2) throws IllegalArgumentException
	{
		if ((entity1 == null) || (entity2 == null))
		{
			throw new IllegalArgumentException("One of the given entities is null.");
		}

		double sigma = entity1.getShape().getRadius() + entity2.getShape().getRadius();
		Vector deltaR = entity2.getPosition().getDifference(entity1.getPosition());
		Vector deltaV = entity2.getVelocity().getDifference(entity1.getVelocity());
		double d = (Math.pow(deltaV.dotProduct(deltaR), 2)) - ((deltaV.dotProduct(deltaV)) * (deltaR.dotProduct(deltaR) - Math.pow(sigma, 2)));
		if (deltaV.dotProduct(deltaR) >= 0 || d <= 0)
		{
			return Double.POSITIVE_INFINITY;
		} else
		{
			return -(deltaV.dotProduct(deltaR) + Math.sqrt(d)) / (deltaV.dotProduct(deltaV));
		}
	}

	/**
	 * Gets the position at which two entities will collide.
	 *
	 * @param   entity1
	 *          The first entity.
	 * @param   entity2
	 *          The second entity.
	 * @return  The position of the place of collision.
	 * 
	 * @throws  IllegalArgumentException
	 *          One of the given entities is null.
	 *          | ((entity1 == null) || (entity1 == null))
	 */
	public static Position getCollisionPosition(Entity entity1, Entity entity2) throws IllegalArgumentException //
	{
		if ((entity1 == null) || (entity2 == null))
		{
			throw new IllegalArgumentException("One of the given entities is null.");
		}

		double deltaT = getTimeToCollision(entity1, entity2);

		if (Double.isInfinite(deltaT))
		{
			return null;
		}

		Position newPosShip1 = entity1.getPosition().getSum(entity1.getVelocity().getScaledBy(deltaT));
		Position newPosShip2 = entity2.getPosition().getSum(entity2.getVelocity().getScaledBy(deltaT));

		double sigma = entity1.getShape().getRadius() + entity2.getShape().getRadius();
		double ship1Radius = entity1.getShape().getRadius();

		return new Position(newPosShip1.getSum(newPosShip2.getDifference(newPosShip1).getScaledBy(ship1Radius / sigma)));
	}
};
