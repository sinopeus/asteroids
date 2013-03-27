package world.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import world.World;
import world.entity.ship.Ship;
import world.physics.Mass;
import world.physics.geometry.Angle;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;
import Utilities.Util;

public class EntityTest //TODO test constructors
{
	@Before
	public void setUpMutableTestFixtureEntity ()
	{
		World w = new World(1000, 1000);

		Angle a = new Angle(Math.PI / 2);
		Direction d = new Direction(a);
		Position p = new Position(55, 55);
		double speedLimit = Velocity.getSpeedOfLight();
		Velocity v = new Velocity(5, 5);
		CircleShape s = new CircleShape(50);
		Mass m = new Mass(40);
		testEntity = new Entity(d, p, speedLimit, v, s, m);
		w.add(testEntity);

		terminatedEntity = new Entity(new Direction(), new Position(100, 100), 50, new Velocity(), new CircleShape(10), new Mass(2));
		w.add(terminatedEntity);
		terminatedEntity.terminate();
	}

	private static Entity	testEntity;
	private static Entity	terminatedEntity;

	@Test
	public void canHaveAsPositionTest ()
	{
		assertTrue(testEntity.canHaveAsPosition(new Position()));
		assertFalse(testEntity.canHaveAsPosition(null));
	}

	@Test
	public void setPositionTest_LegalCase ()
	{
		Position p = new Position(5, 5);
		testEntity.setPosition(p);
		assertEquals(testEntity.getPosition(), p);
	}

	@Test (expected = IllegalStateException.class)
	public void setPositionTest_TerminatedShip ()
	{
		terminatedEntity.setPosition(new Position());
	}

	@Test (expected = IllegalArgumentException.class)
	public void setPositionTest_IllegalCase ()
	{
		testEntity.setPosition(null);
	}

	@Test
	public void canHaveAsVelocityTest ()
	{
		Velocity v = new Velocity(5, 5);
		assertTrue(testEntity.canHaveAsVelocity(v));
		testEntity.setSpeedLimit(50);
		assertFalse(testEntity.canHaveAsVelocity(new Velocity(100, 100)));
		assertFalse(testEntity.canHaveAsVelocity(null));
	}

	@Test
	public void setVelocityTest_LegalCase ()
	{
		Velocity v = new Velocity(5, 5);
		testEntity.setVelocity(v);
		assertEquals(testEntity.getVelocity(), v);
	}

	@Test (expected = IllegalStateException.class)
	public void setVelocityTest_TerminatedShip ()
	{
		terminatedEntity.setVelocity(new Velocity());
	}

	@Test
	public void setVelocityTest_IllegalCase ()
	{
		testEntity.setVelocity(null);
		assertNotSame(testEntity.getVelocity(), null);
	}

	@Test
	public void canHaveAsSpeedLimitTest ()
	{
		assertTrue(testEntity.canHaveAsSpeedLimit(5000));
		assertFalse(testEntity.canHaveAsSpeedLimit(-1));
		assertFalse(testEntity.canHaveAsSpeedLimit(Velocity.getSpeedOfLight() + 1));
	}

	@Test
	public void setSpeedLimitTest_LegalCase ()
	{
		testEntity.setSpeedLimit(5000);
		assertTrue(Util.fuzzyEquals(testEntity.getSpeedLimit(), 5000));
	}

	@Test (expected = IllegalStateException.class)
	public void setSpeedLimitTest_TerminatedShip ()
	{
		terminatedEntity.setSpeedLimit(50);
	}

	@Test
	public void setSpeedLimitTest_IllegalCase ()
	{
		testEntity.setSpeedLimit(-5);
		assertFalse(Util.fuzzyEquals(testEntity.getSpeedLimit(), -5));
		assertTrue(Util.fuzzyEquals(testEntity.getSpeedLimit(), Velocity.getSpeedOfLight()));
		testEntity.setSpeedLimit(Velocity.getSpeedOfLight() + 1);
		assertFalse(Util.fuzzyEquals(testEntity.getSpeedLimit(), Velocity.getSpeedOfLight() + 1));
		assertTrue(Util.fuzzyEquals(testEntity.getSpeedLimit(), Velocity.getSpeedOfLight()));
	}

	@Test
	public void canHaveAsDirectionTest ()
	{
		Angle a = new Angle(Math.PI);
		Direction d = new Direction(a);
		assertTrue(testEntity.canHaveAsDirection(d));
		assertFalse(testEntity.canHaveAsDirection(null));
	}

	@Test
	public void setDirectionTest_LegalCase ()
	{
		Direction d = new Direction();
		testEntity.setDirection(d);
		assertEquals(testEntity.getDirection(), d);
	}

	@Test (expected = IllegalStateException.class)
	public void setDirectionTest_TerminatedShip ()
	{
		terminatedEntity.setDirection(new Direction());
	}

	@Test
	public void canHaveAsShapeTest ()
	{
		CircleShape c = new CircleShape(50);
		assertTrue(testEntity.canHaveAsShape(c));
		assertTrue(testEntity.canHaveAsShape(new CircleShape(5)));
		assertFalse(testEntity.canHaveAsShape(null));
	}

	@Test
	public void moveTest_PerfectParameters ()
	{
		Entity originalState = new Entity(testEntity.getDirection(), testEntity.getPosition(), testEntity.getSpeedLimit(), testEntity.getVelocity(), new CircleShape(testEntity.getShape().getRadius()), new Mass(testEntity.getMass().get()));
		testEntity.move(2.0);
		assertEquals(testEntity.getVelocity(), originalState.getVelocity());
		assertEquals(testEntity.getDirection(), originalState.getDirection());
		assertTrue(Util.fuzzyEquals(testEntity.getPosition().getXComponent(), 65));
		assertTrue(Util.fuzzyEquals(testEntity.getPosition().getYComponent(), 65));
	}

	@Test (expected = IllegalStateException.class)
	public void moveTest_TerminatedShip ()
	{
		terminatedEntity.move(2.0);
	}

	@Test ()
	public void moveTest_IllegalDuration ()
	{
		Entity originalState = new Entity(testEntity.getDirection(), testEntity.getPosition(), testEntity.getSpeedLimit(), testEntity.getVelocity(), new CircleShape(testEntity.getShape().getRadius()), new Mass(testEntity.getMass().get()));
		testEntity.move(-1);
		assertEquals(originalState.getPosition(), originalState.getPosition());
	}

	@Test
	public void turnTest_PerfectParameters ()
	{
		testEntity.turn(new Angle(Math.PI));
		assertEquals(testEntity.getDirection(), new Direction(new Angle(3 * Math.PI / 2)));
	}

	@Test (expected = IllegalStateException.class)
	public void turnTest_TerminatedShip ()
	{
		terminatedEntity.turn(new Angle());
	}
}
