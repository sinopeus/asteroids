package collision;

import vector.Position;
import vector.Quadrant;
import world.World;
import Utilities.Util;
import entity.Bullet;
import entity.Entity;

public final class BorderCollision extends Collision
{

	protected BorderCollision (World world, Entity entity)
	{
		super(world);
		setCollisionEntity(entity);

		calculateCollisionTime();
		//		calculateCollisionPosition ();
		//		calculateCollisionBorder();
	}

	//TODO EVERYTHING
	public enum Border
	{
		BORDER_TOP, BORDER_BOTTOM, BORDER_RIGHT, BORDER_LEFT;
	}

	//TODO EVERYTHING
	public Border getCollisionBorder ()
	{
		return collisionBorder;
	}

	//TODO EVERYTHING
	protected boolean canHaveAsCollisionBorder (Border collisionBorder)
	{
		return true;
	}

	//TODO EVERYTHING
	private Border	collisionBorder;

	//TODO EVERYTHING
	public Entity getCollisionEntity ()
	{
		return collisionEntity;
	}

	//TODO EVERYTHING
	public boolean canHaveAsEntity (Entity collisionEntity)
	{
		return true;
	}

	//TODO EVERYTHING
	public void setCollisionEntity (Entity collisionEntity)
	{
		this.collisionEntity = collisionEntity;
	}

	//TODO EVERYTHING
	private Entity	collisionEntity;

	//TODO EVERYTHING
	@Override
	public void resolve ()
	{
		Entity e = getCollisionEntity();

		World w = e.getWorld();

		//One case for every boundary to hit
		if (Util.fuzzyEquals(getCollisionPosition().getXComponent(), 0))
		{
			e.getVelocity().setYComponent(-e.getVelocity().getYComponent());
		}
		if (Util.fuzzyEquals(getCollisionPosition().getYComponent(), 0))
		{
			e.getVelocity().setXComponent(-e.getVelocity().getXComponent());
		}
		if (Util.fuzzyEquals(getCollisionPosition().getXComponent(), w.getxSize()))
		{
			e.getVelocity().setYComponent(-e.getVelocity().getYComponent());
		}
		if (Util.fuzzyEquals(getCollisionPosition().getYComponent(), w.getySize()))
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

	//TODO EVERYTHING
	@Override
	public double getTimeToCollision ()
	{
		//Step1: calculate which borders could be hit.
		Quadrant q = getCollisionEntity().getVelocity().getQuadrant();
		Border b1 = null;
		Border b2 = null;
		switch (q)
		{
			case QUADRANT_I:
				b1 = Border.BORDER_RIGHT;
				b2 = Border.BORDER_TOP;
				break;
			case QUADRANT_II:
				b1 = Border.BORDER_TOP;
				b2 = Border.BORDER_LEFT;
				break;
			case QUADRANT_III:
				b1 = Border.BORDER_LEFT;
				b2 = Border.BORDER_BOTTOM;
				break;
			case QUADRANT_IV:
				b1 = Border.BORDER_BOTTOM;
				b2 = Border.BORDER_RIGHT;
				break;
		}
		return Math.min(getTimeToBorderCollision(b1), getTimeToBorderCollision(b2));
	}

	private double getTimeToBorderCollision (Border b)
	{
		Position intersectionOfCenter = null;

		Entity e = getCollisionEntity();
		double wsx = getWorld().getxSize();
		double wsy = getWorld().getySize();
		double r = getCollisionEntity().getShape().getRadius();
		double px = e.getPosition().getXComponent();
		double py = e.getPosition().getYComponent();
		double vx = e.getVelocity().getXComponent();
		double vy = e.getVelocity().getYComponent();

		double n, x = 0, y = 0;
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
				n = ( (r - py) / vy);
				x = n * vx + px;
				y = r;
				break;
			case BORDER_BOTTOM:// y = n * vy + py
				n = ( (r - px) / vx);
				x = r;
				y = n * vy + py;
				break;
		}
		intersectionOfCenter = new Position(x, y);
		double difference = intersectionOfCenter.getDistanceTo(e.getPosition());
		return difference / e.getVelocity().get();//TODO
	}

	@Override
	protected void calculateCollisionTime ()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void calculateCollisionPosition ()
	{
		// TODO Auto-generated method stub

	}

	protected void calculateCollisionBorder ()
	{

	}
}
