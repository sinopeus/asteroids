package model.programs.parsing.language.expression.second_order;

import model.programs.ProgramException;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;

public class Addition extends SecondOrderExpressionOfNumbersToNumber
{
	public Addition (int line, int column, Expression firstArgument, Expression secondArgument) throws IllegalArgumentException
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected DoubleLiteral function (DoubleLiteral first, DoubleLiteral second)
	{

			return new DoubleLiteral(getLine(), getColumn(), first.getValue() + second.getValue());

	}

}
