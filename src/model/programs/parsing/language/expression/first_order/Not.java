package model.programs.parsing.language.expression.first_order;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public class Not extends FirstOrderExpressionOfBooleanToBoolean
{
	public Not (int line, int column, Expression argument) throws ProgramException
	{
		super(line, column, argument);
	}

	@Override
	protected BooleanLiteral function (BooleanLiteral argument)
	{
		try
		{
			return new BooleanLiteral(getLine(), getColumn(), !argument.getValue());
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString ()
	{
		return "Not [argument=" + argument + "]";
	}
}
