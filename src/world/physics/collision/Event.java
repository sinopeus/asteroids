package world.physics.collision;

import world.entity.Entity;

public class Event implements Comparable <Event>
{

	public Event (double timeStamp, Entity entity1, Entity entity2)
	{
		this.timeStamp = timeStamp;
		this.entity1 = entity1;
		this.entity2 = entity2;
		this.valid = canHaveAsTimeStamp(timeStamp) ? true : false;
	}

	//TODO
	public double getTimeStamp ()
	{
		return timeStamp;
	}

	private boolean canHaveAsTimeStamp (double timeStamp)
	{
		return (timeStamp > 0);
	}

	private final double	timeStamp;

	public Entity getEntity1 ()
	{
		return entity1;
	}

	private final Entity	entity1;

	public Entity getEntity2 ()
	{
		return entity2;
	}

	private final Entity	entity2;

	public boolean isValid ()
	{
		return valid;
	}

	public void invalidate ()
	{
		this.valid = false;
	}

	private boolean	valid;

	public boolean involves (Entity e)
	{
		return (getEntity1() == e || getEntity2() == e);
	}

	@Override
	public int compareTo (Event o)
	{
		if (this.getTimeStamp() < o.getTimeStamp())
		{
			return -1;
		} else if (this.getTimeStamp() > o.getTimeStamp())
		{
			return +1;
		} else
		{
			return 0;
		}
	}

	@Override
	public String toString ()
	{
		return "Event" + hashCode() + "\n[timeStamp=" + timeStamp + "\n, entity1=" + entity1 + "\n, entity2=" + entity2 + "\n" + ", valid=" + valid + "]";
	}
}
