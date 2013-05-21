package model.programs.parsing.language.expression.constant.literal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import model.programs.ProgramException;
import model.programs.parsing.language.Type;

import org.junit.Before;
import org.junit.Test;

import Utilities.Util;

@SuppressWarnings ("javadoc")
public class DoubleLiteralTest
{
	@Before
	public void setUp () throws IllegalArgumentException
	{
		testLiteral = new DoubleLiteral(0, 0, 0.0);
	}

	private static DoubleLiteral	testLiteral;

	@Test
	public void constructorTest_PerfectParameters () throws IllegalArgumentException
	{
		DoubleLiteral dl = new DoubleLiteral(1, 2, 3.0);
		assertTrue(Util.fuzzyEquals(dl.getLine(), 1));
		assertTrue(Util.fuzzyEquals(dl.getColumn(), 2));
		assertTrue(Util.fuzzyEquals(dl.getValue(), 3.0));
	}

	@Test(expected = ProgramException.class)
	public void constructorTest_IllegalLine () throws IllegalArgumentException
	{
		new DoubleLiteral(-1, 2, 3.0);
	}

	@Test(expected = ProgramException.class)
	public void constructorTest_IllegalColumn () throws IllegalArgumentException
	{
		new DoubleLiteral(1, -2, 3.0);
	}
	
	@Test (expected = ProgramException.class)
	public void constructorTest_IllegalDouble() throws IllegalArgumentException
	{
		new DoubleLiteral(1, 2, null);
	}
	
	@Test
	public void isTypeSafeTest ()
	{
		assertTrue(testLiteral.isTypeSafe());
	}

	@Test
	public void getType ()
	{
		assertEquals(testLiteral.getType(), Type.TYPE_DOUBLE);
	}

	@Test
	public void toStringTest ()
	{
		testLiteral.toString();
	}
}
