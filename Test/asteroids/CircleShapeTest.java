package asteroids;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("javadoc")
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
		assertFalse(testCircleShape.canHaveAsRadius(Double.NaN));
	}

	@Test
	public void constructorTest_PerfectParameters()
	{
		CircleShape cs = new CircleShape(25);
		assertTrue(cs.canHaveAsRadius(cs.getRadius()));
		assertTrue(Util.fuzzyEquals(cs.getRadius(), 25));
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorTest_IllegalInput()
	{
		new CircleShape(-10);
	}

	@Test
	public void simpleConstructorTest()
	{
		CircleShape cs = new CircleShape();
		assertTrue(Util.fuzzyEquals(cs.getRadius(), 0));
	}

	@Test
	public void equalsTest()
	{
		assertEquals(new CircleShape(), new CircleShape());
		assertNotSame(new CircleShape(), new CircleShape(5));
		assertNotSame(new CircleShape(), new Ship());
	}
}
