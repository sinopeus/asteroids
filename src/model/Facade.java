package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import main.CollisionListener;
import world.World;
import world.entity.Asteroid;
import world.entity.Bullet;
import world.entity.Entity;
import world.entity.ship.Ship;
import world.physics.Mass;
import world.physics.geometry.Angle;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;

/**
 * @author Tom Sydney Kerckhove & Xavier Goas Aguililla
 * @version 2.0
 */
@SuppressWarnings ("rawtypes")
public class Facade implements IFacade
{
	@Override
	public Object createWorld (double width, double height)
	{
		return new World(width, height);
	}

	@Override
	public double getWorldWidth (Object world)
	{
		if (world.getClass() != World.class) { throw new ModelException("The given object is not a world."); }
		World w = (World) world;
		return w.getxSize();
	}

	@Override
	public double getWorldHeight (Object world)
	{
		if (world.getClass() != World.class) { throw new ModelException("The given object is not a world."); }
		World w = (World) world;
		return w.getySize();
	}

	@Override
	public HashSet <Ship> getShips (Object world)
	{
		if (world.getClass() != World.class) { throw new ModelException("The given object is not a world."); }
		World w = (World) world;
		HashSet <Ship> hs = new HashSet <Ship>();
		for (Iterator <Entity> iterator = w.iterator(); iterator.hasNext();)
		{
			Entity e = iterator.next();
			if (e instanceof Ship) hs.add((Ship) e);
		}
		return hs;
	}

	@Override
	public HashSet <Asteroid> getAsteroids (Object world)
	{
		if (world.getClass() != World.class) { throw new ModelException("The given object is not a world."); }
		World w = (World) world;
		HashSet <Asteroid> hs = new HashSet <Asteroid>();
		for (Iterator <Entity> iterator = w.iterator(); iterator.hasNext();)
		{
			Entity e = iterator.next();
			if (e instanceof Asteroid) hs.add((Asteroid) e);
		}
		return hs;
	}

	@Override
	public HashSet <Bullet> getBullets (Object world)
	{
		if (world.getClass() != World.class) { throw new ModelException("The given object is not a world."); }
		World w = (World) world;
		HashSet <Bullet> hs = new HashSet <Bullet>();
		for (Iterator <Entity> iterator = w.iterator(); iterator.hasNext();)
		{
			Entity e = iterator.next();
			if (e instanceof Bullet) hs.add((Bullet) e);
		}
		return hs;
	}

	@Override
	public void addShip (Object world, Object ship)
	{
		if (world.getClass() != World.class) { throw new ModelException("The given object is not a world."); }
		World w = (World) world;
		if (! (ship instanceof Ship)) { throw new ModelException("The given object is not a Ship."); }
		Ship s = (Ship) ship;
		w.add(s);
	}

	@Override
	public void addAsteroid (Object world, Object asteroid)
	{
		if (world.getClass() != World.class) { throw new ModelException("The given object is not a world."); }
		World w = (World) world;
		if (! (asteroid instanceof Asteroid)) { throw new ModelException("The given object is not an asteroid."); }
		Asteroid a = (Asteroid) asteroid;
		w.add(a);
	}

	@Override
	public void removeShip (Object world, Object ship)
	{
		if (world.getClass() != World.class) { throw new ModelException("The given object is not a world."); }
		World w = (World) world;
		if (! (ship instanceof Ship)) { throw new ModelException("The given object is not a Ship."); }
		Ship s = (Ship) ship;
		w.remove(s);
	}

	@Override
	public void removeAsteroid (Object world, Object asteroid)
	{
		if (world.getClass() != World.class) { throw new ModelException("The given object is not a world."); }
		World w = (World) world;
		if (! (asteroid instanceof Asteroid)) { throw new ModelException("The given object is not an asteroid."); }
		w.remove(asteroid);
	}

	@Override
	public void evolve (Object world, double dt, CollisionListener collisionListener)
	{
		if (world.getClass() != World.class) { throw new ModelException("The given object is not a world."); }
		World w = (World) world;
		w.evolve(dt, collisionListener);
	}

	@Override
	public Object createShip (double x, double y, double xVelocity, double yVelocity, double radius, double direction, double mass)
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
	public boolean isShip (Object o)
	{
		return o instanceof Ship;
	}

	@Override
	public double getShipX (Object ship)
	{
		if (! (ship instanceof Ship)) { throw new ModelException("The given object is not a Ship."); }
		Ship s = (Ship) ship;
		return s.getPosition()._X();
	}

	@Override
	public double getShipY (Object ship)
	{
		if (! (ship instanceof Ship)) { throw new ModelException("The given object is not a Ship."); }
		Ship s = (Ship) ship;
		return s.getPosition()._Y();
	}

	@Override
	public double getShipXVelocity (Object ship)
	{
		if (! (ship instanceof Ship)) { throw new ModelException("The given object is not a Ship."); }
		Ship s = (Ship) ship;
		return s.getVelocity()._X();
	}

	@Override
	public double getShipYVelocity (Object ship)
	{
		if (! (ship instanceof Ship)) { throw new ModelException("The given object is not a Ship."); }
		Ship s = (Ship) ship;
		return s.getVelocity()._Y();
	}

	@Override
	public double getShipRadius (Object ship)
	{
		if (! (ship instanceof Ship)) { throw new ModelException("The given object is not a Ship."); }
		Ship s = (Ship) ship;
		return s.getShape().getRadius();
	}

	@Override
	public double getShipDirection (Object ship)
	{
		if (! (ship instanceof Ship)) { throw new ModelException("The given object is not a Ship."); }
		Ship s = (Ship) ship;
		return s.getDirection().getAngle().get();
	}

	@Override
	public double getShipMass (Object ship)
	{
		if (! (ship instanceof Ship)) { throw new ModelException("The given object is not a Ship."); }
		Ship s = (Ship) ship;
		return s.getMass().get();
	}

	@Override
	public Object getShipWorld (Object ship)
	{
		if (! (ship instanceof Ship)) { throw new ModelException("The given object is not a Ship."); }
		Ship s = (Ship) ship;
		return s.getWorld();
	}

	@Override
	public boolean isShipThrusterActive (Object ship)
	{
		if (! (ship instanceof Ship)) { throw new ModelException("The given object is not a Ship."); }
		Ship s = (Ship) ship;
		return s.getThruster().isActivated();
	}

	@Override
	public void setThrusterActive (Object ship, boolean active)
	{
		if (! (ship instanceof Ship)) { throw new ModelException("The given object is not a Ship."); }
		Ship s = (Ship) ship;
		if (active) s.getThruster().activate();
		else s.getThruster().deactivate();
	}

	@Override
	public void turn (Object ship, double angle)
	{
		if (! (ship instanceof Ship)) { throw new ModelException("The given object is not a Ship."); }
		Ship s = (Ship) ship;
		s.turn(new Angle(angle));
	}

	@Override
	public void fireBullet (Object ship)
	{
		if (! (ship instanceof Ship)) { throw new ModelException("The given object is not a Ship."); }
		Ship s = (Ship) ship;
		s.fire();
	}

	@Override
	public Object createAsteroid (double x, double y, double xVelocity, double yVelocity, double radius)
	{
		return new Asteroid(new Direction(), new Position(x, y), new Velocity(xVelocity, yVelocity), new CircleShape(radius));
	}

	@Override
	public Object createAsteroid (double x, double y, double xVelocity, double yVelocity, double radius, Random random)
	{
		return new Asteroid(new Direction(new Angle(random.nextDouble() * Math.PI * 2)), new Position(x, y), new Velocity(xVelocity, yVelocity), new CircleShape(radius));
	}

	@Override
	public boolean isAsteroid (Object o)
	{
		return o instanceof Asteroid;
	}

	@Override
	public double getAsteroidX (Object asteroid)
	{
		if (! (asteroid instanceof Asteroid)) { throw new ModelException("The given object is not an asteroid."); }
		Asteroid a = (Asteroid) asteroid;
		return a.getPosition()._X();
	}

	@Override
	public double getAsteroidY (Object asteroid)
	{
		if (! (asteroid instanceof Asteroid)) { throw new ModelException("The given object is not an asteroid."); }
		Asteroid a = (Asteroid) asteroid;
		return a.getPosition()._Y();
	}

	@Override
	public double getAsteroidXVelocity (Object asteroid)
	{
		if (! (asteroid instanceof Asteroid)) { throw new ModelException("The given object is not an asteroid."); }
		Asteroid a = (Asteroid) asteroid;
		return a.getVelocity()._X();
	}

	@Override
	public double getAsteroidYVelocity (Object asteroid)
	{
		if (! (asteroid instanceof Asteroid)) { throw new ModelException("The given object is not an asteroid."); }
		Asteroid a = (Asteroid) asteroid;
		return a.getVelocity()._Y();
	}

	@Override
	public double getAsteroidRadius (Object asteroid)
	{
		if (! (asteroid instanceof Asteroid)) { throw new ModelException("The given object is not an asteroid."); }
		Asteroid a = (Asteroid) asteroid;
		return a.getShape().getRadius();
	}

	@Override
	public double getAsteroidMass (Object asteroid)
	{
		if (! (asteroid instanceof Asteroid)) { throw new ModelException("The given object is not an asteroid."); }
		Asteroid a = (Asteroid) asteroid;
		return a.getMass().get();
	}

	@Override
	public Object getAsteroidWorld (Object asteroid)
	{
		if (! (asteroid instanceof Asteroid)) { throw new ModelException("The given object is not an asteroid."); }
		Asteroid a = (Asteroid) asteroid;
		return a.getWorld();
	}

	@Override
	public boolean isBullets (Object o)
	{
		return o instanceof Bullet;
	}

	@Override
	public double getBulletX (Object bullet)
	{
		if (! (bullet instanceof Bullet)) { throw new ModelException("The given object is not a bullet."); }
		Bullet b = (Bullet) bullet;
		return b.getPosition()._X();
	}

	@Override
	public double getBulletY (Object bullet)
	{
		if (! (bullet instanceof Bullet)) { throw new ModelException("The given object is not a bullet."); }
		Bullet b = (Bullet) bullet;
		return b.getPosition()._Y();
	}

	@Override
	public double getBulletXVelocity (Object bullet)
	{
		if (! (bullet instanceof Bullet)) { throw new ModelException("The given object is not a bullet."); }
		Bullet b = (Bullet) bullet;
		return b.getVelocity()._X();
	}

	@Override
	public double getBulletYVelocity (Object bullet)
	{
		if (! (bullet instanceof Bullet)) { throw new ModelException("The given object is not a bullet."); }
		Bullet b = (Bullet) bullet;
		return b.getVelocity()._Y();
	}

	@Override
	public double getBulletRadius (Object bullet)
	{
		if (! (bullet instanceof Bullet)) { throw new ModelException("The given object is not a bullet."); }
		Bullet b = (Bullet) bullet;
		return b.getShape().getRadius();
	}

	@Override
	public double getBulletMass (Object bullet)
	{
		if (! (bullet instanceof Bullet)) { throw new ModelException("The given object is not a bullet."); }
		Bullet b = (Bullet) bullet;
		return b.getMass().get();
	}

	@Override
	public Object getBulletWorld (Object bullet)
	{
		if (! (bullet instanceof Bullet)) { throw new ModelException("The given object is not a bullet."); }
		Bullet b = (Bullet) bullet;
		return b.getWorld();
	}

	@Override
	public Object getBulletSource (Object bullet)
	{
		if (! (bullet instanceof Bullet)) { throw new ModelException("The given object is not a bullet."); }
		Bullet b = (Bullet) bullet;
		return b.getShooter();
	}

	@Override
	public ParseOutcome parseProgram (String text)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParseOutcome loadProgramFromStream (InputStream stream) throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParseOutcome loadProgramFromUrl (URL url) throws IOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTypeCheckingSupported ()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TypeCheckOutcome typeCheckProgram (Object program)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShipProgram (Object ship, Object program)
	{
		// TODO Auto-generated method stub

	}
}
