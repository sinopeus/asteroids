package model.programs.parsing.language.expression.constant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings ("javadoc")
public class TrueTest
{
	@Before
	public void setUp () throws IllegalArgumentException
	{
		testTrue = new True(1, 2);
	}

	private True	testTrue;

	@Test
	public void evaluateTest () throws IllegalArgumentException
	{
		assertEquals(new BooleanLiteral(1, 2, true), testTrue.evaluate());
	}

	public void isTypeSafeTest ()
	{
		assertTrue(testTrue.isTypeSafe());
	}

	public void getTypeTest ()
	{
		assertEquals(Type.TYPE_BOOLEAN, testTrue.getType());
	}
	
	@Test
	public void toStringTest ()
	{
		testTrue.toString();
	}
}
