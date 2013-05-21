package model.programs.parsing.language.expression.first_order;

import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.DoubleLiteral;

public abstract class FirstOrderExpressionOfNumberToNumber extends FirstOrderExpression
{
	protected FirstOrderExpressionOfNumberToNumber (int line, int column, Expression argument) throws IllegalArgumentException
	{
		super(line, column, argument);
	}

	protected abstract DoubleLiteral function (DoubleLiteral argument);

	@Override
	public boolean isTypeSafe ()
	{
		return (getArgument().isTypeSafe() && (getArgument().getType() == Type.TYPE_DOUBLE));
	}
	
	@Override
	public Type getType ()
	{
		return Type.TYPE_DOUBLE;
	}
	
	@Override
	public DoubleLiteral evaluate ()
	{
		return function((DoubleLiteral) getArgument().evaluate());
	}
}
