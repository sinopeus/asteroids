package world.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import world.World;
import world.entity.ship.Ship;
import world.physics.Mass;
import world.physics.collision.EntityCollision;
import world.physics.geometry.Angle;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;
import Utilities.Util;

@SuppressWarnings ("javadoc")
public class AsteroidTest
{
	@Before
	public void setUpMutableTestFixtureAsteroid ()
	{
		Angle a = new Angle(Math.PI / 2);
		Direction d = new Direction(a);
		Position p = new Position(5, 5);
		Velocity v = new Velocity(5, 5);
		CircleShape s = new CircleShape(10);
		testAsteroid = new Asteroid(d, p, v, s);
		
		testWorld = new World(1000, 1000);
		testAsteroid1 = new Asteroid(new Direction(), new Position(100,10), new Velocity(5, 5), new CircleShape(2));
		testAsteroid2 = new Asteroid(new Direction(), new Position(100, 20), new Velocity(5, -5), new CircleShape(2));
		testShip1 = new Ship(new Direction(), new Position(500, 500), Velocity.getSpeedOfLight(), new Velocity(1, 0), new CircleShape(20), new Mass(2.5E15));
		testShip2 = new Ship(new Direction(new Angle(Math.PI)), new Position(600, 500), Velocity.getSpeedOfLight(), new Velocity(-1, 0), new CircleShape(20), new Mass(2.5E15));
		testBullet1 = new Bullet(testShip1);
		testBullet2 = new Bullet(testShip2);
		testShip1.turn(new Angle(Math.PI));
		testBullet3 = new Bullet(testShip1);
		testWorld.add(testAsteroid1);
		testWorld.add(testAsteroid2);
		testWorld.add(testShip1);
		testWorld.add(testShip2);
		testWorld.add(testBullet1);
		testWorld.add(testBullet2);
		testWorld.add(testBullet3);
	}


	@Test
	public void massTest ()
	{
		assertTrue(Util.fuzzyEquals(testAsteroid.getMass().get(), 1.1100294042683934E16));
	}

	@Test
	public void terminateTest ()
	{
		testAsteroid = new Asteroid(new Direction(), new Position(100, 100), new Velocity(3, 4), new CircleShape(40));
		testWorld = new World(1000, 1000);
		testWorld.add(testAsteroid);
		testAsteroid.terminate();
		assertFalse(testWorld.isEmpty());
		Asteroid child1 = (Asteroid) testWorld.get(0);
		Asteroid child2 = (Asteroid) testWorld.get(1);
		assertEquals(child1.getDirection(), new Direction());
		assertEquals(child2.getDirection(), new Direction(new Angle(Math.PI)));
		assertEquals(child1.getPosition(), new Position(new Position(100, 100).getSum( (new Direction()).getScaledBy(20))));
		assertEquals(child2.getPosition(), new Position(new Position(100, 100).getSum( (new Direction(new Angle(Math.PI))).getScaledBy(20))));
		assertEquals(child1.getVelocity(), new Velocity( (new Velocity(3, 4)).getScaledBy(1.5)));
		assertEquals(child2.getVelocity(), new Velocity( (new Velocity(3, 4)).getScaledBy(-1.5)));
		assertEquals(child1.getShape(), new CircleShape(20));
		assertEquals(child2.getShape(), new CircleShape(20));
	}

	@Test
	public void advanceTurnTest ()
	{
		testAsteroid = new Asteroid(new Direction(), new Position(100, 100), new Velocity(3, 4), new CircleShape(40));
		testAsteroid.advance(3);
		assertEquals(testAsteroid.getDirection().getAngle(), new Angle(Math.PI / 7.0 * 3));
	}
	
	@Test
	public void collideWithTest_PerfectAsteroid ()
	{
		testAsteroid1.collideWith(testAsteroid2);
		assertEquals(new Velocity(5, -5), testAsteroid1.getVelocity());
		assertEquals(new Velocity(5, 5), testAsteroid2.getVelocity());
	}

	@Test
	public void collideWithTest_IllegalAsteroid ()
	{
		testAsteroid1.collideWith((Asteroid) null);
		assertEquals(new Velocity(5, 5), testAsteroid1.getVelocity());
	}

	@Test
	public void collideWithTest_PerfectBullet ()
	{
		testAsteroid1.collideWith(testBullet1);
		assertFalse(testWorld.contains(testAsteroid1));
		assertFalse(testWorld.contains(testBullet1));
	}

	@Test
	public void collideWithTest_IllegalBullet ()
	{
		testAsteroid1.collideWith((Bullet) null);
		assertTrue(testWorld.contains(testAsteroid1));
	}
	
	@Test
	public void collideWithTest_PerfectShip ()
	{
		testAsteroid1.collideWith(testShip1);
		assertFalse(testWorld.contains(testShip1));
	}

	@Test
	public void collideWithTest_IllegalShip ()
	{
		testAsteroid1.collideWith((Ship) null);
		assertTrue(testWorld.contains(testShip1));
	}

	private static Asteroid	testAsteroid;
	private static World	testWorld;
	private static Asteroid	testAsteroid1, testAsteroid2;
	private static Ship		testShip1, testShip2;
	private static Bullet	testBullet1, testBullet2,testBullet3;
}
