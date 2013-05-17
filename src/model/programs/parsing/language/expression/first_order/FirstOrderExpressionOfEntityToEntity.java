package model.programs.parsing.language.expression.first_order;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.EntityLiteral;

public abstract class FirstOrderExpressionOfEntityToEntity extends FirstOrderExpression
{
	protected FirstOrderExpressionOfEntityToEntity (int line, int column, Expression argument) throws ProgramException
	{
		super(line, column, argument);
	}

	protected abstract EntityLiteral function (EntityLiteral argument);

	@Override
	public EntityLiteral evaluate ()
	{
		return function((EntityLiteral) getArgument().evaluate());
	}
}
