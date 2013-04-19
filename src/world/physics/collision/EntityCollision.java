package world.physics.collision;

import world.World;
import world.entity.Entity;
import world.physics.vector.Position;
import world.physics.vector.Vector;

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
	 * Creates a new collision between entities.
	 * 
	 * @param world		The world in which collision occurs.
	 * @param entity1	The first entity involved in the collision.
	 * @param entity2	The second entity involved in the collision.
	 */
	public EntityCollision (World world, Entity entity1, Entity entity2)
	{
		super(world);
		setEntity1(entity1);
		setEntity2(entity2);

		calculateCollisionTime();
	}

	/**
	 * Returns the first entity involved in the collision.
	 * 
	 * @return entity1	The first entity involved in the collision. 
	 */
	public Entity getEntity1 ()
	{
		return entity1;
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
	public void setEntity1 (Entity entity1) throws IllegalArgumentException
	{
		if (!canHaveAsEntity(entity1)) throw new IllegalArgumentException("Illegal entity provided.");
		this.entity1 = entity1;
	}

	/**
	 * Returns the second entity involved in the collision.
	 * 
	 * @return entity2	The second entity involved in the collision. 
	 */
	public Entity getEntity2 ()
	{
		return entity2;
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
	private void setEntity2 (Entity entity2) throws IllegalArgumentException
	{
		if (!canHaveAsEntity(entity2)) throw new IllegalArgumentException("Illegal entity provided.");
		this.entity2 = entity2;
	}

	/**
	 * Returns whether this entity can be an entity.
	 * 
	 * @param entity
	 * @return
	 */
	protected boolean canHaveAsEntity (Entity entity)
	{
		return (entity != null);
	}

	/**
	 * The first entity involved in the collision.
	 */
	private Entity	entity1;

	/**
	 * The second entity involved in the collision.
	 */
	private Entity	entity2;

	/**
	 * @see world.physics.collision.Collision#resolve()
	 */
	@Override
	public void resolve ()
	{
		if (entity1 == entity2) return;
		entity1.collideWith(entity2);
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
		if ( (entity1 == null) || (entity2 == null)) { throw new IllegalArgumentException("One of the given entities is null."); }

		double sigma = entity1.getShape().getRadius() + entity2.getShape().getRadius(); // size difference between entitities

		Vector deltaR = entity2.getPosition().getDifference(entity1.getPosition()); // distance between entitites
		Vector deltaV = entity2.getVelocity().getDifference(entity1.getVelocity()); // difference of the velocities

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
	 */
	@Override
	protected void calculateCollisionPosition ()
	{
		if ( (entity1 == null) || (entity2 == null)) { throw new IllegalArgumentException("One of the given entities is null."); }

		double deltaT = getTimeToCollision();

		if (Double.isInfinite(deltaT)) { return; }

		Position newPosShip1 = entity1.getPosition().getSum(entity1.getVelocity().getScaledBy(deltaT));
		Position newPosShip2 = entity2.getPosition().getSum(entity2.getVelocity().getScaledBy(deltaT));

		double sigma = entity1.getShape().getRadius() + entity2.getShape().getRadius();
		double ship1Radius = entity1.getShape().getRadius();

		this.collisionPosition = new Position(newPosShip1.getSum(newPosShip2.getDifference(newPosShip1).getScaledBy(ship1Radius / sigma)));
	}

	@Override
	public String toString ()
	{
		return "Entity" + super.toString() + " of " + getEntity1() + " and " + getEntity1();
	}
}
