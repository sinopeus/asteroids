package world.physics.collision;

import world.World;
import world.entity.Bullet;
import world.entity.Entity;
import world.entity.ship.Ship;
import world.physics.vector.Position;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 * TODO document
 */
public abstract class Collision
{
	/**
	 * @param collisionPosition
	 * @param world
	 */
	protected Collision (World world)
	{
		setWorld(world);
	}

	/**
	 * Returns the collision position.
	 * 
	 * @return The position at which two entities will collide.
	 */
	public Position getCollisionPosition ()
	{
		if (this.collisionPosition == null)
		{
			calculateCollisionPosition();
		}
		return this.collisionPosition;
	}

	/**
	 * Checks if the position for this collision can be a valid position.
	 * 
	 * @param position
	 * @return	Whether this position is not null and within the world.
	 * 			| position != null && world.isInWorld(position)
	 */
	public boolean canHaveAsPosition (Position position)
	{
		return (position != null && getWorld().isInWorld(position));
	}

	protected abstract void calculateCollisionPosition ();

	/**
	 * A variable registering the position of this collision. 
	 */
	protected Position	collisionPosition;

	/**
	 * A getter for the world in which the collision occurs.
	 * 
	 * @return The world to which this position belongs.
	 */
	public World getWorld ()
	{
		return world;
	}

	/**
	 * Checks whether this collision can have the given world as its world.
	 * 
	 * @param 	world
	 * 			The world to check.
	 * @return	True if and only if the given world is not null.
	 * 			| result = (world!=null)
	 */
	@Basic
	@Raw
	public boolean canHaveAsWorld (World world)
	{
		return (world != null);
	}

	/**
	 * A setter for the world in which the collision occurs.
	 * 
	 * @param world The world we want to set.
	 */
	public void setWorld (World world)
	{
		this.world = world;
	}

	/**
	 * The world in which the collision occurs
	 */
	protected World	world;

	/**
	 * Returns the time of collision.
	 * 
	 * @return The time before this collision occurs.
	 */
	public double getTimeToCollision ()
	{
		return timeToCollision;
	}

	protected abstract void calculateCollisionTime ();

	protected double	timeToCollision;

	/**
	 * Resolves this collision.
	 */
	public abstract void resolve ();

	/**
	 * Gets the next collision to happen in the given world.
	 * 
	 * @param 	world
	 * 			The given world.
	 * @return	//TODO
	 * @throws IllegalArgumentException 
	 */
	public static Collision getNextCollision (World world) throws IllegalArgumentException
	{
		if (world == null) { throw new IllegalArgumentException("Null world provided."); }

		double minimum = Double.POSITIVE_INFINITY;
		Collision first = null;

		for (int indexOfFirst = 0; indexOfFirst < world.numberOfEntities(); indexOfFirst++)
		{
			Entity e1 = world.get(indexOfFirst);
			for (int indexOfSecond = indexOfFirst; indexOfSecond < world.numberOfEntities(); indexOfSecond++)
			{
				Entity e2 = world.get(indexOfSecond);
				if ( (e1 instanceof Ship) && (e2 instanceof Bullet))
				{
					Ship s = (Ship) e1;
					Bullet b = (Bullet) e2;
					if (b.getShooter() == s)
					{
						continue;
					}
				} else if ( (e1 instanceof Bullet) && (e2 instanceof Ship))
				{
					Ship s = (Ship) e2;
					Bullet b = (Bullet) e1;
					if (b.getShooter() == s)
					{
						continue;
					}
				}

				if (e1 != e2)
				{
					Collision ec = new EntityCollision(world, e1, e2);
					if (ec.getTimeToCollision() < minimum)
					{
						minimum = ec.getTimeToCollision();
						first = ec;
					}
				}
			}
			Collision bc = (new BorderCollision(world, e1));
			if (bc.getTimeToCollision() < minimum)
			{
				minimum = bc.getTimeToCollision();
				first = bc;
			}
		}
		return first;
	}

	
	/**
	 * Returns a string representation of this collision.
	 * 
	 * @return A representation in String format of this collision.
	 */
	@Override
	public String toString ()
	{
		return "Collision_" + hashCode() + " in " + getTimeToCollision() + " s";
	}
}
