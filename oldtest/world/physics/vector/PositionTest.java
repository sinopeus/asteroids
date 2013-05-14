package world.physics.vector;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import world.physics.vector.Position;
import world.physics.vector.Vector;
import world.physics.vector.Velocity;

import Utilities.Util;






@SuppressWarnings("javadoc")
public class PositionTest
{
	@Before
	public void setUpImmutableTestFixture_Position()
	{
		testPosition = new Position(5, 5);
	}

	private static Position testPosition;

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents()
	{
		Position p = new Position(5, 6);
		assertTrue(Util.fuzzyEquals(p._X(), 5));
		assertTrue(Util.fuzzyEquals(p._Y(), 6));
	}

	@Test
	public void byVectorConstructorTest_ComponentsMatchVectorComponents()
	{
		Vector v = new Vector(5, 6);
		Position p = new Position(v);
		assertTrue(Util.fuzzyEquals(p._X(), v._X()));
		assertTrue(Util.fuzzyEquals(p._Y(), v._Y()));
	}

	@Test
	public void simpleConstructorTest_ComponentsMatchZero()
	{
		Position p = new Position();
		assertTrue(Util.fuzzyEquals(p._X(), 0));
		assertTrue(Util.fuzzyEquals(p._Y(), 0));
	}

	@Test
	public void setXComponentTest_LegalCase()
	{
		testPosition.setX(10);
		assertTrue(Util.fuzzyEquals(testPosition._X(), 10));
	}

	@Test(expected = IllegalArgumentException.class)
	public void setXComponentTest_IllegalCase()
	{
		testPosition.setX(Double.NaN);
	}

	@Test
	public void setYComponentTest_LegalCase()
	{
		testPosition.setY(10);
		assertTrue(Util.fuzzyEquals(testPosition._Y(), 10));
	}

	@Test(expected = IllegalArgumentException.class)
	public void setYComponentTest_IllegalCase()
	{
		testPosition.setY(Double.NaN);
	}

	@Test
	public void getSumTest()
	{
		Vector v = new Vector(15, 5);
		Position sumVector = testPosition.getSum(v);
		assertTrue(Util.fuzzyEquals(sumVector._X(), 20));
		assertTrue(Util.fuzzyEquals(sumVector._Y(), 10));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getSumTest_nullVector()
	{
		testPosition.getSum(null);
	}

	@Test
	public void moveByTest_PerfectParameters()
	{
		Velocity v = new Velocity(5, 6);
		testPosition.moveBy(v, 2.5);
		assertTrue(Util.fuzzyEquals(testPosition._X(), 17.5));
		assertTrue(Util.fuzzyEquals(testPosition._Y(), 20.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void moveByTest_nullVelocity()
	{
		testPosition.moveBy(null, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void moveByTest_IllegalDuration()
	{
		Velocity v = new Velocity(5, 5);
		testPosition.moveBy(v, -1);
	}
	
	@Test
	public void toStringTest(){
		testPosition.toString();
	}

	@Test
	public void equalsTest()
	{
		assertTrue(testPosition.equals(new Position(5, 5)));
		assertFalse(testPosition.equals(new Position(4, 5)));
		assertFalse(testPosition.equals(new Position(5, 4)));
		assertFalse(testPosition.equals(new Position()));
		assertFalse(testPosition.equals(new Vector(5, 5)));
		assertFalse(testPosition.equals(null));
	}
}