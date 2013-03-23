package main;

import world.World;
import Utilities.Util;
import entity.Bullet;
import entity.Entity;

public class CollisionResolver implements CollisionListener
{
	/**
	 * Resolves the collision of a given entity at a given position with a boundary of the world.
	 * 
	 * @param	entity
	 * 			The given entity.
	 * @param	x
	 * 			The x coordinate of the given collision position.
	 * @param	y
	 * 			The y coordinate of the given collision position.
	 * @throws	IllegalArgumentException
	 * 			One of the given objects is not an entity.
	 */
	@Override
	public void boundaryCollision(Object entity, double x, double y) throws IllegalArgumentException
	{
		if (!(entity instanceof Entity))
		{
			throw new IllegalArgumentException("The provided object is not an entity");
		}
		Entity e = (Entity) entity;

		World w = e.getWorld();

		//One case for every boundary to hit
		if (Util.fuzzyEquals(x, 0))
		{
			e.getVelocity().setYComponent(-e.getVelocity().getYComponent());
		}
		if (Util.fuzzyEquals(y, 0))
		{
			e.getVelocity().setXComponent(-e.getVelocity().getXComponent());
		}
		if (Util.fuzzyEquals(x, w.getxSize()))
		{
			e.getVelocity().setYComponent(-e.getVelocity().getYComponent());
		}
		if (Util.fuzzyEquals(y, w.getySize()))
		{
			e.getVelocity().setXComponent(-e.getVelocity().getXComponent());
		}

		if (e instanceof Bullet)
		{
			Bullet b = (Bullet) e;
			b.setBounceCounter((byte) (b.getBounceCounter() + 1));
		}
	}

	/**
	 * Resolves the collision of two given entities at a given position.
	 * 
	 * @param	entity1
	 * 			The fist given entity.
	 * @param	entity2
	 * 			The second given entity.
	 * @param	x
	 * 			The x coordinate of the given collision position.
	 * @param	y
	 * 			The y coordinate of the given collision position.
	 * @throws	IllegalArgumentException
	 * 			One of the given objects is not an entity.
	 */
	@Override
	public void objectCollision(Object entity1, Object entity2, double x, double y) throws IllegalArgumentException
	{
		if (!(entity1 instanceof Entity) || !(entity2 instanceof Entity))
		{
			throw new IllegalArgumentException("The provided object is not an entity");
		}
		Entity e1 = (Entity) entity1;
		Entity e2 = (Entity) entity2;

		//TODO
	}

	private static double bounciness = 1;
}
