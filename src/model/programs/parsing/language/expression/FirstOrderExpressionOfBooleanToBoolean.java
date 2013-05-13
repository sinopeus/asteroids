package model.programs.parsing.language.expression;


public abstract class FirstOrderExpressionOfBooleanToBoolean extends FirstOrderExpression
{
	public FirstOrderExpressionOfBooleanToBoolean (int line, int column, Expression argument)
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
