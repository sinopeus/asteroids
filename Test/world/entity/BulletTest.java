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
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;

@SuppressWarnings ("javadoc")
public class BulletTest
{
	@Before
	public void setUpImmutableTestFixtureBullet ()
	{
		testShip1 = new Ship();
		testShip1.setPosition(new Position(800, 800));
		testShip2 = new Ship(new Direction(), new Position(200, 200), Velocity.getSpeedOfLight(), new Velocity(), new CircleShape(50), new Mass(4));
		testBullet1 = new Bullet(testShip1);
		testBullet1.setPosition(new Position(500, 500));
		testBullet2 = new Bullet(testShip1);
		testBullet3 = new Bullet(testShip2);
		testAsteroid = new Asteroid(new Direction(), new Position(100, 10), new Velocity(5, 5), new CircleShape(2));
		testWorld = new World(2000, 2000);
		testWorld.add(testShip1);
		testWorld.add(testShip2);
		testWorld.add(testBullet1);
		testWorld.add(testBullet2);
		testWorld.add(testBullet3);
		testWorld.add(testAsteroid);
		System.out.println(testWorld);
	}

	private static Ship	testShip1, testShip2;
	private static Bullet	testBullet1, testBullet2, testBullet3;
	private static Asteroid	testAsteroid;
	private static World	testWorld;

	@Test
	public void constructorTest_PerfectParameter ()
	{
		testBullet1 = new Bullet(testShip1);
		assertTrue(testBullet1.getShooter() == testShip1);
	}

	@Test (expected = NullPointerException.class)
	public void constructorTest_NullShip ()
	{
		testBullet1 = new Bullet(null);
	}

	@Test
	public void getShooterTest ()
	{
		assertEquals(testBullet1.getShooter(), testShip1);
	}

	@Test
	public void canHaveAsBounceCounterTest ()
	{
		assertTrue(testBullet1.canHaveAsBounceCounter((byte) 5));
		assertTrue(testBullet1.canHaveAsBounceCounter((byte) 0));
		assertFalse(testBullet1.canHaveAsBounceCounter((byte) -1));
	}

	@Test
	public void setBounceCounterTest_LegalCase ()
	{
		testBullet1.setBounceCounter((byte) 2);
		assertEquals(testBullet1.getBounceCounter(), 2);
	}

	@Test (expected = IllegalArgumentException.class)
	public void setBounceCounterTest_IllegalCase ()
	{
		testBullet1.setBounceCounter((byte) -1);
	}

	@Test
	public void collideWithTest_PerfectAsteroid ()
	{
		testBullet1.collideWith(testAsteroid);
		assertFalse(testWorld.contains(testWorld));
		assertFalse(testWorld.contains(testAsteroid));
	}

	@Test
	public void collideWithTest_IllegalAsteroid ()
	{
		Asteroid a = null;
		testBullet1.collideWith(a);
	}

	@Test
	public void collideWithTest_PerfectBullet_SameShip ()
	{
		testBullet1.collideWith(testBullet2);
	}

	@Test
	public void collideWithTest_PerfectBullet_OtherShip ()
	{
		testBullet1.collideWith(testBullet3);
		assertFalse(testWorld.contains(testBullet1));
		assertFalse(testWorld.contains(testBullet3));
	}

	@Test
	public void collideWithTest_IllegalBullet ()
	{
		Bullet b = null;
		testBullet1.collideWith(b);
	}

	@Test
	public void collideWithTest_PerfectShip ()
	{
		
	}

	@Test
	public void collideWithTest_IllegalShip ()
	{
		
	}

	@Test
	public void collideWithTest_PerfectBorder ()
	{
		
	}

	@Test
	public void collideWithTest_IllegalBorder ()
	{
		
	}

	@Test
	public void unlinkFromShooterTest ()
	{
		testBullet1.unlinkFromShooter();
		assertEquals(testBullet1.getShooter(), null);
	}

	@Test
	public void terminateTest ()
	{
		testBullet1.terminate();
		assertEquals(testBullet1.getShooter(), null);
	}
}
