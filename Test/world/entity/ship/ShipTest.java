package world.entity.ship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import world.World;
import world.entity.Asteroid;
import world.entity.Bullet;
import world.physics.Mass;
import world.physics.Mechanics;
import world.physics.geometry.Angle;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;
import Utilities.Util;

@SuppressWarnings ("javadoc")
public class ShipTest
{
	@Before
	public void setUpMutableTestFixture_Ship ()
	{
		World w = new World(1000, 1000);

		Angle a = new Angle(Math.PI / 2);
		Direction d = new Direction(a);
		Position p = new Position(50, 50);
		CircleShape s = new CircleShape(15);
		double speedLimit = Velocity.getSpeedOfLight();
		Velocity v = new Velocity(5, 5);
		Mass m = new Mass(5E15);
		testShip = new Ship(d, p, speedLimit, v, s, m);
		w.add(testShip);

		terminatedShip = new Ship(new Direction(), new Position(100, 100), 50, new Velocity(), new CircleShape(10), new Mass(2));
		w.add(terminatedShip);
		terminatedShip.terminate();
		
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

	private static Ship	testShip;
	private static Ship	terminatedShip;

	private static World	testWorld;
	private static Asteroid	testAsteroid1, testAsteroid2;
	private static Ship		testShip1, testShip2;
	private static Bullet	testBullet1, testBullet2,testBullet3;

	@Test
	public void extendedConstructorTest_FieldsMatchPerfectParameters ()
	{
		Angle a = new Angle(Math.PI / 2);
		Direction d = new Direction(a);
		Position p = new Position(5, 5);
		CircleShape s = new CircleShape(15);
		double speedLimit = Velocity.getSpeedOfLight();
		Velocity v = new Velocity(5, 5);
		Mass m = new Mass(40);
		Ship ship = new Ship(d, p, speedLimit, v, s, m);
		assertEquals(ship.getDirection(), d);
		assertEquals(ship.getPosition(), p);
		assertEquals(ship.getShape(), s);
		assertTrue(Util.fuzzyEquals(ship.getSpeedLimit(), speedLimit));
		assertEquals(ship.getVelocity(), v);
		assertEquals(ship.getMass(), m);
	}

	@Test (expected = IllegalArgumentException.class)
	public void extendedConstructorTest_IllegalPosition ()
	{
		new Ship(new Direction(), null, 500, new Velocity(), new CircleShape(50), new Mass(40));
	}

	@Test (expected = IllegalArgumentException.class)
	public void extendedConstructorTest_IllegalCircleShape ()
	{
		CircleShape cs = new CircleShape(Ship.getMinimumRadius() - 1);
		new Ship(new Direction(), new Position(), Velocity.getSpeedOfLight(), new Velocity(), cs, new Mass(40));
	}

	@Test
	public void extendedConstructorTest_IllegalSpeedLimit ()
	{
		double sl = Velocity.getSpeedOfLight() + 1;
		Ship ship = new Ship(new Direction(), new Position(), sl, new Velocity(), new CircleShape(Ship.getMinimumRadius()), new Mass(40));
		assertFalse(Util.fuzzyEquals(ship.getSpeedLimit(), sl));
		assertTrue(Util.fuzzyEquals(ship.getSpeedLimit(), Velocity.getSpeedOfLight()));
	}

	@Test
	public void extendedConstructorTest_IllegalVelocity ()
	{
		Velocity v = new Velocity(Velocity.getSpeedOfLight(), Velocity.getSpeedOfLight());
		Ship ship = new Ship(new Direction(), new Position(), Velocity.getSpeedOfLight(), v, new CircleShape(Ship.getMinimumRadius()), new Mass(40));
		assertEquals(ship.getVelocity(), new Velocity(212132.03435596428, 212132.03435596428));
	}

	@Test (expected = IllegalArgumentException.class)
	public void extendedConstructorTest_IllegalMass ()
	{
		new Ship(new Direction(), new Position(), 500, new Velocity(), new CircleShape(50), null);
	}

	@Test
	public void simpleConstructorTest ()
	{
		Ship ship = new Ship();
		Direction d = new Direction();
		Position p = new Position();
		CircleShape s = new CircleShape(40);
		double speedLimit = Velocity.getSpeedOfLight();
		Velocity v = new Velocity();
		Mass m = new Mass(5E15);
		assertEquals(ship.getDirection(), d);
		assertEquals(ship.getPosition(), p);
		assertEquals(ship.getShape(), s);
		assertTrue(Util.fuzzyEquals(ship.getSpeedLimit(), speedLimit));
		assertEquals(ship.getVelocity(), v);
		assertEquals(ship.getMass(), m);

	}

	@Test
	public void canHaveAsShapeTest ()
	{
		assertTrue(testShip.canHaveAsShape(new CircleShape(50)));
		assertFalse(testShip.canHaveAsShape(new CircleShape(5)));
		assertFalse(testShip.canHaveAsShape(null));
	}

	@Test
	public void terminateTest ()
	{
		testShip.terminate();
		assertNull(testShip.getWorld());
		assertTrue(testShip.isTerminated());
	}

	@Test
	public void advanceTest_thrusterOff ()
	{
		Position newPosition = new Position(60, 60);
		testShip.advance(2);
		assertEquals(newPosition, testShip.getPosition());
	}

	@Test
	public void advanceTest_thrusterOn ()
	{
		Position newPosition = new Position(50.5, 270.5);
		Velocity newVelocity = new Velocity(5, 2205);
		testShip.getThruster().activate();
		testShip.advance(0.1);
		assertEquals(newPosition, testShip.getPosition());
		assertEquals(newVelocity, testShip.getVelocity());
	}

	@Test
	public void fireTest ()
	{
		World testWorld = new World(1000, 1000);
		testWorld.add(testShip);
		testShip.fire();
		assertTrue(testWorld.get(1).getClass() == Bullet.class);
		Bullet b = (Bullet) testWorld.get(1);
		assertEquals(testShip, b.getShooter());
	}

	@Test
	public void fire_recoilTest ()
	{
		World testWorld = new World(1000, 1000);
		testWorld.add(testShip);

		Velocity v = testShip.getVelocity();
		Mass m = testShip.getMass();
		Velocity bv = new Velocity(testShip.getVelocity().getSum(testShip.getDirection().getScaledBy(250)));
		Mass bm = new Mass( (4 * Math.PI * Math.pow(3, 3) * 7.8E10) / 3.0);
		Velocity newShipVelocity = new Velocity(v.getDifference(Mechanics.conservationOfMomentum_CalculateVelocity(bv, bm, m)));

		testShip.fire();

		assertEquals(testShip.getVelocity(), newShipVelocity);
	}

	@Test
	public void collideWithTest_PerfectAsteroid ()
	{
		testShip1.collideWith(testAsteroid1);
		assertFalse(testWorld.contains(testShip1));
	}

	@Test
	public void collideWithTest_IllegalAsteroid ()
	{
		testShip1.collideWith((Asteroid) null);
		assertTrue(testWorld.contains(testShip1));
	}

	@Test
	public void collideWithTest_OwnPerfectBullet ()
	{
		testShip1.collideWith(testBullet1);
		assertTrue(testWorld.contains(testShip1));
		assertTrue(testWorld.contains(testBullet1));
	}

	@Test
	public void collideWithTest_OtherPerfectBullet ()
	{
		testShip1.collideWith(testBullet2);
		assertFalse(testWorld.contains(testShip1));
		assertFalse(testWorld.contains(testBullet2));
	}
	
	@Test
	public void collideWithTest_IllegalBullet ()
	{
		testShip1.collideWith((Bullet) null);
		assertTrue(testWorld.contains(testShip1));
	}

	@Test
	public void toStringTest ()
	{
		testShip.toString();
	}

	//	@Test
	//	public void thrustTest_PerfectParameters()
	//	{
	//		Ship originalState = new Ship(testShip.getDirection(), testShip.getPosition(), testShip.getSpeedLimit(), testShip.getVelocity(), testShip.getShape(), testShip.getMass());
	//		testShip.thrust(2.0);
	//		assertEquals(testShip.getDirection(), originalState.getDirection());
	//		assertEquals(testShip.getPosition(), originalState.getPosition());
	//		assertTrue(Util.fuzzyEquals(testShip.getVelocity().getXComponent(), 5));
	//		assertTrue(Util.fuzzyEquals(testShip.getVelocity().getYComponent(), 7));
	//	}
	//
	//	@Test(expected = IllegalStateException.class)
	//	public void thrustTest_TerminatedShip()
	//	{
	//		terminatedShip.thrust(2.0);
	//	}
	//
	//	@Test
	//	public void thrustTest_IllegalAcceleration()
	//	{
	//		Ship originalState = new Ship(testShip.getDirection(), testShip.getPosition(), testShip.getSpeedLimit(), testShip.getVelocity(), testShip.getShape());
	//		testShip.thrust(-1);
	//		assertEquals(testShip.getDirection(), originalState.getDirection());
	//		assertEquals(testShip.getPosition(), originalState.getPosition());
	//		assertTrue(Util.fuzzyEquals(testShip.getVelocity().getXComponent(), 5));
	//		assertTrue(Util.fuzzyEquals(testShip.getVelocity().getYComponent(), 5));
	//	}
	//
	//	@Test
	//	public void thrustTest_SpeedLimitTest()
	//	{
	//		testShip.setSpeedLimit(50);
	//		testShip.thrust(45);
	//		assertTrue(Util.fuzzyEquals(testShip.getVelocity().get(), testShip.getSpeedLimit()));
	//	}
}
