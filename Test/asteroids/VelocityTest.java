package asteroids;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class VelocityTest
{

	@Before
	public void setUpImmutableTestFixture_Velocity()
	{
		testVelocity = new Velocity(10, 10);
	}

	private static Velocity testVelocity;

	@Test
	public void setXComponentTest_LegalCase()
	{
		testVelocity.setXComponent(5);
		assertTrue(Util.fuzzyEquals(testVelocity.getXComponent(), 5));
	}

	@Test
	public void setXComponentTest_IllegalCase()
	{
	}

	@Test
	public void canHaveAsComponentTest()
	{
		assertTrue(testVelocity.canHaveAsComponent(50));
	}

	@Test
	public void setYComponentTest_LegalCase()
	{
		testVelocity.setYComponent(5);
		assertTrue(Util.fuzzyEquals(testVelocity.getYComponent(), 5));
	}

	@Test
	public void setYComponentTest_IllegalCase()
	{
	}

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenPerfectComponents()
	{
		Velocity v = new Velocity(5, 6);
		assertTrue(Util.fuzzyEquals(v.getXComponent(), 5));
		assertTrue(Util.fuzzyEquals(v.getYComponent(), 6));
	}

	@Test
	public void extendedConstructorTest_IllegalXComponent()
	{
		Velocity v = new Velocity(Velocity.getSpeedOfLight() + 1, 0);
		assertFalse(Util.fuzzyEquals(v.getXComponent(), Velocity.getSpeedOfLight() + 1));
		assertTrue(Util.fuzzyEquals(v.getXComponent(), 0));
	}

	@Test
	public void extendedConstructorTest_IllegalYComponent()
	{
		Velocity v = new Velocity(0, Velocity.getSpeedOfLight() + 1);
		assertFalse(Util.fuzzyEquals(v.getYComponent(), Velocity.getSpeedOfLight() + 1));
		assertTrue(Util.fuzzyEquals(v.getYComponent(), 0));
	}

	@Test
	public void extendedConstructorTest_IllegalComponents()
	{
		Velocity v = new Velocity(Velocity.getSpeedOfLight(), Velocity.getSpeedOfLight());
		assertEquals(v, new Velocity(212132.03435596428, 212132.03435596428));
	}

	@Test
	public void byVectorConstructorTest_ComponentsMatchVectorComponents()
	{
		Vector v = new Vector(5, 6);
		Velocity ve = new Velocity(v);
		assertTrue(Util.fuzzyEquals(ve.getXComponent(), v.getXComponent()));
		assertTrue(Util.fuzzyEquals(ve.getYComponent(), v.getYComponent()));
	}

	@Test
	public void simpleConstructorTest_ComponentsMatchZero()
	{
		Velocity v = new Velocity();
		assertTrue(Util.fuzzyEquals(v.getXComponent(), 0));
		assertTrue(Util.fuzzyEquals(v.getYComponent(), 0));
	}

	@Test
	public void getVelocityTest()
	{
		assertTrue(Util.fuzzyLessThanOrEqualTo(testVelocity.getVelocity(), Velocity.getSpeedOfLight()));
	}

	@Test
	public void getSumTest()
	{
		Vector v = new Vector(15, 5);
		Velocity sumVector = testVelocity.getSum(v);
		assertTrue(Util.fuzzyEquals(sumVector.getXComponent(), 25));
		assertTrue(Util.fuzzyEquals(sumVector.getYComponent(), 15));
	}

	@Test
	public void accelerateTest_LegalDuration()
	{
		Acceleration a = new Acceleration(5, 6);
		testVelocity.accelerateBy(a, 2.5);
		assertTrue(Util.fuzzyEquals(testVelocity.getXComponent(), 22.5));
		assertTrue(Util.fuzzyEquals(testVelocity.getYComponent(), 25.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void accelerateByTest_IllegalDuration()
	{
		Acceleration a = new Acceleration(5, 5);
		testVelocity.accelerateBy(a, -1);
	}

	@Test
	public void accelerateByTest_SpeedLimitTest()
	{
		Velocity v = new Velocity(Velocity.getSpeedOfLight(), 0);
		Acceleration a = new Acceleration(5, 5);
		v.accelerateBy(a, 5);
		Util.fuzzyEquals(v.getVelocity(), Velocity.getSpeedOfLight());
	}

	@Test
	public void getSpeedOfLightTest()
	{
		assertTrue(Util.fuzzyEquals(Velocity.getSpeedOfLight(), 300000));
	}
}
