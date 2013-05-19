package model.programs.parsing.language.expression.first_order;

import model.programs.parsing.language.ProgramException;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;

public abstract class FirstOrderExpressionOfNumberToNumber extends FirstOrderExpression
{
	protected FirstOrderExpressionOfNumberToNumber (int line, int column, Expression argument) throws ProgramException
	{
		super(line, column, argument);
	}

	protected abstract DoubleLiteral function (DoubleLiteral argument);

	@Override
	public DoubleLiteral evaluate ()
	{
		return function((DoubleLiteral) getArgument().evaluate());
	}
}
