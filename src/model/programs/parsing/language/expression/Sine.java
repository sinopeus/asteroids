package model.programs.parsing.language.expression;

public class Sine extends FirstOrderExpressionOfNumber
{
	public Sine (Object argument)
	{
		super(argument);
	}

	@Override
	protected Object function (Double argument)
	{
		return Math.sin(argument);
	}
}
