package model.programs.parsing.language.expression;

import world.entity.Entity;

public abstract class FirstOrderExpressionOfEntityToEntity extends FirstOrderExpression
{
	public FirstOrderExpressionOfEntityToEntity (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected boolean canHaveAsArgument (Expression argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return argument.evaluate() instanceof Entity;
	}

	protected abstract Entity function (Entity argument);

	@Override
	public Entity evaluate ()
	{
		return function((Entity) getArgument().evaluate());
	}
}
