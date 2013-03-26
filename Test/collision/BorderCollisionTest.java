package collision;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


import world.World;
import world.entity.Asteroid;
import world.physics.collision.BorderCollision;
import world.physics.collision.Collision;
import world.physics.collision.BorderCollision.Border;
import world.physics.geometry.Angle;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;

public class BorderCollisionTest
{
	@Test
	public void setUpMutableTestFixture_BorderCollision ()
	{
		testWorld = new World (1000, 1000);
		testAsteroid1 = new Asteroid (new Direction (new Angle (Math.PI)), new Position (50, 50), new Velocity (-10, 0), new CircleShape (10));
		testWorld.add (testAsteroid1);
		Collision collision = Collision.getNextCollision (testWorld);
		testBorderCollision = (BorderCollision) collision;
		
		testAsteroid2 = new Asteroid (new Direction (), new Position (), new Velocity (), new CircleShape ());
	}

	private static World			testWorld;
	private static Asteroid			testAsteroid1,testAsteroid2;
	private static BorderCollision	testBorderCollision;

	@Test
	public void canHaveAsCollisionBorderTest(){
		assertTrue(testBorderCollision.canHaveAsCollisionBorder (Border.BORDER_LEFT));
		assertTrue(testBorderCollision.canHaveAsCollisionBorder (Border.BORDER_RIGHT));
		assertTrue(testBorderCollision.canHaveAsCollisionBorder (Border.BORDER_TOP));
		assertTrue(testBorderCollision.canHaveAsCollisionBorder (Border.BORDER_BOTTOM));
		assertFalse(testBorderCollision.canHaveAsCollisionBorder (null));
	}
	
	@Test
	public void setCollisionBorderTest_LegalCase(){
		testBorderCollision.setCollisionBorder (Border.BORDER_TOP);
		assertEquals(testBorderCollision.getCollisionBorder (),Border.BORDER_TOP);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setCollisionBorderTest_IllegalCase(){
		testBorderCollision.setCollisionBorder (null);
	}
	
	@Test
	public void canHaveAsEntityTest(){
		assertTrue(testBorderCollision.canHaveAsEntity (testAsteroid1));
		assertFalse(testBorderCollision.canHaveAsEntity (null));
	}
	
	@Test
	public void setCollisionEntityTest_LegalCase(){
		testBorderCollision.setCollisionEntity(testAsteroid2);
		assertEquals (testBorderCollision.getCollisionEntity (), testAsteroid2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setCollisionEntityTest_IllegalCase(){
		testBorderCollision.setCollisionEntity(null);
	}
	
	//TODO RESOLVETEST
	//TODO GETTIMETOCOLLISIONTEST
}
