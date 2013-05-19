package model.programs.parsing.language.expression.second_order;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;

public class Addition extends SecondOrderExpressionOfNumbersToNumber
{
	public Addition (int line, int column, Expression firstArgument, Expression secondArgument) throws ProgramException
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected DoubleLiteral function (DoubleLiteral first, DoubleLiteral second)
	{
		try
		{
			return new DoubleLiteral(getLine(), getColumn(), first.getValue() + second.getValue());
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
