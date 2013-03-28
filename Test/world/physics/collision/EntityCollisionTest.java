package world.physics.collision;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import world.World;
import world.entity.Asteroid;
import world.entity.Bullet;
import world.entity.ship.Ship;
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
		testAsteroid1 = new Asteroid(new Direction(), new Position(), new Velocity(5, 5), new CircleShape(2));
		testAsteroid2 = new Asteroid(new Direction(), new Position(0, 10), new Velocity(5, -5), new CircleShape(2));
	}

	private static World	testWorld;
	private static Asteroid	testAsteroid1, testAsteroid2;
	@SuppressWarnings ("unused")
	private static Ship		testShip1, testShip2;
	@SuppressWarnings ("unused")
	private static Bullet	testBullet1, testBullet2;

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
	public void resolveTest_AsteroidAsteroid() {
		EntityCollision ec = new EntityCollision(testWorld, testAsteroid1, testAsteroid2);
		ec.resolve();
	}
	
	
	@Test
	public void calculateCollisionTimeTest() {
		
	}
	
	
	@Test
	public void calculateCollisionPositionTest() {
		
	}
}
