package model.programs.parsing.language.expression;

public class Division extends SecondOrderExpression
{
	public Division (Object firstArgument, Object secondArgument)
	{
		setFirstArgument(firstArgument);
		setSecondArgument(secondArgument);
	}

	@Override
	protected boolean canHaveAsArgument (Object argument)
	{
		if (!super.canHaveAsArgument(argument)) return false;
		return argument instanceof Double;
	}

	@Override
	public Object evaluate ()
	{
		Double first = (Double) getFirstArgument();
		Double second = (Double) getSecondArgument();
		return first / second;
	}
}
