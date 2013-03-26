package collision;

import vector.Position;
import world.World;

public abstract class Collision
{
	//TODO EVERYTHING
	public Position getCollisionPosition()
	{
		return collisionPosition;
	}

	//TODO EVERYTHING
	public boolean canHaveAsPosition(Position position)
	{
		return true;
	}

	//TODO EVERYTHING
	public void setCollisionPosition(Position collisionPosition)
	{
		this.collisionPosition = collisionPosition;
	}

	private Position collisionPosition;

	//TODO EVERYTHING
	public double getTimeToCollision()
	{
		return this.timeToCollision;
	}

	//TODO EVERYTHING
	protected boolean canHaveAsTimeToCollision(double timeToCollision)
	{
		return true;
	}

	//TODO EVERYTHING
	protected void setTimeToCollision(double timeToCollision)
	{
		this.timeToCollision = timeToCollision;
	}

	private double timeToCollision;

	public abstract void resolve();

	//TODO EVERYTHING
	public static Collision getNextCollision(World world)
	{
		return null;
	}
}
