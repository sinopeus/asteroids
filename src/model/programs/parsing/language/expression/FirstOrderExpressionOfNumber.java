package model.programs.parsing.language.expression;

public abstract class FirstOrderExpressionOfNumber extends FirstOrderExpression
{
	public FirstOrderExpressionOfNumber (Object argument)
	{
		super(argument);
	}

	@Override
	protected boolean canHaveAsArgument (Object argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return argument instanceof Double;
	}

	protected abstract Object function (Double argument);

	@Override
	public Object evaluate ()
	{
		return function((Double) getArgument());
	}
}
