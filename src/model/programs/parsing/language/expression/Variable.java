package model.programs.parsing.language.expression;

public class Variable extends FirstOrderExpressionOfNumber
{
	public Variable (Object argument)
	{
		super(argument);
	}

	@Override
	protected Object function (Double argument)
	{
		return argument;
	}
}
