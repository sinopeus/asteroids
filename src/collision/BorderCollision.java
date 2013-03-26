package collision;

import entity.Entity;

public final class BorderCollision extends Collision
{

	//TODO EVERYTHING
	public enum Border
	{
		BORDER_TOP, BORDER_BOTTOM, BORDER_RIGHT, BORDER_LEFT;
	}

	//TODO EVERYTHING
	public BorderCollision.Border getCollisionBorder ()
	{
		return collisionBorder;
	}

	//TODO EVERYTHING
	private boolean canHaveAsCollisionBorder (BorderCollision.Border collisionBorder)
	{
		return true;
	}

	//TODO EVERYTHING
	public void setCollisionBorder (BorderCollision.Border collisionBorder)
	{
		this.collisionBorder = collisionBorder;
	}

	//TODO EVERYTHING
	private BorderCollision.Border	collisionBorder;

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
		// TODO Auto-generated method stub

	}

	//TODO EVERYTHING
	@Override
	public double getTimeToCollision ()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
