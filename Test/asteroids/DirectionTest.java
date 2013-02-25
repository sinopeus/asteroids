package asteroids;

import org.junit.Test;

public class DirectionTest
{
    @Test
	public void setUpTestAngle()
	{
        testAngle = new Angle(Math.PI / 2.0);
	}

    private static Angle testAngle;

    @Test
	public void extendedConstructorTest_ComponentsMatchGivenComponents()
	{
		Direction d = new Direction(testAngle);
        assertTrue(Util.fuzzyEquals(d.getXComponent(), testAngle.cos());
        assertTrue(Util.fuzzyEquals(d.getYComponent(), testAngle.sin());
	}

	@Test
	public void simpleConstructorTest_ComponentsMatchGivenComponents()
	{
		Direction d = new Direction();
        assertTrue(Util.fuzzyEquals(d.getXComponent(), 1.0);
        assertTrue(Util.fuzzyEquals(d.getYComponent(), 0.0);
	}
	
}
