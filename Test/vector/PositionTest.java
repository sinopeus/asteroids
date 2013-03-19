package vector;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.Util;

import org.junit.Before;
import org.junit.Test;


import vector.Position;
import vector.Vector;
import vector.Velocity;


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
		assertTrue(Util.fuzzyEquals(p.getXComponent(), 5));
		assertTrue(Util.fuzzyEquals(p.getYComponent(), 6));
	}

	@Test
	public void byVectorConstructorTest_ComponentsMatchVectorComponents()
	{
		Vector v = new Vector(5, 6);
		Position p = new Position(v);
		assertTrue(Util.fuzzyEquals(p.getXComponent(), v.getXComponent()));
		assertTrue(Util.fuzzyEquals(p.getYComponent(), v.getYComponent()));
	}

	@Test
	public void simpleConstructorTest_ComponentsMatchZero()
	{
		Position p = new Position();
		assertTrue(Util.fuzzyEquals(p.getXComponent(), 0));
		assertTrue(Util.fuzzyEquals(p.getYComponent(), 0));
	}

	@Test
	public void setXComponentTest_LegalCase()
	{
		testPosition.setXComponent(10);
		assertTrue(Util.fuzzyEquals(testPosition.getXComponent(), 10));
	}

	@Test
	public void setXComponentTest_IllegalCase()
	{

	}

	@Test
	public void setYComponentTest_LegalCase()
	{
		testPosition.setYComponent(10);
		assertTrue(Util.fuzzyEquals(testPosition.getYComponent(), 10));
	}

	@Test
	public void setYComponentTest_IllegalCase()
	{

	}

	@Test
	public void getSumTest()
	{
		Vector v = new Vector(15, 5);
		Position sumVector = testPosition.getSum(v);
		assertTrue(Util.fuzzyEquals(sumVector.getXComponent(), 20));
		assertTrue(Util.fuzzyEquals(sumVector.getYComponent(), 10));
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
		assertTrue(Util.fuzzyEquals(testPosition.getXComponent(), 17.5));
		assertTrue(Util.fuzzyEquals(testPosition.getYComponent(), 20.0));
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
	public void equalsTest()
	{
		assertTrue(testPosition.equals(new Position(5, 5)));
		assertFalse(testPosition.equals(new Position()));
		assertFalse(testPosition.equals(new Vector(5, 5)));
		assertFalse(testPosition.equals(null));
	}
}