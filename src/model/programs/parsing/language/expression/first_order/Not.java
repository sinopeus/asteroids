package model.programs.parsing.language.expression.first_order;

import model.programs.ProgramException;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public class Not extends FirstOrderExpressionOfBooleanToBoolean
{
	public Not (int line, int column, Expression argument) throws IllegalArgumentException
	{
		super(line, column, argument);
	}

	@Override
	protected BooleanLiteral function (BooleanLiteral argument)
	{

			return new BooleanLiteral(getLine(), getColumn(), !argument.getValue());

	}

}
