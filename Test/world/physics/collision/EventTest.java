package world.physics.collision;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import world.entity.Asteroid;
import world.entity.Entity;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;
import Utilities.Util;

public class EventTest
{

	@Before
	public void setUpImmutableTestFixture_Event ()
	{
		testEntity1 = new Asteroid(new Direction(), new Position(), new Velocity(), new CircleShape(50));
		testEntity2 = new Asteroid(new Direction(), new Position(100, 100), new Velocity(), new CircleShape(50));
		testEvent1 = new Event(5, testEntity1, testEntity2);
		testEvent2 = new Event(100, testEntity1, testEntity2);
	}

	private Event	testEvent1, testEvent2;
	private Entity	testEntity1, testEntity2;

	@Test
	public void extendedConstructorTest_PerfectParameters ()
	{
		Event e = new Event(50, testEntity1, testEntity2);
		assertTrue(Util.fuzzyEquals(e.getTimeStamp(), 50));
		assertEquals(e.getEntity1(), testEntity1);
		assertEquals(e.getEntity2(), testEntity2);
		assertTrue(e.isValid());
	}

	@Test
	public void extendedConstructorTest_IllegalTimeStamp ()
	{
		Event e = new Event(-50, testEntity1, testEntity2);
		assertTrue(Util.fuzzyEquals(e.getTimeStamp(), -50));
		assertEquals(e.getEntity1(), testEntity1);
		assertEquals(e.getEntity2(), testEntity2);
		assertFalse(e.isValid());
	}

	@Test
	public void invalidateTest ()
	{
		testEvent1.invalidate();
		assertFalse(testEvent1.isValid());
	}

	@Test
	public void involvesTest ()
	{
		assertTrue(testEvent1.involves(testEntity1));
		assertTrue(testEvent1.involves(testEntity2));
		assertFalse(testEvent1.involves(null));
	}

	@Test
	public void compareToTest_ ()
	{
		assertTrue(testEvent1.compareTo(testEvent2) < 0);
	}

	@Test
	public void toStringTest ()
	{
		testEvent1.toString();
	}
}
