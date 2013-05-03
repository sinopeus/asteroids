package model.programs.parsing.language.expression;

public class Cosine extends FirstOrderExpressionOfNumber
{
	public Cosine (Object argument)
	{
		super(argument);
	}

	@Override
	protected Object function (Double argument)
	{
		return Math.cos(argument);
	}
}
