package model.programs.parsing.language.expression.constant;

import model.programs.parsing.language.ProgramException;

public class Null extends ConstantExpression <Object>
{
	public Null (int line, int column) throws ProgramException
	{
		super(line, column, null);
	}

	@Override
	protected boolean canHaveAsValue (Object value)
	{
		return true;
	}

	@Override
	public String toString ()
	{
		return "Null";
	}
}
