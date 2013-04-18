package world;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import main.Asteroids;
import main.FileSoundManager;
import main.Sound;
import main.WorldView;
import model.Facade;

import org.junit.Before;
import org.junit.Test;

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
import Utilities.Util;

@SuppressWarnings ("javadoc")
public class WorldTest
{
	@Before
	public void setUpMutableTestFixture_World ()
	{
		testEntity = new Asteroid(new Direction(new Angle(Math.PI)), new Position(50, 50), new Velocity(), new CircleShape(25));
		outOfWorldEntity = new Asteroid(new Direction(new Angle(Math.PI)), new Position(50, 50), new Velocity(), new CircleShape(250));
		testWorld1 = new World(1000, 1000);
		testWorld2 = new World(1000, 1000);
		testWorld2.add(testEntity);
	}

	private static Entity	testEntity;
	private static Entity	outOfWorldEntity;
	private static World	testWorld1, testWorld2;
	private static Ship		testShip1, testShip2;

	@Test
	public void extendedConstructorTest_SizesMatchGivenSizes_PerfectParameters ()
	{
		World w = new World(5, 6);
		assertTrue(Util.fuzzyEquals(5, w.getxSize()));
		assertTrue(Util.fuzzyEquals(6, w.getySize()));
	}

	@SuppressWarnings ("unused")
	@Test (expected = IllegalArgumentException.class)
	public void extendedConstructorTest_SizesMatchGivenSizes_IllegalXSize ()
	{
		World w = new World(-1, 6);
	}

	@SuppressWarnings ("unused")
	@Test (expected = IllegalArgumentException.class)
	public void extendedConstructorTest_SizesMatchGivenSizes_IllegalYSize ()
	{
		World w = new World(5, -1);
	}

	@Test
	public void simpleConstructorTest_SizesMatchGivenSizes ()
	{
		World w = new World();
		assertTrue(Util.fuzzyEquals(Double.MAX_VALUE, w.getxSize()));
		assertTrue(Util.fuzzyEquals(Double.MAX_VALUE, w.getySize()));
	}

	@Test
	public void addTest_PerfectParameters ()
	{
		testWorld1.add(testEntity);
		assertTrue(testWorld1.contains(testEntity));
	}

	@Test
	public void addTest_NullEntity ()
	{
		assertFalse(testWorld1.add(null));
	}

	@Test
	public void addTest_EntityOutOfWorld ()
	{
		testWorld1.add(outOfWorldEntity);
		assertFalse(testWorld1.contains(testEntity));
	}

	@Test
	public void evolveTest ()
	{
		testWorld1 = new World(1000, 1000);
		testShip1 = new Ship(new Direction(), new Position(100, 100), Velocity.getSpeedOfLight(), new Velocity(4, 3), new CircleShape(30), new Mass(50));
		testShip2 = new Ship(new Direction(), new Position(500, 500), Velocity.getSpeedOfLight(), new Velocity(3, 4), new CircleShape(40), new Mass(40));
		Asteroid testAsteroid = new Asteroid(new Direction(), new Position(700, 700), new Velocity(-3, 4), new CircleShape(50));
		testWorld1.addAll(Arrays.asList(testShip1, testShip2, testAsteroid));

		@SuppressWarnings ("unchecked")
		Asteroids <World, Ship, Asteroid, Bullet> game = new Asteroids <World, Ship, Asteroid, Bullet>(new Facade(), 1000, 1000, true, null);
		WorldView <World, Ship, Asteroid, Bullet> worldview = new WorldView <World, Ship, Asteroid, Bullet>(game, testWorld1, testShip1, testShip2);
		testWorld1.evolve(10, worldview);
		
		assertEquals(testShip1.getPosition(), new Position(140,130));
		assertEquals(testShip2.getPosition(), new Position(530,540));
		assertEquals(testAsteroid.getPosition(), new Position(670,740));
	}

	@Test
	public void isInWorldTest_Position ()
	{
		assertTrue(testWorld1.isInWorld(new Position(0, 0)));
		assertTrue(testWorld1.isInWorld(new Position(50, 50)));
		assertFalse(testWorld1.isInWorld(new Position(-5, -5)));
		assertFalse(testWorld1.isInWorld(new Position(5, -5)));
		assertFalse(testWorld1.isInWorld(new Position(-5, 5)));
	}

	@Test
	public void numberOfEntitiesTest ()
	{
		assertEquals(testWorld2.numberOfEntities(), testWorld2.size());
	}

	@Test
	public void toStringTest ()
	{
		testWorld2.toString();
	}

	//	public void isInWorldTest_Entity ()
	//	{
	//		Direction d = new Direction();
	//		Velocity v = new Velocity();
	//		CircleShape s = new CircleShape(50);
	//
	//		Asteroid a1 = new Asteroid(d, new Position(100, 100), v, s);
	//		Asteroid a2 = new Asteroid(d, new Position(100, 20), v, s);
	//		Asteroid a3 = new Asteroid(d, new Position(20, 100), v, s);
	//		Asteroid a4 = new Asteroid(d, new Position(20, 20), v, s);
	//		Asteroid a5 = new Asteroid(d, new Position(980, 100), v, s);
	//		Asteroid a6 = new Asteroid(d, new Position(100, 980), v, s);
	//		Asteroid a7 = null;
	//
	//		assertTrue(testWorld1.isInWorld(a1));
	//		assertFalse(testWorld1.isInWorld(a2));
	//		assertFalse(testWorld1.isInWorld(a3));
	//		assertFalse(testWorld1.isInWorld(a4));
	//		assertFalse(testWorld1.isInWorld(a5));
	//		assertFalse(testWorld1.isInWorld(a6));
	//		assertFalse(testWorld1.isInWorld(a7));
	//	}

}