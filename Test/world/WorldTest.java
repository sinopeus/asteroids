package world;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Utilities.Util;

public class WorldTest
{
	@Test
	public void extendedConstructorTest_SizesMatchGivenSizes_PerfectParameters()
	{
		World w = new World(5, 6);
		assertTrue(Util.fuzzyEquals(5, w.getxSize()));
		assertTrue(Util.fuzzyEquals(6, w.getySize()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void extendedConstructorTest_SizesMatchGivenSizes_IllegalXSize()
	{
		World w = new World(-1, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void extendedConstructorTest_SizesMatchGivenSizes_IllegalYSize()
	{
		World w = new World(5, -1);
	}

	@Test
	public void simpleConstructorTest_SizesMatchGivenSizes()
	{
		World w = new World();
		assertTrue(Util.fuzzyEquals(Double.MAX_VALUE, w.getxSize()));
		assertTrue(Util.fuzzyEquals(Double.MAX_VALUE, w.getySize()));
	}
}
