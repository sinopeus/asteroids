package model.programs.parsing.language.expression;

public class SquareRoot extends FirstOrderExpressionOfNumber
{
	public SquareRoot (Object argument)
	{
		super(argument);
	}

	@Override
	protected Object function (Double argument)
	{
		return Math.sqrt(argument);
	}
}
