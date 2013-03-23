package world;

import java.util.HashSet;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import entity.Entity;

/**
 * 
 * 
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * TODO document
 */
public class World extends HashSet<Entity>
{
	private static final long serialVersionUID = 1L;

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
	public World(double xSize, double ySize) throws IllegalArgumentException
	{
		setXSize(xSize);
		setYSize(ySize);
	}

	/**
	 * Initializes this new world with default values.
	 */
	public World()
	{
		this(Double.MAX_VALUE, Double.MAX_VALUE);
	}

	/**
	 * Gets the size on the x axis of this world.
	 */
	@SuppressWarnings("javadoc")
	@Basic
	@Raw
	public double getxSize()
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
	private void setXSize(double xSize) throws IllegalArgumentException
	{
		if (!canHaveAsSize(xSize))
		{
			throw new IllegalArgumentException("Invalid xSize provided");
		}
		this.xSize = xSize;
	}

	/**
	 * Gets the size on the y axis of this world.
	 */
	@SuppressWarnings("javadoc")
	@Basic
	@Raw
	public double getySize()
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
	private void setYSize(double ySize) throws IllegalArgumentException
	{
		if (!canHaveAsSize(ySize))
		{
			throw new IllegalArgumentException("Invalid ySize provided");
		}
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
	private boolean canHaveAsSize(double size)
	{
		return ((size > 0) && (size <= Double.MAX_VALUE));
	}

	/**
	 * A variable registering the size of this world on the x axis.
	 */
	private double xSize;

	/**
	 * A variable registering the size of this world on the y axis.
	 */
	private double ySize;

	//	/**
	//	 * Gets the hash set of all entities.
	//	 */
	//	@SuppressWarnings("javadoc")
	//	@Basic
	//	@Raw
	//	public HashSet<Entity> getEntities()
	//	{
	//		return entities;
	//	}
	//
	//	/**
	//	 * Checks whether this world can have the given hash set of entities as its hash set of entities.
	//	 * 
	//	 * @param 	entityQueue
	//	 * 			The hash set of entities to check.
	//	 * @return	True if and only if the given hash set of entities is not null.
	//	 * 			| result == (entityQueue != null)
	//	 */
	//	@Basic
	//	@Raw
	//	private boolean canHaveAsEntitySet(HashSet<Entity> entityQueue)
	//	{
	//		return (entityQueue != null);
	//	}
	//
	//	/**
	//	 * Sets the hash set of entities to the given hash set of entities.
	//	 * 
	//	 * @param	entities
	//	 * 			The given hash set of entities.
	//	 * @post	The hash set of entities of this world is equal to the given hash set of entities.
	//	 * 			| new.getEntities() == entities
	//	 * @throws	The given hash set of entities is not a valid hash set of entities for this world.
	//	 * 			| !canHaveAsEntitySet(entities)
	//	 */
	//	@Basic
	//	@Raw
	//	private void setEntities(HashSet<Entity> entities) throws IllegalArgumentException
	//	{
	//		if (!canHaveAsEntitySet(entities))
	//		{
	//			throw new IllegalArgumentException("Invalid hash set of entities provided");
	//		}
	//		this.entities = entities;
	//	}

	/**
	 * Checks whether the given entity is a valid entity for this world.
	 *  
	 * @param 	entity
	 * 			The given entity
	 * @return	True if and only if the given entity is not null.
	 * 			| result == entity != null
	 */
	private boolean canHaveAsEntity(Entity entity)
	{
		return (entity != null);
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
	public boolean add(Entity entity) throws IllegalArgumentException
	{
		if (!canHaveAsEntity(entity))
		{
			throw new IllegalArgumentException("Invalid entity added");
		}
		entity.setWorld(this);
		super.add(entity);
		return true;
	}

	/**
	 * A priority queue holding all the entities in this world.
	 */
	private HashSet<Entity> entities;

}
