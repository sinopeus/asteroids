package model.programs.parsing.language.expression;

public class Or extends SecondOrderExpressionOfBooleans
{
	public Or (Object firstArgument, Object secondArgument)
	{
		super(firstArgument, secondArgument);
	}

	@Override
	protected Object function (Boolean first, Boolean second)
	{
		return (first || second);
	}
}
