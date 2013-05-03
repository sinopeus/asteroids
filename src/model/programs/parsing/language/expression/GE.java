package model.programs.parsing.language.expression;

public class GE extends SecondOrderExpressionOfNumbers
{
	public GE (Object firstArgument, Object secondArgument)
	{
		super(firstArgument, secondArgument);
	}

	@Override
	protected Object function (Double first, Double second)
	{
		return first >= second;
	}
}
