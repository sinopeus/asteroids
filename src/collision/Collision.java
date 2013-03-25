package collision;

import vector.Position;

public abstract class Collision
{
	//TODO EVERYTHING
	public Position getCollisionPosition()
	{
		return collisionPosition;
	}

	//TODO EVERYTHING
	public boolean canHaveAsPosition(Position position){
		return true;
	}
	
	//TODO EVERYTHING
	public void setCollisionPosition(Position collisionPosition)
	{
		this.collisionPosition = collisionPosition;
	}
	
	private Position collisionPosition;

	public abstract void resolve();
	
	//TODO EVERYTHING
	public abstract double getTimeToCollision();
}
