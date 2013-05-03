package model.programs.parsing.language.expression;

public class LT extends SecondOrderExpressionOfNumbers
{
	public LT (Object firstArgument, Object secondArgument)
	{
		super(firstArgument, secondArgument);
	}

	@Override
	protected Object function (Double first, Double second)
	{
		return first < second;
	}
}