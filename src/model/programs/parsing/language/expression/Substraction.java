package model.programs.parsing.language.expression;

public class Substraction extends SecondOrderExpressionOfNumbers
{
	public Substraction (Object firstArgument, Object secondArgument)
	{
		super(firstArgument, secondArgument);
	}

	@Override
	protected Object function (Double first, Double second)
	{
		return (first - second);
	}
}
