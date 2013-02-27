package asteroids;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.CircleShape;
import asteroids.Util;

public class CircleShapeTest
{

	@Before
	public void setUpsetUpImmutableTestFixture_CircleShape()
	{
		testCircleShape = new CircleShape(15);
	}

	private static CircleShape testCircleShape;

	@Test
	public void canHaveAsRadiusTest()
	{
		assertTrue(testCircleShape.canHaveAsRadius(15));
		assertFalse(testCircleShape.canHaveAsRadius(-5));
	}

	@Test
	public void constructorTest_LegalInput()
	{
		CircleShape cs = new CircleShape(25);
		assertTrue(cs.canHaveAsRadius(cs.getRadius()));
		assertTrue(Util.fuzzyEquals(cs.getRadius(), 25));
	}

	@Test
	public void constructorTest_IllegalInput()
	{
		try
		{
			new CircleShape(-10);
		} catch (IllegalArgumentException e)
		{
			return;
		}
	}

	@Test
	public void simpleConstructorTest()
	{
		CircleShape cs = new CircleShape();
		assertTrue(Util.fuzzyEquals(cs.getRadius(), 0));
	}
	
	@Test
	public void equalsTest(){
		assertEquals(new CircleShape(),new CircleShape());
		assertNotSame(new CircleShape(),new CircleShape(5));
		assertNotSame(new CircleShape(),new Ship());
	}
}
