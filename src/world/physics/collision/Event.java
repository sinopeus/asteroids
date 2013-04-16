package world.physics.collision;

import world.entity.Entity;

public class Event implements Comparable <Event>
{
	private final double	timeStamp;
	private final Entity	entity1;
	private final Entity	entity2;
	private boolean			valid;

	public Event (double timeStamp, Entity entity1, Entity entity2)
	{
		if (!canHaveAsTimeStamp(timeStamp))
		{
			//TODO
		}
		this.timeStamp = timeStamp;
		this.entity1 = entity1;
		this.entity2 = entity2;
		this.valid = true;
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

	public Entity getEntity1 ()
	{
		return entity1;
	}

	public Entity getEntity2 ()
	{
		return entity2;
	}

	public boolean isValid ()
	{
		return valid;
	}

	public void invalidate ()
	{
		this.valid = false;
	}

// 	public boolean hasCollidingEntities (Event e)
// {
// 		return (getEntity1() == e.getEntity1() || getEntity1() == e.getEntity2() || getEntity2() == e.getEntity1() || getEntity2() == e.getEntity2());
// 	}

	public boolean involves (Entity e) {
	    return (getEntity1() == e || getEntity2() == e);
	}

	@Override
	public int compareTo (Event o)
	{
	    return (int) Math.signum(this.getTimeStamp() - o.getTimeStamp());
		// if (this.getTimeStamp() < o.getTimeStamp())
		// {
		// 	return -1;
		// } else if (this.getTimeStamp() > o.getTimeStamp())
		// {
		// 	return +1;
		// } else
		// {
		// 	return 0;
		// }
	}

	@Override
	public String toString ()
	{
		return "Event" + hashCode() + "\n[timeStamp=" + timeStamp + "\n, entity1=" + entity1 + "\n, entity2=" + entity2 + "\n" + ", valid=" + valid + "]";
	}
}
