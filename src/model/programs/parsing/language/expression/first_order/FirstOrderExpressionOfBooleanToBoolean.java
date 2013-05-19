package model.programs.parsing.language.expression.first_order;

import model.programs.ProgramException;
import model.programs.parsing.language.Type;
import model.programs.parsing.language.expression.Expression;
import model.programs.parsing.language.expression.constant.literal.BooleanLiteral;

public abstract class FirstOrderExpressionOfBooleanToBoolean extends FirstOrderExpression
{
	protected FirstOrderExpressionOfBooleanToBoolean (int line, int column, Expression argument) throws ProgramException
	{
		super(line, column, argument);
	}

	protected abstract BooleanLiteral function (BooleanLiteral argument);

	@Override
	public boolean isTypeSafe ()
	{
		return (getArgument().isTypeSafe() && (getArgument().getType() == Type.TYPE_BOOLEAN));
	}
	
	@Override
	public Type getType ()
	{
		return Type.TYPE_BOOLEAN;
	}
	
	@Override
	public BooleanLiteral evaluate ()
	{
		return function((BooleanLiteral) getArgument().evaluate());
	}
}
