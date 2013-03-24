package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import main.CollisionListener;
import vector.Direction;
import vector.Position;
import vector.Velocity;
import world.World;
import entity.Angle;
import entity.Asteroid;
import entity.Bullet;
import entity.CircleShape;
import entity.Entity;
import entity.ship.Mass;
import entity.ship.Ship;

/**
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
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
	//		} catch (ModelException e)
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
			throw new ModelException("The given object is not a world.");
		}
		World w = (World) world;
		return w.getxSize();
	}

	@Override
	public double getWorldHeight(Object world)
	{
		if (world.getClass() != World.class)
		{
			throw new ModelException("The given object is not a world.");
		}
		World w = (World) world;
		return w.getySize();
	}

	@Override
	public Set getShips(Object world)
	{
		if (world.getClass() != World.class)
		{
			throw new ModelException("The given object is not a world.");
		}
		World w = (World) world;
		HashSet<Ship> hs = new HashSet<Ship>();
		for (Iterator<Entity> iterator = w.iterator(); iterator.hasNext();)
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
			throw new ModelException("The given object is not a world.");
		}
		World w = (World) world;
		HashSet<Asteroid> hs = new HashSet<Asteroid>();
		for (Iterator<Entity> iterator = w.iterator(); iterator.hasNext();)
		{
			Entity e = iterator.next();
			if (e instanceof Asteroid)
			{
				hs.add((Asteroid) e);
			}
		}
		return hs;
	}

	@Override
	public Set getBullets(Object world)
	{
		if (world.getClass() != World.class)
		{
			throw new ModelException("The given object is not a world.");
		}
		World w = (World) world;
		HashSet<Bullet> hs = new HashSet<Bullet>();
		for (Iterator<Entity> iterator = w.iterator(); iterator.hasNext();)
		{
			Entity e = iterator.next();
			if (e instanceof Bullet)
			{
				hs.add((Bullet) e);
			}
		}
		return hs;
	}

	@Override
	public void addShip(Object world, Object ship)
	{
		if (world.getClass() != World.class)
		{
			throw new ModelException("The given object is not a world.");
		}
		World w = (World) world;
		if (!(ship instanceof Ship))
		{
			throw new ModelException("The given object is not a Ship.");
		}
		Ship s = (Ship) ship;
		w.add(s);
	}

	@Override
	public void addAsteroid(Object world, Object asteroid)
	{
		if (world.getClass() != World.class)
		{
			throw new ModelException("The given object is not a world.");
		}
		World w = (World) world;
		if (!(asteroid instanceof Asteroid))
		{
			throw new ModelException("The given object is not an asteroid.");
		}
		Asteroid a = (Asteroid) asteroid;
		w.add(a);
	}

	@Override
	public void removeShip(Object world, Object ship)
	{
		if (world.getClass() != World.class)
		{
			throw new ModelException("The given object is not a world.");
		}
		World w = (World) world;
		if (!(ship instanceof Ship))
		{
			throw new ModelException("The given object is not a Ship.");
		}
		Ship s = (Ship) ship;
		w.remove(s);
	}

	@Override
	public void removeAsteroid(Object world, Object asteroid)
	{
		if (world.getClass() != World.class)
		{
			throw new ModelException("The given object is not a world.");
		}
		World w = (World) world;
		if (!(asteroid instanceof Asteroid))
		{
			throw new ModelException("The given object is not an asteroid.");
		}
		Asteroid a = (Asteroid) asteroid;
		w.remove(asteroid);
	}

	@Override
	public void evolve(Object world, double dt, CollisionListener collisionListener)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public Object createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction, double mass)
	{
		Angle a;
		Direction d;
		Position p;
		CircleShape s;
		Velocity v;
		double speedLimit;
		Mass m;
		try
		{
			a = new Angle(direction);
			d = new Direction(a);
			p = new Position(x, y);
			s = new CircleShape(radius);
			v = new Velocity(xVelocity, yVelocity);
			speedLimit = Velocity.getSpeedOfLight();
			m = new Mass(mass);
		} catch (IllegalArgumentException e)
		{
			throw new ModelException("Invalid arguments for facade.createShip(...)");
		} catch (ArithmeticException e)
		{
			throw new ModelException("Invalid arguments for facade.createShip(...)");
		} catch (AssertionError e)
		{
			throw new ModelException("Invalid arguments for facade.createShip(...)");
		}
		return new Ship(d, p, speedLimit, v, s, m);
	}

	@Override
	public boolean isShip(Object o)
	{
		return o instanceof Ship;
	}

	@Override
	public double getShipX(Object ship)
	{
		if (!(ship instanceof Ship))
		{
			throw new ModelException("The given object is not a Ship.");
		}
		Ship s = (Ship) ship;
		return s.getPosition().getXComponent();
	}

	@Override
	public double getShipY(Object ship)
	{
		if (!(ship instanceof Ship))
		{
			throw new ModelException("The given object is not a Ship.");
		}
		Ship s = (Ship) ship;
		return s.getPosition().getYComponent();
	}

	@Override
	public double getShipXVelocity(Object ship)
	{
		if (!(ship instanceof Ship))
		{
			throw new ModelException("The given object is not a Ship.");
		}
		Ship s = (Ship) ship;
		return s.getVelocity().getXComponent();
	}

	@Override
	public double getShipYVelocity(Object ship)
	{
		if (!(ship instanceof Ship))
		{
			throw new ModelException("The given object is not a Ship.");
		}
		Ship s = (Ship) ship;
		return s.getVelocity().getYComponent();
	}

	@Override
	public double getShipRadius(Object ship)
	{
		if (!(ship instanceof Ship))
		{
			throw new ModelException("The given object is not a Ship.");
		}
		Ship s = (Ship) ship;
		return s.getShape().getRadius();
	}

	@Override
	public double getShipDirection(Object ship)
	{
		if (!(ship instanceof Ship))
		{
			throw new ModelException("The given object is not a Ship.");
		}
		Ship s = (Ship) ship;
		return s.getDirection().getAngle().get();
	}

	@Override
	public double getShipMass(Object ship)
	{
		if (!(ship instanceof Ship))
		{
			throw new ModelException("The given object is not a Ship.");
		}
		Ship s = (Ship) ship;
		return s.getMass().get();
	}

	@Override
	public Object getShipWorld(Object ship)
	{
		if (!(ship instanceof Ship))
		{
			throw new ModelException("The given object is not a Ship.");
		}
		Ship s = (Ship) ship;
		return s.getWorld();
	}

	@Override
	public boolean isShipThrusterActive(Object ship)
	{
		if (!(ship instanceof Ship))
		{
			throw new ModelException("The given object is not a Ship.");
		}
		Ship s = (Ship) ship;
		return s.getThruster().isActivated();
	}

	@Override
	public void setThrusterActive(Object ship, boolean active)
	{
		if (!(ship instanceof Ship))
		{
			throw new ModelException("The given object is not a Ship.");
		}
		Ship s = (Ship) ship;
		s.getThruster().activate();
	}

	@Override
	public void turn(Object ship, double angle)
	{
		if (!(ship instanceof Ship))
		{
			throw new ModelException("The given object is not a Ship.");
		}
		Ship s = (Ship) ship;
		s.turn(new Angle(angle));
	}

	@Override
	public void fireBullet(Object ship)
	{
		if (!(ship instanceof Ship))
		{
			throw new ModelException("The given object is not a Ship.");
		}
		Ship s = (Ship) ship;
		s.fire();
	}

	@Override
	public Object createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius)
	{
		return new Asteroid(new Direction(), new Position(x, y), Velocity.getSpeedOfLight(), new Velocity(xVelocity, yVelocity), new CircleShape(radius));
	}

	@Override
	public Object createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius, Random random)
	{
		return new Asteroid(new Direction(new Angle(random.nextDouble() * Math.PI * 2)), new Position(x, y), Velocity.getSpeedOfLight(), new Velocity(xVelocity, yVelocity), new CircleShape(radius));
	}

	@Override
	public boolean isAsteroid(Object o)
	{
		return o instanceof Asteroid;
	}

	@Override
	public double getAsteroidX(Object asteroid)
	{
		if (!(asteroid instanceof Asteroid))
		{
			throw new ModelException("The given object is not an asteroid.");
		}
		Asteroid a = (Asteroid) asteroid;
		return a.getPosition().getXComponent();
	}

	@Override
	public double getAsteroidY(Object asteroid)
	{
		if (!(asteroid instanceof Asteroid))
		{
			throw new ModelException("The given object is not an asteroid.");
		}
		Asteroid a = (Asteroid) asteroid;
		return a.getPosition().getYComponent();
	}

	@Override
	public double getAsteroidXVelocity(Object asteroid)
	{
		if (!(asteroid instanceof Asteroid))
		{
			throw new ModelException("The given object is not an asteroid.");
		}
		Asteroid a = (Asteroid) asteroid;
		return a.getVelocity().getXComponent();
	}

	@Override
	public double getAsteroidYVelocity(Object asteroid)
	{
		if (!(asteroid instanceof Asteroid))
		{
			throw new ModelException("The given object is not an asteroid.");
		}
		Asteroid a = (Asteroid) asteroid;
		return a.getVelocity().getYComponent();
	}

	@Override
	public double getAsteroidRadius(Object asteroid)
	{
		if (!(asteroid instanceof Asteroid))
		{
			throw new ModelException("The given object is not an asteroid.");
		}
		Asteroid a = (Asteroid) asteroid;
		return a.getShape().getRadius();
	}

	@Override
	public double getAsteroidMass(Object asteroid)
	{
		if (!(asteroid instanceof Asteroid))
		{
			throw new ModelException("The given object is not an asteroid.");
		}
		Asteroid a = (Asteroid) asteroid;
		return a.getMass().get();
	}

	@Override
	public Object getAsteroidWorld(Object asteroid)
	{
		if (!(asteroid instanceof Asteroid))
		{
			throw new ModelException("The given object is not an asteroid.");
		}
		Asteroid a = (Asteroid) asteroid;
		return a.getWorld();
	}

	@Override
	public boolean isBullets(Object o)
	{
		return o instanceof Bullet;
	}

	@Override
	public double getBulletX(Object bullet)
	{
		if (!(bullet instanceof Bullet))
		{
			throw new ModelException("The given object is not a bullet.");
		}
		Bullet b = (Bullet) bullet;
		return b.getPosition().getXComponent();
	}

	@Override
	public double getBulletY(Object bullet)
	{
		if (!(bullet instanceof Bullet))
		{
			throw new ModelException("The given object is not a bullet.");
		}
		Bullet b = (Bullet) bullet;
		return b.getPosition().getYComponent();
	}

	@Override
	public double getBulletXVelocity(Object bullet)
	{
		if (!(bullet instanceof Bullet))
		{
			throw new ModelException("The given object is not a bullet.");
		}
		Bullet b = (Bullet) bullet;
		return b.getVelocity().getXComponent();
	}

	@Override
	public double getBulletYVelocity(Object bullet)
	{
		if (!(bullet instanceof Bullet))
		{
			throw new ModelException("The given object is not a bullet.");
		}
		Bullet b = (Bullet) bullet;
		return b.getVelocity().getYComponent();
	}

	@Override
	public double getBulletRadius(Object bullet)
	{
		if (!(bullet instanceof Bullet))
		{
			throw new ModelException("The given object is not a bullet.");
		}
		Bullet b = (Bullet) bullet;
		return b.getShape().getRadius();
	}

	@Override
	public double getBulletMass(Object bullet)
	{
		if (!(bullet instanceof Bullet))
		{
			throw new ModelException("The given object is not a bullet.");
		}
		Bullet b = (Bullet) bullet;
		return b.getMass().get();
	}

	@Override
	public Object getBulletWorld(Object bullet)
	{
		if (!(bullet instanceof Bullet))
		{
			throw new ModelException("The given object is not a bullet.");
		}
		Bullet b = (Bullet) bullet;
		return b.getWorld();
	}

	@Override
	public Object getBulletSource(Object bullet)
	{
		if (!(bullet instanceof Bullet))
		{
			throw new ModelException("The given object is not a bullet.");
		}
		Bullet b = (Bullet) bullet;
		return b.getShooter();
	}
}
