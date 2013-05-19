package model.programs.parsing.language.expression.constant;

import model.programs.ProgramException;
import model.programs.parsing.language.Type;
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
	public boolean isTypeSafe ()
	{
		return true;
	}
	
	@Override
	public Type getType ()
	{
		return Type.TYPE_BOOLEAN;
	}

	@Override
	public String toString ()
	{
		return "False";
	}
}
