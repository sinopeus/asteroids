package model.programs.parsing.language.expression.second_order;

import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
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