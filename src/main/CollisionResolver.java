package main;

import vector.Position;
import vector.Vector;
import vector.Velocity;
import world.World;
import Utilities.Util;
import entity.Asteroid;
import entity.Bullet;
import entity.Entity;
import entity.ship.Ship;

/**
 * TODO document
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 */
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
			if (b.getBounceCounter() > Bullet.maximumBorderBounces)
			{
				b.terminate();
			}
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
		// see: http://www.vobarian.com/collisions/2dcollisions2.pdf for and explanation.
		if (!(entity1 instanceof Entity) || !(entity2 instanceof Entity))
		{
			throw new IllegalArgumentException("One of the provided objects is not an entity");
		}
		Entity e1 = (Entity) entity1;
		Entity e2 = (Entity) entity2;
		
		// TODO this part is written shitty, any ideas?
		if ((e1 instanceof Ship) && (e2 instanceof Ship))
		{
			bounce(e1, e2);
		} else if ((e1 instanceof Asteroid) && (e2 instanceof Asteroid))
		{
			bounce(e1, e2);
		} else if ((e1 instanceof Asteroid) && (e2 instanceof Bullet))
		{
			Asteroid a = (Asteroid) e1;
			Bullet b = (Bullet) e2;
			b.terminate();
			a.terminate();
		} else if ((e1 instanceof Bullet) && (e2 instanceof Asteroid))
		{
			Asteroid a = (Asteroid) e2;
			Bullet b = (Bullet) e1;
			b.terminate();
			a.terminate();
		} else if ((e1 instanceof Ship) && (e2 instanceof Bullet))
		{
			Ship s = (Ship) e1;
			Bullet b = (Bullet) e2;
			if (b.getShooter() != s)
			{
				b.terminate();
				s.terminate();
			}
		} else if ((e1 instanceof Bullet) && (e2 instanceof Ship))
		{
			Ship s = (Ship) e2;
			Bullet b = (Bullet) e1;
			if (b.getShooter() != s)
			{
				b.terminate();
				s.terminate();
			}
		} else if ((e1 instanceof Ship) && (e2 instanceof Asteroid))
		{
			Ship s = (Ship) e1;
			Asteroid a = (Asteroid) e2;
			s.terminate();
		} else if ((e1 instanceof Asteroid) && (e2 instanceof Ship))
		{
			Ship s = (Ship) e2;
			Asteroid a = (Asteroid) e1;
			s.terminate();
		}

	}

	public void bounce(Entity entity1, Entity entity2)
	{
		Position p1 = entity1.getPosition();
		Position p2 = entity2.getPosition();
		Velocity v1 = entity1.getVelocity();
		Velocity v2 = entity2.getVelocity();
		double m1 = entity1.getMass().get();
		double m2 = entity2.getMass().get();

		//--VELOCITY CALCULATION--
		//step 1: find the unit normal vector and the unit tangent vector.
		Vector n = new Vector(p2.getXComponent() - p1.getXComponent(), p2.getYComponent() - p1.getYComponent());
		Vector un = n.getScaledBy(1 / n.getMagnitude());
		Vector ut = new Vector(-un.getYComponent(), un.getXComponent());

		//step 2: find the components of the initial velocity in the normal and tangent directions.
		double v1n = v1.dotProduct(un);
		double v1t = v1.dotProduct(ut);
		double v2n = v2.dotProduct(un);
		double v2t = v2.dotProduct(ut);

		//step 3: the velocity remains the same in the tangent direction.
		double v1tf = v1t;
		double v2tf = v2t;

		//step 4: the velocity in the normal is calculated as if it is a one dimensional collision.
		double v1nf = (v1n * (m1 - m2) + 2 * m2 * v2n) / (m1 + m2);
		double v2nf = (v2n * (m2 - m1) + 2 * m1 * v1n) / (m1 + m2);

		//step 5: multiply the components by their unit vector to get vectors.
		Velocity v1nVector = new Velocity(un.getScaledBy(v1nf));
		Velocity v1tVector = new Velocity(ut.getScaledBy(v1tf));
		Velocity v2nVector = new Velocity(un.getScaledBy(v2nf));
		Velocity v2tVector = new Velocity(ut.getScaledBy(v2tf));

		//step 6: add the component vectors together to get the final vectors.
		Velocity v1f = v1nVector.getSum(v1tVector);
		Velocity v2f = v2nVector.getSum(v2tVector);
		//--VELOCITY CALCULATION--

		entity1.setVelocity(v1f);
		entity2.setVelocity(v2f);
	}
}
