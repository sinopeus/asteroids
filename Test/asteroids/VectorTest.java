package asteroids;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.Util;
import asteroids.Vector;

public class VectorTest
{

	@Before
	public void setUpImmutableTestFixture_Vector()
	{
		testVector1 = new Vector(10, 10);
		testVector2 = new Vector(5, -5);
	}

	private static Vector testVector1;
	private static Vector testVector2;

	@Test
	public void setXComponentTest_LegalCase()
	{
		testVector1.setXComponent(5);
		assertTrue(Util.fuzzyEquals(testVector1.getXComponent(), 5));
	}

	@Test
	public void setXComponentTest_IllegalCase()
	{
		testVector1.setXComponent(Double.NaN);
		assertFalse(Util.fuzzyEquals(testVector1.getXComponent(), Double.NaN));
	}

	@Test
	public void setYComponentTest_LegalCase()
	{
		testVector1.setYComponent(5);
		assertTrue(Util.fuzzyEquals(testVector1.getYComponent(), 5));
	}

	@Test
	public void setYComponentTest_IllegalCase()
	{
		testVector1.setYComponent(Double.NaN);
		assertFalse(Util.fuzzyEquals(testVector1.getYComponent(), Double.NaN));
	}

	@Test
	public void canHaveAsComponentTest()
	{
		assertTrue(testVector1.canHaveAsComponent(50));
		assertFalse(testVector1.canHaveAsComponent(Double.NaN));
		assertFalse(testVector1.canHaveAsComponent(Double.POSITIVE_INFINITY));
		assertFalse(testVector1.canHaveAsComponent(Double.NEGATIVE_INFINITY));
	}

	@Test
	public void getSumTest()
	{
		Vector v = new Vector(15, 5);
		Vector sumVector = testVector1.getSum(testVector2);
		assertTrue(Util.fuzzyEquals(sumVector.getXComponent(), v.getXComponent()));
		assertTrue(Util.fuzzyEquals(sumVector.getYComponent(), v.getYComponent()));
	}

	@Test
	public void getDifferenceTest()
	{
		Vector v = new Vector(5, 15);
		Vector differenceVector = testVector1.getDifference(testVector2);
		assertTrue(Util.fuzzyEquals(differenceVector.getXComponent(), v.getXComponent()));
		assertTrue(Util.fuzzyEquals(differenceVector.getYComponent(), v.getYComponent()));
	}

	@Test
	public void scaleByTest()
	{
		Vector scaleVector = testVector1.scaleBy(2.0);
		assertTrue(Util.fuzzyEquals(scaleVector.getXComponent(), 20.0));
		assertTrue(Util.fuzzyEquals(scaleVector.getYComponent(), 20.0));
	}

	@Test
	public void dotTest_LegalCase()
	{
		double vectorProduct = testVector1.dotProduct(testVector2);
		assertTrue(Util.fuzzyEquals(vectorProduct, 0));
	}

	@Test
	public void magnitudeTest()
	{
		double vectorMagnitude = testVector1.getMagnitude();
		assertTrue(Util.fuzzyEquals(vectorMagnitude, Math.sqrt(200.0)));
	}

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents()
	{
		Vector v = new Vector(5, 6);
		assertTrue(Util.fuzzyEquals(v.getXComponent(), 5));
		assertTrue(Util.fuzzyEquals(v.getYComponent(), 6));
	}

	@Test
	public void simpleConstructorTest_ComponentsMatchZero()
	{
		Vector v = new Vector();
		assertTrue(Util.fuzzyEquals(v.getXComponent(), 0));
		assertTrue(Util.fuzzyEquals(v.getYComponent(), 0));
	}

	@Test
	public void equalsTest(){
		assertEquals(new Vector(),new Vector());
		assertNotSame(new Vector(),new Vector(5,5));
		assertNotSame(new Vector(),new Ship());
	}
}
