package entity;

import org.junit.Before;
import org.junit.Test;

import entity.ship.Ship;

public class BulletTest
{
	@Before
	public void setUpImmutableTestFixtureBullet()
	{
		testShip = new Ship();
		testBullet = new Bullet(testShip);
	}

	//TODO CONTRUCTOR TESTS

	@Test
	public void test()
	{

	}

	private static Ship testShip;
	private static Bullet testBullet;
}
