package model.programs.parsing.language.expression.first_order;

import model.programs.ProgramException;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;

public class GetY extends FirstOrderExpressionOfEntityToNumber
{
	public GetY (int line, int column, Expression argument) throws IllegalArgumentException
	{
		super(line, column, argument);
	}

	@Override
	protected DoubleLiteral function (EntityLiteral argument)
	{

			return new DoubleLiteral(getLine(), getColumn(), argument.getValue().getPosition()._Y());

	}

}
