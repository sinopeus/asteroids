package world.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import world.World;
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
		fail();
	}

	@Test
	public void collideWithTest_IllegalAsteroid ()
	{
		fail();
	}

	@Test
	public void collideWithTest_PerfectBullet ()
	{
		fail();
	}

	@Test
	public void collideWithTest_IllegalBullet ()
	{
		fail();
	}
	
	@Test
	public void collideWithTest_PerfectShip ()
	{
		fail();
	}

	@Test
	public void collideWithTest_IllegalShip ()
	{
		fail();
	}

	private static Asteroid	testAsteroid;
	private static World	testWorld;

}
