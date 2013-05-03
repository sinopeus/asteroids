package model.programs.parsing.language.expression;

import Utilities.Util;

public class NotEquals extends SecondOrderExpressionOfNumbersToBoolean
{
	public NotEquals (int line, int column, Expression firstArgument, Expression secondArgument)
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected BooleanLiteral function (DoubleLiteral first, DoubleLiteral second)
	{
		return new BooleanLiteral(getLine(), getColumn(), (!Util.fuzzyEquals(first.getValue(), second.getValue())));
	}
}
