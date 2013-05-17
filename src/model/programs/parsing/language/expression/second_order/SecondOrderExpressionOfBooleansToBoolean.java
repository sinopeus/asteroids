package model.programs.parsing.language.expression.second_order;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;
import world.entity.ship.Ship;

public abstract class SecondOrderExpressionOfBooleansToBoolean extends SecondOrderExpression
{

	protected SecondOrderExpressionOfBooleansToBoolean (int line, int column, Expression firstArgument, Expression secondArgument) throws ProgramException
	{
		super(line, column, firstArgument, secondArgument);
	}

	@Override
	protected boolean canHaveAsArgument (Expression argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return true; //TODO
	}

	protected abstract BooleanLiteral function (BooleanLiteral first, BooleanLiteral second);

	@Override
	public BooleanLiteral evaluate ()
	{
		return function((BooleanLiteral) getFirstArgument().evaluate(), (BooleanLiteral) getSecondArgument().evaluate());
	}
}
