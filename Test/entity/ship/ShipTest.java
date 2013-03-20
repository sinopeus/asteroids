package entity.ship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import model.Util;

import org.junit.Before;
import org.junit.Test;

import vector.Direction;
import vector.Position;
import vector.Velocity;
import entity.Angle;
import entity.CircleShape;

@SuppressWarnings("javadoc")
public class ShipTest
{
	@Before
	public void setUpMutableTestFixture_Ship()
	{
		Angle a = new Angle(Math.PI / 2);
		Direction d = new Direction(a);
		Position p = new Position(5, 5);
		CircleShape s = new CircleShape(15);
		double speedLimit = Velocity.getSpeedOfLight();
		Velocity v = new Velocity(5, 5);
		testShip = new Ship(d, p, s, speedLimit, v);
		terminatedShip = new Ship();
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
		Ship ship = new Ship(d, p, s, speedLimit, v);
		assertEquals(ship.getDirection(), d);
		assertEquals(ship.getPosition(), p);
		assertEquals(ship.getShape(), s);
		assertTrue(Util.fuzzyEquals(ship.getSpeedLimit(), speedLimit));
		assertEquals(ship.getVelocity(), v);
	}

	@Test
	public void extendedConstructorTest_IllegalDirection()
	{
		return; //TODO write this when there exists a non valid direction.
	}

	@Test
	public void extendedConstructorTest_IllegalPosition()
	{
		return; //TODO write this when there exists a non valid position.
	}

	@Test(expected = IllegalArgumentException.class)
	public void extendedConstructorTest_IllegalCircleShape()
	{
		CircleShape cs = new CircleShape(Ship.getMinimumRadius() - 1);
		Ship ship = new Ship(new Direction(), new Position(), cs, Velocity.getSpeedOfLight(), new Velocity());
		ship.getDirection(); // just to get rid of the warning.
	}

	@Test
	public void extendedConstructorTest_IllegalSpeedLimit()
	{
		double sl = Velocity.getSpeedOfLight() + 1;
		Ship ship = new Ship(new Direction(), new Position(), new CircleShape(Ship.getMinimumRadius()), sl, new Velocity());
		assertFalse(Util.fuzzyEquals(ship.getSpeedLimit(), sl));
		assertTrue(Util.fuzzyEquals(ship.getSpeedLimit(), Velocity.getSpeedOfLight()));
	}

	@Test
	public void extendedConstructorTest_IllegalVelocity()
	{
		Velocity v = new Velocity(Velocity.getSpeedOfLight(), Velocity.getSpeedOfLight());
		Ship ship = new Ship(new Direction(), new Position(), new CircleShape(Ship.getMinimumRadius()), Velocity.getSpeedOfLight(), v);
		assertEquals(ship.getVelocity(), new Velocity(212132.03435596428, 212132.03435596428));
	}

	@Test
	public void simpleConstructorTest()
	{
		Ship ship = new Ship();
		Direction d = new Direction();
		Position p = new Position();
		CircleShape s = new CircleShape(Ship.getMinimumRadius());
		double speedLimit = Velocity.getSpeedOfLight();
		Velocity v = new Velocity();
		assertEquals(ship.getDirection(), d);
		assertEquals(ship.getPosition(), p);
		assertEquals(ship.getShape(), s);
		assertTrue(Util.fuzzyEquals(ship.getSpeedLimit(), speedLimit));
		assertEquals(ship.getVelocity(), v);

	}

	@Test
	public void canHaveAsPositionTest()
	{
		assertTrue(testShip.canHaveAsPosition(new Position()));
		assertFalse(testShip.canHaveAsPosition(null));
	}

	@Test
	public void setPositionTest_LegalCase()
	{
		Position p = new Position(5, 5);
		testShip.setPosition(p);
		assertEquals(testShip.getPosition(), p);
	}

	@Test(expected = IllegalStateException.class)
	public void setPositionTest_TerminatedShip()
	{
		terminatedShip.setPosition(new Position());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setPositionTest_IllegalCase()
	{
		testShip.setPosition(null);
	}

	@Test
	public void canHaveAsVelocityTest()
	{
		Velocity v = new Velocity(5, 5);
		assertTrue(testShip.canHaveAsVelocity(v));
		testShip.setSpeedLimit(50);
		assertFalse(testShip.canHaveAsVelocity(new Velocity(100, 100)));
		assertFalse(testShip.canHaveAsVelocity(null));
	}

	@Test
	public void setVelocityTest_LegalCase()
	{
		Velocity v = new Velocity(5, 5);
		testShip.setVelocity(v);
		assertEquals(testShip.getVelocity(), v);
	}

	@Test(expected = IllegalStateException.class)
	public void setVelocityTest_TerminatedShip()
	{
		terminatedShip.setVelocity(new Velocity());
	}

	@Test
	public void setVelocityTest_IllegalCase()
	{
		testShip.setVelocity(null);
		assertNotSame(testShip.getVelocity(), null);
	}

	@Test
	public void canHaveAsSpeedLimitTest()
	{
		assertTrue(testShip.canHaveAsSpeedLimit(5000));
		assertFalse(testShip.canHaveAsSpeedLimit(-1));
		assertFalse(testShip.canHaveAsSpeedLimit(Velocity.getSpeedOfLight() + 1));
	}

	@Test
	public void setSpeedLimitTest_LegalCase()
	{
		testShip.setSpeedLimit(5000);
		assertTrue(Util.fuzzyEquals(testShip.getSpeedLimit(), 5000));
	}

	@Test(expected = IllegalStateException.class)
	public void setSpeedLimitTest_TerminatedShip()
	{
		terminatedShip.setSpeedLimit(50);
	}

	@Test
	public void setSpeedLimitTest_IllegalCase()
	{
		testShip.setSpeedLimit(-5);
		assertFalse(Util.fuzzyEquals(testShip.getSpeedLimit(), -5));
		assertTrue(Util.fuzzyEquals(testShip.getSpeedLimit(), Velocity.getSpeedOfLight()));
		testShip.setSpeedLimit(Velocity.getSpeedOfLight() + 1);
		assertFalse(Util.fuzzyEquals(testShip.getSpeedLimit(), Velocity.getSpeedOfLight() + 1));
		assertTrue(Util.fuzzyEquals(testShip.getSpeedLimit(), Velocity.getSpeedOfLight()));
	}

	@Test
	public void canHaveAsDirectionTest()
	{
		Angle a = new Angle(Math.PI);
		Direction d = new Direction(a);
		assertTrue(testShip.canHaveAsDirection(d));
		assertFalse(testShip.canHaveAsDirection(null));
	}

	@Test
	public void setDirectionTest_LegalCase()
	{
		Direction d = new Direction();
		testShip.setDirection(d);
		assertEquals(testShip.getDirection(), d);
	}

	@Test(expected = IllegalStateException.class)
	public void setDirectionTest_TerminatedShip()
	{
		terminatedShip.setDirection(new Direction());
	}

	@Test
	public void canHaveAsShapeTest()
	{
		CircleShape c = new CircleShape(50);
		assertTrue(testShip.canHaveAsShape(c));
		assertFalse(testShip.canHaveAsShape(new CircleShape(5)));
		assertFalse(testShip.canHaveAsShape(null));
	}

	@Test
	public void moveTest_PerfectParameters()
	{
		Ship originalState = new Ship(testShip.getDirection(), testShip.getPosition(), testShip.getShape(), testShip.getSpeedLimit(), testShip.getVelocity());
		testShip.move(2.0);
		assertEquals(testShip.getVelocity(), originalState.getVelocity());
		assertEquals(testShip.getDirection(), originalState.getDirection());
		assertTrue(Util.fuzzyEquals(testShip.getPosition().getXComponent(), 15));
		assertTrue(Util.fuzzyEquals(testShip.getPosition().getYComponent(), 15));
	}

	@Test(expected = IllegalStateException.class)
	public void moveTest_TerminatedShip()
	{
		terminatedShip.move(2.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void moveTest_IllegalDuration()
	{
		testShip.move(-1);
	}

	@Test
	public void turnTest_PerfectParameters()
	{
		testShip.turn(new Angle(Math.PI));
		assertEquals(testShip.getDirection(), new Direction(new Angle(3 * Math.PI / 2)));
	}

	@Test(expected = IllegalStateException.class)
	public void turnTest_TerminatedShip()
	{
		terminatedShip.turn(new Angle());
	}

	@Test
	public void thrustTest_PerfectParameters()
	{
		Ship originalState = new Ship(testShip.getDirection(), testShip.getPosition(), testShip.getShape(), testShip.getSpeedLimit(), testShip.getVelocity());
		testShip.thrust(2.0);
		assertEquals(testShip.getDirection(), originalState.getDirection());
		assertEquals(testShip.getPosition(), originalState.getPosition());
		assertTrue(Util.fuzzyEquals(testShip.getVelocity().getXComponent(), 5));
		assertTrue(Util.fuzzyEquals(testShip.getVelocity().getYComponent(), 7));
	}

	@Test(expected = IllegalStateException.class)
	public void thrustTest_TerminatedShip()
	{
		terminatedShip.thrust(2.0);
	}

	@Test
	public void thrustTest_IllegalAcceleration()
	{
		Ship originalState = new Ship(testShip.getDirection(), testShip.getPosition(), testShip.getShape(), testShip.getSpeedLimit(), testShip.getVelocity());
		testShip.thrust(-1);
		assertEquals(testShip.getDirection(), originalState.getDirection());
		assertEquals(testShip.getPosition(), originalState.getPosition());
		assertTrue(Util.fuzzyEquals(testShip.getVelocity().getXComponent(), 5));
		assertTrue(Util.fuzzyEquals(testShip.getVelocity().getYComponent(), 5));
	}

	@Test
	public void thrustTest_SpeedLimitTest()
	{
		testShip.setSpeedLimit(50);
		testShip.thrust(45);
		assertTrue(Util.fuzzyEquals(testShip.getVelocity().getVelocity(), testShip.getSpeedLimit()));
	}
}
