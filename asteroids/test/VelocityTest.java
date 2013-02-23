package asteroids.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asteroids.Util;
import asteroids.Velocity;

public class VelocityTest
{

	@Before
	private void setUpImmutableTestFixture_Velocity()
	{
		testVelocity = new Velocity(10, 10);
	}

	@Test
	private void canHaveAsXComponentTest()
	{
		assertTrue(testVelocity.canHaveAsXComponent(50));
	}

	@Test
	private void setXComponentTest_LegalCase()
	{
		testVelocity.setXComponent(5);
		assertTrue(Util.fuzzyEquals(testVelocity.getXComponent(), 5));
	}

	@Test
	private void setXComponentTest_IllegalCase()
	{
	}

	@Test
	private void canHaveAsYComponentTest()
	{
		assertTrue(testVelocity.canHaveAsYComponent(50));
	}

	@Test
	private void setYComponentTest_LegalCase()
	{
		testVelocity.setYComponent(5);
		assertTrue(Util.fuzzyEquals(testVelocity.getYComponent(), 5));
	}

	@Test
	private void setYComponentTest_IllegalCase()
	{
	}

	@Test
	private void extendedConstructorTest_ComponentsMatchGivenComponents()
	{
		Velocity v = new Velocity(5, 6);
		assertTrue(Util.fuzzyEquals(v.getXComponent(), 5));
		assertTrue(Util.fuzzyEquals(v.getYComponent(), 6));
	}

	@Test
	private void simpleConstructorTest_ComponentsMatchZero()
	{
		Velocity v = new Velocity();
		assertTrue(Util.fuzzyEquals(v.getXComponent(), 0));
		assertTrue(Util.fuzzyEquals(v.getYComponent(), 0));
	}

	private static Velocity testVelocity;
}
