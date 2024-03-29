package world.physics;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import world.physics.vector.Vector;
import Utilities.Util;

public class MassTest
{
	@Before
	public void setUpImmutableTestFixtureMass ()
	{
		testMass = new Mass(50);
	}

	private static Mass	testMass;

	@Test
	public void constructorTest_MassValueMatchesGivenMassValue_PerfectParameters ()
	{
		Mass m = new Mass(50);
		assertTrue(Util.fuzzyEquals(50, m.get()));
	}

	@Test (expected = IllegalArgumentException.class)
	public void constructorTest_IllegalMass ()
	{
		new Mass(-1);
	}

	@Test (expected = IllegalArgumentException.class)
	public void constructorTest_NanMass ()
	{
		new Mass(Double.NaN);
	}

	@Test
	public void canHaveAsMassTest ()
	{
		assertTrue(testMass.canHaveAsMass(50));
		assertFalse(testMass.canHaveAsMass(0));
		assertFalse(testMass.canHaveAsMass(-1));
		assertFalse(testMass.canHaveAsMass(Double.NaN));
	}

	@Test
	public void equalsTest ()
	{
		assertTrue(testMass.equals(testMass));
		assertTrue(testMass.equals(new Mass(50)));
		assertFalse(testMass.equals(new Mass(1)));
		assertFalse(testMass.equals(null));
		assertFalse(testMass.equals(new Vector()));
	}

	@Test
	public void toStringTest ()
	{
		testMass.toString();
	}

	@Test
	public void hashCodeTest ()
	{
		testMass.hashCode();
	}
}
