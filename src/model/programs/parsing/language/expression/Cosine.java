package model.programs.parsing.language.expression;

public class Cosine extends FirstOrderExpression
{
	public Cosine (Object argument)
	{
		setArgument(argument);
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
		Double first = (Double) getArgument();
		return Math.cos(first);
	}
}
