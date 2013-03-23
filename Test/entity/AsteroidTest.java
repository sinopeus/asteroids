package entity;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Utilities.Util;

import vector.Direction;
import vector.Position;
import vector.Velocity;
import entity.ship.Mass;
import entity.CircleShape;
import entity.Asteroid;

public class AsteroidTest
{
	@Before
	public void setUpMutableTestFixtureAsteroid()
	{
		Angle a = new Angle(Math.PI / 2);
		Direction d = new Direction(a);
		Position p = new Position(5, 5);
		double speedLimit = Velocity.getSpeedOfLight();
		Velocity v = new Velocity(5, 5);
		CircleShape s = new CircleShape(10);
		testAsteroid = new Asteroid(d, p, speedLimit, v, s);
	}

  @Test
  public void massTest() {
    assertTrue(Util.fuzzyEquals(testAsteroid.getMass().get(), 3.330088213E16));
  }

	private static Asteroid testAsteroid;

}
