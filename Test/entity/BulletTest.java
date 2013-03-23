package entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import vector.Direction;
import vector.Position;
import vector.Velocity;

import entity.Bullet;
import entity.Angle;
import entity.CircleShape;
import entity.ship.Mass;
import entity.ship.Ship;

public class BulletTest
{
	@Before
	public void setUpImmutableTestFixtureBullet()
	{
		testShip = new Ship();
		testBullet = new Bullet(testShip);
	}

	@Test
  public void constructorTest_PerfectParameter() {
		testBullet = new Bullet(testShip);
  } 
	
	@Test(expected = IllegalArgumentException.class)
  public void constructorTest_NullShip() {
		testBullet = new Bullet(null);
  } 

  //@Test
  //public void getInitialPositionTest() {
		//testShip = new Ship(new Direction(new Angle(Math.PI / 4)), new Position(3, 4), Velocity.getSpeedOfLight(), new Velocity(), new CircleShape(12), new Mass(5E15));
		//testBullet = new Bullet(testShip);
    //assertEquals(testBullet.getInitialPosition(), new Position(new Direction(new Angle(Math.PI /4)).getScaledBy(20));
  //}

  @Test
  public void getShooterTest() {
    assertEquals(testBullet.getShooter(), testShip);
  }

  //@Test
  //public void canHaveAsShooterTest_NonNull() {
		//testBullet = new Bullet(testShip);
    //assertTrue(testBullet.canHaveAsShooter(testShip));
    //assertFalse(testBullet.canHaveAsShooter(null));
  //}

  //@Test
  //public void setShooterTest_PerfectParameter() {
    //testShip = new Ship();
		//testBullet.setShooter(newShip);
    //assertEquals(testBullet.getShooter(), newShip);
  //}

	//@Test(expected = IllegalArgumentException.class)
  //public void setShooterTest_NullParameter() {
    //testShip = new Ship();
		//testBullet.setShooter(newShip);
		//testBullet.setShooter(null);
  //}

  @Test
  public void terminateTest() {
    testShip = new Ship();
		testBullet.setShooter(newShip);
    testBullet.terminate();
    assertEquals(testBullet, null);
  }

	private static Ship testShip;
	private static Bullet testBullet;
}
