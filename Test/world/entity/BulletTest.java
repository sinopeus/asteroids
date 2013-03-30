package world.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import world.entity.Bullet;
import world.entity.ship.Ship;


public class BulletTest
{
	@Before
	public void setUpImmutableTestFixtureBullet()
	{
		testShip = new Ship();
		testBullet = new Bullet(testShip);
	}

	@Test
	public void constructorTest_PerfectParameter()
	{
		testBullet = new Bullet(testShip);
		assertTrue(testBullet.getShooter() == testShip);
	}

	@Test(expected = NullPointerException.class)
	public void constructorTest_NullShip()
	{
		testBullet = new Bullet(null);
	}

	@Test
	public void getShooterTest()
	{
		assertEquals(testBullet.getShooter(), testShip);
	}


	@Test
	public void canHaveAsBounceCounterTest()
	{
		assertTrue(testBullet.canHaveAsBounceCounter((byte) 5));
		assertTrue(testBullet.canHaveAsBounceCounter((byte) 0));
		assertFalse(testBullet.canHaveAsBounceCounter((byte) -1));
	}
	
	@Test
	public void setBounceCounterTest_LegalCase(){
		testBullet.setBounceCounter((byte)2);
		assertEquals(testBullet.getBounceCounter(), 2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setBounceCounterTest_IllegalCase(){
		testBullet.setBounceCounter((byte)-1);
	}

	
	@Test
	public void unlinkFromShooterTest()
	{
		testBullet.unlinkFromShooter();
		assertEquals(testBullet.getShooter(), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void terminateTest()
	{
		testBullet.terminate();
		assertEquals(testBullet, null);
	}

	private static Ship testShip;
	private static Bullet testBullet;
}
