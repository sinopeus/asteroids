package world.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import world.entity.Bullet;
import world.entity.ship.Ship;


@SuppressWarnings ("javadoc")
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
	public void collideWithTest_PerfectAsteroid ()
	{
		fail();
	}

	@Test
	public void collideWithTest_IllegalAsteroid ()
	{
		fail();
	}

	@Test
	public void collideWithTest_PerfectBullet ()
	{
		fail();
	}

	@Test
	public void collideWithTest_IllegalBullet ()
	{
		fail();
	}
	
	@Test
	public void collideWithTest_PerfectShip ()
	{
		fail();
	}

	@Test
	public void collideWithTest_IllegalShip ()
	{
		fail();
	}
	
	@Test
	public void collideWithTest_PerfectBorder ()
	{
		fail();
	}

	@Test
	public void collideWithTest_IllegalBorder()
	{
		fail();
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
