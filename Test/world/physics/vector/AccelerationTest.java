package world.physics.vector;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Utilities.Util;

@SuppressWarnings ("javadoc")
public class AccelerationTest
{
	@Before
	public void setUpImmutableTestFixture_Acceleration ()
	{
		testAcceleration = new Acceleration(5, 5);
	}

	private Acceleration	testAcceleration;

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents_PerfectParameters ()
	{
		Acceleration a = new Acceleration(5, 6);
		assertTrue(Util.fuzzyEquals(a._X(), 5));
		assertTrue(Util.fuzzyEquals(a._Y(), 6));
	}

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents_IllegalXComponent ()
	{
		Acceleration a = new Acceleration(Double.NaN, 6);
		assertFalse(Util.fuzzyEquals(a._X(), 5));
		assertTrue(Util.fuzzyEquals(a._X(), 0));
		assertTrue(Util.fuzzyEquals(a._Y(), 6));
	}

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents_IllegalYComponent ()
	{
		Acceleration a = new Acceleration(5, Double.NaN);
		assertTrue(Util.fuzzyEquals(a._X(), 5));
		assertFalse(Util.fuzzyEquals(a._Y(), 6));
		assertTrue(Util.fuzzyEquals(a._Y(), 0));
	}

	@Test
	public void byVectorAccelerationTest_ComponentsMatchGivenVectorComponents_PerfectParameters ()
	{
		Acceleration a = new Acceleration(new Vector(2, 3));
		assertTrue(Util.fuzzyEquals(a._X(), 2));
		assertTrue(Util.fuzzyEquals(a._Y(), 3));
	}

	@SuppressWarnings ("unused")
	@Test (expected = NullPointerException.class)
	public void byVectorAccelerationTest_ComponentsMatchGivenVectorComponents_nullVector ()
	{
		Acceleration a = new Acceleration(null);
	}

	@Test
	public void simpleConstructorTest_ComponentsMatchZero ()
	{
		Acceleration a = new Acceleration();
		assertTrue(Util.fuzzyEquals(a._X(), 0));
		assertTrue(Util.fuzzyEquals(a._Y(), 0));
	}

	@Test
	public void toStringTest ()
	{
		testAcceleration.toString();
	}

	@Test
	public void equalsTest ()
	{
		assertTrue(testAcceleration.equals(new Acceleration(5, 5)));
		assertFalse(testAcceleration.equals(new Acceleration(4, 5)));
		assertFalse(testAcceleration.equals(new Acceleration(5, 4)));
		assertFalse(testAcceleration.equals(new Acceleration()));
		assertFalse(testAcceleration.equals(new Vector(5, 5)));
		assertFalse(testAcceleration.equals(null));
	}
}
