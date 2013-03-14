package asteroids;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Facade;
import model.ModelException;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class FacadeTest
{
	@Before
	public void setUpMutableTestFixture_Ship()
	{
		testFacade = new Facade();
		testShip1 = testFacade.createShip(700, 400, 1, 2, 50, Math.PI);
		testShip2 = testFacade.createShip(400, 700, 0, 0, 50, Math.PI);
	}

	private static Facade testFacade;
	private static IShip testShip1;
	private static IShip testShip2;

	@Test
	public void createShipSimpleTest()
	{
		testFacade.createShip();
	}

	@Test
	public void createShipExtendedTest_PerfectParameters()
	{
		testFacade.createShip(700, 400, 0, 0, 50, Math.PI);
	}

	@Test(expected = ModelException.class)
	public void createShipExtendedTest_IllegalXPosition()
	{
		testFacade.createShip(Double.NaN, 400, 0, 0, 50, Math.PI);
	}

	@Test(expected = ModelException.class)
	public void createShipExtendedTest_IllegalYPosition()
	{
		testFacade.createShip(700, Double.NaN, 0, 0, 50, Math.PI);
	}

	@Test
	public void createShipExtendedTest_IllegalXVelocity()
	{
		testFacade.createShip(700, 400, Double.POSITIVE_INFINITY, 0, 50, Math.PI);
	}

	@Test
	public void createShipExtendedTest_IllegalYVelocity()
	{
		testFacade.createShip(700, 400, 0, Double.POSITIVE_INFINITY, 50, Math.PI);
	}

	@Test(expected = ModelException.class)
	public void createShipExtendedTest_IllegalRadius()
	{
		testFacade.createShip(700, 400, 0, 0, -1, Math.PI);
	}

	@Test(expected = ModelException.class)
	public void createShipExtendedTest_IllegalAngle()
	{
		testFacade.createShip(700, 400, 0, 0, -1, Double.NaN);
	}

	@Test
	public void getXTest()
	{
		assertTrue(Util.fuzzyEquals(testFacade.getX(testShip1), ((Ship) testShip1).getPosition().getXComponent()));
	}

	@Test
	public void getYTest()
	{
		assertTrue(Util.fuzzyEquals(testFacade.getY(testShip1), ((Ship) testShip1).getPosition().getYComponent()));
	}

	@Test
	public void getXVelocityTest()
	{
		assertTrue(Util.fuzzyEquals(testFacade.getXVelocity(testShip1), ((Ship) testShip1).getVelocity().getXComponent()));
	}

	@Test
	public void getYVelocityTest()
	{
		assertTrue(Util.fuzzyEquals(testFacade.getYVelocity(testShip1), ((Ship) testShip1).getVelocity().getYComponent()));
	}

	@Test
	public void getRadiusTest()
	{
		assertTrue(Util.fuzzyEquals(testFacade.getRadius(testShip1), ((Ship) testShip1).getShape().getRadius()));
	}

	@Test
	public void getDirectionTest()
	{
		assertTrue(Util.fuzzyEquals(testFacade.getDirection(testShip1), ((Ship) testShip1).getDirection().getAngle().getAngle()));
	}

	@Test
	public void moveTest_PerfectParameters()
	{
		testFacade.move(testShip1, 5);
		Position p = new Position(705, 410);
		assertEquals(((Ship) testShip1).getPosition(), p);
	}

	@Test(expected = ModelException.class)
	public void moveTest_IllegalShip()
	{
		testFacade.move(null, 5);
	}

	@Test(expected = ModelException.class)
	public void moveTest_IllegalDuration()
	{
		testFacade.move(testShip1, -1);
	}

	@Test
	public void thrustTest_PerfectParamaters()
	{
		testFacade.thrust(testShip2, 5);
		Velocity v = new Velocity(-5, 0);
		assertEquals(((Ship) testShip2).getVelocity(), v);
	}

	@Test
	public void thrustTest_SpeedLimitTest()
	{
		Ship s = (Ship) testFacade.createShip(0, 0, Velocity.getSpeedOfLight(), 0, 10, 0);
		testFacade.thrust(s, 50);
		assertTrue(Util.fuzzyEquals(s.getVelocity().getVelocity(), Velocity.getSpeedOfLight()));
	}

	@Test(expected = ModelException.class)
	public void thrustTest_RubbishShip()
	{
		testFacade.thrust(null, 5);
	}

	@Test
	public void thrustTest_IllegalAcceleration()
	{
		testFacade.thrust(testShip1, -1);
		Velocity v = new Velocity(1, 2);
		assertEquals(((Ship) testShip1).getVelocity(), v);
	}

	@Test
	public void turnTest_PerfectParameters()
	{
		testFacade.turn(testShip1, Math.PI);
		Angle a = new Angle(0);
		assertEquals(((Ship) testShip1).getDirection().getAngle(), a);
	}

	@Test(expected = ModelException.class)
	public void turnTest_IllegalShip()
	{
		testFacade.turn(null, Math.PI);
	}

	@Test
	public void turnTest_IllegalAngle()
	{
		testFacade.turn(testShip1, -Math.PI / 2);
		Angle a = new Angle(Math.PI / 2);
		assertEquals(((Ship) testShip1).getDirection().getAngle(), a);
	}

	@Test
	public void getDistanceBetweenTest_PerfectParamaters()
	{
		double distance = testFacade.getDistanceBetween(testShip1, testShip2);
		assertTrue(Util.fuzzyEquals(distance, 424.264068712));
	}

	@Test(expected = ModelException.class)
	public void getDistanceBetweenTest_IllegalFirstShip()
	{
		testFacade.getDistanceBetween(null, testShip2);
	}

	@Test(expected = ModelException.class)
	public void getDistanceBetweenTest_IllegalSecondShip()
	{
		testFacade.getDistanceBetween(testShip1, null);
	}

	@Test
	public void overlapTest_PerfectParameters()
	{
		assertTrue(testFacade.overlap(testFacade.createShip(), testFacade.createShip()));
		assertFalse(testFacade.overlap(testShip1, testShip2));
	}

	@Test(expected = ModelException.class)
	public void overlapTest_IllegalFirstShip()
	{
		testFacade.overlap(null, testShip1);
	}

	@Test(expected = ModelException.class)
	public void overlapTest_IllegalSecondShip()
	{
		testFacade.overlap(testShip1, null);
	}

	@Test
	public void getTimeToCollisionTest_PerfectParameters()
	{
		Ship s1 = (Ship) testFacade.createShip(0, 0, 1, 2, 10, 0);
		Ship s2 = (Ship) testFacade.createShip(10, 20, -1, -2, 10, 0);

		double result = testFacade.getTimeToCollision(s1, s2);
		assertTrue(Util.fuzzyEquals(result, 0.5278640450004204));
	}

	@Test(expected = ModelException.class)
	public void getTimeToCollisionTest_IllegalFirstShip()
	{
		testFacade.getTimeToCollision(null, testShip2);
	}

	@Test(expected = ModelException.class)
	public void getTimeToCollisionTest_IllegalSecondShip()
	{
		testFacade.getTimeToCollision(testShip1, null);
	}

	@Test
	public void getCollisionPositionTest_PerfectParameters()
	{
		Ship s1 = (Ship) testFacade.createShip(0, 0, 1, 2, 10, 0);
		Ship s2 = (Ship) testFacade.createShip(10, 20, -1, -2, 10, 0);

		double[] result = testFacade.getCollisionPosition(s1, s2);
		assertTrue(Util.fuzzyEquals(result[0], 5));
		assertTrue(Util.fuzzyEquals(result[1], 10));
	}

	@Test(expected = ModelException.class)
	public void getCollisionPositionTest_IllegalFirstShip()
	{
		testFacade.getCollisionPosition(null, testShip2);
	}

	@Test(expected = ModelException.class)
	public void getCollisionPositionTest_IllegalSecondShip()
	{
		testFacade.getCollisionPosition(testShip1, null);
	}

	// TODO: zet op een andere plaats
	@Test(expected = ModelException.class)
	public void thisShouldNotThrow()
	{
		testFacade.move(new Ship(), Double.NaN);
	}
}
