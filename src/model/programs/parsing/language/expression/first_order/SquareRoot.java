package model.programs.parsing.language.expression.first_order;

import model.programs.ProgramException;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;

public class SquareRoot extends FirstOrderExpressionOfNumberToNumber
{
	public SquareRoot (int line,int column,Expression argument) throws ProgramException
	{
		super(line,column,argument);
	}

	@Override
	protected DoubleLiteral function (DoubleLiteral argument)
	{
		try
		{
			return new DoubleLiteral(getLine(),getColumn(),Math.sqrt(argument.getValue()));
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
