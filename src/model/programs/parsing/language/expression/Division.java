package model.programs.parsing.language.expression;

public class Division extends SecondOrderExpressionOfNumbers
{
	public Division (Object firstArgument, Object secondArgument)
	{
		super(firstArgument, secondArgument);
	}

	@Override
	protected Object function (Double first, Double second)
	{
		return (first / second);
	}
}
