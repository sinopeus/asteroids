package asteroids.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asteroids.Util;
import asteroids.Velocity;

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
	public void extendedConstructorTest_ComponentsMatchGivenComponents()
	{
		Velocity v = new Velocity(5, 6);
		assertTrue(Util.fuzzyEquals(v.getXComponent(), 5));
		assertTrue(Util.fuzzyEquals(v.getYComponent(), 6));
	}

	@Test
	public void simpleConstructorTest_ComponentsMatchZero()
	{
		Velocity v = new Velocity();
		assertTrue(Util.fuzzyEquals(v.getXComponent(), 0));
		assertTrue(Util.fuzzyEquals(v.getYComponent(), 0));
	}

	@Test
	public void getVelocityTest(){
		assertTrue(Util.fuzzyLessThanOrEqualTo(testVelocity.getVelocity(), Velocity.getSpeedOfLight()));
	}
	
	@Test
	public void getSpeedOfLightTest(){
		assertTrue(Util.fuzzyEquals(Velocity.getSpeedOfLight(), 300000));
	}
}
