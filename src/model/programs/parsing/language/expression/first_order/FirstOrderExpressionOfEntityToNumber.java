package model.programs.parsing.language.expression.first_order;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;

public abstract class FirstOrderExpressionOfEntityToNumber extends FirstOrderExpression
{
	protected FirstOrderExpressionOfEntityToNumber (int line, int column, Expression argument) throws ProgramException
	{
		super(line, column, argument);
	}

	protected abstract DoubleLiteral function (EntityLiteral argument);

	@Override
	public DoubleLiteral evaluate ()
	{
		return function((EntityLiteral) getArgument().evaluate());
	}
}
