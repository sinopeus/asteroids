package world.physics.collision;

import java.util.ArrayList;

import world.World;
import world.entity.Bullet;
import world.entity.Entity;
import world.physics.vector.Position;
import world.physics.vector.Quadrant;
import world.physics.vector.Vector;

public final class BorderCollision extends Collision
{

	//TODO document
	/**
	 * @param world
	 * @param entity
	 */
	public BorderCollision (World world, Entity entity)
	{
		super(world);
		setCollisionEntity(entity);

		calculateCollisionTime();
	}

	//TODO document
	/**
	 * @author xavier
	 *
	 */
	public enum Border
	{
		BORDER_TOP, BORDER_BOTTOM, BORDER_RIGHT, BORDER_LEFT;
	}

	//TODO document
	/**
	 * @return
	 */
	public Border getCollisionBorder ()
	{
		return collisionBorder;
	}

	//TODO document
	/**
	 * @param collisionBorder
	 * @return
	 */
	protected boolean canHaveAsCollisionBorder (Border collisionBorder)
	{
		return (collisionBorder != null);
	}

	//TODO fucking document
	/**
	 * @param collisionBorder
	 */
	public void setCollisionBorder (Border collisionBorder)
	{
		if (!canHaveAsCollisionBorder(collisionBorder)) { throw new IllegalArgumentException("Illegal border provided."); }
		this.collisionBorder = collisionBorder;
	}

	//TODO document
	/**
	 * 
	 */
	private Border	collisionBorder;

	//TODO document
	/**
	 * @return
	 */
	public Entity getCollisionEntity ()
	{
		return collisionEntity;
	}

	//TODO document
	/**
	 * @param collisionEntity
	 * @return
	 */
	public boolean canHaveAsEntity (Entity collisionEntity)
	{
		return (collisionEntity != null);
	}

	//TODO document
	/**
	 * @param collisionEntity
	 */
	public void setCollisionEntity (Entity collisionEntity)
	{
		if (!canHaveAsEntity(collisionEntity)) { throw new IllegalArgumentException("Illegal collision entity provided."); }
		this.collisionEntity = collisionEntity;
	}

	/**
	 * The entity colliding with a world border.
	 */
	private Entity	collisionEntity;

	//TODO document
	/**
	 * @see world.physics.collision.Collision#resolve()
	 */
	@Override
	public void resolve ()
	{
		Entity e = getCollisionEntity();

		//One case for every boundary to hit
		switch (getCollisionBorder())
		{
			case BORDER_TOP:
			case BORDER_BOTTOM:
				e.getVelocity().setY(-e.getVelocity().getY());
				break;
			case BORDER_RIGHT:
			case BORDER_LEFT:
				e.getVelocity().setX(-e.getVelocity().getX());
				break;
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

	//TODO document
	/**
	 * @see world.physics.collision.Collision#getTimeToCollision()
	 */
	@Override
	public double getTimeToCollision ()
	{
		return this.timeToCollision;
	}

	/**
	 * @param b
	 * @return
	 */
	private double getTimeToBorderCollision (Border b)
	{
		Position intersectionOfCenter = null;

		Entity e = getCollisionEntity();
		double wsx = getWorld().getxSize();
		double wsy = getWorld().getySize();
		double r = getCollisionEntity().getShape().getRadius();
		double px = e.getPosition().getX();
		double py = e.getPosition().getY();
		double vx = e.getVelocity().getX();
		double vy = e.getVelocity().getY();

		double n = 0, x = 0, y = 0;
		switch (b)
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
	 * //TODO
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
	 * //TODO not even necessary?
	 * @see world.physics.collision.Collision#calculateCollisionPosition()
	 */
	@Override
	protected void calculateCollisionPosition ()
	{
		double deltaT = getTimeToCollision();

//		if (Double.isInfinite(deltaT)) { return; }

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
	/**
	 * //TODO document this 
	 * @see world.physics.collision.Collision#toString()
	 */
	@Override
	public String toString ()
	{
		return "Border" + super.toString() + " of " + getCollisionEntity() + " and " + getCollisionBorder();
	}
}
