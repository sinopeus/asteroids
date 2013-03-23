package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import main.CollisionListener;
import world.World;
import entity.Entity;
import entity.ship.Ship;

/**
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 1.0
 */
public class Facade implements IFacade
{
	//
	//	/**
	//	 * Returns a new ship with default values.
	//	 *
	//	 * @return  A new ship with default values.
	//	 */
	//	@Override
	//	public IShip createShip()
	//	{
	//		return new Ship();
	//	}
	//
	//	/**
	//	 * Creates a ship using the given properties.
	//	 *
	//	 * @param   x
	//	 *          The x component of the ship's position vector.
	//	 * @param   y
	//	 *          The y component of the ship's position vector.
	//	 * @param   xVelocity
	//	 *          The x component of the ship's velocity vector.
	//	 * @param   yVelocity
	//	 *          The y component of the ship's velocity vector.
	//	 * @param   radius
	//	 *          The ship's radius.
	//	 * @param   angle
	//	 *          The ship's angle.
	//	 * @return  A new ship with the given values.
	//	 * 			| result == new Ship(...)
	//	 * @throws  ModelException
	//	 *          When invalid properties are provided for the ship.	 
	//	 */
	//	@Override
	//	public IShip createShip(double x, double y, double xVelocity, double yVelocity, double radius, double angle) throws ModelException
	//	{
	//		Angle a;
	//		Direction direction;
	//		Position position;
	//		CircleShape shape;
	//		Velocity velocity;
	//		double speedLimit;
	//
	//		try
	//		{
	//			a = new Angle(angle);
	//			direction = new Direction(a);
	//			position = new Position(x, y);
	//			shape = new CircleShape(radius);
	//			velocity = new Velocity(xVelocity, yVelocity);
	//			speedLimit = Velocity.getSpeedOfLight();
	//		} catch (IllegalArgumentException e)
	//		{
	//			throw new ModelException("Invalid arguments for facade.createShip(...)");
	//		} catch (ArithmeticException e)
	//		{
	//			throw new ModelException("Invalid arguments for facade.createShip(...)");
	//		} catch (AssertionError e)
	//		{
	//			throw new ModelException("Invalid arguments for facade.createShip(...)");
	//		}
	//
	//		return new Ship(direction, position, shape, speedLimit, velocity);
	//	}
	//
	//	/**
	//	 * Gets the x component of the ship's position vector.
	//	 *
	//	 * @param   ship
	//	 *          The ship of which we want to get the x component of the position vector.
	//	 * @return  The x component of the ship's position vector.
	//	 */
	//	@Override
	//	public double getX(IShip ship)
	//	{
	//		return ((Ship) ship).getPosition().getXComponent();
	//	}
	//
	//	/**
	//	 * Gets the y component of the ship's position vector.
	//	 *
	//	 * @param   ship
	//	 *          The ship of which we want to get the y component of the position vector.
	//	 * @return  The y component of the ship's position vector.
	//	 */
	//	@Override
	//	public double getY(IShip ship)
	//	{
	//		return ((Ship) ship).getPosition().getYComponent();
	//	}
	//
	//	/**
	//	 * Gets the x component of the ship's velocity vector.
	//	 *
	//	 * @param   ship
	//	 *          The ship of which we want to get the x component of the velocity vector.
	//	 * @return  The x component of the ship's velocity vector.
	//	 */
	//	@Override
	//	public double getXVelocity(IShip ship)
	//	{
	//		return ((Ship) ship).getVelocity().getXComponent();
	//	}
	//
	//	/**
	//	 * Gets the y component of the ship's velocity vector.
	//	 *
	//	 * @param   ship
	//	 *          The ship of which we want to get the y component of the velocity vector.
	//	 * @return  The y component of the ship's velocity vector.
	//	 */
	//	@Override
	//	public double getYVelocity(IShip ship)
	//	{
	//		return ((Ship) ship).getVelocity().getYComponent();
	//	}
	//
	//	/**
	//	 * Gets the ship's radius.
	//	 *
	//	 * @param   ship
	//	 *          The ship whose radius we want to know.
	//	 * @return  The ship's radius.
	//	 */
	//	@Override
	//	public double getRadius(IShip ship)
	//	{
	//		return ((Ship) ship).getShape().getRadius();
	//	}
	//
	//	/**
	//	 * Gets the ship's direction expressed in radians.
	//	 *
	//	 * @param   ship
	//	 *          The ship whose direction we want to know. 
	//	 * @return  The ship's direction in radians.
	//	 */
	//	@Override
	//	public double getDirection(IShip ship)
	//	{
	//		return ((Ship) ship).getDirection().getAngle().getAngle();
	//	}
	//
	//	/**
	//	 * Moves the ship with the current velocity and acceleration for a given time.
	//	 *
	//	 * @param   ship  
	//	 *          The ship we want to move. 
	//	 * @param   dt    
	//	 *          The length of time during which we want to move the ship.
	//	 * @effect  Moves the given ship for the given duration
	//	 * 			| ship.move(dt)
	//	 * @throws  ModelException
	//	 *          When an invalid ship or time is provided.	 
	//	 */
	//	@Override
	//	public void move(IShip ship, double dt) throws ModelException
	//	{
	//		if (ship == null)
	//		{
	//			throw new ModelException("Invalid ship.");
	//		}
	//		if (Double.isNaN(dt) || dt < 0)
	//		{
	//			throw new ModelException("Invalid time.");
	//		}
	//		((Ship) ship).move(dt);
	//	}
	//
	//	/**
	//	 * Applies thrust in order to accelerate the ship.
	//	 *
	//	 * @param   ship      
	//	 *          The ship to which we apply thrust.
	//	 * @param   amount
	//	 *          The amount of thrust we want to apply.
	//	 * @effect  Thrusts the given ship for a given acceleration.
	//	 * 			| ship.thrust(amount)
	//	 * @throws  ModelException
	//	 *          When an invalid ship is provided.
	//	 */
	//	@Override
	//	public void thrust(IShip ship, double amount) throws ModelException
	//	{
	//		if (ship == null)
	//		{
	//			throw new ModelException("Invalid ship.");
	//		}
	//		if (Double.isNaN(amount) || amount < 0)
	//		{
	//			amount = 0;
	//		}
	//		((Ship) ship).thrust(amount);
	//	}
	//
	//	/**
	//	 * Turns the ship by the given angle.
	//	 *
	//	 * @param   ship
	//	 *          The ship we want to turn.
	//	 * @param   angle
	//	 *          The angle by which we want to turn the ship.
	//	 * @effect  Turns the given ship by a given angle.
	//	 * 			| ship.turn(new Angle(angle))
	//	 * @throws  ModelException
	//	 *          When an invalid ship is provided.
	//	 */
	//	@Override
	//	public void turn(IShip ship, double angle) throws ModelException
	//	{
	//		if (ship == null)
	//		{
	//			throw new ModelException("Invalid ship.");
	//		}
	//		((Ship) ship).turn(new Angle(angle));
	//	}
	//
	//	/**
	//	 * Gets the distance between two ships.
	//	 *
	//	 * @param   ship1
	//	 *          The first ship.
	//	 * @param   ship2
	//	 *          The second ship.
	//	 * @return	The distance between the two ships.
	//	 * 			| result == ((Ship) ship1).getPosition().getDistanceTo(((Ship) ship2).getPosition())
	//	 * @throws  ModelException
	//	 *          When an invalid ship is provided.
	//	 */
	//	@Override
	//	public double getDistanceBetween(IShip ship1, IShip ship2) throws ModelException
	//	{
	//		if ((ship1 == null) || (ship2 == null))
	//		{
	//			throw new ModelException("Invalid ship.");
	//		}
	//		return ((Ship) ship1).getPosition().getDistanceTo(((Ship) ship2).getPosition());
	//	}
	//
	//	/**
	//	 * Checks if two ships overlap.
	//	 *
	//	 * @param   ship1
	//	 *          The first ship.
	//	 * @param   ship2
	//	 *          The second ship.
	//	 * @return  Whether the ships overlap or not.
	//	 * 			| result == getDistanceBetween(ship1, ship2) - getRadius(ship1) - getRadius(ship2) <= 0
	//	 * @throws  ModelException
	//	 *          One of the given ships is null
	//	 *          | ((ship1 == null) || (ship2 == null))
	//	 */
	//	@Override
	//	public boolean overlap(IShip ship1, IShip ship2) throws ModelException
	//	{
	//		if ((ship1 == null) || (ship2 == null))
	//		{
	//			throw new ModelException("Invalid ship.");
	//		}
	//		return getDistanceBetween(ship1, ship2) - getRadius(ship1) - getRadius(ship2) <= 0;
	//	}
	//
	//	/**
	//	 * Gets the time to collision between two ships.
	//	 *
	//	 * @param   ship1
	//	 *          The first ship.
	//	 * @param   ship2
	//	 *          The second ship.
	//	 * @return  The time to collision between the two ships.
	//	 *			
	//	 * @throws  ModelException
	//	 *          One of the given ships is null.
	//	 *          | ((ship1 == null) || (ship2 == null))
	//	 */
	//	@Override
	//	public double getTimeToCollision(IShip ship1, IShip ship2) throws ModelException
	//	{
	//		if ((ship1 == null) || (ship2 == null))
	//		{
	//			throw new ModelException("Invalid ship.");
	//		}
	//
	//		double sigma = ((Ship) ship1).getShape().getRadius() + ((Ship) ship2).getShape().getRadius();
	//		Vector deltaR = ((Ship) ship2).getPosition().getDifference(((Ship) ship1).getPosition());
	//		Vector deltaV = ((Ship) ship2).getVelocity().getDifference(((Ship) ship1).getVelocity());
	//		double d = (Math.pow(deltaV.dotProduct(deltaR), 2)) - ((deltaV.dotProduct(deltaV)) * (deltaR.dotProduct(deltaR) - Math.pow(sigma, 2)));
	//		if (deltaV.dotProduct(deltaR) >= 0 || d <= 0)
	//		{
	//			return Double.POSITIVE_INFINITY;
	//		} else
	//		{
	//			return -(deltaV.dotProduct(deltaR) + Math.sqrt(d)) / (deltaV.dotProduct(deltaV));
	//		}
	//	}
	//
	//	/**
	//	 * Gets the position at which two ships will collide.
	//	 *
	//	 * @param   ship1
	//	 *          The first ship.
	//	 * @param   ship2
	//	 *          The second ship.
	//	 * @return  The position of the place of collision.
	//	 * 
	//	 * @throws  ModelException
	//	 *          One of the given ships is null.
	//	 *          | ((ship1 == null) || (ship2 == null))
	//	 */
	//	@Override
	//	public double[] getCollisionPosition(IShip ship1, IShip ship2) throws ModelException //
	//	{
	//		if ((ship1 == null) || (ship2 == null))
	//		{
	//			throw new ModelException("Invalid ship.");
	//		}
	//
	//		double deltaT = getTimeToCollision(ship1, ship2);
	//
	//		if (Double.isInfinite(deltaT))
	//		{
	//			return null;
	//		}
	//
	//		Position newPosShip1 = ((Ship) ship1).getPosition().getSum(((Ship) ship1).getVelocity().getScaledBy(deltaT));
	//		Position newPosShip2 = ((Ship) ship2).getPosition().getSum(((Ship) ship2).getVelocity().getScaledBy(deltaT));
	//
	//		double sigma = ((Ship) ship1).getShape().getRadius() + ((Ship) ship2).getShape().getRadius();
	//		double ship1Radius = ((Ship) ship1).getShape().getRadius();
	//
	//		Vector collisionPos = newPosShip1.getSum(newPosShip2.getDifference(newPosShip1).getScaledBy(ship1Radius / sigma));
	//		double[] result =
	//		{ collisionPos.getXComponent(), collisionPos.getYComponent() };
	//
	//		return result;
	//	}

	@Override
	public Object createWorld(double width, double height)
	{
		return new World(width, height);
	}

	@Override
	public double getWorldWidth(Object world)
	{
		if (world.getClass() != World.class)
		{
			throw new IllegalArgumentException("The given object is not a world.");
		}
		World w = (World) world;
		return w.getxSize();
	}

	@Override
	public double getWorldHeight(Object world)
	{
		if (world.getClass() != World.class)
		{
			throw new IllegalArgumentException("The given object is not a world.");
		}
		World w = (World) world;
		return w.getySize();
	}

	@Override
	public Set getShips(Object world)
	{
		if (world.getClass() != World.class)
		{
			throw new IllegalArgumentException("The given object is not a world.");
		}
		World w = (World) world;
		HashSet<Ship> hs = new HashSet<Ship>();
		for (Iterator<Entity> iterator = w.getEntities().iterator(); iterator.hasNext();)
		{
			Entity e = iterator.next();
			if (e instanceof Ship)
			{
				hs.add((Ship) e);
			}
		}
		return hs;
	}

	@Override
	public Set getAsteroids(Object world)
	{
		if (world.getClass() != World.class)
		{
			throw new IllegalArgumentException("The given object is not a world.");
		}
		World w = (World) world;
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set getBullets(Object world)
	{
		if (world.getClass() != World.class)
		{
			throw new IllegalArgumentException("The given object is not a world.");
		}
		World w = (World) world;
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addShip(Object world, Object ship)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void addAsteroid(Object world, Object asteroid)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void removeShip(Object world, Object ship)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAsteroid(Object world, Object asteroid)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void evolve(Object world, double dt, CollisionListener collisionListener)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Object createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction, double mass)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShip(Object o)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getShipX(Object ship)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipY(Object ship)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipXVelocity(Object ship)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipYVelocity(Object ship)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipRadius(Object ship)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipDirection(Object ship)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipMass(Object ship)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getShipWorld(Object ship)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShipThrusterActive(Object ship)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setThrusterActive(Object ship, boolean active)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void turn(Object ship, double angle)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void fireBullet(Object ship)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Object createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius, Random random)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAsteroid(Object o)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getAsteroidX(Object asteroid)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidY(Object asteroid)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidXVelocity(Object asteroid)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidYVelocity(Object asteroid)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidRadius(Object asteroid)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidMass(Object asteroid)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getAsteroidWorld(Object asteroid)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBullets(Object o)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getBulletX(Object bullet)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletY(Object bullet)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletXVelocity(Object bullet)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletYVelocity(Object bullet)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletRadius(Object bullet)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletMass(Object bullet)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getBulletWorld(Object bullet)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getBulletSource(Object bullet)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
