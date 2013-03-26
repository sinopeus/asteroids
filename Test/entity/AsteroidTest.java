package entity;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import vector.Direction;
import vector.Position;
import vector.Velocity;
import Utilities.Util;

public class AsteroidTest
{
	@Before
	public void setUpMutableTestFixtureAsteroid ()
	{
		Angle a = new Angle (Math.PI / 2);
		Direction d = new Direction (a);
		Position p = new Position (5, 5);
		Velocity v = new Velocity (5, 5);
		CircleShape s = new CircleShape (10);
		testAsteroid = new Asteroid (d, p, v, s);
	}

	@Test
	public void massTest ()
	{
		assertTrue (Util.fuzzyEquals (testAsteroid.getMass ().get (),
				1.1100294042683934E16));
	}

	private static Asteroid	testAsteroid;

}
