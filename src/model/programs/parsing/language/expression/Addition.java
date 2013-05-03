package model.programs.parsing.language.expression;

public class Addition extends SecondOrderExpressionOfNumbers
{
	public Addition (Object firstArgument, Object secondArgument)
	{
		super(firstArgument, secondArgument);
	}

	@Override
	protected Object function (Double first, Double second)
	{
		return (first + second);
	}
}
