package model.programs.parsing.language.expression.second_order;

import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
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
		return true;
	}

	protected abstract BooleanLiteral function (DoubleLiteral first, DoubleLiteral second);

	@Override
	public BooleanLiteral evaluate ()
	{
		return function((DoubleLiteral) getFirstArgument().evaluate(), (DoubleLiteral) getSecondArgument().evaluate());
	}
}