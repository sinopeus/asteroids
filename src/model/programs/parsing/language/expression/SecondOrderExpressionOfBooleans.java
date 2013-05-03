package model.programs.parsing.language.expression;

public abstract class SecondOrderExpressionOfBooleans extends SecondOrderExpression
{

	public SecondOrderExpressionOfBooleans (Object firstArgument, Object secondArgument)
	{
		super(firstArgument, secondArgument);
	}

	@Override
	protected boolean canHaveAsArgument (Object argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return argument instanceof Boolean;
	}

	protected abstract Object function (Boolean first, Boolean second);

	@Override
	public Object evaluate ()
	{
		return function((Boolean) getFirstArgument(), (Boolean) getSecondArgument());
	}
}
