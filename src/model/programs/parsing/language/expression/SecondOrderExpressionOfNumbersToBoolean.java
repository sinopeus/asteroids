package model.programs.parsing.language.expression;

import world.entity.ship.Ship;

public abstract class SecondOrderExpressionOfNumbersToBoolean extends SecondOrderExpression
{

	public SecondOrderExpressionOfNumbersToBoolean (int line, int column, Expression firstArgument, Expression secondArgument)
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected boolean canHaveAsArgument (Expression argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		//		return argument.evaluate() instanceof DoubleLiteral; //TODO
		return true;
	}

	protected abstract BooleanLiteral function (DoubleLiteral first, DoubleLiteral second);

	@Override
	public BooleanLiteral evaluate ()
	{
		return function((DoubleLiteral) getFirstArgument().evaluate(), (DoubleLiteral) getSecondArgument().evaluate());
	}
}