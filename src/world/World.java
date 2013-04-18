package world;

import java.util.ArrayList;

import main.CollisionListener;
import world.entity.Asteroid;
import world.entity.Entity;
import world.physics.collision.BorderCollision;
import world.physics.collision.Collision;
import world.physics.collision.EntityCollision;
import world.physics.vector.Position;
import world.physics.vector.Vector;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of worlds describing the game world of a game of asteroids.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 * TODO document
 */
public class World extends ArrayList <Entity>
{
	private static final long	serialVersionUID	= 1L;

	/**
	 * Initializes this new world with a given size on the x- and y axis.
	 * 
	 * @param	xSize
	 * 			The given size on the x axis.
	 * @param 	ySize
	 * 			The given size on the y axis.
	 * @throws	IllegalArgumentException
	 * 			Any of the sizes is not a valid size.
	 * @post	The size of this world on the x axis is equal to the given size.
	 * 			| new.getXSize() == xSize
	 * @post	The size of this world on the y axis is equal to the given size.
	 * 			| new.getYSize() == ySize
	 */
	public World (double xSize, double ySize) throws IllegalArgumentException
	{
		setXSize(xSize);
		setYSize(ySize);
	}

	/**
	 * Initializes this new world with default values.
	 */
	public World ()
	{
		this(Double.MAX_VALUE, Double.MAX_VALUE);
	}

	/**
	 * Gets the size on the x axis of this world.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public double getxSize ()
	{
		return xSize;
	}

	/**
	 * sets the size on the x axis of this world to the given size.
	 * 
	 * @param 	xSize
	 * 			The given size on the x axis.
	 * @post	The size of this world on the x axis is equal to the given size.
	 * 			| new.getXSize() == xSize
	 * @throws	IllegalArgumentException
	 * 			The given size is and illegal size for this world.
	 * 			| ! canHaveAsSize(xSize)
	 */
	@Basic
	@Raw
	private void setXSize (double xSize) throws IllegalArgumentException
	{
		if (!canHaveAsSize(xSize)) { throw new IllegalArgumentException("Invalid xSize provided"); }
		this.xSize = xSize;
	}

	/**
	 * Gets the size on the y axis of this world.
	 */
	@SuppressWarnings ("javadoc")
	@Basic
	@Raw
	public double getySize ()
	{
		return ySize;
	}

	/**
	 * sets the size on the y axis of this world to the given size.
	 * 
	 * @param 	ySize
	 * 			The given size on the y axis.
	 * @post	The size of this world on the y axis is equal to the given size.
	 * 			| new.getYSize() == ySize
	 * @throws	IllegalArgumentException
	 * 			The given size is and illegal size for this world.
	 * 			| ! canHaveAsSize(ySize)
	 */
	@Basic
	@Raw
	private void setYSize (double ySize) throws IllegalArgumentException
	{
		if (!canHaveAsSize(ySize)) { throw new IllegalArgumentException("Invalid ySize provided"); }
		this.ySize = ySize;
	}

	/**
	 * Checks whether this world can have the given size as a component of its size.
	 * 
	 * @param 	size
	 * 			The given size.
	 * @return	True if and only if the given size is strictly positive and at most the max value for a double.
	 */
	@Basic
	@Raw
	private boolean canHaveAsSize (double size)
	{
		return ( (size > 0) && (size <= Double.MAX_VALUE));
	}

	/**
	 * A variable registering the size of this world on the x axis.
	 */
	private double	xSize;

	/**
	 * A variable registering the size of this world on the y axis.
	 */
	private double	ySize;

	/**
	 * Checks whether the given entity is a valid entity for this world.
	 *  
	 * @param 	entity
	 * 			The given entity
	 * @return	True if and only if the given entity is not null.
	 * 			| result == entity != null
	 */
	private boolean canHaveAsEntity (Entity entity)
	{
		return ( (entity != null) && ! (this.contains(entity)));
	}

	/**
	 * Adds the given entity to this world.
	 * 
	 * @param 	entity
	 * 			The given entity
	 * @post	This world now contains the given entity.
	 * 			| new.contains(e)
	 * @throws	IllegalArgumentException
	 * 			The given entity is not a valid entity.
	 */
	@Override
	public boolean add (Entity entity) throws IllegalArgumentException
	{
		if (entity == null) return false;
		if (!canHaveAsEntity(entity)) { throw new IllegalArgumentException("Invalid entity added"); }
		if (isInWorld(entity))
		{
			entity.setWorld(this);
			super.add(entity);
		}
		return true;
	}

	//TODO DOCUMENT
	/**
	 * @param dt
	 * @param collisionListener
	 */
	public void evolve (double dt, CollisionListener collisionListener)
	{
		Collision c = Collision.getNextCollision(this);

		if (c != null && c.getTimeToCollision() <= dt)
		{
			advanceAll(c.getTimeToCollision());
			Position collisionPosition = c.getCollisionPosition();
			if (c.getClass() == BorderCollision.class)
			{
				BorderCollision bc = (BorderCollision) c;
				collisionListener.boundaryCollision(bc.getCollisionEntity(), collisionPosition._X(), collisionPosition._Y());
			} else if (c.getClass() == EntityCollision.class)
			{
				EntityCollision ec = (EntityCollision) c;
				collisionListener.objectCollision(ec.getEntity1(), ec.getEntity2(), collisionPosition._X(), collisionPosition._Y());
			}
			c.resolve();
			evolve( (dt - c.getTimeToCollision()), collisionListener);
		} else
		{
			advanceAll(dt);
		}
	}

	//TODO DOCUMENT &TEST
	private void advanceAll (double dt)
	{
		for (Entity e : this)
		{
			e.advance(dt);
		}
	}

	/**
	 * Returns whether the given position is within the boundaries of the world.
	 * 
	 * @param	position
	 * 			The given position
	 * @return	| position.x <= this.x && position.x >= 0 && position.y <= this.y && position.y <= this.y
	 */
	public boolean isInWorld (Position position)
	{
		return (position._X() <= getxSize() && position._X() >= 0 && position._Y() <= getySize() && position._Y() >= 0);
	}

	/**
	 * Checks whether the given entity is within the world.
	 * @param 	entity
	 * 			The given entity
	 * @return	//TODO
	 */
	private boolean isInWorld (Entity entity)
	{
		if (entity == null) { return false; }
		double r = entity.getShape().getRadius();
		boolean p1 = isInWorld(entity.getPosition().getSum(new Vector(r, 0)));
		boolean p2 = isInWorld(entity.getPosition().getSum(new Vector(-r, 0)));
		boolean p3 = isInWorld(entity.getPosition().getSum(new Vector(0, r)));
		boolean p4 = isInWorld(entity.getPosition().getSum(new Vector(0, -r)));
		return p1 && p2 && p3 && p4;
	}

	/**
	 * Checks whether there is space in the world to add the given entity
	 * 
	 * @param	entity
	 * 			The given entity
	 * @return	True if and only if the given entity is null or does not overlap with any other entity.
	 * 			| //TODO
	 */
	private boolean isSpaceForEntity (Entity entity)
	{
		if (entity == null) return true;
		for (Entity e : this) if (entity.overlapsWith(e)) return false;
		return true;
	}

	/**
	 * Gets the number of entities in this world.
	 * 
	 * @return	The number of entities in this world.
	 * 			| result == this.size();
	 */
	public int numberOfEntities ()
	{
		return this.size();
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return A representation of this object in String format.
	 */
	@Override
	public String toString ()
	{
		String result = "World_" + hashCode() + " with size (" + getxSize() + "," + getySize() + ")\nEntities in this world: \n";
		for (Entity e : this)
		{
			result = result + e.toString() + "\n";
		}
		return result;
	}
}
