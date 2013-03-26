package entity.ship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


import world.World;
import world.entity.ship.Ship;
import world.physics.Mass;
import world.physics.geometry.Angle;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;
import Utilities.Util;

@SuppressWarnings("javadoc")
public class ShipTest
{
	@Before
	public void setUpMutableTestFixture_Ship()
	{
		World w = new World();

		Angle a = new Angle(Math.PI / 2);
		Direction d = new Direction(a);
		Position p = new Position(5, 5);
		CircleShape s = new CircleShape(15);
		double speedLimit = Velocity.getSpeedOfLight();
		Velocity v = new Velocity(5, 5);
		Mass m = new Mass(40);
		testShip = new Ship(d, p, speedLimit, v, s, m);
		w.add(testShip);

		terminatedShip = new Ship();
		w.add(terminatedShip);
		terminatedShip.terminate();
	}

	private static Ship testShip;
	private static Ship terminatedShip;

	@Test
	public void extendedConstructorTest_FieldsMatchPerfectParameters()
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

	@Test(expected = IllegalArgumentException.class)
	public void extendedConstructorTest_IllegalPosition()
	{
		new Ship(new Direction(), null, 500, new Velocity(), new CircleShape(50), new Mass(40));
	}

	@Test(expected = IllegalArgumentException.class)
	public void extendedConstructorTest_IllegalCircleShape()
	{
		CircleShape cs = new CircleShape(Ship.getMinimumRadius() - 1);
		Ship ship = new Ship(new Direction(), new Position(), Velocity.getSpeedOfLight(), new Velocity(), cs, new Mass(40));
	}

	@Test
	public void extendedConstructorTest_IllegalSpeedLimit()
	{
		double sl = Velocity.getSpeedOfLight() + 1;
		Ship ship = new Ship(new Direction(), new Position(), sl, new Velocity(), new CircleShape(Ship.getMinimumRadius()), new Mass(40));
		assertFalse(Util.fuzzyEquals(ship.getSpeedLimit(), sl));
		assertTrue(Util.fuzzyEquals(ship.getSpeedLimit(), Velocity.getSpeedOfLight()));
	}

	@Test
	public void extendedConstructorTest_IllegalVelocity()
	{
		Velocity v = new Velocity(Velocity.getSpeedOfLight(), Velocity.getSpeedOfLight());
		Ship ship = new Ship(new Direction(), new Position(), Velocity.getSpeedOfLight(), v, new CircleShape(Ship.getMinimumRadius()), new Mass(40));
		assertEquals(ship.getVelocity(), new Velocity(212132.03435596428, 212132.03435596428));
	}

	@Test(expected = IllegalArgumentException.class)
	public void extendedConstructorTest_IllegalMass()
	{
		new Ship(new Direction(), new Position(), 500, new Velocity(), new CircleShape(50), null);
	}

	@Test
	public void simpleConstructorTest()
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
