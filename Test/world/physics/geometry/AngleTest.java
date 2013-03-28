package world.physics.geometry;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import world.entity.ship.Ship;
import world.physics.geometry.Angle;

import Utilities.Util;

@SuppressWarnings("javadoc")
public class AngleTest
{
	@Before
	public void setUpsetUpImmutableTestFixture_Angle()
	{
		testAngle = new Angle();
	}

	private static Angle testAngle;

	@Test
	public void constructorTest_AngleMatchesGivenAngle_PerfectParameters()
	{
		Angle a = new Angle(Math.PI);
		assertTrue(Util.fuzzyEquals(a.get(), Math.PI));
	}

	@Test
	public void constructorTest_AngleMatchesZero()
	{
		Angle a = new Angle();
		assertTrue(Util.fuzzyEquals(a.get(), 0));
	}

	@Test
	public void canHaveAsAngleTest()
	{
		assertTrue(testAngle.canHaveAsAngle(Math.PI));
		assertFalse(testAngle.canHaveAsAngle(Double.NaN));
	}

	@Test
	public void setAngleTest_LegalCase()
	{
		testAngle.set(0);
		assertTrue(Util.fuzzyEquals(testAngle.get(), 0));
		testAngle.set(-Math.PI / 2);
		assertTrue(Util.fuzzyEquals(testAngle.get(), 3 * Math.PI / 2));
	}

	@Test
	public void getSinTest()
	{
		assertTrue(Util.fuzzyEquals(testAngle.getSin(), 0));
	}

	@Test
	public void getCosTest()
	{
		assertTrue(Util.fuzzyEquals(testAngle.getCos(), 1));
	}

	@Test
	public void addTest()
	{
		Angle testAngle2 = new Angle(Math.PI);
		testAngle.add(testAngle2);
		assertTrue(Util.fuzzyEquals(testAngle.get(), Math.PI));
		testAngle2.add(testAngle2);
		assertTrue(Util.fuzzyEquals(testAngle2.get(), 0));
	}
	
	@Test
	public void toStringTest(){
		testAngle.toString();
	}

	@Test
	public void equalsTest_PerfectParameter()
	{
		assertTrue(testAngle.equals(new Angle()));
		assertFalse(testAngle.equals(new Angle(5)));
		assertFalse(testAngle.equals(new Ship()));
		assertFalse(testAngle.equals(null));
	}
}
