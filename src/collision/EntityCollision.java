package collision;

import entity.Entity;

public final class EntityCollision extends Collision
{
	//TODO EVERYTHING
	public Entity getEntity1()
	{
		return entity1;
	}

	//TODO EVERYTHING
	public Entity getEntity2()
	{
		return entity2;
	}

	//TODO EVERYTHING
	public void setEntity1(Entity entity1)
	{
		this.entity1 = entity1;
	}

	//TODO EVERYTHING
	public void setEntity2(Entity entity2)
	{
		this.entity2 = entity2;
	}

	//TODO EVERYTHING
	private boolean canHaveAsEntity(Entity entity)
	{
		return true;
	}

	private Entity entity1;
	private Entity entity2;

	//TODO EVERYTHING
	@Override
	public void resolve()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public double getTimeToCollision()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
