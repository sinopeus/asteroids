package model.programs.parsing.language.expression;

public abstract class SecondOrderExpressionOfNumbers extends SecondOrderExpression
{

	public SecondOrderExpressionOfNumbers (Object firstArgument, Object secondArgument)
	{
		super(firstArgument, secondArgument);
	}

	@Override
	protected boolean canHaveAsArgument (Object argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return argument instanceof Double;
	}

	protected abstract Object function (Double first, Double second);

	@Override
	public Object evaluate ()
	{
		return function((Double) getFirstArgument(), (Double) getSecondArgument());
	}
}
