package world.physics.collision;

import world.World;
import world.entity.Bullet;
import world.entity.Entity;
import world.entity.ship.Ship;
import world.physics.vector.Position;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * An abstract class describing the properties of collisions.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 * 
 * @invar	The world of this collision is a valid world.
 * 			| canHaveAsWorld(getWorld())
 * @invar	The time to collision is always positive.
 * 			| getTimeToCollision() >= 0
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
	@Basic
	public Position getCollisionPosition ()
	{
		if (this.collisionPosition == null) calculateCollisionPosition();
		return this.collisionPosition;
	}

	/**
	 * Checks if the given position is a valid position for this collision.
	 * 
	 * @param 	position
	 * 			The given position
	 * @return	Whether this position is not null and within the world.
	 * 			| position != null && world.isInWorld(position)
	 */
	@Basic
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
	@Basic
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
	 * @param	world
	 * 			The given world.
	 * @throws	IllegalArgumentException
	 * 			The given world is not a valid world.
	 * 			| !canHaveAsWorld(world)
	 */
	@Basic
	public void setWorld (World world)
	{
		if (!canHaveAsWorld(world)) throw new IllegalArgumentException("Invalid world provided.");
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
	@Basic
	public double getTimeToCollision ()
	{
		return timeToCollision;
	}

	protected abstract void calculateCollisionTime ();

	/**
	 * A variable registering the time to this collision.
	 */
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
	 * @return	The next collision to happen in the given world.
	 * 			| for each collision
	 * 			|   for each other collision
	 * 			|      calculate collision time
	 * 			|      if (this collision time < smallest collision time)
	 * 			|		 smallest collision time = this collision time
	 * 			| return smallest collision time
	 * @throws 	IllegalArgumentException
	 * 			The given world is null.
	 * 			| world == null
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
					if (b.getShooter() == s) continue;
				} else if ( (e1 instanceof Bullet) && (e2 instanceof Ship))
				{
					Ship s = (Ship) e2;
					Bullet b = (Bullet) e1;
					if (b.getShooter() == s) continue;
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

	@Override
	public int hashCode ()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( (collisionPosition == null) ? 0 : collisionPosition.hashCode());
		long temp;
		temp = Double.doubleToLongBits(timeToCollision);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ( (world == null) ? 0 : world.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Collision other = (Collision) obj;
		if (collisionPosition == null)
		{
			if (other.collisionPosition != null) return false;
		} else if (!collisionPosition.equals(other.collisionPosition)) return false;
		if (Double.doubleToLongBits(timeToCollision) != Double.doubleToLongBits(other.timeToCollision)) return false;
		if (world == null)
		{
			if (other.world != null) return false;
		} else if (!world.equals(other.world)) return false;
		return true;
	}
}
