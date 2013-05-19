package model.programs.parsing.language.expression.second_order;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;

public class LE extends SecondOrderExpressionOfNumbersToBoolean
{
	public LE (int line, int column, Expression firstArgument, Expression secondArgument) throws ProgramException
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected BooleanLiteral function (DoubleLiteral first, DoubleLiteral second)
	{
		try
		{
			return new BooleanLiteral(getLine(), getColumn(), (first.getValue() <= second.getValue()));
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	

}
