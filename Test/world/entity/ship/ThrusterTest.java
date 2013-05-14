package world.entity.ship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import world.entity.ship.Ship;
import world.entity.ship.Thruster;
import world.physics.Mass;
import world.physics.geometry.Angle;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;

import Utilities.Util;

@SuppressWarnings ("javadoc")
public class ThrusterTest
{
	@Before
	public void setUpMutableTestCase_Thruster()
	{
		testShip1 = new Ship(new Direction(new Angle(Math.PI/2.0)), new Position(), 5000.0, new Velocity(3, 4), new CircleShape(Ship.getMinimumRadius()), new Mass(40));
		testThruster = new Thruster(2, testShip1);
	}

	private static Ship testShip1;
	private static Thruster testThruster;
	
	@Test
	public void extendedConstructorTest_FieldsMatchParameters_PerfectParameters()
	{
		Thruster th = new Thruster(25, testShip1);
		assertTrue(Util.fuzzyEquals(25, th.getMaximumThrustPerSecond()));
		assertEquals(testShip1, th.getOwner());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void extendedConstructorTest_NullShip()
	{
		new Thruster(25, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void extendedConstructorTest_IllegalThrust()
	{
		new Thruster(-1, testShip1);
	}
	
	@Test
	public void canHaveAsMaximumThrustTest()
	{
		assertTrue(testThruster.canHaveAsMaximumThrust(50));
		assertTrue(testThruster.canHaveAsMaximumThrust(0));
		assertFalse(testThruster.canHaveAsMaximumThrust(-1));
	}

	@Test
	public void activateTest()
	{
		testThruster.activate();
		assertTrue(testThruster.isActivated());
	}

	@Test
	public void deactivateTest()
	{
		testThruster.deactivate();
		assertFalse(testThruster.isActivated());
	}

	@Test
	public void toggleActivationTest()
	{
		testThruster.activate();
		testThruster.toggleActivation();
		assertFalse(testThruster.isActivated());
		testThruster.toggleActivation();
		assertTrue(testThruster.isActivated());
	}

	@Test
	public void canHaveAsOwnerTest()
	{
		assertTrue(testThruster.canHaveAsOwner(testShip1));
		assertFalse(testThruster.canHaveAsOwner(null));
	}

	@Test
	public void setOwnerTest_LegalCase()
	{
		Ship s = new Ship();
		testThruster.setOwner(s);
		assertEquals(s, testThruster.getOwner());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setOwnerTest_IllegalCase()
	{
		testThruster.setOwner(null);
	}
	
	@Test
	public void terminationTest()
	{
		testThruster.terminate();
		assertTrue(testThruster.getOwner() == null);
		assertTrue(testThruster.isTerminated());
	}

	@Test
	public void simpleThrustTest_PerfectParameters()
	{
		testThruster.thrust(6);
		assertEquals(testShip1.getVelocity(),new Velocity(3, 5.8));
	}

	@Test
	public void simpleThrustTest_IllegalDuration()
	{
		Velocity initialVelocity = testShip1.getVelocity();
		testThruster.thrust(-1);
		assertEquals(initialVelocity, testShip1.getVelocity());
	}

	@Test
	public void extendedThrustTest_PerfectParameters()
	{
		testThruster.thrust(5, 6);
		assertEquals(testShip1.getVelocity(),new Velocity(3, 8.5));
	}

	@Test
	public void extendedThrustTest_IllegalThrust()
	{
		Velocity initialVelocity = testShip1.getVelocity();
		testThruster.thrust(-1, 5);
		assertEquals(initialVelocity, testShip1.getVelocity());
	}
	
	@Test
	public void extendedThrustTest_EnormousThrust()
	{
		testThruster.thrust(Double.MAX_VALUE, 6);
		assertEquals(testShip1.getVelocity(), (new Velocity(3, 4)).getSum(testShip1.getDirection().getScaledBy(testShip1.getThrustPerSecond())));
	}

	@Test
	public void extendedThrustTest_IllegalDuration()
	{
		Velocity initialVelocity = testShip1.getVelocity();
		testThruster.thrust(5, -1);
		assertEquals(initialVelocity, testShip1.getVelocity());
	}
	
	@Test
	public void toStringTest(){
		testThruster.toString();
	}
}
