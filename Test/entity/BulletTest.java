package entity;

import org.junit.Before;

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

	private static Ship testShip;
	private static Bullet testBullet;
}
