package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import main.CollisionListener;
import model.programs.Program;
import model.programs.parsing.MyFactory;
import model.programs.parsing.ProgramParser;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.statement.Statement;

import org.antlr.runtime.RecognitionException;

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
public class Facade implements IFacade <World, Ship, Asteroid, Bullet, Program>
{

	@Override
	public World createWorld (double width, double height)
	{
		return new World(width, height);
	}

	@Override
	public double getWorldWidth (World world)
	{
		return world.getxSize();
	}

	@Override
	public double getWorldHeight (World world)
	{
		return world.getySize();
	}

	@Override
	public Set <Ship> getShips (World world)
	{
		HashSet <Ship> hs = new HashSet <Ship>();
		for (Iterator <Entity> iterator = world.iterator(); iterator.hasNext();)
		{
			Entity e = iterator.next();
			if (e instanceof Ship) hs.add((Ship) e);
		}
		return hs;
	}

	@Override
	public Set <Asteroid> getAsteroids (World world)
	{
		HashSet <Asteroid> hs = new HashSet <Asteroid>();
		for (Iterator <Entity> iterator = world.iterator(); iterator.hasNext();)
		{
			Entity e = iterator.next();
			if (e instanceof Asteroid) hs.add((Asteroid) e);
		}
		return hs;
	}

	@Override
	public Set <Bullet> getBullets (World world)
	{
		HashSet <Bullet> hs = new HashSet <Bullet>();
		for (Iterator <Entity> iterator = world.iterator(); iterator.hasNext();)
		{
			Entity e = iterator.next();
			if (e instanceof Bullet) hs.add((Bullet) e);
		}
		return hs;
	}

	@Override
	public void addShip (World world, Ship ship)
	{
		world.add(ship);
	}

	@Override
	public void addAsteroid (World world, Asteroid asteroid)
	{
		world.add(asteroid);

	}

	@Override
	public void removeShip (World world, Ship ship)
	{
		world.remove(ship);
	}

	@Override
	public void removeAsteroid (World world, Asteroid asteroid)
	{
		world.remove(asteroid);
	}

	@Override
	public void evolve (World world, double dt, CollisionListener collisionListener)
	{
		world.evolve(dt, collisionListener);
	}

	@Override
	public Ship createShip (double x, double y, double xVelocity, double yVelocity, double radius, double direction, double mass)
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
		return (o instanceof Ship);
	}

	@Override
	public double getShipX (Ship ship)
	{
		return ship.getPosition()._X();
	}

	@Override
	public double getShipY (Ship ship)
	{
		return ship.getPosition()._Y();
	}

	@Override
	public double getShipXVelocity (Ship ship)
	{
		return ship.getVelocity()._X();
	}

	@Override
	public double getShipYVelocity (Ship ship)
	{
		return ship.getVelocity()._Y();
	}

	@Override
	public double getShipRadius (Ship ship)
	{
		return ship.getShape().getRadius();
	}

	@Override
	public double getShipDirection (Ship ship)
	{
		return ship.getDirection().getAngle().get();
	}

	@Override
	public double getShipMass (Ship ship)
	{
		return ship.getMass().get();
	}

	@Override
	public World getShipWorld (Ship ship)
	{
		return ship.getWorld();
	}

	@Override
	public boolean isShipThrusterActive (Ship ship)
	{
		return ship.getThruster().isActivated();
	}

	@Override
	public void setThrusterActive (Ship ship, boolean active)
	{
		ship.getThruster().activate();
	}

	@Override
	public void turn (Ship ship, double angle)
	{
		ship.turn(new Angle(angle));
	}
	
	@Override
	public boolean canFire (Ship ship)
	{
		return ship.canFire();
	}


	@Override
	public void fireBullet (Ship ship)
	{
		ship.fire();
	}

	@Override
	public Asteroid createAsteroid (double x, double y, double xVelocity, double yVelocity, double radius)
	{
		return new Asteroid(new Direction(), new Position(x, y), new Velocity(xVelocity, yVelocity), new CircleShape(radius));
	}

	@Override
	public Asteroid createAsteroid (double x, double y, double xVelocity, double yVelocity, double radius, Random random)
	{
		return new Asteroid(new Direction(new Angle(random.nextDouble() * Math.PI * 2)), new Position(x, y), new Velocity(xVelocity, yVelocity), new CircleShape(radius));
	}

	@Override
	public boolean isAsteroid (Object o)
	{
		return (o instanceof Asteroid);
	}

	@Override
	public double getAsteroidX (Asteroid asteroid)
	{
		return asteroid.getPosition()._X();
	}

	@Override
	public double getAsteroidY (Asteroid asteroid)
	{
		return asteroid.getPosition()._Y();
	}

	@Override
	public double getAsteroidXVelocity (Asteroid asteroid)
	{
		return asteroid.getVelocity()._X();
	}

	@Override
	public double getAsteroidYVelocity (Asteroid asteroid)
	{
		return asteroid.getVelocity()._Y();
	}

	@Override
	public double getAsteroidRadius (Asteroid asteroid)
	{
		return asteroid.getShape().getRadius();
	}

	@Override
	public double getAsteroidMass (Asteroid asteroid)
	{
		return asteroid.getMass().get();
	}

	@Override
	public World getAsteroidWorld (Asteroid asteroid)
	{
		return asteroid.getWorld();
	}

	@Override
	public boolean isBullets (Object o)
	{
		return (o instanceof Bullet);
	}

	@Override
	public double getBulletX (Bullet bullet)
	{
		return bullet.getPosition()._X();
	}

	@Override
	public double getBulletY (Bullet bullet)
	{
		return bullet.getPosition()._Y();
	}

	@Override
	public double getBulletXVelocity (Bullet bullet)
	{
		return bullet.getVelocity()._X();
	}

	@Override
	public double getBulletYVelocity (Bullet bullet)
	{
		return bullet.getVelocity()._Y();
	}

	@Override
	public double getBulletRadius (Bullet bullet)
	{
		return bullet.getShape().getRadius();
	}

	@Override
	public double getBulletMass (Bullet bullet)
	{
		return bullet.getMass().get();
	}

	@Override
	public World getBulletWorld (Bullet bullet)
	{
		return bullet.getWorld();
	}

	@Override
	public Ship getBulletSource (Bullet bullet)
	{
		return bullet.getShooter();
	}

	@Override
	public model.IFacade.ParseOutcome <Program> parseProgram (String text)
	{
		MyFactory factory = new MyFactory();
		ProgramParser <Expression, Statement, Type> parser = new ProgramParser <>(factory);
		try
		{
			parser.parse(text);
			List <String> errors = parser.getErrors();
			if (!errors.isEmpty())
			{
				return ParseOutcome.failure(errors.get(0));
			} else
			{
				return ParseOutcome.success(new Program(parser.getGlobals(), parser.getStatement()));
			}
		} catch (RecognitionException e)
		{
			return ParseOutcome.failure(e.getMessage());
		}
	}

	@Override
	public model.IFacade.ParseOutcome <Program> loadProgramFromStream (InputStream stream) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		StringBuilder sb = new StringBuilder();
		String line;
		while ( (line = br.readLine()) != null)
			sb.append(line);
		stream.close();
		return parseProgram(sb.toString());
	}

	@Override
	public model.IFacade.ParseOutcome <Program> loadProgramFromUrl (URL url) throws IOException
	{
		System.out.println(url);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ( (line = br.readLine()) != null)
			sb.append(line);
		br.close();
		return parseProgram(sb.toString());
	}

	@Override
	public boolean isTypeCheckingSupported ()
	{
		return false;
	}

	@Override
	public model.IFacade.TypeCheckOutcome typeCheckProgram (Program program)
	{
		return TypeCheckOutcome.success();
	}

	@Override
	public void setShipProgram (Ship ship, Program program)
	{
		ship.setProgram(program);
	}
}
