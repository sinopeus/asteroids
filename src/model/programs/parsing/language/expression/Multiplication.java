package model.programs.parsing.language.expression;

public class Multiplication extends SecondOrderExpressionOfNumbers
{
	public Multiplication (Object firstArgument, Object secondArgument)
	{
		super(firstArgument, secondArgument);
	}

	@Override
	protected Object function (Double first, Double second)
	{
		return (first * second);
	}
}
