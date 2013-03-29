package world.physics.vector;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import world.physics.vector.Force;
import world.physics.vector.Vector;

import Utilities.Util;

public class ForceTest
{
	@Before
	public void setUpImmutableTestFixture_Force(){
		testForce = new Force(4, 5);
	}
	
	private static Force testForce;
	
	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents_PerfectParameters()
	{
		Force f = new Force(3, 4);
		assertTrue(Util.fuzzyEquals(3, f.getX()));
		assertTrue(Util.fuzzyEquals(4, f.getY()));
	}

	@Test
	public void byVectorConstructorTest_ComponentsMatchGivenComponents_PerfectParameters()
	{
		Force f = new Force(new Vector(5, 6));
		assertTrue(Util.fuzzyEquals(5, f.getX()));
		assertTrue(Util.fuzzyEquals(6, f.getY()));
	}

	@SuppressWarnings ("unused")
	@Test(expected = NullPointerException.class)
	public void byVectorConstructorTest_NullVector()
	{
		Force f = new Force(null);
	}

	@Test
	public void simpleConstructorTest_ComponentsMatchGivenComponents()
	{
		Force f = new Force();
		assertTrue(Util.fuzzyEquals(0, f.getX()));
		assertTrue(Util.fuzzyEquals(0, f.getY()));
	}
	
	@Test
	public void toStringTest(){
		testForce.toString();
	}
}
