package asteroids;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AccelerationTest
{
	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents_PerfectParameters()
	{
		Acceleration a = new Acceleration(5, 6);
		assertTrue(Util.fuzzyEquals(a.getXComponent(), 5));
		assertTrue(Util.fuzzyEquals(a.getYComponent(), 6));
	}

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents_RubbishXComponent()
	{
		Acceleration a = new Acceleration(Double.NaN, 6);
		assertFalse(Util.fuzzyEquals(a.getXComponent(), 5));
		assertTrue(Util.fuzzyEquals(a.getXComponent(), 0));
		assertTrue(Util.fuzzyEquals(a.getYComponent(), 6));
	}

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents_RubbishYComponent()
	{
		Acceleration a = new Acceleration(5, Double.NaN);
		assertTrue(Util.fuzzyEquals(a.getXComponent(), 5));
		assertFalse(Util.fuzzyEquals(a.getYComponent(), 6));
		assertTrue(Util.fuzzyEquals(a.getYComponent(), 0));
	}

	@Test
	public void byVectorAccelerationTest_ComponentsMatchGivenVectorComponents_PerfextParameters()
	{
		Acceleration a = new Acceleration(new Vector(2, 3));
		assertTrue(Util.fuzzyEquals(a.getXComponent(), 2));
		assertTrue(Util.fuzzyEquals(a.getYComponent(), 3));
	}

	@Test
	public void simpleConstructorTest_ComponentsMatchZero()
	{
		Acceleration a = new Acceleration();
		assertTrue(Util.fuzzyEquals(a.getXComponent(), 0));
		assertTrue(Util.fuzzyEquals(a.getYComponent(), 0));
	}
}
