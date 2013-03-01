package asteroids;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DirectionTest
{
	@Before
	public void setUpTestAngle()
	{
		testAngle = new Angle(Math.PI / 2.0);
		testDirection = new Direction(testAngle);
	}

	private static Angle testAngle;
	private static Direction testDirection;

	@Test
	public void setXComponentTest(){
		testDirection.setXComponent(5);
		assertTrue(Util.fuzzyEquals(testDirection.getXComponent(), 5));
	}
	
	@Test
	public void setYComponentTest(){
		testDirection.setYComponent(5);
		assertTrue(Util.fuzzyEquals(testDirection.getYComponent(), 5));
	}
	
	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents()
	{
		Direction d = new Direction(testAngle);
		assertTrue(Util.fuzzyEquals(d.getXComponent(), testAngle.cos()));
		assertTrue(Util.fuzzyEquals(d.getYComponent(), testAngle.sin()));
	}

	@Test
	public void simpleConstructorTest_ComponentsMatchGivenComponents()
	{
		Direction d = new Direction();
		assertTrue(Util.fuzzyEquals(d.getXComponent(), 1.0));
		assertTrue(Util.fuzzyEquals(d.getYComponent(), 0.0));
	}

}
