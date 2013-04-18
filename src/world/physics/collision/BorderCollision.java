package world.physics.collision;

import java.util.ArrayList;

import world.World;
import world.entity.Entity;
import world.physics.vector.Position;
import world.physics.vector.Quadrant;
import world.physics.vector.Vector;

/**
 * @author syd
 *
 */
public final class BorderCollision extends Collision
{

	//TODO document
	/**
	 * Initializes this border collision with a given world and a given colliding entity.
	 * 
	 * @param	world
	 * 			The given world.
	 * @param	entity
	 * 			The given colliding entity.
	 * @effect	Constructs a Collision with the given world.
	 * 			| super(world)
	 * @effect	Sets the collision entity of this border collision to the given entity
	 * 			| setCollisionEntity(entity)
	 * @effect	Calculates the amount of time to pass before this collision happens.
	 * 			| calculateCollisionTime()
	 * @throws	IllegalArgumentException
	 * 			The given world is not a valid world for this collision
	 * 			| !canHaveAsWorld(world)
	 * @throws	IllegalArgumentException
	 * 			The given entity is not a valid a collision entity for this collision.
	 * 			| !canHaveAsCollisionEntity(entity)
	 */
	public BorderCollision (World world, Entity entity) throws IllegalArgumentException
	{
		super(world);
		setCollisionEntity(entity);
		calculateCollisionTime();
	}

	/**
	 * Gets the border to collide with.
	 */
	@SuppressWarnings ("javadoc")
	public Border getCollisionBorder ()
	{
		return collisionBorder;
	}

	/**
	 * Checks whether the given collision border is a valid border to collide with.
	 * 
	 * @param	collisionBorder
	 * 			The given collision border.
	 * @return	True if and only if the given collision border is not null.
	 * 			| result == collisionBorder != null
	 */
	protected boolean canHaveAsCollisionBorder (Border collisionBorder)
	{
		return (collisionBorder != null);
	}

	/**
	 * Sets the collision border of this border collision to the given collision border.
	 * 
	 * @param	collisionBorder
	 * 			| The given collision border.
	 * @throws	IllegalArgumentException
	 * 			The given collision border is not a valid collision border.
	 * 			| !canHaveAsCollisionBorder(collisionBorder)
	 */
	public void setCollisionBorder (Border collisionBorder) throws IllegalArgumentException
	{
		if (!canHaveAsCollisionBorder(collisionBorder)) { throw new IllegalArgumentException("Illegal border provided."); }
		this.collisionBorder = collisionBorder;
	}

	/**
	 * A variable registering the border with which the entity of this border collision collides.
	 */
	private Border	collisionBorder;

	/**
	 * Gets the collisions entity of this border collision.
	 */
	@SuppressWarnings ("javadoc")
	public Entity getCollisionEntity ()
	{
		return collisionEntity;
	}

	/**
	 * Checks whether the given entity is a valid entity to collide for this border collision.
	 * 
	 * @param	collisionEntity
	 * 			The given entity.
	 * @return	True if and only if the given entity is not null.
	 * 			| result == collisionEntity != null
	 */
	public boolean canHaveAsEntity (Entity collisionEntity)
	{
		return (collisionEntity != null);
	}

	/**
	 * Sets the collision entity of this border collision to the given entity.
	 * 
	 * @param	collisionEntity
	 * 			The given entity
	 * @throws	IllegalArgumentException
	 * 			The given entity is not a valid collision entity for this border collision.
	 * 			| !canHaveAsEntity(collisionEntity)
	 */
	public void setCollisionEntity (Entity collisionEntity) throws IllegalArgumentException
	{
		if (!canHaveAsEntity(collisionEntity)) { throw new IllegalArgumentException("Illegal collision entity provided."); }
		this.collisionEntity = collisionEntity;
	}

	/**
	 * The entity colliding with a world border.
	 */
	private Entity	collisionEntity;

	/**
	 * @see world.physics.collision.Collision#resolve()
	 */
	@Override
	public void resolve ()
	{
		getCollisionEntity().collideWith(getCollisionBorder());
	}

	/**
	 * Gets the time to collision of this border collision.
	 */
	@Override
	public double getTimeToCollision ()
	{
		return this.timeToCollision;
	}

	/**
	 * Gets the time it takes the collision to collide with a given border.
	 * 
	 * @param	border
	 * 			The given border.
	 * @return	The time it takes the collision to collide with a given border.
	 * 			| //TODO weirdly complex?
	 */
	private double getTimeToBorderCollision (Border border)
	{
		Position intersectionOfCenter = null;

		Entity e = getCollisionEntity();
		double wsx = getWorld().getxSize();
		double wsy = getWorld().getySize();
		double r = getCollisionEntity().getShape().getRadius();
		double px = e.getPosition()._X();
		double py = e.getPosition()._Y();
		double vx = e.getVelocity()._X();
		double vy = e.getVelocity()._Y();

		double n = 0, x = 0, y = 0;
		switch (border)
		{
			case BORDER_TOP: // x = n * vx + px
				n = ( (wsy - r - py) / vy);
				x = n * vx + px;
				y = wsy - r;
				break;
			case BORDER_RIGHT: // y = n * vy + py
				n = ( (wsx - r - px) / vx);
				x = wsx - r;
				y = n * vy + py;
				break;
			case BORDER_LEFT:// x = n * vx + px
				n = ( (r - px) / vx);
				x = r;
				y = n * vy + py;
				break;
			case BORDER_BOTTOM:// y = n * vy + py
				n = ( (r - py) / vy);
				x = n * vx + px;
				y = r;
				break;
		}
		if (!Double.isInfinite(n))
		{
			intersectionOfCenter = new Position(x, y);
			double difference = intersectionOfCenter.getDistanceTo(e.getPosition());
			return difference / e.getVelocity().get();
		} else
		{
			return Double.POSITIVE_INFINITY;
		}
	}

	/**
	 * Calculates the time it takes for the collision entity to collide with a border and sets it to.
	 * 
	 * @post	The time to collision of this entity is now equal to the time it takes for the collision entity to collide with a border.
	 * 			| //TODO WTF
	 */
	@Override
	protected void calculateCollisionTime ()
	{
		//Step1: calculate which borders could be hit.
		Quadrant q = getCollisionEntity().getVelocity().getQuadrant();
		ArrayList <Border> borders = new ArrayList <Border>();
		switch (q)
		{
			case QUADRANT_I:
				borders.add(Border.BORDER_RIGHT);
				borders.add(Border.BORDER_TOP);
				break;
			case QUADRANT_II:
				borders.add(Border.BORDER_TOP);
				borders.add(Border.BORDER_LEFT);
				break;
			case QUADRANT_III:
				borders.add(Border.BORDER_LEFT);
				borders.add(Border.BORDER_BOTTOM);
				break;
			case QUADRANT_IV:
				borders.add(Border.BORDER_BOTTOM);
				borders.add(Border.BORDER_RIGHT);
				break;
		}
		double minimum = Double.POSITIVE_INFINITY;
		for (Border border : borders)
		{
			double timeToBorderCollision = getTimeToBorderCollision(border);
			if (timeToBorderCollision < minimum)
			{
				setCollisionBorder(border);
				minimum = timeToBorderCollision;
			}
		}
		this.timeToCollision = minimum;
	}

	/**
	 * Calculates the position this collision occurs at.
	 * 
	 * @post	Sets the collision position of this collision to the position this collision occurs at.
	 * 			| //TODO
	 */
	@Override
	protected void calculateCollisionPosition ()
	{
		double deltaT = getTimeToCollision();

		if (Double.isInfinite(deltaT)) { return; }

		Position newPosition = getCollisionEntity().getPosition().getSum(getCollisionEntity().getVelocity().getScaledBy(deltaT));

		switch (getCollisionBorder())
		{
			case BORDER_TOP:
				this.collisionPosition = new Position(newPosition.getSum(new Vector(0, getCollisionEntity().getShape().getRadius())));
				break;
			case BORDER_LEFT:
				this.collisionPosition = new Position(newPosition.getSum(new Vector(-getCollisionEntity().getShape().getRadius(), 0)));
				break;
			case BORDER_RIGHT:
				this.collisionPosition = new Position(newPosition.getSum(new Vector(+getCollisionEntity().getShape().getRadius(), 0)));
				break;
			case BORDER_BOTTOM:
				this.collisionPosition = new Position(newPosition.getSum(new Vector(0, -getCollisionEntity().getShape().getRadius())));
				break;
		}
	}

	@Override
	public String toString ()
	{
		return "Border" + super.toString() + " of " + getCollisionEntity() + " and " + getCollisionBorder();
	}
}
