package model.programs.parsing.language.expression.first_order;

import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;
import world.entity.ship.Ship;

public abstract class FirstOrderExpressionOfNumberToNumber extends FirstOrderExpression
{
	protected FirstOrderExpressionOfNumberToNumber (int line, int column, Expression argument)
	{
		super(line, column, argument);
	}

	@Override
	protected boolean canHaveAsArgument (Expression argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return true;
	}

	protected abstract DoubleLiteral function (DoubleLiteral argument);

	@Override
	public DoubleLiteral evaluate ()
	{
		return function((DoubleLiteral) getArgument().evaluate());
	}
}
