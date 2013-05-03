package model.programs.parsing.language.expression;

public abstract class FirstOrderExpression extends Expression
{
	public FirstOrderExpression (Object argument)
	{
		setArgument(argument);
	}

	Object	argument;

	public Object getArgument ()
	{
		return argument;
	}

	protected boolean canHaveAsArgument (Object argument)
	{
		return argument != null;
	}

	protected void setArgument (Object argument)
	{
		if (!canHaveAsArgument(argument)) throw new IllegalArgumentException("Invalid argument for first order expression.");
		this.argument = argument;
	}
}
