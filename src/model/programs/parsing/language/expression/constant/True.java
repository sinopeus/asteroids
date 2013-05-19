package model.programs.parsing.language.expression.constant;

import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public class True extends ConstantExpression <Boolean>
{
	public True (int line, int column) throws ProgramException
	{
		super(line, column, true);
	}

	@Override
	public BooleanLiteral evaluate ()
	{
		BooleanLiteral bl = null;
		try
		{
			bl = new BooleanLiteral(getLine(), getColumn(), true);
		} catch (ProgramException e)
		{
			e.printStackTrace(); //THSS SHOULD NEVER EVER HAPPEN.
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
		return "True";
	}
}
