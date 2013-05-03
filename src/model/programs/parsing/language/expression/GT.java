package model.programs.parsing.language.expression;

public class GT extends SecondOrderExpressionOfNumbers
{
	public GT (Object firstArgument, Object secondArgument)
	{
		super(firstArgument, secondArgument);
	}

	@Override
	protected Object function (Double first, Double second)
	{
		return (first > second);
	}
}
