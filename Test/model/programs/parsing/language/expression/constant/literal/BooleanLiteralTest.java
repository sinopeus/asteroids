package model.programs.parsing.language.expression.constant.literal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import model.programs.ProgramException;
import model.programs.parsing.language.Type;

import org.junit.Before;
import org.junit.Test;

import Utilities.Util;

@SuppressWarnings ("javadoc")
public class BooleanLiteralTest
{
	@Before
	public void setUp () throws IllegalArgumentException
	{
		testLiteral = new BooleanLiteral(0, 0, true);
	}

	private static BooleanLiteral	testLiteral;

	@Test
	public void constructorTest_PerfectParameters () throws IllegalArgumentException
	{
		BooleanLiteral bl = new BooleanLiteral(1, 2, true);
		assertTrue(Util.fuzzyEquals(bl.getLine(), 1));
		assertTrue(Util.fuzzyEquals(bl.getColumn(), 2));
		assertTrue(bl.getValue());
	}

	@Test (expected = IllegalArgumentException.class)
	public void constructorTest_IllegalLine () throws IllegalArgumentException
	{
		new BooleanLiteral(-1, 2, true);
	}

	@Test (expected = IllegalArgumentException.class)
	public void constructorTest_IllegalColumn () throws IllegalArgumentException
	{
		new BooleanLiteral(1, -2, true);
	}

	@Test (expected = IllegalArgumentException.class)
	public void constructorTest_IllegalBoolean () throws IllegalArgumentException
	{
		new BooleanLiteral(1, 2, null);
	}
	
	@Test
	public void getType ()
	{
		assertEquals(testLiteral.getType(), Type.TYPE_BOOLEAN);
	}

	@Test
	public void toStringTest ()
	{
		testLiteral.toString();
	}
}
