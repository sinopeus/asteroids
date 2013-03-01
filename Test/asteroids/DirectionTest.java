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
	public void canHaveAsAngleTest(){
		return;
	}
	
	@Test
	public void setAngleTest_LegalCase(){
		testDirection.setAngle(new Angle(Math.PI));
		assertEquals(testDirection.getAngle(), new Angle(Math.PI));
	}
	
	@Test
	public void setAngleTest_IllegalCase(){
		return; //Implement this when there exists an illegal case.
	}
	
	@Test
	public void getXComponentTest(){
		assertTrue(Util.fuzzyEquals(testDirection.getXComponent(), 0));
		assertTrue(Util.fuzzyEquals(testDirection.getYComponent(), 1));
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
	
	@Test
	public void equalsTest(){
		assertEquals(testDirection, new Direction(testAngle));
		assertNotSame(testDirection, new Direction());
		assertNotSame(testDirection, new Ship());
	}

	@Test
	public void rotateTest(){
		testDirection.rotate(testAngle);
		assertEquals(testDirection.getAngle(), new Angle(Math.PI));
	}
}
