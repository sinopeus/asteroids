package world.physics.vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Utilities.Util;

@SuppressWarnings ("javadoc")
public class VelocityTest
{

	@Before
	public void setUpImmutableTestFixture_Velocity ()
	{
		testVelocity = new Velocity(10, 10);
	}

	private static Velocity	testVelocity;

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents_PerfectParameters ()
	{
		Velocity v = new Velocity(5, 6);
		assertTrue(Util.fuzzyEquals(v._X(), 5));
		assertTrue(Util.fuzzyEquals(v._Y(), 6));
	}

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents_FasterThanTheSpeedOfLight ()
	{
		Velocity v = new Velocity(Velocity.getSpeedOfLight() * 2, 0);
		assertEquals(v, new Velocity(Velocity.getSpeedOfLight(), 0));
	}

	@Test
	public void extendedConstructorTest_IllegalXComponent ()
	{
		Velocity v = new Velocity(Double.NaN, 0);
		assertTrue(Util.fuzzyEquals(v._X(), 0));
	}

	@Test
	public void extendedConstructorTest_IllegalYComponent ()
	{
		Velocity v = new Velocity(0, Double.NaN);
		assertTrue(Util.fuzzyEquals(v._Y(), 0));
	}

	@Test
	public void byVectorConstructorTest_ComponentsMatchVectorComponents_PerfectParameters ()
	{
		Vector v = new Vector(5, 6);
		Velocity ve = new Velocity(v);
		assertTrue(Util.fuzzyEquals(ve._X(), v._X()));
		assertTrue(Util.fuzzyEquals(ve._Y(), v._Y()));
	}

	@Test (expected = NullPointerException.class)
	public void byVectorConstructorTest_ComponentsMatchVectorComponents_NullVector ()
	{
		new Vector(null);
	}

	@Test
	public void simpleConstructorTest_ComponentsMatchZero ()
	{
		Velocity v = new Velocity();
		assertTrue(Util.fuzzyEquals(v._X(), 0));
		assertTrue(Util.fuzzyEquals(v._Y(), 0));
	}

	@Test
	public void setXComponentTest_LegalCase ()
	{
		testVelocity.setX(5);
		assertTrue(Util.fuzzyEquals(testVelocity._X(), 5));
	}

	@Test
	public void setYComponentTest_LegalCase ()
	{
		testVelocity.setY(5);
		assertTrue(Util.fuzzyEquals(testVelocity._Y(), 5));
	}

	@Test
	public void getVelocityTest ()
	{
		assertTrue(Util.fuzzyLessThanOrEqualTo(testVelocity.get(), Velocity.getSpeedOfLight()));
	}

	@Test
	public void getSumTest ()
	{
		Vector v = new Vector(15, 5);
		Velocity sumVector = testVelocity.getSum(v);
		assertTrue(Util.fuzzyEquals(sumVector._X(), 25));
		assertTrue(Util.fuzzyEquals(sumVector._Y(), 15));
	}

	@Test
	public void accelerateTest_LegalDuration ()
	{
		Acceleration a = new Acceleration(5, 6);
		testVelocity.accelerateBy(a, 2.5);
		assertTrue(Util.fuzzyEquals(testVelocity._X(), 22.5));
		assertTrue(Util.fuzzyEquals(testVelocity._Y(), 25.0));
	}

	@Test
	public void accelerateByTest_IllegalDuration ()
	{
		Acceleration a = new Acceleration(5, 5);
		testVelocity.accelerateBy(a, -1);
	}

	@Test
	public void accelerateByTest_SpeedLimitTest ()
	{
		Velocity v = new Velocity(Velocity.getSpeedOfLight(), 0);
		Acceleration a = new Acceleration(5, 5);
		v.accelerateBy(a, 5);
		Util.fuzzyEquals(v.get(), Velocity.getSpeedOfLight());
	}

	@Test
	public void toStringTest ()
	{
		testVelocity.toString();
	}

	@Test
	public void equalsTest ()
	{
		assertTrue(testVelocity.equals(new Velocity(10, 10)));
		assertFalse(testVelocity.equals(new Velocity(10, -10)));
		assertFalse(testVelocity.equals(new Velocity(-10, 10)));
		assertFalse(testVelocity.equals(new Velocity()));
		assertFalse(testVelocity.equals(new Vector(10, 10)));
		assertFalse(testVelocity.equals(null));
	}

	@Test
	public void getSpeedOfLightTest ()
	{
		assertTrue(Util.fuzzyEquals(Velocity.getSpeedOfLight(), 300000));
	}
}
