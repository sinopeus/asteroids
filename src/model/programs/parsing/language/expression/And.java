package model.programs.parsing.language.expression;

public class And extends SecondOrderExpressionOfBooleans
{
	public And (Object firstArgument, Object secondArgument)
	{
		super(firstArgument, secondArgument);
	}

	@Override
	protected Object function (Boolean first, Boolean second)
	{
		return (first && second);
	}
}
