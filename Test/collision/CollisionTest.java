package collision;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import vector.Direction;
import vector.Position;
import vector.Velocity;
import world.World;
import Utilities.Util;
import entity.Angle;
import entity.Asteroid;
import entity.CircleShape;

@SuppressWarnings ("javadoc")
public class CollisionTest
{

	@Before
	public void setUpMutableTestCaseWorld ()
	{
		testWorld1 = new World (1000, 1000);
		testAsteroid1 = new Asteroid (new Direction (new Angle (Math.PI)), new Position (50, 50), new Velocity (-10, 0), new CircleShape (10));
		testWorld1.add (testAsteroid1);
		testWorld2 = new World (1000, 1000);
		testAsteroid2 = new Asteroid (new Direction (new Angle (Math.PI)), new Position (950, 50), new Velocity (10, 0), new CircleShape (10));
		testWorld2.add (testAsteroid2);
		testWorld3 = new World (1000, 1000);
		testAsteroid3 = new Asteroid (new Direction (new Angle (Math.PI)), new Position (500, 950), new Velocity (0, 10), new CircleShape (10));
		testWorld3.add (testAsteroid3);
		testWorld4 = new World (1000, 1000);
		testAsteroid4 = new Asteroid (new Direction (new Angle (Math.PI)), new Position (500, 50), new Velocity (0, -10), new CircleShape (10));
		testWorld4.add (testAsteroid4);
	}

	private static World	testWorld1, testWorld2, testWorld3, testWorld4;
	private static Asteroid	testAsteroid1, testAsteroid2, testAsteroid3,
			testAsteroid4;

	@Test
	public void getNextCollisionTest_PerfectParameters_LeftBorderCollision ()
	{
		Collision c = Collision.getNextCollision (testWorld1);

		assertEquals (c.getCollisionPosition (), new Position (0, 50));
		assertTrue (Util.fuzzyEquals (c.getTimeToCollision (), 4));
		assertTrue (c instanceof BorderCollision);
		BorderCollision bc = (BorderCollision) c;
		assertEquals (bc.getCollisionBorder (), BorderCollision.Border.BORDER_LEFT);
		assertEquals (bc.getCollisionEntity (), testAsteroid1);
	}

	@Test
	public void getNextCollisionTest_PerfectParameters_RightBorderCollision ()
	{
		Collision c = Collision.getNextCollision (testWorld2);

		assertEquals (c.getCollisionPosition (), new Position (1000, 50));
		assertTrue (Util.fuzzyEquals (c.getTimeToCollision (), 4));
		assertTrue (c instanceof BorderCollision);
		BorderCollision bc = (BorderCollision) c;
		assertEquals (bc.getCollisionBorder (), BorderCollision.Border.BORDER_RIGHT);
		assertEquals (bc.getCollisionEntity (), testAsteroid1);
	}

	@Test
	public void getNextCollisionTest_PerfectParameters_TopBorderCollision ()
	{
		Collision c = Collision.getNextCollision (testWorld3);

		assertEquals (c.getCollisionPosition (), new Position (500, 1000));
		assertTrue (Util.fuzzyEquals (c.getTimeToCollision (), 4));
		assertTrue (c instanceof BorderCollision);
		BorderCollision bc = (BorderCollision) c;
		assertEquals (bc.getCollisionBorder (), BorderCollision.Border.BORDER_TOP);
		assertEquals (bc.getCollisionEntity (), testAsteroid1);
	}

	@Test
	public void getNextCollisionTest_PerfectParameters_BottomBorderCollision ()
	{
		Collision c = Collision.getNextCollision (testWorld4);

		assertEquals (c.getCollisionPosition (), new Position (500, 0));
		assertTrue (Util.fuzzyEquals (c.getTimeToCollision (), 4));
		assertTrue (c instanceof BorderCollision);
		BorderCollision bc = (BorderCollision) c;
		assertEquals (bc.getCollisionBorder (), BorderCollision.Border.BORDER_BOTTOM);
		assertEquals (bc.getCollisionEntity (), testAsteroid1);
	}

	//TODO	

	@Test (expected = IllegalArgumentException.class)
	public void getNextCollisionTest_IllegalWorld ()
	{
	}
}
