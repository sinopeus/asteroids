package model.programs.parsing.language.expression;

public abstract class SecondOrderExpression extends Expression
{
	public SecondOrderExpression (Object firstArgument, Object secondArgument)
	{
		setFirstArgument(firstArgument);
		setSecondArgument(secondArgument);
	}

	Object	firstArgument;

	public Object getFirstArgument ()
	{
		return firstArgument;
	}

	protected void setFirstArgument (Object firstArgument)
	{
		if (!canHaveAsArgument(firstArgument)) throw new IllegalArgumentException("Invalid first argument for second order expression.");
		this.firstArgument = firstArgument;
	}

	Object	secondArgument;

	public Object getSecondArgument ()
	{
		return secondArgument;
	}

	protected void setSecondArgument (Object secondArgument)
	{
		if (!canHaveAsArgument(firstArgument)) throw new IllegalArgumentException("Invalid second argument for second order expression.");
		this.secondArgument = secondArgument;
	}

	protected boolean canHaveAsArgument (Object argument)
	{
		return argument != null;
	}
}
