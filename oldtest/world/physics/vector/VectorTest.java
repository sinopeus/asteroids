package world.physics.vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Utilities.Util;

@SuppressWarnings (
{ "unused", "javadoc" })
public class VectorTest
{

	@Before
	public void setUpImmutableTestFixture_Vector ()
	{
		testVector1 = new Vector(10, 10);
		testVector2 = new Vector(5, -5);
		infiniteVector1 = new Vector(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
		infiniteVector2 = new Vector(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
		nilVector = new Vector();
	}

	private static Vector	testVector1;
	private static Vector	testVector2;
	private static Vector	infiniteVector1;
	private static Vector	infiniteVector2;
	private static Vector	nilVector;

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents_PerfectParameters ()
	{
		Vector v = new Vector(5, 6);
		assertTrue(Util.fuzzyEquals(v._X(), 5));
		assertTrue(Util.fuzzyEquals(v._Y(), 6));
	}

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents_IllegalXComponent ()
	{
		Vector v = new Vector(Double.NaN, 6);
		assertFalse(Util.fuzzyEquals(v._X(), 5));
		assertTrue(Util.fuzzyEquals(v._X(), 0));
		assertTrue(Util.fuzzyEquals(v._Y(), 6));
	}

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents_IllegalYComponent ()
	{
		Vector v = new Vector(5, Double.NaN);
		assertTrue(Util.fuzzyEquals(v._X(), 5));
		assertFalse(Util.fuzzyEquals(v._Y(), 6));
		assertTrue(Util.fuzzyEquals(v._Y(), 0));
	}

	@Test
	public void byVectorConstructorTest_ComponentsMatchGivenVectorsComponents_PerfectParameters ()
	{
		Vector v = new Vector(testVector1);
		assertTrue(Util.fuzzyEquals(v._X(), testVector1._X()));
		assertTrue(Util.fuzzyEquals(v._Y(), testVector1._Y()));
	}

	@Test (expected = NullPointerException.class)
	public void byVectorConstructorTest_ComponentsMatchGivenVectorsComponents_NullVector ()
	{
		Vector v = new Vector(null);
	}

	@Test
	public void simpleConstructorTest_ComponentsMatchZero ()
	{
		Vector v = new Vector();
		assertTrue(Util.fuzzyEquals(v._X(), 0));
		assertTrue(Util.fuzzyEquals(v._Y(), 0));
	}

	@Test
	public void setXComponentTest_LegalCase ()
	{
		testVector1.setX(5);
		assertTrue(Util.fuzzyEquals(testVector1._X(), 5));
	}

	@Test
	public void setXComponentTest_IllegalCase ()
	{
		testVector1.setX(Double.NaN);
		assertFalse(Util.fuzzyEquals(testVector1._X(), Double.NaN));
	}

	@Test
	public void setYComponentTest_LegalCase ()
	{
		testVector1.setY(5);
		assertTrue(Util.fuzzyEquals(testVector1._Y(), 5));
	}

	@Test
	public void setYComponentTest_IllegalCase ()
	{
		testVector1.setY(Double.NaN);
		assertFalse(Util.fuzzyEquals(testVector1._Y(), Double.NaN));
	}

	@Test
	public void canHaveAsComponentTest ()
	{
		assertTrue(testVector1.canHaveAsComponent(50));
		assertFalse(testVector1.canHaveAsComponent(Double.NaN));
	}

	@Test
	public void getSumTest_PerfectParameters ()
	{
		Vector v = new Vector(15, 5);
		Vector sumVector = testVector1.getSum(testVector2);
		assertTrue(Util.fuzzyEquals(sumVector._X(), v._X()));
		assertTrue(Util.fuzzyEquals(sumVector._Y(), v._Y()));
	}

	@Test (expected = ArithmeticException.class)
	public void getSumTest_NaNResultVectorXComponent ()
	{
		Vector sumVector = infiniteVector1.getSum(infiniteVector2);
	}

	@Test (expected = ArithmeticException.class)
	public void getSumTest_NaNResultVectorYComponent ()
	{
		Vector v1 = new Vector(0, Double.POSITIVE_INFINITY);
		Vector v2 = new Vector(0, Double.NEGATIVE_INFINITY);
		v1.getSum(v2);
	}

	@Test
	public void getDifferenceTest_PerfectParameters ()
	{
		Vector v = new Vector(5, 15);
		Vector differenceVector = testVector1.getDifference(testVector2);
		assertTrue(Util.fuzzyEquals(differenceVector._X(), v._X()));
		assertTrue(Util.fuzzyEquals(differenceVector._Y(), v._Y()));
	}

	@Test (expected = ArithmeticException.class)
	public void getDifferenceTest_NaNResultVectorXComponent ()
	{
		Vector differenceVector = infiniteVector1.getDifference(infiniteVector1);
	}

	@Test (expected = ArithmeticException.class)
	public void getDifferenceTest_NaNResultVectorYComponent ()
	{
		Vector v1 = new Vector(0, Double.POSITIVE_INFINITY);
		Vector v2 = new Vector(0, Double.POSITIVE_INFINITY);
		v1.getDifference(v2);
	}

	@Test
	public void scaleByTest_PerfectParameters ()
	{
		Vector scaleVector = testVector1.getScaledBy(2.0);
		assertTrue(Util.fuzzyEquals(scaleVector._X(), 20.0));
		assertTrue(Util.fuzzyEquals(scaleVector._Y(), 20.0));
	}

	@Test (expected = ArithmeticException.class)
	public void scaleByTest_NaNResultVectorXComponent ()
	{
		Vector scaleVector = infiniteVector1.getScaledBy(0.0);
	}

	@Test (expected = ArithmeticException.class)
	public void scaleByTest_NaNResultVectorYComponent ()
	{
		Vector v1 = new Vector(0, Double.POSITIVE_INFINITY);
		Vector scaleVector = v1.getScaledBy(0.0);
	}

	@Test
	public void dotTest_PerfectParameters ()
	{
		double vectorProduct = testVector1.dotProduct(testVector2);
		assertTrue(Util.fuzzyEquals(vectorProduct, 0));
	}

	@Test (expected = ArithmeticException.class)
	public void dotTest_IllegalVectoy ()
	{
		infiniteVector1.dotProduct(nilVector);
	}

	@Test
	public void magnitudeTest ()
	{
		double vectorMagnitude = testVector1.getMagnitude();
		assertTrue(Util.fuzzyEquals(vectorMagnitude, Math.sqrt(200.0)));
	}

	@Test
	public void GetUnitVectorInSameDirectionTest_LegalCase ()
	{
		Vector v = new Vector(Math.sqrt(2) / 2, Math.sqrt(2) / 2);
		Vector result = testVector1.getUnitVectorInDirection();
		assertTrue(Util.fuzzyEquals(v._X(), result._X()));
		assertTrue(Util.fuzzyEquals(v._Y(), result._Y()));
	}

	@Test (expected = ArithmeticException.class)
	public void GetUnitVectorInSameDirectionTest_NullVector ()
	{
		nilVector.getUnitVectorInDirection();
	}

	@Test
	public void distanceToTest_PerfectParameters ()
	{
		double result = testVector1.getDistanceTo(testVector2);
		assertTrue(Util.fuzzyEquals(result, 15.811388300841896));
	}

	@Test (expected = ArithmeticException.class)
	public void distanceToTest_IllegalVector ()
	{
		infiniteVector1.getDistanceTo(infiniteVector1);
	}
	
	@Test
	public void getQuadrantTest(){
		assertEquals(new Vector(1, 1).getQuadrant(),Quadrant.QUADRANT_I);
		assertEquals(new Vector(-1, 1).getQuadrant(),Quadrant.QUADRANT_II);
		assertEquals(new Vector(-1, -1).getQuadrant(),Quadrant.QUADRANT_III);
		assertEquals(new Vector(1, -1).getQuadrant(),Quadrant.QUADRANT_IV);
	}
	
	@Test
	public void toStringTest(){
		testVector1.toString();
	}

	@Test
	public void equalsTest ()
	{
		assertTrue(testVector1.equals(new Vector(10, 10)));
		assertFalse(testVector1.equals(new Vector(10, -10)));
		assertFalse(testVector1.equals(new Vector(-10, 10)));
		assertFalse(testVector1.equals(new Vector(5, 5)));
		assertFalse(testVector1.equals(new Position()));
		assertFalse(testVector1.equals(null));
	}

	@Test
	public void hashCodeTest ()
	{
		testVector1.hashCode();
	}

}
