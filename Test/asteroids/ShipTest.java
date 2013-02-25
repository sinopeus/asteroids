package asteroids;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asteroids.Angle;
import asteroids.CircleShape;
import asteroids.Direction;
import asteroids.Ship;
import asteroids.Velocity;

public class ShipTest
{
	@Before
	public void setUpImmutableTestFixture_Ship(){
		
	}
	
	private static Ship testShip;
	
	@Test
	public void canHaveAsPositionTest(){
		//TODO
	}
	
	@Test
	public void setPositionTest_LegalCase(){
		//TODO
	}
	
	@Test
	public void setPositionTest_IllegalCase(){
		//TODO
	}
	
	@Test
	public void canHaveAsVelocityTest(){
		Velocity v = new Velocity(5, 5);
		assertTrue(testShip.canHaveAsVelocity(v));
	}
	
	@Test
	public void setVelocityTest_LegalCase(){
		Velocity v = new Velocity(5,5);
		testShip.setVelocity(v);
		assertEquals(testShip.getVelocity(), v);
	}
	
	@Test
	public void setVelocityTest_IllegalCase(){
		//TODO
	}
	
	@Test
	public void canHaveAsDirectionTest(){
		Angle a = new Angle(Math.PI);
		Direction d = new Direction(a);
		assertTrue(testShip.canHaveAsDirection(d));
	}
	
	@Test
	public void setDirectionTest_LegalCase(){
		//TODO
	}
	
	@Test
	public void setDirectionTest_IllegalCase(){
		//TODO
	}
	
	@Test
	public void canHaveAsShapeTest(){
		CircleShape c = new CircleShape(50);
		assertTrue(testShip.canHaveAsShape(c));
	}
	
	@Test
	public void setShapeTest_LegalCase(){
		//TODO
	}
	
	@Test
	public void setShapeTest_IllegalCase(){
		//TODO
	}
	
}
