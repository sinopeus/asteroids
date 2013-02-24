
package asteroids.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asteroids.Util;
import asteroids.Vector;

public class VectorTest
{

    @Before
    public void setUpImmutableTestFixture_Vector() {
        testVector1 = new Vector(10, 10);
        testVector2 = new Vector(5, -5);
    }

    private static Vector testVector1;
    private static Vector testVector2;

    @Test
    public void setXComponentTest_LegalCase() {
        testVector1.setXComponent(5);
        assertTrue(Util.fuzzyEquals(testVector1.getXComponent(), 5));
    }

    @Test
    public void setXComponentTest_IllegalCase() {
        try { 
            testVector1.setXComponent("fifty");
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    @Test
    public void setYComponentTest_LegalCase() {
        testVector1.setYComponent(5);
        assertTrue(Util.fuzzyEquals(testVector1.getYComponent(), 5));
    }

    @Test
    public void setYComponentTest_IllegalCase() {
        try { 
            testVector1.setYComponent("fifty");
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    @Test
    public void canHaveAsComponentTest() {
        assertTrue(testVector1.canHaveAsXComponent(50));
    }

    @Test
    public void sumTest() {
        Vector v = new Vector(15,5);
        Vector sumVector = testVector1.sum(testVector2);
        assert (sumVector.getXComponent() == v.getXComponent());
        assert (sumVector.getYComponent() == v.getXComponent());
    }

    @Test
    public void differenceTest() {
        Vector v = new Vector(5,15);
        Vector sumVector = testVector1.sum(testVector2);
        assert (sumVector.getXComponent() == v.getXComponent());
        assert (sumVector.getYComponent() == v.getXComponent());
    }

    @Test
    public void scaleTest() {
        Vector scaleVector = testVector1.scale(2.0);
        assert (sumVector.getXComponent() == 20.0);
        assert (sumVector.getYComponent() == 20.0);
    }

    @Test
    public void dotTest_LegalCase() {
        double vectorProduct = testVector1.dot(testVector2);
        assert (vectorProduct == 20.0);
    }

    @Test
    public void dotTest_IllegalCase() {
        double vectorProduct = testVector1.dot(100.0);
    }

    @Test
    public void magnitudeTest() {
        double vectorMagnitude = testVector1.magnitude();
        assert (vectorMagnitude == Math.sqrt(200.0));
    }

    @Test
    public void extendedConstructorTest_ComponentsMatchGivenComponents() {
        Vector v = new Vector(5, 6);
        assertTrue(Util.fuzzyEquals(v.getXComponent(), 5));
        assertTrue(Util.fuzzyEquals(v.getYComponent(), 6));
    }

    @Test
    public void simpleConstructorTest_ComponentsMatchZero() {
        Vector v = new Vector();
        assertTrue(Util.fuzzyEquals(v.getXComponent(), 0));
        assertTrue(Util.fuzzyEquals(v.getYComponent(), 0));
    }

}
