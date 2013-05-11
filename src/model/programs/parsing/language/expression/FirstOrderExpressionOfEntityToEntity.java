package model.programs.parsing.language.expression;

import world.entity.ship.Ship;

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
		//		return argument.evaluate() instanceof EntityLiteral; //TODO
		return true;
	}

	protected abstract EntityLiteral function (EntityLiteral argument);

	@Override
	public EntityLiteral evaluate ()
	{
		return function((EntityLiteral) getArgument().evaluate());
	}
}
