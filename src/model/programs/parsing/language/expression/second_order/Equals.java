package model.programs.parsing.language.expression.second_order;

import model.programs.ProgramException;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import Utilities.Util;

public class Equals extends SecondOrderExpressionOfNumbersToBoolean
{
	public Equals (int line, int column, Expression firstArgument, Expression secondArgument) throws ProgramException
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected BooleanLiteral function (DoubleLiteral first, DoubleLiteral second)
	{
		try
		{
			return new BooleanLiteral(getLine(), getColumn(), (Util.fuzzyEquals(first.getValue(), second.getValue())));
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	

}
