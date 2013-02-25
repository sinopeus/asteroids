package asteroids;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.Angle;
import asteroids.CircleShape;
import asteroids.Util;

public class AngleTest
{
	@Before
	public void setUpsetUpImmutableTestFixture_CircleShape()
	{
		testAngle = new Angle();
	}
	
	private static Angle testAngle;
	
	@Test
	public void canHaveAsAngleTest(){
		assertTrue(testAngle.canHaveAsAngle(Math.PI));
	}
	
	@Test
	public void setAngleTest_LegalCase(){
		testAngle.setAngle(0);
		assertTrue(Util.fuzzyEquals(testAngle.getAngle(), 0));
	}
	
	@Test
	public void setAngleTest_IllegalCase(){
		testAngle.setAngle(Double.NaN);
		assertFalse(Util.fuzzyEquals(testAngle.getAngle(), Double.NaN));
	}
	
	@Test
	public void sinTest(){
		assertTrue(Util.fuzzyEquals(testAngle.sin(),0));
	}
	
	@Test
	public void cosTest(){
		assertTrue(Util.fuzzyEquals(testAngle.cos(),1));
	}
	
	@Test
	public void constructorTest_AngleMatchesGivenAngle(){
		Angle a = new Angle(Math.PI);
		assertTrue(Util.fuzzyEquals(a.getAngle(), Math.PI));
	}
	
	@Test
	public void constructorTest_AngleMatchesZero(){
		Angle a = new Angle();
		assertTrue(Util.fuzzyEquals(a.getAngle(), 0));
	}
}
