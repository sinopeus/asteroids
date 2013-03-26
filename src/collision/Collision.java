package collision;

import vector.Position;
import world.World;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class Collision
{
	/**
	 * @param collisionPosition
	 * @param world
	 */
	protected Collision (Position collisionPosition, World world)
	{
		setCollisionPosition (collisionPosition);
		setWorld (world);
	}

	/**
	 * Returns the collision position.
	 * 
	 * @return The position at which two entities will collide.
	 */
	public Position getCollisionPosition ()
	{
		return collisionPosition;
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
		return (position != null && getWorld ().isInWorld (position));
	}

	/**
	 * Sets the collision position.
	 * 
	 * @param collisionPosition
	 */
	public void setCollisionPosition (Position collisionPosition)
	{
		this.collisionPosition = collisionPosition;
	}

	/**
	 * A variable registering the position of this collision. 
	 */
	private Position	collisionPosition;

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
	 * Resolves this collision.
	 */
	public abstract void resolve ();

	/**
	 * Returns the time of collision.
	 * 
	 * @return The time before this collision occurs.
	 */
	public double getTimeToCollision () {
		return timeToCollision;
	}

	protected abstract void calculateCollisionTime ();

	protected double timeToCollision;
	
	
	/**
	 * Gets the next collision to happen in the given world.
	 * 
	 * @param 	world
	 * 			The given world.
	 * @return	//TODO
	 */
	public static Collision getNextCollision (World world)
	{
		return null;
	}
}
