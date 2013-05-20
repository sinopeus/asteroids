package model.programs.parsing.language;

import static org.junit.Assert.assertEquals;
import model.programs.ProgramException;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;

import org.junit.Test;

@SuppressWarnings ("javadoc")
public class TypeTest
{
	@Test
	public void typeTest ()
	{
		Type b = Type.TYPE_BOOLEAN;
		Type d = Type.TYPE_DOUBLE;
		Type e = Type.TYPE_ENTITY;
	}

	@Test
	public void booleanDefaultValueTest () throws ProgramException
	{
		assertEquals(new BooleanLiteral(0, 0, false), Type.TYPE_BOOLEAN.defaultValue(0, 0));
	}

	@Test
	public void doubleDefaultValueTest () throws ProgramException
	{
		assertEquals(new DoubleLiteral(0, 0, 0.0), Type.TYPE_DOUBLE.defaultValue(0, 0));
	}

	@Test
	public void entityDefaultValueTest () throws ProgramException
	{
		assertEquals(new EntityLiteral(0, 0, null), Type.TYPE_ENTITY.defaultValue(0, 0));
	}
}
