package collision;

import vector.Position;
import vector.Vector;
import vector.Velocity;
import world.World;
import entity.Asteroid;
import entity.Bullet;
import entity.Entity;
import entity.ship.Ship;

public final class EntityCollision extends Collision
{
	public EntityCollision (World world, Entity entity1, Entity entity2)
	{
		super(world);
		setEntity1(entity1);
		setEntity2(entity2);

		calculateCollisionTime();
		//		calculateCollisionPosition ();
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
	 * @param entity1
	 */
	public void setEntity1 (Entity entity1)
	{
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
	 * @param entity2
	 */
	public void setEntity2 (Entity entity2)
	{
		this.entity2 = entity2;
	}

	/**
	 * Returns whether this entity can be an entity.
	 * 
	 * @param entity
	 * @return
	 */
	private boolean canHaveAsEntity (Entity entity)
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

	/* (non-Javadoc)
	 * @see collision.Collision#resolve()
	 */
	@Override
	public void resolve ()
	{
		// see: http://www.vobarian.com/collisions/2dcollisions2.pdf for and explanation.
		if (! (entity1 instanceof Entity) || ! (entity2 instanceof Entity)) { throw new IllegalArgumentException("One of the provided objects is not an entity"); }
		Entity e1 = (Entity) entity1;
		Entity e2 = (Entity) entity2;

		// TODO this part is written shittily, any ideas?
		if (e1 instanceof Bullet && e2 instanceof Bullet)
		{
			bounce();
		} else if ( (e1 instanceof Ship) && (e2 instanceof Ship))
		{
			bounce();
		} else if ( (e1 instanceof Asteroid) && (e2 instanceof Asteroid))
		{
			bounce();
		} else if ( (e1 instanceof Asteroid) && (e2 instanceof Bullet))
		{
			Asteroid a = (Asteroid) e1;
			Bullet b = (Bullet) e2;
			b.terminate();
			a.terminate();
		} else if ( (e1 instanceof Bullet) && (e2 instanceof Asteroid))
		{
			Asteroid a = (Asteroid) e2;
			Bullet b = (Bullet) e1;
			b.terminate();
			a.terminate();
		} else if ( (e1 instanceof Ship) && (e2 instanceof Bullet))
		{
			Ship s = (Ship) e1;
			Bullet b = (Bullet) e2;
			if (b.getShooter() != s)
			{
				b.terminate();
				s.terminate();
			}
		} else if ( (e1 instanceof Bullet) && (e2 instanceof Ship))
		{
			Ship s = (Ship) e2;
			Bullet b = (Bullet) e1;
			if (b.getShooter() != s)
			{
				b.terminate();
				s.terminate();
			}
		} else if ( (e1 instanceof Ship) && (e2 instanceof Asteroid))
		{
			Ship s = (Ship) e1;
			Asteroid a = (Asteroid) e2;
			s.terminate();
		} else if ( (e1 instanceof Asteroid) && (e2 instanceof Ship))
		{
			Ship s = (Ship) e2;
			Asteroid a = (Asteroid) e1;
			s.terminate();
		}
	}

	private void bounce ()
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

	/**
	 * Calculates the time to collision between two entities. //TODO document	
	 * 
	 * @throws  IllegalArgumentException
	 *          One of the given entities is null.
	 *          | ((entity1 == null) || (entity2 == null))
	 */
	@Override
	protected void calculateCollisionTime () throws IllegalArgumentException
	{
		if ( (entity1 == null) || (entity2 == null)) { throw new IllegalArgumentException("One of the given entities is null."); }

		double sigma = entity1.getShape().getRadius() + entity2.getShape().getRadius();
		Vector deltaR = entity2.getPosition().getDifference(entity1.getPosition());
		Vector deltaV = entity2.getVelocity().getDifference(entity1.getVelocity());
		double d = (Math.pow(deltaV.dotProduct(deltaR), 2)) - ( (deltaV.dotProduct(deltaV)) * (deltaR.dotProduct(deltaR) - Math.pow(sigma, 2)));
		if (deltaV.dotProduct(deltaR) >= 0 || d <= 0)
		{
			this.timeToCollision = Double.POSITIVE_INFINITY;
		} else
		{
			this.timeToCollision = - (deltaV.dotProduct(deltaR) + Math.sqrt(d)) / (deltaV.dotProduct(deltaV));
		}
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

}
