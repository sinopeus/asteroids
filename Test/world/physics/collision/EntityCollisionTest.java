package world.physics.collision;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import world.World;
import world.entity.Asteroid;
import world.entity.Bullet;
import world.entity.ship.Ship;
import world.physics.Mass;
import world.physics.geometry.Angle;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;
import Utilities.Util;

public class EntityCollisionTest
{
	@Before
	public void setUpMutableTestFixture_EntityCollision ()
	{
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

	private static World	testWorld;
	private static Asteroid	testAsteroid1, testAsteroid2;
	private static Ship		testShip1, testShip2;
	private static Bullet	testBullet1, testBullet2,testBullet3;

	@Test
	public void resolveTest_SameEntities(){
		EntityCollision ec = new EntityCollision(testWorld, testBullet1, testBullet1);
		ec.resolve();
		//Nothing should have happened.
	}
	
	@Test
	public void constructorTest_FieldsMatchGivenParameters_PerfectParameters ()
	{
		EntityCollision ec = new EntityCollision(testWorld, testAsteroid1, testAsteroid2);
		assertEquals(ec.getWorld(), testWorld);
		assertEquals(ec.getEntity1(), testAsteroid1);
		assertEquals(ec.getEntity2(), testAsteroid2);
		assertTrue(Util.fuzzyEquals(ec.getTimeToCollision(), 0.6));
	}

	@Test
	public void resolveTest_BulletBullet_OtherShips()
	{
		EntityCollision ec = new EntityCollision(testWorld, testBullet1, testBullet2);
		ec.resolve();
		assertFalse(testWorld.contains(testBullet1));
		assertFalse(testWorld.contains(testBullet2));
	}
	
	@Test
	public void resolveTest_BulletBullet_SameShip()
	{
		EntityCollision ec = new EntityCollision(testWorld, testBullet1, testBullet3);
		ec.resolve();
		assertEquals(new Velocity(251, 0), testBullet1.getVelocity());
		assertEquals(new Velocity(-249, 0), testBullet3.getVelocity());
	}
	
	@Test
	public void resolveTest_ShipShip ()
	{
		EntityCollision ec = new EntityCollision(testWorld, testShip1, testShip2);
		ec.resolve();
		assertEquals(new Velocity(-1, 0), testShip1.getVelocity());
		assertEquals(new Velocity(1, 0), testShip2.getVelocity());
	}

	@Test
	public void resolveTest_AsteroidAsteroid ()
	{
		EntityCollision ec = new EntityCollision(testWorld, testAsteroid1, testAsteroid2);
		ec.resolve();
		assertEquals(new Velocity(5, -5), testAsteroid1.getVelocity());
		assertEquals(new Velocity(5, 5), testAsteroid2.getVelocity());
	}
	
	@Test
	public void resolveTest_AsteroidBullet ()
	{
		EntityCollision ec = new EntityCollision(testWorld, testAsteroid1, testBullet1);
		ec.resolve();	
		assertFalse(testWorld.contains(testAsteroid1));
		assertFalse(testWorld.contains(testBullet1));
	}
	
	@Test
	public void resolveTest_BulletAsteroid ()
	{
		EntityCollision ec = new EntityCollision(testWorld, testBullet1, testAsteroid1);
		ec.resolve();	
		assertFalse(testWorld.contains(testAsteroid1));
		assertFalse(testWorld.contains(testBullet1));
	}
	
	@Test
	public void resolveTest_ShipBulletFromSameShip ()
	{
		EntityCollision ec = new EntityCollision(testWorld, testShip1, testBullet1);
		ec.resolve();	
		//Nothing should have happened
	}
	
	@Test
	public void resolveTest_ShipBulletFromOtherShip ()
	{
		EntityCollision ec = new EntityCollision(testWorld, testShip1, testBullet2);
		ec.resolve();	
		assertFalse(testWorld.contains(testShip1));
		assertFalse(testWorld.contains(testBullet2));
	}
	
	@Test
	public void resolveTest_BulletFromSameShipShip ()
	{
		EntityCollision ec = new EntityCollision(testWorld, testBullet1, testShip1);
		ec.resolve();	
		//Nothing should have happened
	}
	
	@Test
	public void resolveTest_BulletFromOtherShipShip ()
	{
		EntityCollision ec = new EntityCollision(testWorld, testBullet2, testShip1);
		ec.resolve();	
		assertFalse(testWorld.contains(testBullet2));
		assertFalse(testWorld.contains(testShip1));
	}
	
	@Test
	public void resolveTest_ShipAsteroid ()
	{
		EntityCollision ec = new EntityCollision(testWorld, testShip1, testAsteroid1);
		ec.resolve();	
		assertFalse(testWorld.contains(testShip1));
	}
	
	@Test
	public void resolveTest_AsteroidShip ()
	{
		EntityCollision ec = new EntityCollision(testWorld, testAsteroid1, testShip1);
		ec.resolve();	
		assertFalse(testWorld.contains(testShip1));
	}

	@Test
	public void calculateCollisionTimeTest ()
	{
		EntityCollision ec = new EntityCollision(testWorld, testAsteroid1, testAsteroid2);
		ec.calculateCollisionTime();
		assertTrue(Util.fuzzyEquals(0.6, ec.getTimeToCollision()));
	}

	@Test
	public void calculateCollisionPositionTest ()
	{
		EntityCollision ec = new EntityCollision(testWorld, testAsteroid1, testAsteroid2);
		ec.calculateCollisionPosition();
		assertEquals(new Position(103,15), ec.getCollisionPosition());
	}
}
