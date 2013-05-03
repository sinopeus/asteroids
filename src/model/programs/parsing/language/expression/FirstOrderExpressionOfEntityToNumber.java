package model.programs.parsing.language.expression;

import world.entity.Entity;

public abstract class FirstOrderExpressionOfEntityToNumber extends FirstOrderExpression
{
	public FirstOrderExpressionOfEntityToNumber (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected boolean canHaveAsArgument (Expression argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return argument.evaluate() instanceof Entity;
	}

	protected abstract DoubleLiteral function (Entity argument);

	@Override
	public DoubleLiteral evaluate ()
	{
		return function((Entity) getArgument().evaluate());
	}
}
