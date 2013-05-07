package model.programs.parsing.language.expression;

import world.entity.ship.Ship;

public abstract class SecondOrderExpressionOfBooleansToBoolean extends SecondOrderExpression
{

	public SecondOrderExpressionOfBooleansToBoolean (int line, int column, Expression firstArgument, Expression secondArgument)
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected boolean canHaveAsArgument (Expression argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		//		return argument.evaluate() instanceof BooleanLiteral;
		return true; //TODO
	}

	protected abstract BooleanLiteral function (BooleanLiteral first, BooleanLiteral second);

	@Override
	public BooleanLiteral evaluate (Ship ship)
	{
		return function((BooleanLiteral) getFirstArgument().evaluate(ship), (BooleanLiteral) getSecondArgument().evaluate(ship));
	}
}
