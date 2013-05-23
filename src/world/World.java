package world;

import java.util.ArrayList;

import main.CollisionListener;
import world.entity.Entity;
import world.physics.collision.BorderCollision;
import world.physics.collision.Collision;
import world.physics.collision.EntityCollision;
import world.physics.vector.Position;
import world.physics.vector.Vector;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of worlds describing the game world of a game of Asteroids.
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 * 
 * @invar	Both the size in the x dirction and the y direction are valid dimensions.
 * 			| canHaveAsSize(getxSize()) && canHaveAsSize(getySize())
 * @invar	A world does not contain the same entity twice.
 * 			| for each Entity e1 in a world
 * 			| 	for each other Entity e2 in that world
 * 			| 	  e1 != e2
 * @invar	All the entities in a world fit into the dimensional constraints of that world.
 * 			| for each Entity e in a world
 * 			| 	isInWorld(e) && isSpaceForEntity(e)
 */
public class World extends ArrayList <Entity>
{
	private static final long	serialVersionUID	= 1L;

	/**
	 * Initializes this new world with a given size on the x and y axis.
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
		setGameTime(0);
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
	@Model
	protected void setXSize (double xSize) throws IllegalArgumentException
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
	@Model
	protected void setYSize (double ySize) throws IllegalArgumentException
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
	 * 			| ((size > 0) && (size <= Double.MAX_VALUE))
	 */
	@Basic
	@Raw
	@Model
	protected static boolean canHaveAsSize (double size)
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

	//TODO document
	private double	gameTime;

	//TODO document
	@Basic
	@Raw
	public double getGameTime ()
	{
		return gameTime;
	}

	//TODO document
	@Basic
	protected static boolean canHaveAsGameTime (double gameTime)
	{
		return (gameTime >= 0);
	}

	@Basic
	@Raw
	@Model
	protected void setGameTime (double gameTime)
	{
		if (!canHaveAsGameTime(gameTime)) throw new IllegalArgumentException("Invalid game time provided.");
		this.gameTime = gameTime;
	}

	@Model
	protected void advandeGameTime (double duration)
	{
		try
		{
			setGameTime(getGameTime() + duration);
		} catch (IllegalArgumentException e)
		{
			System.err.println("The provided duration " + duration + " is negative.");
		}
	}

	/**
	 * Checks whether the given entity is a valid entity for this world.
	 *  
	 * @param 	entity
	 * 			The given entity
	 * @return	True if and only if the given entity is not null.
	 * 			| result == entity != null
	 */
	@Basic
	@Raw
	@Model
	private boolean canHaveAsNewEntity (Entity entity)
	{
		return ( (entity != null) && (!this.contains(entity)));
	}

	/**
	 * Adds the given entity to this world.
	 * 
	 * @param 	entity
	 * 			The given entity
	 * @post	This world now contains the given entity.
	 * 			| new.contains(e)
	 */
	@Override
	public boolean add (Entity entity) throws IllegalArgumentException//TODO DOCUMENT
	{
		if (entity == null) return false;
		//if (!isSpaceForEntity(entity)) return false; TODO remove this
		if (!canHaveAsNewEntity(entity)) { throw new IllegalArgumentException("Invalid entity added"); }
		if (!isInWorld(entity)) return false;
		entity.setWorld(this);
		super.add(entity);
		return true;
	}

	/**
	 * Evolves the state of this world by a given time period and a collision detector. Also needs a collision listener to render explosions.
	 * 
	 * @param	dt
	 * 			The time difference over which to evolve this world.
	 * @param	coll
	 * 			The required collision listener.
	 * @note	//TODO more extensive informal doc
	 */
	public void evolve (double dt, CollisionListener coll)
	{
		Collision next = getNextCollision();

		if (next == null || next.getTimeToCollision() > dt)
		{
			advanceAll(dt);
			return;
		} else
		{
			advanceAll(next.getTimeToCollision());
			Position collisionPosition = next.getCollisionPosition();
			if (next.getClass() == BorderCollision.class)
			{
				BorderCollision bc = (BorderCollision) next;
				coll.boundaryCollision(bc.getCollisionEntity(), collisionPosition._X(), collisionPosition._Y());
			} else if (next.getClass() == EntityCollision.class)
			{
				EntityCollision ec = (EntityCollision) next;
				coll.objectCollision(ec.getEntity1(), ec.getEntity2(), collisionPosition._X(), collisionPosition._Y());
			}
			next.resolve();
			evolve( (dt - next.getTimeToCollision()), coll);
		}

	}

	/**
	 * Returns the next collision that will occur in this world.
	 * 
	 * @return | Collision.getNextCollision(this)
	 */
	@Model
	private Collision getNextCollision ()
	{
		return Collision.getNextCollision(this);
	}

	/**
	 * Advances the state of all entities in this world by a given time <code>dt</code>.
	 * 
	 * @param	dt
	 * 			The time over which to advance all entities.
	 * @effect	| for (Entity e : this) e.advance(dt);
	 */
	@Model
	private void advanceAll (double dt)
	{
		ArrayList <Entity> temp = new ArrayList <Entity>(this.numberOfEntities() * 2 + 1);
		for (Entity e : this)
			temp.add(e);
		for (Entity e : temp)
			e.advance(dt);
		advandeGameTime(dt);//TODO change doc
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
	 * @return	True if and only if the entity is not null and every edge of the shape is in the world. //TODO
	 * 			| result == (entity != null)
	 * 			| && isInWorld(entity.getPosition().getSum(new Vector(entity.getShape().getRadius(), 0)))
	 * 			| && isInWorld(entity.getPosition().getSum(new Vector(-entity.getShape().getRadius(), 0)))
	 * 			| && isInWorld(entity.getPosition().getSum(new Vector(0, entity.getShape().getRadius())));
	 * 			| && isInWorld(entity.getPosition().getSum(new Vector(0, -entity.getShape().getRadius())));
	 */
	@Model
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
	 * 			| (entity == null) || for (Entity e : this) (!entity.overlapsWith(e))
	 */
	@Model
	private boolean isSpaceForEntity (Entity entity) //TODO we might want to use this?
	{
		if (entity == null) return true;
		for (Entity e : this)
			if (entity.overlapsWith(e)) return false;
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
			result = result + e.toString() + "\n";
		return result;
	}
}
