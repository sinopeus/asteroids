package model.programs.parsing.language.expression;

public abstract class FirstOrderExpression extends Expression
{
	public FirstOrderExpression (int line, int column, Expression argument)
	{
		super(line, column);
		setArgument(argument);
	}

	Expression	argument;

	public Expression getArgument ()
	{
		return argument;
	}

	protected boolean canHaveAsArgument (Expression argument)
	{
		return argument != null;
	}

	protected void setArgument (Expression argument)
	{
		if (!canHaveAsArgument(argument)) throw new IllegalArgumentException("Invalid argument for first order expression.");
		this.argument = argument;
	}
}
