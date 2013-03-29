package world.physics.vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import world.entity.ship.Ship;
import world.physics.geometry.Angle;
import world.physics.vector.Direction;

import Utilities.Util;






@SuppressWarnings("javadoc")
public class DirectionTest
{
	@Before
	public void setUpImmutableTestFixture_Direction()
	{
		testAngle = new Angle(Math.PI / 2.0);
		testDirection = new Direction(testAngle);
	}

	private static Angle testAngle;
	private static Direction testDirection;

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents()
	{
		Direction d = new Direction(testAngle);
		assertTrue(Util.fuzzyEquals(d.getX(), testAngle.getCos()));
		assertTrue(Util.fuzzyEquals(d.getY(), testAngle.getSin()));
	}

	@Test
	public void simpleConstructorTest_ComponentsMatchGivenComponents()
	{
		Direction d = new Direction();
		assertTrue(Util.fuzzyEquals(d.getX(), 1.0));
		assertTrue(Util.fuzzyEquals(d.getY(), 0.0));
	}
	
	@Test
	public void canHaveAsAngleTest()
	{
		assertTrue(testDirection.canHaveAsAngle(testAngle));
		assertFalse(testDirection.canHaveAsAngle(null));
	}

	@Test
	public void setAngleTest_LegalCase()
	{
		testDirection.setAngle(new Angle(Math.PI));
		assertEquals(testDirection.getAngle(), new Angle(Math.PI));
	}

	@Test
	public void getXComponentTest()
	{
		assertTrue(Util.fuzzyEquals(testDirection.getX(), 0));
		assertTrue(Util.fuzzyEquals(testDirection.getY(), 1));
	}

	@Test
	public void equalsTest()
	{
		assertTrue(testDirection.equals(new Direction(testAngle)));
		assertFalse(testDirection.equals(new Direction()));
		assertFalse(testDirection.equals(new Ship()));
		assertFalse(testDirection.equals(null));
	}

	@Test
	public void rotateTest()
	{
		testDirection.rotate(testAngle);
		assertEquals(testDirection.getAngle(), new Angle(Math.PI));
	}
	
	@Test
	public void toStringTest(){
		testDirection.toString();
	}
}
