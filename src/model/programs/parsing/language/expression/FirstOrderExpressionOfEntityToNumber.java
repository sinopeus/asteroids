package model.programs.parsing.language.expression;

import world.entity.ship.Ship;

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
		//		return argument.evaluate() instanceof EntityLiteral; //TODO
		return true;
	}

	protected abstract DoubleLiteral function (EntityLiteral argument);

	@Override
	public DoubleLiteral evaluate ()
	{
		return function((EntityLiteral) getArgument().evaluate());
	}
}
