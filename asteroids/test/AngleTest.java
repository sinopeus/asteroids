package asteroids.test;

import org.junit.Before;

import asteroids.CircleShape;

public class AngleTest
{
	@Before
	public void setUpsetUpImmutableTestFixture_CircleShape()
	{
		testAngle = new Angle(15);
	}
	
	private static Angle testAngle;
}
