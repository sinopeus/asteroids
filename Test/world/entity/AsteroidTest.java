package world.entity;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import world.entity.Asteroid;
import world.physics.geometry.Angle;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;

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