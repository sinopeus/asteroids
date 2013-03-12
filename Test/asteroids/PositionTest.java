package asteroids;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
	public void getSumTest()
	{
		Vector v = new Vector(15, 5);
		Position sumVector = testPosition.getSum(v);
		assertTrue(Util.fuzzyEquals(sumVector.getXComponent(), 20));
		assertTrue(Util.fuzzyEquals(sumVector.getYComponent(), 10));
	}

	@Test
	public void moveByTest_LegalDuration()
	{
		Velocity v = new Velocity(5, 6);
		testPosition.moveBy(v,2.5);
		assertTrue(Util.fuzzyEquals(testPosition.getXComponent(), 17.5));
		assertTrue(Util.fuzzyEquals(testPosition.getYComponent(), 20.0));
	}

	@Test
	public void moveByTest_IllegalDuration()
	{
		Velocity v = new Velocity(5, 5);
		try
		{
			testPosition.moveBy(v, -1);
			fail();
		} catch (IllegalArgumentException e)
		{
			return;
		}
	}
}