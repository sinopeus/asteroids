package model.programs.parsing.language.expression.constant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings ("javadoc")
public class FalseTest
{
	@Before
	public void setUp () throws ProgramException
	{
		testFalse = new False(1, 2);
	}

	private False	testFalse;

	@Test
	public void evaluateTest () throws ProgramException
	{
		assertEquals(new BooleanLiteral(1, 2, false), testFalse.evaluate());
	}

	public void isTypeSafeTest ()
	{
		assertTrue(testFalse.isTypeSafe());
	}

	public void getTypeTest ()
	{
		assertEquals(Type.TYPE_BOOLEAN, testFalse.getType());
	}
	
	@Test
	public void toStringTest ()
	{
		testFalse.toString();
	}
}
