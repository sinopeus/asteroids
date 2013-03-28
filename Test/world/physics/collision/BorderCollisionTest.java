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
import world.physics.collision.BorderCollision.Border;
import world.physics.geometry.Angle;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;

public class BorderCollisionTest
{
	@Before
	public void setUpMutableTestFixture_BorderCollision ()
	{
		testWorldLeft = new World(1000, 1000);
		testAsteroidLeft = new Asteroid(new Direction(new Angle(Math.PI)), new Position(50, 50), new Velocity(-100, -1), new CircleShape(10));
		testWorldLeft.add(testAsteroidLeft);
		Collision leftCollision = Collision.getNextCollision(testWorldLeft);
		leftBorderCollision = (BorderCollision) leftCollision;

		testWorldRight = new World(1000, 1000);
		testAsteroidRight = new Asteroid(new Direction(new Angle(Math.PI)), new Position(50, 50), new Velocity(100, 1), new CircleShape(10));
		testWorldRight.add(testAsteroidRight);
		Collision rightCollision = Collision.getNextCollision(testWorldRight);
		rightBorderCollision = (BorderCollision) rightCollision;

		testWorldTop = new World(1000, 1000);
		testAsteroidTop = new Asteroid(new Direction(new Angle(Math.PI)), new Position(50, 50), new Velocity(-1, 100), new CircleShape(10));
		testWorldTop.add(testAsteroidTop);
		Collision topCollision = Collision.getNextCollision(testWorldTop);
		topBorderCollision = (BorderCollision) topCollision;

		testWorldBottom = new World(1000, 1000);
		testAsteroidBottom = new Asteroid(new Direction(new Angle(Math.PI)), new Position(50, 50), new Velocity(1, -100), new CircleShape(10));
		testWorldBottom.add(testAsteroidBottom);
		Collision bottomCollision = Collision.getNextCollision(testWorldBottom);
		bottomBorderCollision = (BorderCollision) bottomCollision;

		testShip = new Ship(new Direction(), new Position(500, 500), Velocity.getSpeedOfLight(), new Velocity(), new CircleShape(Ship.getMinimumRadius()), new Mass(5E13));

		testWorldHorizontal = new World(1000, 1000);
		testBullet = new Bullet(testShip);
		testWorldHorizontal.add(testBullet);
		Collision horizontalcollision = Collision.getNextCollision(testWorldHorizontal);
		horizontalBorderCollision = (BorderCollision) horizontalcollision;

		testAsteroid = new Asteroid(new Direction(), new Position(), new Velocity(), new CircleShape(50));

	}

	private static World	testWorldLeft, testWorldRight, testWorldTop,
			testWorldBottom, testWorldHorizontal;
	private static Ship		testShip;
	private static Asteroid	testAsteroidLeft, testAsteroidRight,
			testAsteroidTop, testAsteroidBottom, testAsteroid;
	private static Bullet	testBullet;
	private static BorderCollision	leftBorderCollision, rightBorderCollision,
			topBorderCollision, bottomBorderCollision,
			horizontalBorderCollision;

	@Test
	public void canHaveAsCollisionBorderTest ()
	{
		assertTrue(leftBorderCollision.canHaveAsCollisionBorder(Border.BORDER_LEFT));
		assertTrue(leftBorderCollision.canHaveAsCollisionBorder(Border.BORDER_RIGHT));
		assertTrue(leftBorderCollision.canHaveAsCollisionBorder(Border.BORDER_TOP));
		assertTrue(leftBorderCollision.canHaveAsCollisionBorder(Border.BORDER_BOTTOM));
		assertFalse(leftBorderCollision.canHaveAsCollisionBorder(null));
	}

	@Test
	public void setCollisionBorderTest_LegalCase ()
	{
		leftBorderCollision.setCollisionBorder(Border.BORDER_TOP);
		assertEquals(leftBorderCollision.getCollisionBorder(), Border.BORDER_TOP);
	}

	@Test (expected = IllegalArgumentException.class)
	public void setCollisionBorderTest_IllegalCase ()
	{
		leftBorderCollision.setCollisionBorder(null);
	}

	@Test
	public void canHaveAsEntityTest ()
	{
		assertTrue(leftBorderCollision.canHaveAsEntity(testAsteroidLeft));
		assertFalse(leftBorderCollision.canHaveAsEntity(null));
	}

	@Test
	public void setCollisionEntityTest_LegalCase ()
	{
		leftBorderCollision.setCollisionEntity(testAsteroid);
		assertEquals(leftBorderCollision.getCollisionEntity(), testAsteroid);
	}

	@Test (expected = IllegalArgumentException.class)
	public void setCollisionEntityTest_IllegalCase ()
	{
		leftBorderCollision.setCollisionEntity(null);
	}

	@Test
	public void resolveTest ()
	{
		leftBorderCollision.resolve();
		assertEquals(leftBorderCollision.getCollisionEntity().getVelocity(), new Velocity(100, -1));

		rightBorderCollision.resolve();
		assertEquals(rightBorderCollision.getCollisionEntity().getVelocity(), new Velocity(-100, 1));

		topBorderCollision.resolve();
		assertEquals(topBorderCollision.getCollisionEntity().getVelocity(), new Velocity(-1, -100));

		bottomBorderCollision.resolve();
		assertEquals(bottomBorderCollision.getCollisionEntity().getVelocity(), new Velocity(1, 100));

		horizontalBorderCollision.resolve();
		assertEquals(horizontalBorderCollision.getCollisionEntity().getVelocity(), new Velocity(-250, 0));
	}

	@Test
	public void resolveTest_DissapearingBullet ()
	{
		testBullet.setBounceCounter(Bullet.maximumBorderBounces);
		horizontalBorderCollision.resolve();
		assertEquals(horizontalBorderCollision.getCollisionEntity().getVelocity(), new Velocity(-250, 0));
	}

	@Test
	public void getCollisionPositionTest ()
	{
		assertEquals(leftBorderCollision.getCollisionPosition(), new Position(0, 49.6));

		assertEquals(rightBorderCollision.getCollisionPosition(), new Position(1000, 59.4));

		assertEquals(topBorderCollision.getCollisionPosition(), new Position(40.6, 1000));

		assertEquals(bottomBorderCollision.getCollisionPosition(), new Position(50.4, 0));

		assertEquals(horizontalBorderCollision.getCollisionPosition(), new Position(1000, 500));
	}

	@Test
	public void toStringTest ()
	{
		horizontalBorderCollision.toString();
	}
}
