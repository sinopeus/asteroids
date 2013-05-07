package model.programs.parsing.language.expression;

import world.entity.ship.Ship;

public abstract class FirstOrderExpressionOfNumberToNumber extends FirstOrderExpression
{
	public FirstOrderExpressionOfNumberToNumber (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected boolean canHaveAsArgument (Expression argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		//		return argument.evaluate() instanceof DoubleLiteral; //TODO
		return true;
	}

	protected abstract DoubleLiteral function (DoubleLiteral argument);

	@Override
	public DoubleLiteral evaluate (Ship ship)
	{
		return function((DoubleLiteral) getArgument().evaluate(ship));
	}
}
