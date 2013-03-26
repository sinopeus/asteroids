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
		assertTrue(Util.fuzzyEquals(v.getXComponent(), 5));
		assertTrue(Util.fuzzyEquals(v.getYComponent(), 6));
	}

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents_IllegalXComponent ()
	{
		Vector v = new Vector(Double.NaN, 6);
		assertFalse(Util.fuzzyEquals(v.getXComponent(), 5));
		assertTrue(Util.fuzzyEquals(v.getXComponent(), 0));
		assertTrue(Util.fuzzyEquals(v.getYComponent(), 6));
	}

	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents_IllegalYComponent ()
	{
		Vector v = new Vector(5, Double.NaN);
		assertTrue(Util.fuzzyEquals(v.getXComponent(), 5));
		assertFalse(Util.fuzzyEquals(v.getYComponent(), 6));
		assertTrue(Util.fuzzyEquals(v.getYComponent(), 0));
	}

	@Test
	public void byVectorConstructorTest_ComponentsMatchGivenVectorsComponents_PerfectParameters ()
	{
		Vector v = new Vector(testVector1);
		assertTrue(Util.fuzzyEquals(v.getXComponent(), testVector1.getXComponent()));
		assertTrue(Util.fuzzyEquals(v.getYComponent(), testVector1.getYComponent()));
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
		assertTrue(Util.fuzzyEquals(v.getXComponent(), 0));
		assertTrue(Util.fuzzyEquals(v.getYComponent(), 0));
	}

	@Test
	public void setXComponentTest_LegalCase ()
	{
		testVector1.setXComponent(5);
		assertTrue(Util.fuzzyEquals(testVector1.getXComponent(), 5));
	}

	@Test
	public void setXComponentTest_IllegalCase ()
	{
		testVector1.setXComponent(Double.NaN);
		assertFalse(Util.fuzzyEquals(testVector1.getXComponent(), Double.NaN));
	}

	@Test
	public void setYComponentTest_LegalCase ()
	{
		testVector1.setYComponent(5);
		assertTrue(Util.fuzzyEquals(testVector1.getYComponent(), 5));
	}

	@Test
	public void setYComponentTest_IllegalCase ()
	{
		testVector1.setYComponent(Double.NaN);
		assertFalse(Util.fuzzyEquals(testVector1.getYComponent(), Double.NaN));
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
		assertTrue(Util.fuzzyEquals(sumVector.getXComponent(), v.getXComponent()));
		assertTrue(Util.fuzzyEquals(sumVector.getYComponent(), v.getYComponent()));
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
		assertTrue(Util.fuzzyEquals(differenceVector.getXComponent(), v.getXComponent()));
		assertTrue(Util.fuzzyEquals(differenceVector.getYComponent(), v.getYComponent()));
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
		assertTrue(Util.fuzzyEquals(scaleVector.getXComponent(), 20.0));
		assertTrue(Util.fuzzyEquals(scaleVector.getYComponent(), 20.0));
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
		assertTrue(Util.fuzzyEquals(v.getXComponent(), result.getXComponent()));
		assertTrue(Util.fuzzyEquals(v.getYComponent(), result.getYComponent()));
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
	public void equalsTest ()
	{
		assertTrue(testVector1.equals(new Vector(10, 10)));
		assertFalse(testVector1.equals(new Vector(5, 5)));
		assertFalse(testVector1.equals(new Position()));
		assertFalse(testVector1.equals(null));
	}

	@Test
	public void hashCodeTest ()
	{
		assertEquals(testVector1.hashCode(), (new Vector(10, 10)).hashCode());
		assertFalse(testVector1.hashCode() == ( (new Vector(5, 5)).hashCode()));
		assertFalse(testVector1.hashCode() == ( (new Position()).hashCode()));
	}

}
