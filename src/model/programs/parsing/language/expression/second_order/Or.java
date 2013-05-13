package model.programs.parsing.language.expression.second_order;

import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public class Or extends SecondOrderExpressionOfBooleansToBoolean
{
	public Or (int line, int column, Expression firstArgument, Expression secondArgument)
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected BooleanLiteral function (BooleanLiteral first, BooleanLiteral second)
	{
		return new BooleanLiteral(getLine(), getColumn(), (first.getValue() || second.getValue()));
	}
}
