package entity.ship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import model.Util;

import org.junit.Before;
import org.junit.Test;

import vector.Vector;

public class MassTest
{
	@Before
	public void setUpImmutableTestFixtureMass()
	{

	}

	private static Mass testMass;

	@Test
	public void constructorTest_MassValueMatchesGivenMassValue_PerfectParameters()
	{
		Mass m = new Mass(50);
		assertTrue(Util.fuzzyEquals(50, m.get()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorTest_IllegalMass()
	{
		new Mass(-1);
	}

	@Test
	public void canHaveAsMassTest()
	{
		assertTrue(testMass.canHaveAsMass(50));
		assertFalse(testMass.canHaveAsMass(0));
		assertFalse(testMass.canHaveAsMass(-1));
	}

	@Test
	public void equalsTest()
	{
		assertTrue(testMass.equals(testMass));
		assertTrue(testMass.equals(new Mass(50)));
		assertFalse(testMass.equals(new Mass(0)));
		assertFalse(testMass.equals(null));
		assertFalse(testMass.equals(new Vector()));
	}

	@Test
	public void toStringTest()
	{
		assertEquals("50kg", testMass.toString());
	}

	@Test
	public void hashCodeTest()
	{
		assertNotSame(new Mass(50), testMass);
	}
}
