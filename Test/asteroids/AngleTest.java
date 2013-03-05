package asteroids;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AngleTest
{
	@Before
	public void setUpsetUpImmutableTestFixture_CircleShape()
	{
		testAngle = new Angle();
	}

	private static Angle testAngle;

	@Test
	public void canHaveAsAngleTest()
	{
		assertTrue(testAngle.canHaveAsAngle(Math.PI));
	}

	@Test
	public void setAngleTest_LegalCase()
	{
		testAngle.setAngle(0);
		assertTrue(Util.fuzzyEquals(testAngle.getAngle(), 0));
		testAngle.setAngle(-Math.PI / 2);
		assertTrue(Util.fuzzyEquals(testAngle.getAngle(), 3 * Math.PI / 2));
	}

	@Test
	public void setAngleTest_IllegalCase()
	{
		testAngle.setAngle(Double.NaN);
		assertFalse(Util.fuzzyEquals(testAngle.getAngle(), Double.NaN));
	}

	@Test
	public void sinTest()
	{
		assertTrue(Util.fuzzyEquals(testAngle.sin(), 0));
	}

	@Test
	public void cosTest()
	{
		assertTrue(Util.fuzzyEquals(testAngle.cos(), 1));
	}

	@Test
	public void constructorTest_AngleMatchesGivenAngle()
	{
		Angle a = new Angle(Math.PI);
		assertTrue(Util.fuzzyEquals(a.getAngle(), Math.PI));
	}

	@Test
	public void constructorTest_AngleMatchesZero()
	{
		Angle a = new Angle();
		assertTrue(Util.fuzzyEquals(a.getAngle(), 0));
	}

	@Test
	public void equalsTest()
	{
		assertEquals(testAngle, new Angle());
		assertNotSame(testAngle, new Angle(5));
		assertNotSame(testAngle, new Ship());
	}

	@Test
	public void sumTest()
	{
		Angle testAngle2 = new Angle(Math.PI);
		testAngle.sum(testAngle2);
		assertTrue(Util.fuzzyEquals(testAngle.getAngle(), Math.PI));
		testAngle2.sum(testAngle2);
		assertTrue(Util.fuzzyEquals(testAngle2.getAngle(), 0));
	}
}
