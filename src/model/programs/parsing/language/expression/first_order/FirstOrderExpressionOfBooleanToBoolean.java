package model.programs.parsing.language.expression.first_order;

import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public abstract class FirstOrderExpressionOfBooleanToBoolean extends FirstOrderExpression
{
	protected FirstOrderExpressionOfBooleanToBoolean (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected boolean canHaveAsArgument (Expression argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return true;
	}

	protected abstract BooleanLiteral function (BooleanLiteral argument);

	@Override
	public BooleanLiteral evaluate ()
	{
		return function((BooleanLiteral) getArgument().evaluate());
	}
}
