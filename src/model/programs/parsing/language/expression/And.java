package model.programs.parsing.language.expression;

public class And extends SecondOrderExpression
{
	public And (Object firstArgument, Object secondArgument)
	{
		setFirstArgument(firstArgument);
		setSecondArgument(secondArgument);
	}

	@Override
	protected boolean canHaveAsArgument (Object argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return argument instanceof Boolean;
	}

	@Override
	public Object evaluate ()
	{
		Boolean first = (Boolean) getFirstArgument();
		Boolean second = (Boolean) getSecondArgument();
		return first && second;
	}
}
