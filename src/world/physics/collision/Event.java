package world.physics.collision;

import java.util.Arrays;

import world.entity.Entity;

public class Event implements Comparable <Event>
{
	public Event (double timeStamp, Entity entity1, Entity entity2)
	{
		this.timeStamp = timeStamp;
		this.entity1 = entity1;
		this.entity2 = entity2;
		setCollisionCountersAtStart(getCurrentCollisionCounters());
		//		setCollisionPosition(getCurrentCollisionPosition());
		//		this.valid = canHaveAsTimeStamp(timeStamp);
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

	public int[] getCollisionCountersAtStart ()
	{
		return collisionCountersAtStart;
	}

	//TODO canhaveAsser
	private void setCollisionCountersAtStart (int[] collisionCounters)
	{
		this.collisionCountersAtStart = collisionCounters;
	}

	private int[]	collisionCountersAtStart;

	private int[] getCurrentCollisionCounters ()
	{
		int[] result = new int[2];
		result[0] = getEntity1() != null ? getEntity1().getCollisionsCounter() : 0;
		result[1] = getEntity2() != null ? getEntity2().getCollisionsCounter() : 0;
		return result;
	}
	
	private boolean collisionsCountersAreIntact(){
		int[] start = getCollisionCountersAtStart();
		int[] now = getCurrentCollisionCounters();
		for (int i = 0; i < start.length; i++)
		{
			if(start[i] != now[i]) return false;
		}
		return true;
	}

//	public Position getCollisionPosition ()
//	{
//		return collisionPosition;
//	}
//
//	//TODO canhaveasser
//	public void setCollisionPosition (Position collisionPosition)
//	{
//		this.collisionPosition = collisionPosition;
//	}
//
//	private Position	collisionPosition;
//
//	public Position getCurrentCollisionPosition ()
//	{
//		if (getEntity1() != null && getEntity2() != null)
//		{
//			double deltaT = getEntity1().timeToEntityCollision(getEntity2());
//
//			if (Double.isInfinite(deltaT)) { return null; }
//
//			Position newPosShip1 = entity1.getPosition().getSum(entity1.getVelocity().getScaledBy(deltaT));
//			Position newPosShip2 = entity2.getPosition().getSum(entity2.getVelocity().getScaledBy(deltaT));
//
//			double sigma = entity1.getShape().getRadius() + entity2.getShape().getRadius();
//			double ship1Radius = entity1.getShape().getRadius();
//
//			this.collisionPosition = new Position(newPosShip1.getSum(newPosShip2.getDifference(newPosShip1).getScaledBy(ship1Radius / sigma)));
//		} else
//		{
//			if (getEntity1() == null && getEntity2() != null)
//			{
//				double timeToHorizontal = getEntity2().timeToHorizontalWallCollision();
//				if (Double.isInfinite(timeToHorizontal)) { return null; }
//				if (getEntity2().getVelocity()._Y() >= 0)
//				{
//					return new Position(timeToHorizontal * getEntity2().getVelocity()._X() + getEntity2().getPosition()._X(), getEntity2().getWorld().getySize());
//				} else
//				{
//					return new Position(timeToHorizontal * getEntity2().getVelocity()._X() + getEntity2().getPosition()._X(), 0);
//				}
//			} else
//			{
//				if (getEntity1() != null && getEntity2() == null)
//				{
//					double timeToVertical = getEntity1().timeToVerticalWallCollision();
//					if (Double.isInfinite(timeToVertical)) { return null; }
//					if (getEntity1().getVelocity()._X() >= 0)
//					{
//						return new Position(getEntity1().getWorld().getxSize(), timeToVertical * getEntity1().getVelocity()._Y() + getEntity1().getPosition()._Y());
//					} else
//					{
//						return new Position(0, timeToVertical * getEntity1().getVelocity()._Y() + getEntity1().getPosition()._Y());
//					}
//				} else System.out.println("ERROR");
//			}
//		}
//		return null;
//	}

	public boolean isValid ()
	{
		if (getEntity1() != null && getEntity1().isTerminated()) return false;
		if (getEntity2() != null && getEntity2().isTerminated()) return false;
//		Position currentCollisionPosition = getCurrentCollisionPosition();
//		if (currentCollisionPosition == null) return false;
		boolean collisionCountersIntact = collisionsCountersAreIntact();
//		if (!collisionCountersIntact) System.out.println(Arrays.toString(getCollisionCountersAtStart()) + " does not equal " + Arrays.toString(getCurrentCollisionCounters()));
		return collisionCountersIntact /*&& getCollisionPosition().equals(currentCollisionPosition)*/;
	}

	//	public boolean isValid ()
	//	{
	//		return valid;
	//	}
	//
	//	public void invalidate ()
	//	{
	//		this.valid = false;
	//	}
	//
	//	private boolean	valid;

	public boolean involves (Entity e)
	{
		return (getEntity1() == e || getEntity2() == e);
	}

	@Override
	public int compareTo (Event o)
	{
		return (int) Math.signum(this.getTimeStamp() - o.getTimeStamp());
	}

	@Override
	public String toString ()
	{
		return "Event [timeStamp=" + timeStamp + ",\n entity1=" + entity1 + ",\n entity2=" + entity2 + ",\n collisionCountersAtStart=" + Arrays.toString(collisionCountersAtStart) + /*", collisionPosition=" + collisionPosition +*/ ",valid=" + isValid() + "]";
	}
}
