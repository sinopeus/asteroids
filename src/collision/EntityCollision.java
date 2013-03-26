package collision;

import vector.Position;
import world.World;
import entity.Entity;

public final class EntityCollision extends Collision
{
	protected EntityCollision (Position collisionPosition, World world, Entity entity1, Entity entity2)
	{
		super (collisionPosition, world);
		setEntity1 (entity1);
		setEntity2 (entity2);
	}

	/**
	 * Returns the first entity involved in the collision.
	 * 
	 * @return entity1	The first entity involved in the collision. 
	 */
	public Entity getEntity1 ()
	{
		return entity1;
	}

	/**
	 * Set the first entity involved involved in the collision.
	 * 
	 * @param entity1
	 */
	public void setEntity1 (Entity entity1)
	{
		this.entity1 = entity1;
	}

	/**
	 * Returns the second entity involved in the collision.
	 * 
	 * @return entity2	The second entity involved in the collision. 
	 */
	public Entity getEntity2 ()
	{
		return entity2;
	}

	/**
	 * Set the second entity involved involved in the collision.
	 * 
	 * @param entity2
	 */
	public void setEntity2 (Entity entity2)
	{
		this.entity2 = entity2;
	}

	/**
	 * Returns whether this entity can be an entity.
	 * 
	 * @param entity
	 * @return
	 */
	private boolean canHaveAsEntity (Entity entity)
	{
		return (entity != null);
	}

	/**
	 * The first entity involved in the collision.
	 */
	private Entity	entity1;

	/**
	 * The second entity involved in the collision.
	 */
	private Entity	entity2;

	/* (non-Javadoc)
	 * @see collision.Collision#resolve()
	 */
	@Override
	public void resolve ()
	{
		// TODO Auto-generated method stub

	}

	private void bounce ()
	{
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see collision.Collision#getTimeToCollision()
	 */
	@Override
	public double getTimeToCollision ()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
