package world.physics.collision;

import world.World;
import world.entity.Entity;
import world.physics.vector.Position;
import world.physics.vector.Vector;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of entity collisions extending collisions
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 * 
 * @invar	Both entities of this entity collision are valid
 * 			| canHaveAsEntity(getEntity1()) && canHaveAsEntity(getEntity2())
 */
public final class EntityCollision extends Collision
{
	/**
	 * Creates a new collision between entities. //TODO document
	 * 
	 * @param	world	
	 * 			The world in which collision occurs.
	 * @param	entity1
	 * 			The first entity involved in the collision.
	 * @param	entity2
	 * 			The second entity involved in the collision.
	 */
	public EntityCollision (World world, Entity entity1, Entity entity2)
	{
		super(world);
		this.entity = new Entity[2];
		setEntity1(entity1);
		setEntity2(entity2);
		calculateCollisionTime();
	}

	/**
	 * Returns the first entity involved in the collision.
	 * 
	 * @return entity1	The first entity involved in the collision. 
	 */
	@Basic
	@Raw
	public Entity getEntity1 ()
	{
		return entity[0];
	}

	/**
	 * Set the first entity involved involved in the collision.
	 * 
	 * @param	entity1
	 * 			The given first entity.
	 * @throws	IllegalArgumentException
	 * 			The given entity is not a valid entity.
	 * 			| !canHaveAsEntity(entity2)
	 */
	@Basic
	@Raw
	public void setEntity1 (@Raw Entity entity1) throws IllegalArgumentException
	{
		if (!canHaveAsEntity(entity1)) throw new IllegalArgumentException("Illegal entity provided.");
		this.entity[0] = entity1;
	}

	/**
	 * Returns the second entity involved in the collision.
	 * 
	 * @return entity2	The second entity involved in the collision. 
	 */
	@Basic
	@Raw
	public Entity getEntity2 ()
	{
		return entity[1];
	}

	/**
	 * Set the second entity involved involved in the collision.
	 * 
	 * @param	entity2
	 * 			The given second entity.
	 * @throws	IllegalArgumentException
	 * 			The given entity is not a valid entity.
	 * 			| !canHaveAsEntity(entity2)
	 */
	@Basic
	@Raw
	private void setEntity2 (@Raw Entity entity2) throws IllegalArgumentException
	{
		if (!canHaveAsEntity(entity2)) throw new IllegalArgumentException("Illegal entity provided.");
		this.entity[1] = entity2;
	}

	/**
	 * Returns whether this entity can be an entity.
	 * 
	 * @param 	entity
	 * 			The entity to be checked for validity.
	 * @return	| entity != null
	 */
	@Basic
	protected static boolean canHaveAsEntity (@Raw Entity entity)
	{
		return (entity != null);
	}

	/**
	 * A variable referencing the array of entities involved in this collision.
	 */
	private final Entity[]	entity;

	/**
	 * @see world.physics.collision.Collision#resolve()
	 */
	@Override
	public void resolve ()
	{
		if (entity[0] == entity[1]) return;
		entity[0].collideWith(entity[1]);
	}

	/**
	 * Calculates the time to collision between two entities.
	 * 
	 * @post  The resulting time is not negative and different from Double.NaN
	 *        | Util.fuzzyLeq(0.0,new.getCollisionTime() && (! Double.isNaN(new.getCollisionTime()))
	 * @post  If the resulting time is finite, the distance between both
	 *          ships would be fuzzy equal to zero if they would both move
	 *          during the resulting time.
	 *        | if (new.getCollisionTime() < Double.POSITIVE_INFINITY) then
	 *        |   Util.fuzzyEquals(this.distanceBetween(other,new.getCollisionTime()),0.0)
	 * @post  If the resulting distance is finite, the distance between both ships
	 *          would be fuzzy different from zero if they would move for a time shorter than the
	 *          resulting time.
	 *        | if (new.getCollisionTime() < Double.POSITIVE_INFINITY) then
	 *        |   for each time in 0.0..result:
	 *        |     if (time < new.getCollisionTime())
	 *        |       then ! Util.fuzzyEquals(getEntity1().distanceTo(getEntity2(),time),0.0)
	 * @post  If the resulting time is infinite, this ship is the same as the
	 *          other ship or the distance between both
	 *          ships would be different from zero for each finite time they would move.
	 *        | if (result == Double.POSITIVE_INFINITY) then
	 *        |   (this == other) ||
	 *        |   (for each time in 0.0..Double.POSITIVE_INFINITY:
	 *        |     if (! Double.isInfinite(time)) then
	 *        |       (! Util.fuzzyEquals(this.distanceBetween(other,time),0.0))
	 * @throws  IllegalArgumentException
	 *          One of the given entities is null.
	 *          | ((entity1 == null) || (entity2 == null))
	 */
	@Override
	protected void calculateCollisionTime () throws IllegalArgumentException
	{
		if ( (entity[0] == null) || (entity[1] == null)) { throw new IllegalArgumentException("One of the given entities is null."); }

		double sigma = entity[0].getShape().getRadius() + entity[1].getShape().getRadius(); // size difference between entitities

		Vector deltaR = entity[1].getPosition().getDifference(entity[0].getPosition()); // distance between entitites
		Vector deltaV = entity[1].getVelocity().getDifference(entity[0].getVelocity()); // difference of the velocities

		double d = (Math.pow(deltaV.dotProduct(deltaR), 2)) - ( (deltaV.dotProduct(deltaV)) * (deltaR.dotProduct(deltaR) - Math.pow(sigma, 2)));

		if (deltaV.dotProduct(deltaR) >= 0 || d <= 0) this.timeToCollision = Double.POSITIVE_INFINITY;
		else this.timeToCollision = - (deltaV.dotProduct(deltaR) + Math.sqrt(d)) / (deltaV.dotProduct(deltaV));
	}

	/**
	 * Sets the position at which two entities will collide.
	 * 
	 * @throws  IllegalArgumentException
	 *          One of the given entities is null.
	 *          | ((entity1 == null) || (entity1 == null))
	 * @effect	| dt = time to collision
	 * 			| p1, p2 = positions at collision time
	 * 			| sigma = radius 1 + radius 2
	 * 			| collisionPosition = p1 + ((radius 1 / sigma) (p2 - p1)) 
	 */
	@Override
	protected void calculateCollisionPosition ()
	{
		if ( (entity[0] == null) || (entity[1] == null)) { throw new IllegalArgumentException("One of the given entities is null."); }

		double deltaT = getTimeToCollision();

		if (Double.isInfinite(deltaT)) { return; }

		Position newPosShip1 = entity[0].getPosition().getSum(entity[0].getVelocity().getScaledBy(deltaT));
		Position newPosShip2 = entity[1].getPosition().getSum(entity[1].getVelocity().getScaledBy(deltaT));

		double sigma = entity[0].getShape().getRadius() + entity[1].getShape().getRadius();
		double ship1Radius = entity[0].getShape().getRadius();

		this.collisionPosition = new Position(newPosShip1.getSum(newPosShip2.getDifference(newPosShip1).getScaledBy(ship1Radius / sigma)));
	}

	/**
	 * Returns a string representation of this collision.
	 * 
	 * @return A representation in String format of this collision.
	 */
	@Override
	public String toString ()
	{
		return "Entity" + super.toString() + " of " + getEntity1() + " and " + getEntity1();
	}

	@Override
	public int hashCode ()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( (entity[0] == null) ? 0 : entity[0].hashCode());
		result = prime * result + ( (entity[1] == null) ? 0 : entity[1].hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj)
	{
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		EntityCollision other = (EntityCollision) obj;
		if (entity[0] == null)
		{
			if (other.entity[0] != null) return false;
		} else if (!entity[0].equals(other.entity[0])) return false;
		if (entity[1] == null)
		{
			if (other.entity[1] != null) return false;
		} else if (!entity[1].equals(other.entity[1])) return false;
		return true;
	}
}
