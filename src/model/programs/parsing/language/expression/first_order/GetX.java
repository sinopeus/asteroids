package model.programs.parsing.language.expression.first_order;

import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;


public class GetX extends FirstOrderExpressionOfEntityToNumber
{
	public GetX (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected DoubleLiteral function (EntityLiteral argument)
	{
		return new DoubleLiteral(getLine(), getColumn(), argument.getValue().getPosition()._X());
	}

	@Override
	public String toString ()
	{
		return "GetX [argument=" + argument + "]";
	}

}
