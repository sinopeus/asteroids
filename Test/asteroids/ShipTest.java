package asteroids;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.Angle;
import asteroids.CircleShape;
import asteroids.Direction;
import asteroids.Ship;
import asteroids.Velocity;

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
	}

	private static Ship testShip;

	@Test
	public void canHaveAsPositionTest()
	{
		assertTrue(testShip.canHaveAsPosition(new Position()));
	}

	@Test
	public void setPositionTest_LegalCase()
	{
		Position p = new Position(5, 5);
		testShip.setPosition(p);
		assertEquals(testShip.getPosition(), p);
	}

	@Test
	public void setPositionTest_IllegalCase()
	{
		return; // There is no illegal case yet.
	}

	@Test
	public void canHaveAsVelocityTest()
	{
		Velocity v = new Velocity(5, 5);
		assertTrue(testShip.canHaveAsVelocity(v));
	}

	@Test
	public void setVelocityTest_LegalCase()
	{
		Velocity v = new Velocity(5, 5);
		testShip.setVelocity(v);
		assertEquals(testShip.getVelocity(), v);
	}

	@Test
	public void setVelocityTest_IllegalCase()
	{
		return;//TODO write this when the speedlimit is no longer the speed of light.
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
	}

	@Test
	public void setDirectionTest_LegalCase()
	{
		Direction d = new Direction();
		testShip.setDirection(d);
		assertEquals(testShip.getDirection(), d);
	}

	@Test
	public void setDirectionTest_IllegalCase()
	{
		return; //There is no illegal case yet.
	}

	@Test
	public void canHaveAsShapeTest()
	{
		CircleShape c = new CircleShape(50);
		assertTrue(testShip.canHaveAsShape(c));
	}

	@Test
	public void setShapeTest_LegalCase()
	{
		CircleShape c = new CircleShape(50);
		testShip.setShape(c);
		assertEquals(testShip.getShape(), c);
	}

	@Test
	public void setShapeTest_IllegalCase()
	{
		CircleShape c = new CircleShape(5);
		try
		{
			testShip.setShape(c);
		} catch (IllegalArgumentException e)
		{
			return;
		}
	}

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
	public void extendedConstructorTest_RubbishDirection()
	{
		return; //TODO write this when there exists a non valid direction.
	}

	@Test
	public void extendedConstructorTest_RubbishPosition()
	{
		return; //TODO write this when there exists a non valid position.
	}

	@Test
	public void extendedConstructorTest_RubbishCircleShape()
	{
		CircleShape cs = new CircleShape(Ship.getMinimumRadius()-1);
		Ship ship = new Ship(new Direction(), new Position(), cs, Velocity.getSpeedOfLight(), new Velocity());
		assertNotSame(ship.getShape(),cs);
		assertEquals(ship.getShape(), new CircleShape(Ship.getMinimumRadius()));
	}

	@Test
	public void extendedConstructorTest_RubbishSpeedLimit()
	{
		double sl = Velocity.getSpeedOfLight()+1;
		Ship ship = new Ship(new Direction(), new Position(), new CircleShape(Ship.getMinimumRadius()), sl, new Velocity());
		assertFalse(Util.fuzzyEquals(ship.getSpeedLimit(), sl));
		assertTrue(Util.fuzzyEquals(ship.getSpeedLimit(), Velocity.getSpeedOfLight()));
	}

	@Test
	public void extendedConstructorTest_RubbishVelocity()
	{
		Velocity v = new Velocity(Velocity.getSpeedOfLight(),Velocity.getSpeedOfLight());
		Ship ship = new Ship(new Direction(), new Position(), new CircleShape(Ship.getMinimumRadius()), Velocity.getSpeedOfLight(), v);
		assertEquals(ship.getVelocity(), new Velocity(0.0,0.0));
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

}
