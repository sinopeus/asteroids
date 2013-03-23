package main;

import world.World;
import Utilities.Util;
import entity.Entity;

public class CollisionResolver implements CollisionListener
{
	@Override
	public void boundaryCollision(Object entity, double x, double y)
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
	}

	@Override
	public void objectCollision(Object entity1, Object entity2, double x, double y)
	{

	}

	private static double bounciness = 1;
}
