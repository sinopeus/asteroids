package model.programs.parsing.language.expression;

public class LE extends SecondOrderExpressionOfNumbers
{
	public LE (Object firstArgument, Object secondArgument)
	{
		super(firstArgument, secondArgument);
	}

	@Override
	protected Object function (Double first, Double second)
	{
		return first <= second;
	}
}
