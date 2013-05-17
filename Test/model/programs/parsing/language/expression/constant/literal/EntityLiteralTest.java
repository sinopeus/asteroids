package model.programs.parsing.language.expression.constant.literal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import model.programs.parsing.language.ProgramException;

import org.junit.Before;
import org.junit.Test;

import world.entity.Asteroid;
import world.physics.geometry.CircleShape;
import world.physics.vector.Direction;
import world.physics.vector.Position;
import world.physics.vector.Velocity;
import Utilities.Util;

@SuppressWarnings ("javadoc")
public class EntityLiteralTest
{
	@Before
	public void setUpImmutableTestFixture_DoubleLiteral () throws ProgramException
	{
		testEntity = new Asteroid(new Direction(), new Position(), new Velocity(), new CircleShape(50));
		testLiteral = new EntityLiteral(0, 0, testEntity);
	}

	private static EntityLiteral	testLiteral;
	private static Asteroid			testEntity;

	@Test
	public void constructorTest_PerfectParameters () throws ProgramException
	{
		EntityLiteral el = new EntityLiteral(1, 2, testEntity);
		assertTrue(Util.fuzzyEquals(el.getLine(), 1));
		assertTrue(Util.fuzzyEquals(el.getColumn(), 2));
		assertEquals(el.getValue(), testEntity);
	}

	@Test (expected = ProgramException.class)
	public void constructorTest_IllegalLine () throws ProgramException
	{
		new EntityLiteral(-1, 2, testEntity);
	}

	@Test (expected = ProgramException.class)
	public void constructorTest_IllegalColumn () throws ProgramException
	{
		new EntityLiteral(1, -2, testEntity);
	}
	
	@Test (expected = ProgramException.class)
	public void constructorTest_IllegalEntity () throws ProgramException
	{
		new EntityLiteral(1, 2, null);
	}

	@Test
	public void toStringTest ()
	{
		testLiteral.toString();
	}
}
