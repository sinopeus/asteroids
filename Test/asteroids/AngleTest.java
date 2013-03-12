package asteroids;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class AngleTest
{
	@Before
	public void setUpsetUpImmutableTestFixture_Angle()
	{
		testAngle = new Angle();
	}

	private static Angle testAngle;

	@Test
	public void constructorTest_AngleMatchesGivenAngle_PerfectParameters()
	{
		Angle a = new Angle(Math.PI);
		assertTrue(Util.fuzzyEquals(a.getAngle(), Math.PI));
	}

	@Test
	public void constructorTest_AngleMatchesGivenAngle_IllegalAngle()
	{
		Angle a1 = new Angle(Double.NaN);
		assertFalse(Util.fuzzyEquals(a1.getAngle(), Double.NaN));
		assertTrue(Util.fuzzyEquals(a1.getAngle(), 0));
		Angle a2 = new Angle(Double.POSITIVE_INFINITY);
		assertFalse(Util.fuzzyEquals(a2.getAngle(), Double.POSITIVE_INFINITY));
		assertTrue(Util.fuzzyEquals(a2.getAngle(), 0));
	}

	@Test
	public void constructorTest_AngleMatchesZero()
	{
		Angle a = new Angle();
		assertTrue(Util.fuzzyEquals(a.getAngle(), 0));
	}

	@Test
	public void canHaveAsAngleTest()
	{
		assertTrue(testAngle.canHaveAsAngle(Math.PI));
		assertFalse(testAngle.canHaveAsAngle(Double.NaN));
		assertFalse(testAngle.canHaveAsAngle(Double.POSITIVE_INFINITY));
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
		assertTrue(Util.fuzzyEquals(testAngle.getAngle(), 0));
		testAngle.setAngle(Double.POSITIVE_INFINITY);
		assertFalse(Util.fuzzyEquals(testAngle.getAngle(), Double.POSITIVE_INFINITY));
		assertTrue(Util.fuzzyEquals(testAngle.getAngle(), 0));
	}

	@Test
	public void getSinTest()
	{
		assertTrue(Util.fuzzyEquals(testAngle.getSin(), 0));
	}

	@Test
	public void getCosTest()
	{
		assertTrue(Util.fuzzyEquals(testAngle.getCos(), 1));
	}

	@Test
	public void equalsTest_PerfectParameter()
	{
		assertEquals(testAngle, new Angle());
		assertNotSame(testAngle, new Angle(5));
		assertNotSame(testAngle, new Ship());
	}

	@Test
	public void addTest()
	{
		Angle testAngle2 = new Angle(Math.PI);
		testAngle.add(testAngle2);
		assertTrue(Util.fuzzyEquals(testAngle.getAngle(), Math.PI));
		testAngle2.add(testAngle2);
		assertTrue(Util.fuzzyEquals(testAngle2.getAngle(), 0));
	}
}
