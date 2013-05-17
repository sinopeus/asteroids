package model.programs.parsing.language.expression.constant.literal;

import static org.junit.Assert.assertTrue;

import model.programs.parsing.language.ProgramException;

import org.junit.Before;
import org.junit.Test;

import Utilities.Util;

@SuppressWarnings ("javadoc")
public class BooleanLiteralTest
{
	@Before
	public void setUp () throws ProgramException
	{
		testLiteral = new BooleanLiteral(0, 0, true);
	}

	private static BooleanLiteral	testLiteral;

	@Test
	public void constructorTest_PerfectParameters () throws ProgramException
	{
		BooleanLiteral bl = new BooleanLiteral(1, 2, true);
		assertTrue(Util.fuzzyEquals(bl.getLine(), 1));
		assertTrue(Util.fuzzyEquals(bl.getColumn(), 2));
		assertTrue(bl.getValue());
	}

	@Test(expected = ProgramException.class)
	public void constructorTest_IllegalLine () throws ProgramException
	{
		new BooleanLiteral(-1, 2, true);
	}

	@Test(expected = ProgramException.class)
	public void constructorTest_IllegalColumn () throws ProgramException
	{
		new BooleanLiteral(1, -2, true);
	}
	
	@Test (expected = ProgramException.class)
	public void constructorTest_IllegalBoolean() throws ProgramException
	{
		new BooleanLiteral(1, 2, null);
	}

	@Test
	public void toStringTest ()
	{
		testLiteral.toString();
	}
}
