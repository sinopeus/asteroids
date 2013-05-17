package model.programs.parsing.language.expression.constant;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public class False extends ConstantExpression <Boolean>
{
	public False (int line, int column) throws ProgramException
	{
		super(line, column, false);
	}

	@Override
	public BooleanLiteral evaluate ()
	{
		BooleanLiteral bl = null;
		try
		{
			bl = new BooleanLiteral(getLine(), getColumn(), false);
		} catch (ProgramException e)
		{
			e.printStackTrace(); //THIS should never ever happen, 
		}
		return bl;
	}

	@Override
	public String toString ()
	{
		return "False";
	}
}
