package model.programs.parsing.language.expression.first_order;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;

public class GetVX extends FirstOrderExpressionOfEntityToNumber
{
	public GetVX (int line, int column, Expression argument) throws ProgramException
	{
		super(line, column, argument);
	}

	@Override
	protected DoubleLiteral function (EntityLiteral argument)
	{
		try
		{
			return new DoubleLiteral(getLine(), getColumn(), argument.getValue().getVelocity()._X());
		} catch (ProgramException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
