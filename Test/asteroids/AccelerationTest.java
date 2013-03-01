package asteroids;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccelerationTest
{
	@Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents(){
		Acceleration a = new Acceleration(5, 6);
		assertTrue(Util.fuzzyEquals(a.getXComponent(), 5));
		assertTrue(Util.fuzzyEquals(a.getYComponent(), 6));
	}
	
	@Test
	public void byVectorAccelerationTest_ComponentsMatchGivenVectorComponents(){
		Acceleration a = new Acceleration(new Vector(2,3));
		assertTrue(Util.fuzzyEquals(a.getXComponent(), 2));
		assertTrue(Util.fuzzyEquals(a.getYComponent(), 3));
	}
	
	@Test
	public void simpleConstructorTest_ComponentsMatchZero(){
		Acceleration a = new Acceleration(0,0);
		assertTrue(Util.fuzzyEquals(a.getXComponent(), 0));
		assertTrue(Util.fuzzyEquals(a.getYComponent(), 0));
	}
}
